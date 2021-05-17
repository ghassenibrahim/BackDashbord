package hr.alphacloud.server.model.dto.report_validate.quantity_control;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class QuantityControlDTO {

    private Long kontoCode;
    private String kontoName;
    private BigDecimal quantitySupplyMP;
    private BigDecimal quantitySupplyVP;
    private BigDecimal quantitySalesReturn;
    private BigDecimal quantitySalesMP;
    private BigDecimal quantitySalesVP;
    private BigDecimal quantityDifference;

    public static QuantityControlDTO of(QuantityControl quantityControl) {
        QuantityControlDTO cmd = new QuantityControlDTO();
        cmd.kontoCode = quantityControl.getKontoCode();
        cmd.kontoName = quantityControl.getKontoName();
        cmd.quantityDifference = quantityControl.getQuantityDifference();
        cmd.quantitySalesMP = quantityControl.getQuantitySalesMP();
        cmd.quantitySalesReturn = quantityControl.getQuantitySalesReturn();
        cmd.quantitySalesVP = quantityControl.getQuantitySalesVP();
        cmd.quantitySupplyMP = quantityControl.getQuantitySupplyMP();
        cmd.quantitySupplyVP = quantityControl.getQuantitySupplyVP();


        return cmd;
    }

    public static List<QuantityControlDTO> of(List<QuantityControl> quantityControlsList) {
        return quantityControlsList.stream().map(QuantityControlDTO::of).collect(Collectors.toList());
    }
}
