package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.AdditionalSettings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdditionalSettingsDTO {
    private Long id;
    private Long companyId;
    private Float principal;
    private Float interest;
    private Float ratePd;

    public static AdditionalSettingsDTO of(AdditionalSettings additionalSettings) {
        if (additionalSettings != null) {
            return builder()
                    .id(additionalSettings.getId())
                    .companyId(additionalSettings.getCompanyId())
                    .principal(additionalSettings.getPrincipal())
                    .interest(additionalSettings.getInterest())
                    .ratePd(additionalSettings.getRatePd())
                    .build();
        }
        return null;
    }

}
