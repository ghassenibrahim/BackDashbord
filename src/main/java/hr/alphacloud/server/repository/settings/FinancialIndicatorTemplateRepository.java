package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.dashboard.FinancialIndicatorTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FinancialIndicatorTemplateRepository extends JpaRepository<FinancialIndicatorTemplate, Long>, JpaSpecificationExecutor<FinancialIndicatorTemplate> {

    FinancialIndicatorTemplate findFirstById(Long id);

}