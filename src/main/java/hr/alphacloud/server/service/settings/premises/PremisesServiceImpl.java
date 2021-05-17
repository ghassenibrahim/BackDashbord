package hr.alphacloud.server.service.settings.premises;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.premises.PremisesFilterCommand;
import hr.alphacloud.server.model.command.settings.premises.PremisesSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.PremisesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremisesServiceImpl implements PremisesService {

    private final PremisesRepository premisesRepository;

    public PremisesServiceImpl(PremisesRepository premisesRepository) {
        this.premisesRepository = premisesRepository;
    }

    @Override
    public ApiBaseDTO<PremisesDTO> save(ApiBaseCommand<PremisesSaveCommand> command) {
        Boolean duplicateName = premisesRepository.existsByNameAndCompanyId(command.getCommand().getName(), command.getCommand().getCompanyId());
        if (!duplicateName) {
            return ApiBaseDTO.generateSuccessResponse(PremisesDTO.fromEntity(premisesRepository.save(command.getCommand().convertToEntity())));
        } else {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
        }
    }

    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command) {
        premisesRepository.deleteById(command.getCommand().getId());
        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBasePageDTO<PremisesDTO> filter(ApiBasePageCommand<PremisesFilterCommand> command) {
        final Pageable pageable = command.getPaginationAndSorting().generatePagingAndSortingRequest();
        Specification<Premises> specification = SharedSpecification.getNumericValueEqual(Premises.class, command.getCommand().getCompanyId(), Premises.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(Premises.class, command.getCommand().getName(), Premises.NAME_FIELD));
        final Page<Premises> premisesPage = this.premisesRepository.findAll(specification, pageable);
        return ApiBasePageDTO.generateSuccessResponse(PremisesDTO.fromCollection(premisesPage.getContent()),
                PageDTO.ofPage(premisesPage));
    }

    @Override
    public List<PremisesDTO> filter(Long companyId, Pageable pageable) {
        Specification<Premises> specification = SharedSpecification.getNumericValueEqual(Premises.class,
                companyId, Premises.COMPANY_ID);

        final Page<Premises> premises = this.premisesRepository.findAll(specification, pageable);
        return PremisesDTO.fromCollection(premises.getContent());
    }
}
