package hr.alphacloud.server.controller.settings;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.additional_settings.AdditionalSettingsFilterCommand;
import hr.alphacloud.server.model.command.settings.additional_settings.AdditionalSettingsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.settings.AdditionalSettingsDTO;
import hr.alphacloud.server.service.settings.additional_settings.AdditionalSettingsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/settings/additional-settings")
public class AdditionalSettingsController {

    private final AdditionalSettingsService additionalSettingsService;

    public AdditionalSettingsController(AdditionalSettingsService additionalSettingsService) {
        this.additionalSettingsService = additionalSettingsService;
    }

    @PostMapping("/save")
    public ApiBaseDTO<AdditionalSettingsDTO> save(@RequestBody @Valid ApiBaseCommand<AdditionalSettingsSaveCommand> command) {
        return this.additionalSettingsService.save(command);
    }

    @PostMapping("/filter")
    public ApiBaseDTO<AdditionalSettingsDTO> filter(@RequestBody @Valid ApiBasePageCommand<AdditionalSettingsFilterCommand> command) {
        return this.additionalSettingsService.fetch(command);
    }
}
