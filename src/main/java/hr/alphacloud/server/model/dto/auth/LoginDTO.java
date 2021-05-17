package hr.alphacloud.server.model.dto.auth;

import hr.alphacloud.server.model.dto.account.AccountDTO;
import hr.alphacloud.server.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class LoginDTO {
    private AccountDTO account;
    private String jwtToken;
    private String refreshToken;

    public static LoginDTO formDTO(User user) {
        return LoginDTO.builder()
                .account(AccountDTO.formDTO(user))
                .jwtToken(user.getJwtToken())
                .refreshToken(user.getRefreshToken())
                .build();
    }
}
