package hr.alphacloud.server.model.dto.settings.position_template;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PositionDTO {

    private Long id;
    private Long companyId;
    private String sheetType;
    private List<String> forDate;
    private List<PositionPropertiesDTO> positionProperties = new ArrayList<>();

    public static PositionDTO of(PositionDTO bilanceList) {
        PositionDTO cmd = new PositionDTO();
        cmd.id = bilanceList.id;
        cmd.companyId = bilanceList.companyId;
        cmd.sheetType = bilanceList.sheetType;
        cmd.forDate = bilanceList.forDate;
        if (bilanceList.getPositionProperties() != null) {
            cmd.positionProperties = PositionPropertiesDTO.of(bilanceList.getPositionProperties());
        }
        return cmd;
    }

    public static List<PositionDTO> of(List<PositionDTO> bilanceList) {
        return bilanceList.stream().map(PositionDTO::of).collect(Collectors.toList());
    }
}
