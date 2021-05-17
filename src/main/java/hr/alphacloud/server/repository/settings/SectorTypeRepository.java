package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SectorTypeRepository extends JpaRepository<SectorType, Long>, JpaSpecificationExecutor<SectorType> {

    Optional<SectorType> findByIdAndCompanyId(Long id, Long companyId);

    Boolean existsByNameAndCompanyId(String name, Long companyId);
}

