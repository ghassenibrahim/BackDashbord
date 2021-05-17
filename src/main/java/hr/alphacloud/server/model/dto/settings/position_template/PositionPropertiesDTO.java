package hr.alphacloud.server.model.dto.settings.position_template;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
public class PositionPropertiesDTO {

    private String name;
    private List<PositionPropertiesDTO> positionProperties = new ArrayList<>();
    private Integer konto;
    private List<BigDecimal> totalValue = new ArrayList<>();

    public static PositionPropertiesDTO of(PositionPropertiesDTO bilanceList) {
        PositionPropertiesDTO cmd = new PositionPropertiesDTO();
        cmd.name = bilanceList.name;
        cmd.totalValue = bilanceList.getTotalValue();
        if (bilanceList.getKonto() != null) {
            cmd.konto = bilanceList.konto;
        }
        if (bilanceList.getPositionProperties() != null) {
            cmd.positionProperties = PositionPropertiesDTO.of(bilanceList.getPositionProperties());
        }

        return cmd;
    }

    public static List<PositionPropertiesDTO> of(List<PositionPropertiesDTO> bilanceList) {
        return bilanceList.stream().map(PositionPropertiesDTO::of).collect(Collectors.toList());
    }

}
