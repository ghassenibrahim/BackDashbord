package hr.alphacloud.server.service.auth;

import hr.alphacloud.server.model.command.auth.LoginCommand;
import hr.alphacloud.server.model.command.auth.RefreshTokenCommand;
import hr.alphacloud.server.model.dto.account.AccountDTO;
import hr.alphacloud.server.model.dto.account.CompanyDTO;
import hr.alphacloud.server.model.dto.auth.LoginDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;
import hr.alphacloud.server.model.enums.UserRole;
import hr.alphacloud.server.repository.CompanyRepository;
import hr.alphacloud.server.repository.UserRepository;
import hr.alphacloud.server.security.JwtTokenUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class AuthServiceImpI implements AuthService {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final CompanyRepository companyRepository;

    public AuthServiceImpI(JwtTokenUtil jwtTokenUtil, UserRepository userRepository, AuthenticationManager authenticationManager, CompanyRepository companyRepository) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.companyRepository = companyRepository;
    }

    @Transactional
    @Override
    public ApiBaseDTO<LoginDTO> loginUser(LoginCommand loginCommand) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginCommand.getEmail(), loginCommand.getPassword()));

            final Optional<User> userOptional = userRepository.findByEmail(loginCommand.getEmail());
            return userOptional.map(user -> {
                final String jwtToken = jwtTokenUtil.generateToken(user);
                final String refreshToken = generateRefreshTokenForUser();
                user.setRefreshToken(refreshToken);
                user.setJwtToken(jwtToken);
                this.userRepository.save(user);

                final LoginDTO loginDTO = LoginDTO.formDTO(user);
                // if user is admin it must contain list of companies in order to change selected company
                if (user.getRole().equals(UserRole.ADMIN)) {
                    List<CompanyDTO> companyDTOList = CompanyDTO.forCompanyDTOAccounts(companyRepository.findAll());
                    loginDTO.getAccount().setCompanyList(companyDTOList);
                } else {
                    // if user is not admin then selected company id and company name must be assigned to the same company
                    loginDTO.getAccount().setSelectedCompanyId(user.getCompany().getId());
                    loginDTO.getAccount().setSelectedCompanyName(user.getCompany().getFullName());
                }
                loginDTO.getAccount().setSelectedCompanyId(user.getCompany().getId());
                return ApiBaseDTO.generateSuccessResponse(loginDTO);
            }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
        } catch (Exception e) {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        }
    }

    @Transactional
    @Override
    public ApiBaseDTO<LoginDTO> chooseCompany(LoginCommand command, HttpServletRequest request) {
        final String jwtToken = jwtTokenUtil.getJwtFromRequest(request);
        final User user = findUserByToken(jwtToken);

        if (user != null) {
            final Optional<Company> companyOptional = this.companyRepository.findById(command.getCompany().getId());
            return companyOptional.map(company -> {
                user.setSelectedCompanyId(company.getId());
                user.setSelectedCompanyName(company.getFullName());
                this.userRepository.save(user);

                final LoginDTO loginDTO = LoginDTO.formDTO(user);
                loginDTO.getAccount().setSelectedCompanyId(company.getId());
                loginDTO.getAccount().setSelectedCompanyName(company.getFullName());
                loginDTO.getAccount().setCompanyList(CompanyDTO.forCompanyDTOAccounts(companyRepository.findAll()));
                return ApiBaseDTO.generateSuccessResponse(loginDTO);
            }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
        }
        return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
    }

    @Override
    public ApiBaseDTO<Boolean> logoutUser() {
        final Optional<User> loggedUserOptional = this.userRepository.findByEmail(getUserFromPrincipal());
        return loggedUserOptional.map(loggedUser -> {
            if (loggedUser.getEmail() != null && !loggedUser.getEmail().equals("")) {
                SecurityContextHolder.getContext().setAuthentication(null);
                return ApiBaseDTO.generateSuccessResponse(true);
            }
            return ApiBaseDTO.generateSuccessResponse(false);
        }).orElseGet(() -> ApiBaseDTO.generateSuccessResponse(false));
    }

    @Override
    public ApiBaseDTO<LoginDTO> refreshToken(RefreshTokenCommand refreshTokenCommand) {
        final Optional<User> userOptional = userRepository.findByRefreshTokenAndJwtToken(refreshTokenCommand.getRefreshToken(), refreshTokenCommand.getJwtToken());
        return userOptional.map(u -> {
            AccountDTO user = AccountDTO.formDTO(u);
            final String jwtToken = jwtTokenUtil.generateToken(u);
            final String refreshToken = generateRefreshTokenForUser();
            u.setJwtToken(jwtToken);
            u.setRefreshToken(refreshToken);
            userRepository.save(u);
            return ApiBaseDTO.generateSuccessResponse(new LoginDTO(user, jwtToken, refreshToken));
        }).orElseGet(() -> {
            this.userRepository.deleteByRefreshTokenAndJwtToken(refreshTokenCommand.getRefreshToken(), refreshTokenCommand.getJwtToken());
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        });
    }

    @Override
    public ApiBaseDTO<LoginDTO> getLoggedUserData() {
        final Optional<User> loggedUser = userRepository.findByEmail(getUserFromPrincipal());
        if (loggedUser.isPresent()) {
            AccountDTO accountDTO = AccountDTO.formDTO(loggedUser.get());
            if (loggedUser.get().getRole().equals(UserRole.ADMIN)) {
                List<CompanyDTO> companyDTOList = CompanyDTO.forCompanyDTOAccounts(companyRepository.findAll());
                accountDTO.setCompanyList(companyDTOList);
            }
            accountDTO.setSelectedCompanyId(loggedUser.get().getSelectedCompanyId());
            accountDTO.setSelectedCompanyName(loggedUser.get().getSelectedCompanyName());
            return ApiBaseDTO.generateSuccessResponse(new LoginDTO(accountDTO, loggedUser.get().getJwtToken(), loggedUser.get().getRefreshToken()));
        }
        return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
    }

    private String generateRefreshTokenForUser() {
        return UUID.randomUUID().toString();
    }

    private String getUserFromPrincipal() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                log.warn("Current user: " + authentication.getName());
                return authentication.getName();
            } else {
                log.warn("Anonymous");
            }
        } catch (NullPointerException e) {
            log.error("Null pointer error");
        }
        return null;
    }

    private User findUserByToken(String jwt) {
        String email = this.getUserFromPrincipal();
        // return this.userRepository.findByEmailAndJwtToken(email, jwt).orElse(null);
        return this.userRepository.findByEmail(email).orElse(null);
    }
}
