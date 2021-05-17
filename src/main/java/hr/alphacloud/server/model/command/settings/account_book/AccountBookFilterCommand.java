package hr.alphacloud.server.model.command.settings.account_book;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountBookFilterCommand {
    @NotNull
    private Long companyId;
    private String name;
    private Long code;
}
