package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ImportSettingsRepository extends JpaRepository<ImportSettings, Long>, JpaSpecificationExecutor<ImportSettings> {

    Optional<ImportSettings> findByIdAndCompanyId(Long id, Long companyId);
}
