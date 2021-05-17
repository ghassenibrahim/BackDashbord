package hr.alphacloud.server.model.command.planning;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import hr.alphacloud.server.model.entity.reporting.planning.PlanPeriod;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlanSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    private SpendingLocation spendingLocation;
    private String name;
    private String locale;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate lastUpdate;
    private List<PlanPeriod> planPeriodList;

    public Plan convertToEntity() {
        Plan plan = new Plan();
        plan.setId(this.id);
        plan.setCompanyId(this.companyId);
        plan.setName(this.name);
        plan.setLastUpdate(this.lastUpdate);
        plan.setSpendingLocation(this.spendingLocation);
        plan.setPlanPeriodList(this.planPeriodList);
/*
        if (plan.getPlanPeriodList() != null) {
            this.planPeriodList.forEach(item -> item.setPlan(plan));
            plan.setPlanPeriodList(this.planPeriodList);
        }*/

        // List<PlanPeriod> planPeriodList = new ArrayList<>();

/*        for (int i = 0; i < 29; i++) {
            PlanPeriod period = new PlanPeriod();
            period.setPlan(plan);
            planPeriodList.add(period);
        }*/
/*

        if (this.planPeriodList == null){
            plan.getPlanPeriodList().forEach(item -> {
                item.setPlan(plan);
            });
            plan.setPlanPeriodList(this.planPeriodList);

        }*/

/*       if (this.planPeriodList != null){
            plan.getPlanPeriodList().forEach(item -> {
                item.setPlan(plan.getId());
                item.setPlanPeriodType();

            } );
        }*/
        return plan;
    }
}
