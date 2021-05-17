package hr.alphacloud.server.model.command.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordCommand {
    private String email;
    private String oldPassword;
    private String newPassword;
    private String token;
    private String confirmPassword;
}
