package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Long>, JpaSpecificationExecutor<BusinessType> {

    Optional<BusinessType> findByIdAndCompanyId(Long id, Long companyId);

}
