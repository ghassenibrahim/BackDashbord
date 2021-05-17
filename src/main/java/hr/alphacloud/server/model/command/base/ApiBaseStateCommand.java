package hr.alphacloud.server.model.command.base;

import hr.alphacloud.server.model.dto.account.AccountDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiBaseStateCommand {
    AccountDTO account;
}
