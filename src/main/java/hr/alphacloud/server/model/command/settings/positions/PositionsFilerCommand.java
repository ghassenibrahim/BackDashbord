package hr.alphacloud.server.model.command.settings.positions;

import lombok.Data;

@Data
public class PositionsFilerCommand {
    private Long companyId;
    private String sheetType;
}
