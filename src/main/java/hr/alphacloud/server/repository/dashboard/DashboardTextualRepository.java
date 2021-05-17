package hr.alphacloud.server.repository.dashboard;

import hr.alphacloud.server.model.dto.report_validate.bruto_book.BrutoBook;
import hr.alphacloud.server.model.entity.reporting.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DashboardTextualRepository extends JpaRepository<Report, Long> {

    /**
     * Find every possible konto/ kontoName from selected parameters in order to create accurate list
     *
     * @param reportType - annual, monthly,..
     */
    @Query(value =
            "SELECT DISTINCT base.konto as konto, STRING_AGG(DISTINCT base.kontoName, ', ') as kontoName " +
                    "from " +
                    "(SELECT report_id, report_date_to, (y.x ->'konto')\\:\\:decimal as konto, (y.x ->'kontoName')\\:\\:text as kontoName " +
                    "from brutto_balance " +
                    "join report on report.id = brutto_balance.report_id," +
                    "LATERAL (SELECT jsonb_array_elements(brutto_balance.brutto_balance_properties) x) y " +
                    "where report.report_type_code = :reportType " +
                    "and report.company_id = :companyId " +
                    "and report.report_date_from BETWEEN :dateFrom AND :dateTo " +
                    "and report.report_date_to BETWEEN :dateFrom AND :dateTo " +
                    "group by report_id, report_date_to, konto, kontoName " +
                    "order by konto " +
                    ") as base " +
                    "group by base.konto " +
                    "order by base.konto ", nativeQuery = true)
    List<BrutoBook> findDistinctBrutoCodes(@Param("companyId") Long companyId,
                                           @Param("reportType") String reportType,
                                           @Param("dateFrom") Date dateFrom,
                                           @Param("dateTo") Date dateTo);
}
