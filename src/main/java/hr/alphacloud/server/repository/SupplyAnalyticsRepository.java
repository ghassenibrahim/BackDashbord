package hr.alphacloud.server.repository;

import hr.alphacloud.server.model.entity.reporting.SupplyAnalytics;
import hr.alphacloud.server.repository.base.BaseReportRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyAnalyticsRepository extends BaseReportRepository<SupplyAnalytics> {

    List<SupplyAnalytics> findAllByReportId(Long reportId);

}
