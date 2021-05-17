package hr.alphacloud.server.service.settings.import_settings;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.import_settings.ImportSettingsFilterCommand;
import hr.alphacloud.server.model.command.settings.import_settings.ImportSettingsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.ImportSettingsDTO;
import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.ImportSettingsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ImportSettingsServiceImpl implements ImportSettingsService {

    private final ImportSettingsRepository importSettingsRepository;

    public ImportSettingsServiceImpl(ImportSettingsRepository importSettingsRepository) {
        this.importSettingsRepository = importSettingsRepository;
    }

    public List<ImportSettingsDTO> filter(Long companyId, Pageable pageable) {
        Specification<ImportSettings> specification = SharedSpecification
                .getNumericValueEqual(ImportSettings.class, companyId, ImportSettings.COMPANY_ID);
//                .and(SharedSpecification.getStringValueEquals(ImportSettings.class, filterCommand.getCommand().getName(), ImportSettings.NAME_FIELD));

        final Page<ImportSettings> importSettingsPage = this.importSettingsRepository
                .findAll(specification, pageable);

        return ImportSettingsDTO.of(importSettingsPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public ApiBasePageDTO<ImportSettingsDTO> filter(ApiBasePageCommand<ImportSettingsFilterCommand> filterCommand) {
        Specification<ImportSettings> specification = SharedSpecification
                .getNumericValueEqual(ImportSettings.class, filterCommand.getCommand().getCompanyId(), ImportSettings.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(ImportSettings.class, filterCommand.getCommand().getName(), ImportSettings.NAME_FIELD));

        final Page<ImportSettings> importSettingsPage = this.importSettingsRepository
                .findAll(specification, filterCommand.getPaginationAndSorting().generatePagingAndSortingRequest());

        return ApiBasePageDTO.generateSuccessResponse(ImportSettingsDTO.of(importSettingsPage.getContent()),
                PageDTO.ofPage(importSettingsPage));
    }

    @Override
    public ApiBaseDTO<ImportSettingsDTO> save(ApiBaseCommand<ImportSettingsSaveCommand> saveCommand) {
        ImportSettings importSettings = saveCommand.getCommand().convertToEntity();
        this.importSettingsRepository.save(importSettings);

        return ApiBaseDTO.generateSuccessResponse(ImportSettingsDTO.of(importSettings));
    }

    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> deleteCommand) {
        Optional<ImportSettings> importSettingsOptional = this.importSettingsRepository
                .findByIdAndCompanyId(deleteCommand.getCommand().getId(), deleteCommand.getCommand().getCompanyId());

        return importSettingsOptional.map(importSettings -> {
            this.importSettingsRepository.delete(importSettings);
            return ApiBaseDTO.generateSuccessResponse(true);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }

    @Override
    public Set<String> getReportFields() {
        return null;
    }
}
