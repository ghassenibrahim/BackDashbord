package hr.alphacloud.server.model.dto.dashboard.customer_analysis;

import hr.alphacloud.server.model.entity.reporting.SalesRecap;
import hr.alphacloud.server.model.entity.reporting.SalesRecapProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class SalesRecapPivotDTO {

    private String partnerName;
    private String articleName;
    private String date;
    private Integer dateByYear;
    private BigDecimal soldQuantity;
    private BigDecimal purchaseValue;
    private BigDecimal salesValue;
    private BigDecimal margin;
    private BigDecimal singleMargin;
    private BigDecimal marginPercentage;
    private BigDecimal marginNV;
    private BigDecimal singleSalesPrice;
    private BigDecimal singlePurchasePrice;

    public static SalesRecapPivotDTO of(SalesRecapProperties salesRecapProperties, SalesRecap salesRecap) {
        SalesRecapPivotDTO cmd = new SalesRecapPivotDTO();
        cmd.partnerName = salesRecapProperties.getPartnerName();
        cmd.articleName = salesRecapProperties.getArticleName();
        cmd.date = salesRecapProperties.getDate();
        cmd.dateByYear = salesRecap.getReport().getReportDateFrom().getYear();
        cmd.soldQuantity = salesRecapProperties.getQuantity();
        cmd.purchaseValue = salesRecapProperties.getPurchaseValue();
        cmd.salesValue = salesRecapProperties.getSalesValue();
        cmd.margin = salesRecapProperties.getSalesValue().subtract(salesRecapProperties.getPurchaseValue());
        if (salesRecapProperties.getQuantity().compareTo(BigDecimal.ZERO) != 0) {
            cmd.singleSalesPrice = salesRecapProperties.getSalesValue().divide(salesRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP);
            cmd.singlePurchasePrice = salesRecapProperties.getPurchaseValue().divide(salesRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP);
            cmd.singleMargin = (salesRecapProperties.getSalesValue().divide(salesRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP))
                    .subtract(salesRecapProperties.getPurchaseValue().divide(salesRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP));
        } else {
            cmd.singleSalesPrice = BigDecimal.ZERO;
            cmd.singlePurchasePrice = BigDecimal.ZERO;
            cmd.singleMargin = BigDecimal.ZERO;
        }
        if (salesRecapProperties.getSalesValue().compareTo(BigDecimal.ZERO) != 0) {
            cmd.marginPercentage = (salesRecapProperties.getSalesValue().subtract(salesRecapProperties.getPurchaseValue()))
                    .divide(salesRecapProperties.getSalesValue(), 2, RoundingMode.HALF_UP);
        } else {
            cmd.marginPercentage = BigDecimal.ZERO;
        }
        if (salesRecapProperties.getPurchaseValue().compareTo(BigDecimal.ZERO) != 0) {
            cmd.marginNV = (salesRecapProperties.getSalesValue().subtract(salesRecapProperties.getPurchaseValue()))
                    .divide(salesRecapProperties.getPurchaseValue(), 2, RoundingMode.HALF_UP);
        } else {
            cmd.marginNV = BigDecimal.ZERO;
        }
        return cmd;
    }
}
