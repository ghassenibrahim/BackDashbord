package hr.alphacloud.server.service.settings.warehouse_type;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.warehouse_type.WarehouseTypeFilterCommand;
import hr.alphacloud.server.model.command.settings.warehouse_type.WarehouseTypeSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.WarehouseTypeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WarehouseTypeService {

    ApiBasePageDTO<WarehouseTypeDTO> filter(ApiBasePageCommand<WarehouseTypeFilterCommand> command);

    List<WarehouseTypeDTO> filter(Long companyId, String locale, Pageable pageable);

    ApiBaseDTO<WarehouseTypeDTO> save(ApiBaseCommand<WarehouseTypeSaveCommand> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command);
}
