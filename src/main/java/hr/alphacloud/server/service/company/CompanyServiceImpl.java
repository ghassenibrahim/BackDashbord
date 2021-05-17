package hr.alphacloud.server.service.company;


import hr.alphacloud.server.model.command.account.CompanyFilter;
import hr.alphacloud.server.model.command.account.CompanySaveCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.account.CompanyDTO;
import hr.alphacloud.server.model.dto.base.*;
import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;
import hr.alphacloud.server.model.entity.reporting.settings.position_template.PositionTemplate;
import hr.alphacloud.server.model.entity.reporting.settings.position_template.Positions;
import hr.alphacloud.server.model.enums.UserRole;
import hr.alphacloud.server.repository.CompanyRepository;
import hr.alphacloud.server.repository.UserRepository;
import hr.alphacloud.server.repository.settings.PositionsRepository;
import hr.alphacloud.server.repository.settings.PositionsTemplateRepository;
import hr.alphacloud.server.service.mail.SendMailService;
import hr.alphacloud.server.service.user.UserServiceImpl;
import hr.alphacloud.server.specification.CompanySpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final SendMailService sendMailService;
    private final PositionsTemplateRepository positionsTemplateRepository;
    private final PositionsRepository positionsRepository;

    public ApiBaseDTO<CompanyDTO> findCompany(ApiBaseCommand<CompanyFilter> command) {
        Optional<Company> company = companyRepository.findById(command.getCommand().getId());
        return company.map(n -> ApiBaseDTO.generateSuccessResponse(CompanyDTO.fromEntity(n))).orElse(ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    public ApiBasePageDTO<CompanyDTO> findAll(ApiBasePageCommand<CompanyFilter> command) {
        Page<Company> company = companyRepository.findAll(CompanySpecification.getCompanyClientFilterSpecification(command.getCommand()), command.getPaginationAndSorting().generatePagingAndSortingRequest());
        return ApiBasePageDTO.generateSuccessResponse(CompanyDTO.forCompanyDTOAccounts(company.getContent()), PageDTO.ofPage(company));
    }

    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<CompanyFilter> command) {
        this.companyRepository.deleteById(command.getCommand().getId());
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Transactional
    public ApiBaseDTO<CompanyDTO> save(ApiBaseCommand<CompanySaveCommand> command) {
        CompanySaveCommand saveCommand = command.getCommand();
        Company company = saveCommand.toEntity();

        this.companyRepository.save(company);
        savePositionTemplates(company.getId());
        if (command.getCommand().getAccount().getEmail() != null) {
            User user = UserServiceImpl.createUser(saveCommand.getAccount(), company);
            if (command.getCommand().getId() == null) {
                user.setRole(UserRole.COMPANY_ADMIN);
            }
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            try {
                // sendMailService.sendActivationEmail(saveCommand, password);

                this.userRepository.save(user);
            } catch (Exception ex) {
                return new ApiBaseDTO<>(false, new ApiErrorDTO("", ex.getMessage()), null);
            }
        }
        return ApiBaseDTO.generateSuccessResponse(CompanyDTO.fromEntity(company));
    }

    public void savePositionTemplates(Long companyId) {
        List<PositionTemplate> templateList = this.positionsTemplateRepository.findAll();
        List<Positions> positions = this.positionsRepository.findAllByCompanyId(companyId);
        List<String> namesList = new ArrayList<>();
        positions.forEach(position -> namesList.add(position.getSheetType()));

        templateList.forEach(template -> {
            if (!namesList.contains(template.getSheetType())) {
                Positions tempPosition = new Positions();
                tempPosition.setSheetType(template.getSheetType());
                tempPosition.setCompanyId(companyId);
                tempPosition.setPositionProperties(template.getPositionProperties());
                positions.add(tempPosition);
            }
        });
        this.positionsRepository.saveAll(positions);
    }

}
