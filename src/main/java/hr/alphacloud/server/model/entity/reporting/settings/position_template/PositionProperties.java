package hr.alphacloud.server.model.entity.reporting.settings.position_template;

import lombok.Data;

import java.util.List;

@Data
public class PositionProperties {

    private String name;
    private List<PositionProperties> positionProperties;
    private Integer konto;

}
