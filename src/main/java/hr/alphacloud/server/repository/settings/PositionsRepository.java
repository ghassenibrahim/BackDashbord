package hr.alphacloud.server.repository.settings;

import hr.alphacloud.server.model.entity.reporting.settings.position_template.Positions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PositionsRepository extends JpaRepository<Positions, Long>, JpaSpecificationExecutor<Positions> {

    Positions findByCompanyIdAndSheetType(Long companyId, String sheetType);

    // Positions findByCompanyIdAndSpendingLocation(Long companyId, Long spendingLocation);

    List<Positions> findAllByCompanyId(Long companyId);

}