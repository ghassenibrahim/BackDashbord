package hr.alphacloud.server.model.command.planning;

import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import hr.alphacloud.server.model.entity.reporting.planning.PlanPeriod;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlanPeriodSaveCommand {
    private Long id;
    @NotNull
    private Long planId;
    @NotNull
    private String planPeriodType;

    public PlanPeriod fromEntity(Plan plan) {
        PlanPeriod period = new PlanPeriod();
        period.setPlan(plan);
        period.setId(this.id);
       // period.setPlanId

        return period;
    }
}
