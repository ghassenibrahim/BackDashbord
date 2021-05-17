package hr.alphacloud.server.model.dto.dashboard.customer_analysis;

import hr.alphacloud.server.model.entity.reporting.PurchaseRecap;
import hr.alphacloud.server.model.entity.reporting.PurchaseRecapProperties;
import hr.alphacloud.server.model.entity.reporting.SalesRecapProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class PurchaseRecapPivotDTO {

    private String partnerName;
    private String articleName;
    private String date;
    private Integer dateByYear;
    private BigDecimal quantity;
    private BigDecimal purchaseValue;
    private BigDecimal salesValue;
    private BigDecimal margin;
    private BigDecimal singleMargin;
    private BigDecimal marginPercentage;
    private BigDecimal singleSalesPrice;
    private BigDecimal singlePurchasePrice;

    public static PurchaseRecapPivotDTO of(PurchaseRecapProperties purchaseRecapProperties, SalesRecapProperties salesRecapProperties, PurchaseRecap purchaseRecap) {
        PurchaseRecapPivotDTO cmd = new PurchaseRecapPivotDTO();
        cmd.partnerName = purchaseRecapProperties.getPartnerName();
        cmd.articleName = purchaseRecapProperties.getArticleName();
        cmd.date = purchaseRecapProperties.getDate();
        cmd.dateByYear = purchaseRecap.getReport().getReportDateFrom().getYear();
        cmd.quantity = purchaseRecapProperties.getQuantity();
        cmd.purchaseValue = purchaseRecapProperties.getPurchaseValue();
        cmd.salesValue = salesRecapProperties.getSalesValue();
        cmd.margin = salesRecapProperties.getSalesValue().subtract(purchaseRecapProperties.getPurchaseValue());
        if (purchaseRecapProperties.getQuantity().compareTo(BigDecimal.ZERO) != 0) {
            cmd.singleSalesPrice = salesRecapProperties.getSalesValue().divide(purchaseRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP);
            cmd.singlePurchasePrice = purchaseRecapProperties.getPurchaseValue().divide(purchaseRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP);
            cmd.singleMargin = (salesRecapProperties.getSalesValue().divide(purchaseRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP))
                    .subtract(purchaseRecapProperties.getPurchaseValue().divide(purchaseRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP));
        } else {
            cmd.singleSalesPrice = BigDecimal.ZERO;
            cmd.singlePurchasePrice = BigDecimal.ZERO;
            cmd.singleMargin = BigDecimal.ZERO;
        }
        if (salesRecapProperties.getSalesValue().compareTo(BigDecimal.ZERO) != 0) {
            cmd.marginPercentage = (salesRecapProperties.getSalesValue().subtract(purchaseRecapProperties.getPurchaseValue()))
                    .divide(salesRecapProperties.getSalesValue(), 2, RoundingMode.HALF_UP);
        } else {
            cmd.marginPercentage = BigDecimal.ZERO;
        }
        return cmd;
    }

    public static PurchaseRecapPivotDTO of(PurchaseRecapProperties purchaseRecapProperties, PurchaseRecap purchaseRecap) {
        PurchaseRecapPivotDTO cmd = new PurchaseRecapPivotDTO();
        cmd.partnerName = purchaseRecapProperties.getPartnerName();
        cmd.articleName = purchaseRecapProperties.getArticleName();
        cmd.date = purchaseRecapProperties.getDate();
        cmd.dateByYear = purchaseRecap.getReport().getReportDateFrom().getYear();
        cmd.quantity = purchaseRecapProperties.getQuantity();
        cmd.purchaseValue = purchaseRecapProperties.getPurchaseValue();
        cmd.salesValue = BigDecimal.ZERO;
        cmd.margin = BigDecimal.ZERO;
        if (purchaseRecapProperties.getQuantity().compareTo(BigDecimal.ZERO) != 0) {
            cmd.singleSalesPrice = BigDecimal.ZERO;
            cmd.singlePurchasePrice = purchaseRecapProperties.getPurchaseValue().divide(purchaseRecapProperties.getQuantity(), 2, RoundingMode.HALF_UP);
            cmd.singleMargin = BigDecimal.ZERO;
        } else {
            cmd.singleSalesPrice = BigDecimal.ZERO;
            cmd.singlePurchasePrice = BigDecimal.ZERO;
            cmd.singleMargin = BigDecimal.ZERO;
        }
        cmd.marginPercentage = BigDecimal.ZERO;

        return cmd;
    }
}
