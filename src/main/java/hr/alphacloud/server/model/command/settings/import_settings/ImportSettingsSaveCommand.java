package hr.alphacloud.server.model.command.settings.import_settings;

import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import lombok.Getter;

@Getter
public class ImportSettingsSaveCommand {
    private Long id;
    private Long companyId;
    private String name;
    private Integer skipFirstRowAmount;
    private Integer skipLastRowAmount;
    private Integer minimumAcceptedColumnLength;
    private String acceptedDateFormat;
    private Integer skipFirstColumnAmount;

    public ImportSettings convertToEntity() {
        ImportSettings importSettings = new ImportSettings();
        importSettings.setId(this.id);
        importSettings.setCompanyId(this.companyId);
        importSettings.setName(this.name);
        importSettings.setSkipFirstRowAmount(this.skipFirstRowAmount);
        importSettings.setSkipLastRowAmount(this.skipLastRowAmount);
        importSettings.setMinimumAcceptedColumnLength(this.minimumAcceptedColumnLength);
        importSettings.setAcceptedDateFormat(this.acceptedDateFormat);
        importSettings.setSkipFirstColumnAmount(this.skipFirstColumnAmount);
        return importSettings;
    }
}
