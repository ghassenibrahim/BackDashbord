package hr.alphacloud.server.service.settings.sector_type;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.sector_type.SectorFilterCommand;
import hr.alphacloud.server.model.command.settings.sector_type.SectorSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SectorTypeService {

    ApiBaseDTO<SectorTypeDTO> save(ApiBaseCommand<SectorSaveCommand> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command);

    ApiBasePageDTO<SectorTypeDTO> filter(ApiBasePageCommand<SectorFilterCommand> command);

    List<SectorTypeDTO> filter(Long companyId, Pageable pageable);
}
