package hr.alphacloud.server.service.settings.account_book;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.settings.account_book.AccountBookFilterCommand;
import hr.alphacloud.server.model.command.settings.account_book.AccountBookSaveCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ApiBasePageDTO;
import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountBookSettingsService {

    ApiBaseDTO<AccountBookDTO> save(ApiBaseCommand<AccountBookSaveCommand> command);

    ApiBaseDTO<Boolean> delete(ApiBaseCommand<DeleteCommand> command);

    ApiBasePageDTO<AccountBookDTO> filter(ApiBasePageCommand<AccountBookFilterCommand> command);

    ApiBaseDTO<List<AccountBookDTO>> filterList(ApiBaseCommand<AccountBookFilterCommand> companyId);

    List<AccountBookDTO> filterList(Long companyId, Pageable pageable);

}