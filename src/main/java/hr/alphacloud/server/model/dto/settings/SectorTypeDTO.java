package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class SectorTypeDTO {
    private Long id;
    private String name;
    private Long companyId;

    public static SectorTypeDTO fromEntity(SectorType sectorType) {
        if (sectorType != null) {
            return builder()
                    .id(sectorType.getId())
                    .name(sectorType.getName())
                    .companyId(sectorType.getCompanyId())
                    .build();
        }
        return null;
    }

    public static List<SectorTypeDTO> fromCollection(List<SectorType> sectorTypeList) {
        return sectorTypeList.stream().map(SectorTypeDTO::fromEntity).collect(Collectors.toList());
    }
}
