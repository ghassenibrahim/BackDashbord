package hr.alphacloud.server.model.enums.report;

import java.util.HashMap;
import java.util.Map;

public enum ExcelType {
    BRUTO_BALANCE("BRUTO_BALANCE"),
    SALES_RECAP("SALES_RECAP"),
    PURCHASE_RECAP("PURCHASE_RECAP"),
    SUPPLY_ANALYTIC("SUPPLY_ANALYTIC"),
    RECEIVABLE_MATURITY("RECEIVABLE_MATURITY"),
    CUSTOMER("CUSTOMER"),
    SUPPLIER("SUPPLIER"),
    CUSTOMER_ADVANCE("CUSTOMER_ADVANCE"),
    SUPPLIER_ADVANCE("SUPPLIER_ADVANCE"),
    GIVER_LOANS("GIVER_LOANS"),
    RECEIVED_LOANS("RECEIVED_LOANS");

    private final String value;
    private static final Map<String, ExcelType> lookup = new HashMap<String, ExcelType>();

    ExcelType(String key) {
        this.value = key;
    }

    static {
        for (ExcelType excelType : ExcelType.values()) {
            lookup.put(excelType.getValue(), excelType);
        }
    }

    public String getValue() {
        return value;
    }

    public static ExcelType getKey(String value) {
        return lookup.get(value);
    }
}

