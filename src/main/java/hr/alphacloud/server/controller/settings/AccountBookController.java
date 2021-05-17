package hr.alphacloud.server.controller.settings;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.account_book.AccountBookFilterCommand;
import hr.alphacloud.server.model.command.settings.account_book.AccountBookSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import hr.alphacloud.server.service.settings.account_book.AccountBookSettingsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/settings/account-book")
public class AccountBookController {

    private final AccountBookSettingsService accountBookSettingsService;

    public AccountBookController(AccountBookSettingsService accountBookSettingsService) {
        this.accountBookSettingsService = accountBookSettingsService;
    }

    @PostMapping("/filter")
    public ApiBasePageDTO<AccountBookDTO> filter(@RequestBody @Valid ApiBasePageCommand<AccountBookFilterCommand> command) {
        return this.accountBookSettingsService.filter(command);
    }

    @PostMapping("/filter-list")
    public ApiBaseDTO<List<AccountBookDTO>> filterList(@RequestBody @Valid ApiBaseCommand<AccountBookFilterCommand> command) {
        return this.accountBookSettingsService.filterList(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<AccountBookDTO> save(@RequestBody @Valid ApiBaseCommand<AccountBookSaveCommand> command) {
        return this.accountBookSettingsService.save(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> delete(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.accountBookSettingsService.delete(command);
    }
}
