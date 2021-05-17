package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.AdditionalSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdditionalSettingsRepository extends JpaRepository<AdditionalSettings, Long>, JpaSpecificationExecutor<AdditionalSettings> {

    AdditionalSettings findByCompanyId(Long companyId);

}
