package hr.alphacloud.server.service.planning;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.planning.PlanDeleteCommand;
import hr.alphacloud.server.model.command.planning.PlanFilterCommand;
import hr.alphacloud.server.model.command.planning.PlanSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.planning.PlanDTO;

public interface PlanningService {

    ApiBasePageDTO<PlanDTO> filterPlan(ApiBasePageCommand<PlanFilterCommand> command);

    ApiBaseDTO<PlanDTO> savePlan(ApiBaseCommand<PlanSaveCommand> command);

    ApiBaseDTO<Boolean> deletePlan(ApiBaseCommand<PlanDeleteCommand> command);

    ApiBaseDTO<PlanDTO> findPlan(ApiBaseCommand<PlanFilterCommand> command);
}
