package hr.alphacloud.server.model.command.settings.import_settings;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class ImportSettingsFilterCommand {
    @NotNull
    private Long companyId;
    private String name;
}
