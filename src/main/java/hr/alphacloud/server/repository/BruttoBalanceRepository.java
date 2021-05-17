package hr.alphacloud.server.repository;

import hr.alphacloud.server.model.entity.reporting.BruttoBalance;
import hr.alphacloud.server.repository.base.BaseReportRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BruttoBalanceRepository extends BaseReportRepository<BruttoBalance> {

    /**
     * Primary use is to extract properties from BrutoBalance based on parameters. These get populated as values inside a map.
     *
     * @param reportType- annual, monthly,..
     */
    @Query(value = "Select bruto.id, bruto.report_id, bruto.spending_location_id, bruto.import_settings_id, " +
            "bruto.business_type_id, bruto.premises_id," +
            "bruto.sector_type_id, bruto.brutto_balance_properties " +
            "from brutto_balance as bruto " +
            "join report on report.id = bruto.report_id " +
            "WHERE report.report_type_code = :reportType " +
            "and report.company_id = :companyId " +
            "and report.report_date_from BETWEEN :dateFrom AND :dateTo " +
            "and report.report_date_to BETWEEN :dateFrom AND :dateTo " +
            "order by report.report_date_to asc", nativeQuery = true)
    List<BruttoBalance> findBruttoBooks(@Param("companyId") Long companyId,
                                        @Param("reportType") String reportType,
                                        @Param("dateFrom") Date dateFrom,
                                        @Param("dateTo") Date dateTo);

}
