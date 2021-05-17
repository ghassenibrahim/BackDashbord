package hr.alphacloud.server.service.settings.spending_location;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.spending_location.SpendingLocationFilterCommand;
import hr.alphacloud.server.model.command.settings.spending_location.SpendingLocationSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.base.PageDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import hr.alphacloud.server.model.specifications.SharedSpecification;
import hr.alphacloud.server.repository.settings.SpendingLocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpendingLocationServiceImpl implements SpendingLocationService {

    private final SpendingLocationRepository spendingLocationRepository;

    public SpendingLocationServiceImpl(SpendingLocationRepository spendingLocationRepository) {
        this.spendingLocationRepository = spendingLocationRepository;
    }

    @Override
    public List<SpendingLocationDTO> filter(Long companyId, Pageable pageable) {
        Specification<SpendingLocation> specification = SharedSpecification
                .getNumericValueEqual(SpendingLocation.class, companyId, SpendingLocation.COMPANY_ID);
//                .and(SharedSpecification.getStringValueEquals(ImportSettings.class, filterCommand.getCommand().getName(), ImportSettings.NAME_FIELD));

        final Page<SpendingLocation> spendingLocationPage = this.spendingLocationRepository
                .findAll(specification, pageable);

        return SpendingLocationDTO.of(spendingLocationPage.getContent());
    }

    @Override
    @Transactional(readOnly = true)
    public ApiBasePageDTO<SpendingLocationDTO> filter(ApiBasePageCommand<SpendingLocationFilterCommand> command) {
        Specification<SpendingLocation> specification = SharedSpecification
                .getNumericValueEqual(SpendingLocation.class, command.getCommand().getCompanyId(), SpendingLocation.COMPANY_ID)
                .and(SharedSpecification.getStringValueEquals(SpendingLocation.class, command.getCommand().getName(), SpendingLocation.NAME_FIELD));

        final Page<SpendingLocation> spendingLocationPage = this.spendingLocationRepository
                .findAll(specification, command.getPaginationAndSorting().generatePagingAndSortingRequest());

        return ApiBasePageDTO.generateSuccessResponse(SpendingLocationDTO.of(spendingLocationPage.getContent()),
                PageDTO.ofPage(spendingLocationPage));
    }


    @Override
    public ApiBaseDTO<SpendingLocationDTO> save(ApiBaseCommand<SpendingLocationSaveCommand> command) {
        SpendingLocation spendingLocation;
        if (command.getCommand().getId() != null) {
            Optional<SpendingLocation> spendingLocationOptional = this.spendingLocationRepository
                    .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

            if (spendingLocationOptional.isPresent()) {
                spendingLocation = spendingLocationOptional.get();
                command.getCommand().updateEntity(spendingLocation);
            } else {
                return ApiBaseDTO.generateErrorResponse(ErrorInfo.GENERAL_ERROR);
            }
        } else {
            spendingLocation = command.getCommand().convertToEntity();
        }
        this.spendingLocationRepository.save(spendingLocation);
        return ApiBaseDTO.generateSuccessResponse(SpendingLocationDTO.of(spendingLocation));
    }

    @Override
    public ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command) {
        final Optional<SpendingLocation> spendingLocationOptional = this.spendingLocationRepository
                .findByIdAndCompanyId(command.getCommand().getId(), command.getCommand().getCompanyId());

        return spendingLocationOptional.map(spendingLocation -> {
            this.spendingLocationRepository.delete(spendingLocation);

            return ApiBaseDTO.generateSuccessResponse(true);
        }).orElseGet(() -> ApiBaseDTO.generateErrorResponse(ErrorInfo.SPENDING_LOCATION_NOT_EXIST));
    }
}
