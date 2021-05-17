package hr.alphacloud.server.service.user;

import hr.alphacloud.server.model.command.account.ChangePasswordCommand;
import hr.alphacloud.server.model.command.account.RegisterUserCommand;
import hr.alphacloud.server.model.command.account.UserFilterCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.account.AccountDTO;
import hr.alphacloud.server.model.dto.auth.LoginDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;
import hr.alphacloud.server.model.enums.UserRole;
import hr.alphacloud.server.repository.CompanyRepository;
import hr.alphacloud.server.repository.UserRepository;
import hr.alphacloud.server.service.mail.SendMailService;
import hr.alphacloud.server.specification.AccountSpecification;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@Data
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final SendMailService sendMailService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, CompanyRepository companyRepository, SendMailService sendMailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.sendMailService = sendMailService;
    }

    @Transactional
    public ApiBaseDTO save(ApiBaseCommand<RegisterUserCommand> command) {
        final Optional<Company> companyOptional = companyRepository.findById(command.getCommand().getCompanyId());
        return companyOptional.map(company -> {
            if (command.getCommand().getId() != null) {
                Optional<User> userOptional = this.userRepository.findById(command.getCommand().getId());
                return userOptional.map(user -> ApiBaseDTO.generateSuccessResponse(AccountDTO.formDTO(
                        this.userRepository.save(command.getCommand().updateUser(company, user)))))
                        .orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
            }
            User user = createUser(command.getCommand(), company);
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            try {
                // sendMailService.sendActivationEmail(command.getCommand(), password, company);
            } catch (Exception e) {
                return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
            }
            return ApiBaseDTO.generateSuccessResponse(AccountDTO.formDTO(user));
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    public static User createUser(RegisterUserCommand command, Company company) {
        User user = new User();
        user.setEmail(command.getEmail());
        user.setEnabled(command.getEnabled());
        user.setPhone(command.getPhone());
        user.setPosition(command.getPosition());
        user.setCompany(company);
        user.setFirstName(command.getFirstName());
        user.setLastName(command.getLastName());
        user.setRole(UserRole.USER);
        user.setPassword(UUID.randomUUID().toString());
        return user;
    }

    @Override
    public ApiBaseDTO delete(ApiBaseCommand<UserFilterCommand> command) {
        this.userRepository.deleteById(command.getCommand().getId());
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBasePageDTO findAll(ApiBasePageCommand<UserFilterCommand> command) {
        final Page<User> users = this.userRepository.findAll(AccountSpecification.getUserSpecification(command.getCommand()), command.getPaginationAndSorting().generatePagingAndSortingRequest());
        return ApiBasePageDTO.generateSuccessResponse(AccountDTO.formAccountsDTO(users.getContent()), PageDTO.ofPage(users));
    }

    @Override
    public ApiBaseDTO findCompany(ApiBaseCommand<UserFilterCommand> command) {
        final Optional<User> user = this.userRepository.findById(command.getCommand().getId());
        return user.map(n -> ApiBaseDTO.generateSuccessResponse(AccountDTO.formDTO(n))).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    public ApiBaseDTO changePassword(ApiBaseCommand<ChangePasswordCommand> command) {
        final Optional<User> userOptional = this.userRepository.findByEmail(command.getCommand().getEmail());
        return userOptional.map(user -> {
            if (passwordEncoder.matches(command.getCommand().getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(command.getCommand().getNewPassword()));
                return ApiBaseDTO.generateSuccessResponse(LoginDTO.formDTO(this.userRepository.save(user)));
            }
            log.error("Error while changing password");
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    public ApiBaseDTO resetPassword(ApiBaseCommand<ChangePasswordCommand> command) {
        final Optional<User> userOptional = this.userRepository.findByEmail(command.getCommand().getEmail());
        if (userOptional.isEmpty()) {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.CUSTOMER_NOT_EXIST);
        }
        String token = UUID.randomUUID().toString();
        userOptional.map(user -> {
            user.setJwtToken(token);
            user.setJwtTokenResetTimer(LocalDateTime.now());
            this.userRepository.save(user);
            try {
                sendMailService.sendPasswordResetEmail(token, user);
            } catch (MessagingException e) {
                return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
            }
            return null;
        });
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    //TODO not show page but input new password
    @Override
    public ApiBaseDTO saveNewPassword(ApiBaseCommand<ChangePasswordCommand> command) {
        final Optional<User> userOptional = this.userRepository.findByEmail(command.getCommand().getEmail());
        return userOptional.map(user -> {
            if (command.getCommand().getToken().equals(user.getJwtToken())
                    && user.getJwtTokenResetTimer().isAfter(LocalDateTime.now().minusMinutes(30))) {
                user.setPassword(passwordEncoder.encode(command.getCommand().getNewPassword()));
                user.setJwtToken(UUID.randomUUID().toString());
                return ApiBaseDTO.generateSuccessResponse(LoginDTO.formDTO(this.userRepository.save(user)));
            }
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

}
