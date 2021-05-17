package hr.alphacloud.server.service.settings.additional_settings;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.settings.additional_settings.AdditionalSettingsFilterCommand;
import hr.alphacloud.server.model.command.settings.additional_settings.AdditionalSettingsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.settings.AdditionalSettingsDTO;

public interface AdditionalSettingsService {

    ApiBaseDTO<AdditionalSettingsDTO> save(ApiBaseCommand<AdditionalSettingsSaveCommand> command);

    ApiBaseDTO<AdditionalSettingsDTO> fetch(ApiBaseCommand<AdditionalSettingsFilterCommand> command);


}
