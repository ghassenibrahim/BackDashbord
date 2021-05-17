package hr.alphacloud.server.model.dto.dashboard.customer_analysis;

import lombok.Data;

import java.lang.reflect.Field;
import java.math.BigDecimal;

@Data
public class PivotTableDataDTO {

    private String partnerName;
    private String articleName;
    private String type;
    private String date;
    private Integer dateByYear;
    private BigDecimal value;

    public static PivotTableDataDTO of(SalesRecapPivotDTO salesRecapPivotDTO, Field field) {
        PivotTableDataDTO cmd = new PivotTableDataDTO();
        cmd.partnerName = !salesRecapPivotDTO.getPartnerName().isEmpty() ? salesRecapPivotDTO.getPartnerName() : "";
        cmd.articleName = !salesRecapPivotDTO.getArticleName().isEmpty() ? salesRecapPivotDTO.getArticleName() : "";
        cmd.date = !salesRecapPivotDTO.getDate().isEmpty() ? salesRecapPivotDTO.getDate() : "";
        cmd.dateByYear = salesRecapPivotDTO.getDateByYear();
        cmd.type = !field.getName().isEmpty() ? field.getName().substring(0, 1).toUpperCase() + field.getName()
                .substring(1).replaceAll("([^_])([A-Z])", "$1 $2") : "";
        try {
            field.setAccessible(true);
            cmd.value = (new BigDecimal(field.get(salesRecapPivotDTO).toString()));
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return cmd;
    }

    public static PivotTableDataDTO of(PurchaseRecapPivotDTO purchaseRecapPivotDTO, Field field) {
        PivotTableDataDTO cmd = new PivotTableDataDTO();
        cmd.partnerName = !purchaseRecapPivotDTO.getPartnerName().isEmpty() ? purchaseRecapPivotDTO.getPartnerName() : "";
        cmd.articleName = !purchaseRecapPivotDTO.getArticleName().isEmpty() ? purchaseRecapPivotDTO.getArticleName() : "";
        cmd.date = !purchaseRecapPivotDTO.getDate().isEmpty() ? purchaseRecapPivotDTO.getDate() : "";
        cmd.dateByYear = purchaseRecapPivotDTO.getDateByYear();
        cmd.type = !field.getName().isEmpty() ? field.getName().substring(0, 1).toUpperCase() + field.getName()
                .substring(1).replaceAll("([^_])([A-Z])", "$1 $2") : "";
        try {
            field.setAccessible(true);
            cmd.value = (new BigDecimal(field.get(purchaseRecapPivotDTO).toString()));
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return cmd;
    }
}
