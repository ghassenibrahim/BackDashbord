package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpendingLocationRepository extends JpaRepository<SpendingLocation, Long>, JpaSpecificationExecutor<SpendingLocation> {

    Optional<SpendingLocation> findByIdAndCompanyId(Long id, Long companyId);

}
