package hr.alphacloud.server.repository;


import hr.alphacloud.server.model.entity.reporting.SalesRecap;
import hr.alphacloud.server.repository.base.BaseReportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesRecapRepository extends BaseReportRepository<SalesRecap> {

    List<SalesRecap> findAllByReportId(Long reportId);

    @Query(value = "select rec.id, rec.report_id, rec.spending_location_id, rec.business_type_id, " +
            "rec.premises_id, rec.sector_type_id, rec.account_book_id, rec.import_settings_id, rec.import_type, rec.sales_recap_properties " +
            "from sales_recap as rec " +
            "join report on report.id = rec.report_id " +
            "WHERE report.company_id = :companyId " +
            "and extract(year from report.report_date_from) = :currentYear " +
            "or extract(year from report.report_date_from) = :previousYear", nativeQuery = true)
    List<SalesRecap> findSalesForPivot(@Param("companyId") Long companyId,
                                       @Param("currentYear") Integer currentYear,
                                       @Param("previousYear") Integer previousYear);

}
