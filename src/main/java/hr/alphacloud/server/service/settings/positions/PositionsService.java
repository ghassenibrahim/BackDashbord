package hr.alphacloud.server.service.settings.positions;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsDeleteCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsFilerCommand;
import hr.alphacloud.server.model.command.settings.positions.PositionsSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionDTO;

import java.util.List;

public interface PositionsService {

    ApiBaseDTO<List<PositionDTO>> filter(ApiBasePageCommand<PositionsFilerCommand> command);

    ApiBaseDTO<Boolean> savePositionsData(ApiBaseCommand<PositionsSaveCommand> command);

    ApiBaseDTO<Boolean> deletePosition(ApiBaseCommand<PositionsDeleteCommand> command);
}
