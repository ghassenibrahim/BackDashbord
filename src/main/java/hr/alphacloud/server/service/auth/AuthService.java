package hr.alphacloud.server.service.auth;

import hr.alphacloud.server.model.command.auth.LoginCommand;
import hr.alphacloud.server.model.command.auth.RefreshTokenCommand;
import hr.alphacloud.server.model.dto.auth.LoginDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    ApiBaseDTO<LoginDTO> loginUser(LoginCommand loginCommand);

    ApiBaseDTO<LoginDTO> chooseCompany(LoginCommand command, HttpServletRequest request);

    ApiBaseDTO<Boolean> logoutUser();

    ApiBaseDTO<LoginDTO> refreshToken(RefreshTokenCommand refreshTokenCommand);

    ApiBaseDTO<LoginDTO> getLoggedUserData();
}
