package hr.alphacloud.server.service.user;

import hr.alphacloud.server.model.command.account.ChangePasswordCommand;
import hr.alphacloud.server.model.command.account.RegisterUserCommand;
import hr.alphacloud.server.model.command.account.UserFilterCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;

public interface UserService {
    ApiBaseDTO save(ApiBaseCommand<RegisterUserCommand> command);

    ApiBaseDTO delete(ApiBaseCommand<UserFilterCommand> command);

    ApiBasePageDTO findAll(ApiBasePageCommand<UserFilterCommand> command);

    ApiBaseDTO findCompany(ApiBaseCommand<UserFilterCommand> command);

    ApiBaseDTO changePassword(ApiBaseCommand<ChangePasswordCommand> command);

    ApiBaseDTO resetPassword(ApiBaseCommand<ChangePasswordCommand> command);

    ApiBaseDTO saveNewPassword(ApiBaseCommand<ChangePasswordCommand> command);

}
