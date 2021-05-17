package hr.alphacloud.server.controller;

import hr.alphacloud.server.model.command.account.ChangePasswordCommand;
import hr.alphacloud.server.model.command.account.RegisterUserCommand;
import hr.alphacloud.server.model.command.account.UserFilterCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/find-user")
    public ApiBaseDTO findCompany(@RequestBody ApiBaseCommand<UserFilterCommand> command) {
        return userService.findCompany(command);
    }

    @PostMapping("/find-all")
    public ApiBasePageDTO findAll(@RequestBody ApiBasePageCommand<UserFilterCommand> command) {
        return userService.findAll(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO delete(@RequestBody ApiBaseCommand<UserFilterCommand> command) {
        return userService.delete(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO save(@RequestBody ApiBaseCommand<RegisterUserCommand> command) {
        return userService.save(command);
    }

    @PostMapping("/change-password")
    public ApiBaseDTO changePassword(@RequestBody ApiBaseCommand<ChangePasswordCommand> command) {
        return userService.changePassword(command);
    }

    @PostMapping("/reset-password")
    public ApiBaseDTO resetPassword(@RequestBody ApiBaseCommand<ChangePasswordCommand> command) {
        return userService.resetPassword(command);
    }

    @PostMapping("/new-password")
    public ApiBaseDTO saveNewPassword(@RequestBody ApiBaseCommand<ChangePasswordCommand> command) {
        return userService.saveNewPassword(command);
    }
}
