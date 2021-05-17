package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class SpendingLocationDTO implements Serializable {
    private Long id;
    private String name;
    private Long companyId;

    public static SpendingLocationDTO of(SpendingLocation spendingLocation) {
        if (spendingLocation != null) {
            return builder()
                    .id(spendingLocation.getId())
                    .name(spendingLocation.getName())
                    .companyId(spendingLocation.getCompanyId())
                    .build();
        }
        return null;

    }

    public static List<SpendingLocationDTO> of(List<SpendingLocation> spendingLocationList) {
        return spendingLocationList.stream()
                .map(SpendingLocationDTO::of)
                .collect(Collectors.toList());
    }
}
