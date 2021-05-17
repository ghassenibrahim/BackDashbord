package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class ImportSettingsDTO {
    private Long id;
    private Long companyId;
    private String name;
    private Integer skipFirstRowAmount;
    private Integer skipLastRowAmount;
    private Integer minimumAcceptedColumnLength;
    private String acceptedDateFormat;
    private Integer skipFirstColumnAmount;

    public static ImportSettingsDTO of(ImportSettings importSettings) {
        return builder()
                .id(importSettings.getId())
                .companyId(importSettings.getCompanyId())
                .name(importSettings.getName())
                .skipFirstRowAmount(importSettings.getSkipFirstRowAmount())
                .skipLastRowAmount(importSettings.getSkipLastRowAmount())
                .minimumAcceptedColumnLength(importSettings.getMinimumAcceptedColumnLength())
                .acceptedDateFormat(importSettings.getAcceptedDateFormat())
                .skipFirstColumnAmount(importSettings.getSkipFirstColumnAmount())
                .build();
    }

    public static List<ImportSettingsDTO> of(List<ImportSettings> importSettingsList) {
        return importSettingsList.stream()
                .map(ImportSettingsDTO::of)
                .collect(Collectors.toList());
    }
}
