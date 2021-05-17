package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class PremisesDTO {
    private Long id;
    private String name;
    private Long companyId;

    public static PremisesDTO fromEntity(Premises premises) {
        if (premises != null) {
            return builder()
                    .id(premises.getId())
                    .name(premises.getName())
                    .companyId(premises.getCompanyId())
                    .build();
        }
        return null;
    }

    public static List<PremisesDTO> fromCollection(List<Premises> premisesList) {
        return premisesList.stream().map(PremisesDTO::fromEntity).collect(Collectors.toList());
    }
}
