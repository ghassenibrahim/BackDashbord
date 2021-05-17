package hr.alphacloud.server.model.command;

import hr.alphacloud.server.model.entity.reporting.settings.ImportSettings;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessCsvCommand {
    private ImportSettings importSettings;
}
