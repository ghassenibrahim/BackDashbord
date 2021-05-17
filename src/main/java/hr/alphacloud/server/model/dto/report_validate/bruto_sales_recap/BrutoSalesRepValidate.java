package hr.alphacloud.server.model.dto.report_validate.bruto_sales_recap;

import java.math.BigDecimal;

public interface BrutoSalesRepValidate {

    Long getId();

    Long getReportId();

    Long getBrutoId();

    Long getBrutoCode();

    String getBrutoArticleName();

    BigDecimal getSalesRepTotalSellingPrice();

    BigDecimal getBrutoFinalStateBalance();
}
