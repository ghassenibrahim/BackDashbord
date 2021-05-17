package hr.alphacloud.server.model.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeleteCommand {
    @NotNull
    private Long id;
    @NotNull
    private Long companyId;
}
