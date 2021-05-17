package hr.alphacloud.server.controller;

import hr.alphacloud.server.model.command.auth.LoginCommand;
import hr.alphacloud.server.model.command.auth.RefreshTokenCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.dto.auth.LoginDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.service.auth.AuthService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ApiBaseDTO<LoginDTO> loginUser(@RequestBody LoginCommand command) {
        return authService.loginUser(command);
    }

    @GetMapping("logout")
    public ApiBaseDTO<Boolean> logoutUser() {
        return authService.logoutUser();
    }

    @PostMapping("choose-company")
    public ApiBaseDTO<LoginDTO> chooseCompany(@RequestBody LoginCommand command, HttpServletRequest request) {
        return this.authService.chooseCompany(command, request);
    }

    @PostMapping("refresh-token")
    public ApiBaseDTO refreshAuthToken(@RequestBody ApiBaseCommand<RefreshTokenCommand> command) {
        return authService.refreshToken(command.getCommand());
    }

    @GetMapping("logged-user-data")
    public ApiBaseDTO<LoginDTO> getLoggedUserData() {
        return authService.getLoggedUserData();
    }

}
