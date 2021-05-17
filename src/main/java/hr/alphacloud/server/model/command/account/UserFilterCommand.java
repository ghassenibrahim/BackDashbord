package hr.alphacloud.server.model.command.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterCommand {
    private Long id;
    private Long companyId;
    private String email;
    private String firstName;
    private String surname;
    private String position;
    private boolean enabled;
}
