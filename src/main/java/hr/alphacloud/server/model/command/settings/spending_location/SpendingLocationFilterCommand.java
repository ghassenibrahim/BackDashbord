package hr.alphacloud.server.model.command.settings.spending_location;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SpendingLocationFilterCommand {
    @NotNull
    private Long companyId;
    private String name;
}
