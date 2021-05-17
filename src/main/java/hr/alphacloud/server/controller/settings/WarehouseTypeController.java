package hr.alphacloud.server.controller.settings;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.warehouse_type.WarehouseTypeFilterCommand;
import hr.alphacloud.server.model.command.settings.warehouse_type.WarehouseTypeSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.WarehouseTypeDTO;
import hr.alphacloud.server.service.settings.warehouse_type.WarehouseTypeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/settings/warehouse")
public class WarehouseTypeController {

    private final WarehouseTypeService warehouseTypeService;

    public WarehouseTypeController(WarehouseTypeService warehouseTypeService) {
        this.warehouseTypeService = warehouseTypeService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<WarehouseTypeDTO> filter(@RequestBody @Valid ApiBasePageCommand<WarehouseTypeFilterCommand> command) {
        return this.warehouseTypeService.filter(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<WarehouseTypeDTO> save(@RequestBody @Valid ApiBaseCommand<WarehouseTypeSaveCommand> command) {
        return this.warehouseTypeService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.warehouseTypeService.delete(command);
    }
}
