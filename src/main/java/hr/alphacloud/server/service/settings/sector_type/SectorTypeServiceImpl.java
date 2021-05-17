package hr.alphacloud.server.service.settings.sector_type;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.sector_type.SectorFilterCommand;
import hr.alphacloud.server.model.command.settings.sector_type.SectorSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.SectorTypeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorTypeServiceImpl implements SectorTypeService {

    private final SectorTypeRepository sectorTypeRepository;

    public SectorTypeServiceImpl(SectorTypeRepository sectorTypeRepository) {
        this.sectorTypeRepository = sectorTypeRepository;
    }

    @Override
    public ApiBaseDTO<SectorTypeDTO> save(ApiBaseCommand<SectorSaveCommand> command) {
        Boolean duplicateName = sectorTypeRepository.existsByNameAndCompanyId(command.getCommand().getName(), command.getCommand().getCompanyId());
        if (!duplicateName) {
            return ApiBaseDTO.generateSuccessResponse(SectorTypeDTO.fromEntity(sectorTypeRepository.save(command.getCommand().convertToEntity())));
        } else {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        }
    }

    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command) {
        sectorTypeRepository.deleteById(command.getCommand().getId());
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBasePageDTO<SectorTypeDTO> filter(ApiBasePageCommand<SectorFilterCommand> command) {
        final Pageable pageable = command.getPaginationAndSorting().generatePagingAndSortingRequest();
        Specification<SectorType> specification = SharedSpecification.getNumericValueEqual(SectorType.class, command.getCommand().getCompanyId(), SectorType.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(SectorType.class, command.getCommand().getName(), SectorType.NAME_FIELD));
        final Page<SectorType> sectorPage = this.sectorTypeRepository.findAll(specification, pageable);
        return ApiBasePageDTO.generateSuccessResponse(SectorTypeDTO.fromCollection(sectorPage.getContent()),
                PageDTO.ofPage(sectorPage));
    }

    @Override
    public List<SectorTypeDTO> filter(Long companyId, Pageable pageable) {
        Specification<SectorType> specification = SharedSpecification.getNumericValueEqual(SectorType.class,
                companyId, SectorType.COMPANY_ID);

        final Page<SectorType> sectors = this.sectorTypeRepository.findAll(specification, pageable);
        return SectorTypeDTO.fromCollection(sectors.getContent());
    }
}
