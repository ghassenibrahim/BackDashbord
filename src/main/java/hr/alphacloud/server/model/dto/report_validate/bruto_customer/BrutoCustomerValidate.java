package hr.alphacloud.server.model.dto.report_validate.bruto_customer;

import java.math.BigDecimal;

public interface BrutoCustomerValidate {

    Long getReportId();

    Long getBrutoId();

    Long getCustomerId();

    String getBrutoName();

    Long getBrutoCode();

    BigDecimal getCustomerSum();

    BigDecimal getBrutoCost();

}
