package hr.alphacloud.server.controller.settings;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.spending_location.SpendingLocationFilterCommand;
import hr.alphacloud.server.model.command.settings.spending_location.SpendingLocationSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.service.settings.spending_location.SpendingLocationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings/spending-location")
public class SpendingLocationController {

    private final SpendingLocationService spendingLocationService;

    public SpendingLocationController(SpendingLocationService spendingLocationService) {
        this.spendingLocationService = spendingLocationService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<SpendingLocationDTO> filter(@RequestBody @Valid ApiBasePageCommand<SpendingLocationFilterCommand> command) {
        return this.spendingLocationService.filter(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<SpendingLocationDTO> save(@RequestBody @Valid ApiBaseCommand<SpendingLocationSaveCommand> command) {
        return this.spendingLocationService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.spendingLocationService.delete(command);
    }
}
