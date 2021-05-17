package hr.alphacloud.server.controller.settings;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.premises.PremisesFilterCommand;
import hr.alphacloud.server.model.command.settings.premises.PremisesSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.service.settings.premises.PremisesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/settings/premises")
public class PremisesController {

    private final PremisesService premisesService;

    public PremisesController(PremisesService premisesService) {
        this.premisesService = premisesService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<PremisesDTO> filter(@RequestBody ApiBasePageCommand<PremisesFilterCommand> command) {
        return this.premisesService.filter(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<PremisesDTO> save(@RequestBody ApiBaseCommand<PremisesSaveCommand> command) {
        return this.premisesService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody ApiBaseCommand<DeleteCommand> command) {
        return this.premisesService.delete(command);
    }
}
