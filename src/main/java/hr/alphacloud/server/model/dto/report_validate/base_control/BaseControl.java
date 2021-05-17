package hr.alphacloud.server.model.dto.report_validate.base_control;

import java.math.BigDecimal;

public interface BaseControl {

    Long getBrutoKonto();

    String getBrutoName();

    BigDecimal getBrutoValue();

    BigDecimal getImportValue();
}
