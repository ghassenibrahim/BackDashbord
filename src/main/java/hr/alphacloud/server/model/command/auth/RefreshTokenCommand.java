package hr.alphacloud.server.model.command.auth;

import lombok.Data;

@Data
public class RefreshTokenCommand {
    private String refreshToken;
    private String jwtToken;
}
