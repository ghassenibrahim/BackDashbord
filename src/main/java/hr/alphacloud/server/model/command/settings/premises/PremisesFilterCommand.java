package hr.alphacloud.server.model.command.settings.premises;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class PremisesFilterCommand {
    @NotNull
    private Long companyId;
    @NotEmpty
    private String name;
}
