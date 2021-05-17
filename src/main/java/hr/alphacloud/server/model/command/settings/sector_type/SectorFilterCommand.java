package hr.alphacloud.server.model.command.settings.sector_type;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SectorFilterCommand {
    @NotNull
    private Long companyId;
    @NotEmpty
    private String name;
}
