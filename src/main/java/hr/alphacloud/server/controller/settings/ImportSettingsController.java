package hr.alphacloud.server.controller.settings;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.import_settings.ImportSettingsFilterCommand;
import hr.alphacloud.server.model.command.settings.import_settings.ImportSettingsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.ImportSettingsDTO;
import hr.alphacloud.server.service.settings.import_settings.ImportSettingsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings/import")
public class ImportSettingsController {

    private final ImportSettingsService importSettingsService;

    public ImportSettingsController(ImportSettingsService importSettingsService) {
        this.importSettingsService = importSettingsService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<ImportSettingsDTO> filter(@Valid @RequestBody ApiBasePageCommand<ImportSettingsFilterCommand> filterCommand) {
        return this.importSettingsService.filter(filterCommand);
    }

    @PostMapping("/save")
    public ApiBaseDTO<ImportSettingsDTO> save(@Valid @RequestBody ApiBaseCommand<ImportSettingsSaveCommand> saveCommand) {
        return this.importSettingsService.save(saveCommand);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@Valid @RequestBody ApiBaseCommand<DeleteCommand> deleteCommand) {
        return this.importSettingsService.delete(deleteCommand);
    }
}
