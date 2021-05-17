package hr.alphacloud.server.service.settings.business_type;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.base.PaginationAndSortingCommand;
import hr.alphacloud.server.model.command.settings.business_type.BusinessTypeFilterCommand;
import hr.alphacloud.server.model.command.settings.business_type.BusinessTypeSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.BusinessTypeDTO;
import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.BusinessTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessTypeSettingsServiceImpl implements BusinessTypeSettingsService {

    private final BusinessTypeRepository businessTypeRepository;

    public BusinessTypeSettingsServiceImpl(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public ApiBasePageDTO<BusinessTypeDTO> filter(ApiBasePageCommand<BusinessTypeFilterCommand> command) {
        return fetchLocalBusinessTypes(command);
    }

    public List<BusinessTypeDTO> filter(Long companyId, String locale) {
        final Pageable pageable = new PaginationAndSortingCommand(1, 0).generatePagingAndSortingRequest();
        Specification<BusinessType> specification = SharedSpecification.getNumericValueEqual(BusinessType.class, companyId, BusinessType.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(BusinessType.class, null, BusinessType.VALUE_FIELD))
                .and(SharedSpecification.getStringValueEquals(BusinessType.class, locale, BusinessType.LOCALE_FIELD));

        final Page<BusinessType> businessTypePage = this.businessTypeRepository.findAll(specification, pageable);
        return BusinessTypeDTO.of(businessTypePage.getContent());
    }


    /**
     * Filters warehouse types
     *
     * @param command - filter command
     * @return Paged warehouse DTO-s
     */
    private ApiBasePageDTO<BusinessTypeDTO> fetchLocalBusinessTypes(ApiBasePageCommand<BusinessTypeFilterCommand> command) {
        final Pageable pageable = command.getPaginationAndSorting().generatePagingAndSortingRequest();
        Specification<BusinessType> specification = SharedSpecification.getNumericValueEqual(BusinessType.class, command.getCommand().getCompanyId(), BusinessType.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(BusinessType.class, command.getCommand().getValue(), BusinessType.VALUE_FIELD))
                .and(SharedSpecification.getStringValueEquals(BusinessType.class, command.getCommand().getLocale(), BusinessType.LOCALE_FIELD));

        final Page<BusinessType> businessTypePage = this.businessTypeRepository.findAll(specification, pageable);
        return ApiBasePageDTO.generateSuccessResponse(BusinessTypeDTO.of(businessTypePage.getContent()),
                PageDTO.ofPage(businessTypePage));
    }

    @Override
    public ApiBaseDTO<BusinessTypeDTO> save(ApiBaseCommand<BusinessTypeSaveCommand> command) {
        BusinessType businessType;
        if (command.getCommand().getId() != null) {
            Optional<BusinessType> businessTypeOptional = this.businessTypeRepository
                    .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

            if (businessTypeOptional.isPresent()) {
                businessType = businessTypeOptional.get();
                command.getCommand().updateEntity(businessType);
            } else {
                return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
            }
        } else {
            businessType = command.getCommand().convertToEntity();
        }
        this.businessTypeRepository.save(businessType);
        return ApiBaseDTO.generateSuccessResponse(BusinessTypeDTO.of(businessType));
    }

    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command) {
        final Optional<BusinessType> businessTypeOptional = this.businessTypeRepository
                .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

        return businessTypeOptional.map(businessType -> {
            this.businessTypeRepository.delete(businessType);

            return ApiBaseDTO.generateSuccessResponse(true);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR));
    }
}
