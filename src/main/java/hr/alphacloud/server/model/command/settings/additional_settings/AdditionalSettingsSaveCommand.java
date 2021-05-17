package hr.alphacloud.server.model.command.settings.additional_settings;

import hr.alphacloud.server.model.entity.reporting.settings.AdditionalSettings;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdditionalSettingsSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    private Float principal;
    private Float interest;
    private Float ratePd;

    public AdditionalSettings convertToEntity() {
        AdditionalSettings additionalSettings = new AdditionalSettings();
        additionalSettings.setId(this.id);
        additionalSettings.setCompanyId(this.companyId);
        additionalSettings.setPrincipal(this.principal);
        additionalSettings.setInterest(this.interest);
        additionalSettings.setRatePd(this.ratePd);

        this.updateEntity(additionalSettings);
        return additionalSettings;
    }

    public void updateEntity(AdditionalSettings additionalSettings) {
        additionalSettings.setPrincipal(this.principal);
        additionalSettings.setInterest(this.interest);
        additionalSettings.setRatePd(this.ratePd);
    }
}
