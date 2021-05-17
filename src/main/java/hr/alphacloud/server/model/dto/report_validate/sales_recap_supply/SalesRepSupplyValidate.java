package hr.alphacloud.server.model.dto.report_validate.sales_recap_supply;

import java.math.BigDecimal;

public interface SalesRepSupplyValidate {

    Long getReportId();

    Long getSalesId();

    Long getSupplyId();

    Long getSalesArticleNumber();

    BigDecimal getSalesSoldAmount();

    BigDecimal getSalesItemSellingPrice();

    Long getSupplyArticleNumber();

    BigDecimal getSupplyAmountOutgoing();

    BigDecimal getSupplyValueOutgoing();

    String getSupplyArticleName();
}
