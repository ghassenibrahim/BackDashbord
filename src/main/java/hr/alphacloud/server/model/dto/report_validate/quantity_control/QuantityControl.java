package hr.alphacloud.server.model.dto.report_validate.quantity_control;

import java.math.BigDecimal;

public interface QuantityControl {

    Long getKontoCode();

    String getKontoName();

    BigDecimal getQuantitySupplyMP();

    BigDecimal getQuantitySupplyVP();

    BigDecimal getQuantitySalesReturn();

    BigDecimal getQuantitySalesMP();

    BigDecimal getQuantitySalesVP();

    BigDecimal getQuantityDifference();
}
