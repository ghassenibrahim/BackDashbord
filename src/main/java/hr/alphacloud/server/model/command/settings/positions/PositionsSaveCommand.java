package hr.alphacloud.server.model.command.settings.positions;

import hr.alphacloud.server.model.entity.reporting.settings.position_template.Positions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionsSaveCommand {

    private Positions positions;

}