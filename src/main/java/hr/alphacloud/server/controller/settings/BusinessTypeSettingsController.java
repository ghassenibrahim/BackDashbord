package hr.alphacloud.server.controller.settings;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.business_type.BusinessTypeFilterCommand;
import hr.alphacloud.server.model.command.settings.business_type.BusinessTypeSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.BusinessTypeDTO;
import hr.alphacloud.server.service.settings.business_type.BusinessTypeSettingsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings/business")
public class BusinessTypeSettingsController {

    private final BusinessTypeSettingsService businessTypeSettingsService;

    public BusinessTypeSettingsController(BusinessTypeSettingsService businessTypeSettingsService) {
        this.businessTypeSettingsService = businessTypeSettingsService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<BusinessTypeDTO> filter(@RequestBody @Valid ApiBasePageCommand<BusinessTypeFilterCommand> command) {
        return this.businessTypeSettingsService.filter(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<BusinessTypeDTO> save(@RequestBody @Valid ApiBaseCommand<BusinessTypeSaveCommand> command) {
        return this.businessTypeSettingsService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.businessTypeSettingsService.delete(command);
    }
}
