package hr.alphacloud.server.model.command.settings.additional_settings;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AdditionalSettingsFilterCommand {
    @NotNull
    private Long companyId;
}
