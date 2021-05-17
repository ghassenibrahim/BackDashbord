package hr.alphacloud.server.controller.planning;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.planning.PlanDeleteCommand;
import hr.alphacloud.server.model.command.planning.PlanFilterCommand;
import hr.alphacloud.server.model.command.planning.PlanSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.planning.PlanDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hr.alphacloud.server.service.planning.PlanningService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/planning")
public class PlanningController {

    private final PlanningService planningService;

    public PlanningController(PlanningService planningService) {
        this.planningService = planningService;
    }

    @PostMapping("/save")
    public ApiBaseDTO<PlanDTO> savePlan(@RequestBody @Valid ApiBaseCommand<PlanSaveCommand> command){
        return this.planningService.savePlan(command);
    }

    @PostMapping("/find-plan")
    public ApiBaseDTO<PlanDTO> getPlan(@RequestBody ApiBaseCommand<PlanFilterCommand> command){
        return this.planningService.findPlan(command);
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<PlanDTO> filter(@RequestBody @Valid ApiBasePageCommand<PlanFilterCommand> command){
        return this.planningService.filterPlan(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<PlanDeleteCommand> command){
        return this. planningService.deletePlan(command);
    }
}
