package hr.alphacloud.server.controller.settings;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsDeleteCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsFilerCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionDTO;
import hr.alphacloud.server.service.settings.positions.PositionsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/settings/position")
public class PositionsController {

    private final PositionsService positionsService;

    public PositionsController(PositionsService positionsService) {
        this.positionsService = positionsService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<PositionDTO>> filter(@Valid @RequestBody ApiBasePageCommand<PositionsFilerCommand> command) {
        return this.positionsService.filter(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> savePositionsData(@RequestBody ApiBaseCommand<PositionsSaveCommand> command) {
        return this.positionsService.savePositionsData(command);
    }
    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody ApiBaseCommand<PositionsDeleteCommand> command) {
        return this.positionsService.deletePosition(command);
    }
}
