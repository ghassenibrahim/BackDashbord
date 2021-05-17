package hr.alphacloud.server.model.command.settings.positions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionsDeleteCommand {
    private Long companyId;
    private String sheetType;
    private String position;
}
