package hr.alphacloud.server.model.command.planning;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlanDeleteCommand {
    @NotNull
    private long id;
    @NotNull
    private long companyId;
}
