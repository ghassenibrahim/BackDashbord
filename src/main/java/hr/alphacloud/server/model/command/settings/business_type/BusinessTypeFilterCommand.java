package hr.alphacloud.server.model.command.settings.business_type;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class BusinessTypeFilterCommand {
    @NotNull
    private Long companyId;
    @NotEmpty
    private String locale;
    private String value;
    private boolean importCodeBooks;
}
