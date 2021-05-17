package hr.alphacloud.server.util;

import hr.alphacloud.server.exception.exceptions.EntityNotExistException;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.entity.reporting.settings.*;
import hr.alphacloud.server.repository.settings.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AbstractReportUtil {
    private final SpendingLocationRepository spendingLocationRepository;
    private final BusinessTypeRepository businessTypeRepository;
    private final WarehouseTypeRepository warehouseTypeRepository;
    private final PremisesRepository premisesRepository;
    private final SectorTypeRepository sectorTypeRepository;

    public AbstractReportUtil(SpendingLocationRepository spendingLocationRepository,
                              BusinessTypeRepository businessTypeRepository,
                              WarehouseTypeRepository warehouseTypeRepository, PremisesRepository premisesRepository, SectorTypeRepository sectorTypeRepository) {
        this.warehouseTypeRepository = warehouseTypeRepository;
        this.spendingLocationRepository = spendingLocationRepository;
        this.businessTypeRepository = businessTypeRepository;
        this.premisesRepository = premisesRepository;
        this.sectorTypeRepository = sectorTypeRepository;
    }

    public BusinessType getBusinessType(Long businessTypeId, Long companyId) {
        if (businessTypeId != null) {
            Optional<BusinessType> businessTypeOptional = this.businessTypeRepository
                    .findByIdAndCompanyId(businessTypeId, companyId);

            if (businessTypeOptional.isPresent()) {
                return businessTypeOptional.get();
            } else {
                throw new EntityNotExistException(ErrorInfo.BUSINESS_TYPE_NOT_EXIST);
            }
        }
        return null;
    }

    public SpendingLocation getSpendingLocation(Long spendingLocationId, Long companyId) {
        if (spendingLocationId != null) {
            Optional<SpendingLocation> spendingLocationOptional = this.spendingLocationRepository
                    .findByIdAndCompanyId(spendingLocationId, companyId);

            if (spendingLocationOptional.isPresent()) {
                return spendingLocationOptional.get();
            } else {
                throw new EntityNotExistException(ErrorInfo.SPENDING_LOCATION_NOT_EXIST);
            }
        }
        return null;
    }

    public WarehouseType getWarehouseType(Long warehouseTypeId, Long companyId) {
        if (warehouseTypeId != null) {
            Optional<WarehouseType> warehouseTypeOptional = this.warehouseTypeRepository
                    .findByIdAndCompanyId(warehouseTypeId, companyId);

            if (warehouseTypeOptional.isPresent()) {
                return warehouseTypeOptional.get();
            } else {
                throw new EntityNotExistException(ErrorInfo.WAREHOUSE_TYPE_NOT_EXIST);
            }
        }
        return null;
    }

    public Premises getPremises(Long premisesId, Long companyId) {
        if (premisesId != null) {
            Optional<Premises> premisesOptional = this.premisesRepository
                    .findByIdAndCompanyId(premisesId, companyId);

            if (premisesOptional.isPresent()) {
                return premisesOptional.get();
            } else {
                throw new EntityNotExistException(ErrorInfo.PREMISES_NOT_EXIST);
            }
        }
        return null;
    }

    public SectorType getSectorType(Long sectorTypeId, Long companyId) {
        if (sectorTypeId != null) {
            Optional<SectorType> sectorTypeOptional = this.sectorTypeRepository
                    .findByIdAndCompanyId(sectorTypeId, companyId);

            if (sectorTypeOptional.isPresent()) {
                return sectorTypeOptional.get();
            } else {
                throw new EntityNotExistException(ErrorInfo.PREMISES_NOT_EXIST);
            }
        }
        return null;
    }
}
