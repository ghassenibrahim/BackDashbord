package hr.alphacloud.server.model.command.settings.business_type;

import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BusinessTypeSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    @NotEmpty
    private String value;
    @NotEmpty
    private String locale;

    public BusinessType convertToEntity() {
        BusinessType businessType = new BusinessType();
        businessType.setId(this.id);
        businessType.setCompanyId(this.companyId);
        this.updateEntity(businessType);
        return businessType;
    }

    public void updateEntity(BusinessType businessType) {
        businessType.setValue(this.value);
        businessType.setLocale(this.locale);
    }
}
