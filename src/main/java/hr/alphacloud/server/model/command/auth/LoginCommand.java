package hr.alphacloud.server.model.command.auth;

import hr.alphacloud.server.model.dto.account.CompanyDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class LoginCommand {
    private String email;
    private String password;
    private CompanyDTO company;
}
