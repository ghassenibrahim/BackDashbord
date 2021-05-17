package hr.alphacloud.server.service.settings.warehouse_type;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.warehouse_type.WarehouseTypeFilterCommand;
import hr.alphacloud.server.model.command.settings.warehouse_type.WarehouseTypeSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.WarehouseTypeDTO;
import hr.alphacloud.server.model.entity.reporting.settings.WarehouseType;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.WarehouseTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseTypeServiceImpl implements WarehouseTypeService {

    private final WarehouseTypeRepository warehouseTypeRepository;

    public WarehouseTypeServiceImpl(WarehouseTypeRepository warehouseTypeRepository) {
        this.warehouseTypeRepository = warehouseTypeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ApiBasePageDTO<WarehouseTypeDTO> filter(ApiBasePageCommand<WarehouseTypeFilterCommand> command) {
        return fetchLocalWarehouseTypes(command);
    }

    @Override
    public List<WarehouseTypeDTO> filter(Long companyId, String locale, Pageable pageable) {
        Specification<WarehouseType> specification = SharedSpecification.getNumericValueEqual(WarehouseType.class, companyId, WarehouseType.COMPANY_ID)
//                .and(SharedSpecification.getStringValueEquals(WarehouseType.class, command.getCommand().getValue(), WarehouseType.VALUE_FIELD))
                .and(SharedSpecification.getStringValueEquals(WarehouseType.class, locale, WarehouseType.LOCALE_FIELD));

        final Page<WarehouseType> warehousePage = this.warehouseTypeRepository.findAll(specification, pageable);
        return WarehouseTypeDTO.of(warehousePage.getContent());
    }


    /**
     * Filters warehouse types
     *
     * @param command - filter command
     * @return Paged warehouse DTO-s
     */
    private ApiBasePageDTO<WarehouseTypeDTO> fetchLocalWarehouseTypes(ApiBasePageCommand<WarehouseTypeFilterCommand> command) {
        final Pageable pageable = command.getPaginationAndSorting().generatePagingAndSortingRequest();
        Specification<WarehouseType> specification = SharedSpecification.getNumericValueEqual(WarehouseType.class, command.getCommand().getCompanyId(), WarehouseType.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(WarehouseType.class, command.getCommand().getValue(), WarehouseType.VALUE_FIELD))
                .and(SharedSpecification.getStringValueEquals(WarehouseType.class, command.getCommand().getLocale(), WarehouseType.LOCALE_FIELD));

        final Page<WarehouseType> warehousePage = this.warehouseTypeRepository.findAll(specification, pageable);
        return ApiBasePageDTO.generateSuccessResponse(WarehouseTypeDTO.of(warehousePage.getContent()),
                PageDTO.ofPage(warehousePage));
    }

    /**
     * Saves or updates warehouse type
     *
     * @param command - warehouse type save command
     * @return - ApiBaseDTO
     */
    @Override
    public ApiBaseDTO<WarehouseTypeDTO> save(ApiBaseCommand<WarehouseTypeSaveCommand> command) {
        WarehouseType warehouseType;
        if (command.getCommand().getId() != null) {
            Optional<WarehouseType> warehouseTypeOptional = this.warehouseTypeRepository
                    .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

            if (warehouseTypeOptional.isPresent()) {
                warehouseType = warehouseTypeOptional.get();
                command.getCommand().updateEntity(warehouseType);
            } else {
                return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
            }
        } else {
            warehouseType = command.getCommand().convertToEntity();
        }
        this.warehouseTypeRepository.save(warehouseType);
        return ApiBaseDTO.generateSuccessResponse(WarehouseTypeDTO.of(warehouseType));
    }

    /**
     * Deletes warehouse type
     *
     * @param command - delete command
     * @return ApiBaseDTO
     */
    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command) {
        final Optional<WarehouseType> warehouseOptional = this.warehouseTypeRepository
                .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

        return warehouseOptional.map(warehouse -> {
            this.warehouseTypeRepository.delete(warehouse);
            return ApiBaseDTO.generateSuccessResponse(true);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.WAREHOUSE_TYPE_NOT_EXIST));
    }
}
