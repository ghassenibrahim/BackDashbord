package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PremisesRepository extends JpaRepository<Premises, Long>, JpaSpecificationExecutor<Premises> {

    Optional<Premises> findByIdAndCompanyId(Long id, Long companyId);

    Boolean existsByNameAndCompanyId(String name, Long companyId);

}
