package hr.alphacloud.server.model.dto.client;


import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import hr.alphacloud.server.model.entity.reporting.settings.WarehouseType;
import lombok.Getter;

@Getter
public class CodeBookDTO {
    private String value;
    private String code;
    private String locale;
    private String countryCode;

    public WarehouseType convertToWarehouse(Long companyId) {
        WarehouseType warehouse = new WarehouseType();
        warehouse.setCompanyId(companyId);
        warehouse.setValue(value);
        warehouse.setLocale(locale);
        return warehouse;
    }

    public BusinessType convertToBusinessType(Long companyId) {
        BusinessType businessType = new BusinessType();
        businessType.setCompanyId(companyId);
        businessType.setValue(value);
        businessType.setLocale(locale);
        return businessType;
    }
}
