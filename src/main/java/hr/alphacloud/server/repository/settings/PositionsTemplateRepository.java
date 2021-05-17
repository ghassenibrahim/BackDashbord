package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.position_template.PositionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PositionsTemplateRepository extends JpaRepository<PositionTemplate, Long>, JpaSpecificationExecutor<PositionTemplate> {

    Optional<PositionTemplate> findByIdAndSheetType(Long companyId, String sheetType);

}
