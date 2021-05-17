package hr.alphacloud.server.controller.settings;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.sector_type.SectorFilterCommand;
import hr.alphacloud.server.model.command.settings.sector_type.SectorSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.service.settings.sector_type.SectorTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/settings/sector")
public class SectorController {

    private final SectorTypeService sectorTypeService;

    public SectorController(SectorTypeService sectorTypeService) {
        this.sectorTypeService = sectorTypeService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<SectorTypeDTO> filter(@RequestBody ApiBasePageCommand<SectorFilterCommand> command) {
        return this.sectorTypeService.filter(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<SectorTypeDTO> save(@RequestBody ApiBaseCommand<SectorSaveCommand> command) {
        return this.sectorTypeService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody ApiBaseCommand<DeleteCommand> command) {
        return this.sectorTypeService.delete(command);
    }
}
