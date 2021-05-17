package hr.alphacloud.server.model.command.settings.account_book;

import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AccountBookSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    @NotEmpty
    private String name;
    @NotEmpty
    private Integer code;

    public AccountBook convertToEntity() {
        AccountBook accountBook = new AccountBook();
        accountBook.setId(this.id);
        accountBook.setCompanyId(this.companyId);
        accountBook.setName(this.name);
        accountBook.setCode(this.code);
        this.updateEntity(accountBook);
        return accountBook;
    }

    public void updateEntity(AccountBook accountBook) {
        accountBook.setName(this.name);
        accountBook.setCode(this.code);
    }
}
