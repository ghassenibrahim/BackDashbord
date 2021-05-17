package hr.alphacloud.server.model.dto.planning;

import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.planning.Plan;
import hr.alphacloud.server.model.entity.reporting.planning.PlanPeriod;
import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PlanDTO implements Serializable {
    private Long id;
    private Long companyId;
    private String name;
    private LocalDate lastUpdate;
    private LocalDateTime addedOn;
    private SpendingLocationDTO spendingLocation;
    private List<PlanPeriodDTO> planPeriodList;

    public static PlanDTO fromEntity(Plan plan) {
        PlanDTO data = builder()
                .id(plan.getId())
                .companyId(plan.getCompanyId())
                .name(plan.getName())
                .lastUpdate(plan.getLastUpdate())
                .addedOn(plan.getAddedOn())
                .planPeriodList(PlanPeriodDTO.fromEntityList(plan.getPlanPeriodList()))
                .build();

        if (plan.getSpendingLocation() != null){
            data.setSpendingLocation(SpendingLocationDTO.of(plan.getSpendingLocation()));
        }
/*        if (plan.getPlanPeriodList() != null){
            data.setPlanPeriodList(PlanPeriodDTO.fromEntityList(plan.getPlanPeriodList()));
        }*/

        return data;
    }

    public static List<PlanDTO> of(List<Plan> planList) {
        return planList.stream()
                .map(PlanDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
