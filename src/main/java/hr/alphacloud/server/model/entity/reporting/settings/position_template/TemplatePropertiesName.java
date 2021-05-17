package hr.alphacloud.server.model.entity.reporting.settings.position_template;

import lombok.Data;

import java.util.List;

@Data
public class TemplatePropertiesName {

    private String name;
    private List<TemplatePropertiesName> positionProperties;
    private Integer konto;


}
