package hr.alphacloud.server.model.command.settings.warehouse_type;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class WarehouseTypeFilterCommand {
    @NotNull
    private Long companyId;
    @NotEmpty
    private String locale;
    private String value;
}
