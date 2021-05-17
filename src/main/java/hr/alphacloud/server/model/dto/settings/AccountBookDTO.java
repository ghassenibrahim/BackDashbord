package hr.alphacloud.server.model.dto.settings;

import hr.alphacloud.server.model.entity.reporting.settings.AccountBook;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class AccountBookDTO {
    private Long id;
    private Long companyId;
    private String name;
    private Integer code;

    public static AccountBookDTO of(AccountBook accountBook) {
        if (accountBook != null) {
            return builder()
                    .id(accountBook.getId())
                    .companyId(accountBook.getCompanyId())
                    .name(accountBook.getName())
                    .code(accountBook.getCode())
                    .build();
        }
        return null;
    }

    public static List<AccountBookDTO> of(List<AccountBook> accountBookList) {
        return accountBookList.stream()
                .map(AccountBookDTO::of)
                .collect(Collectors.toList());
    }
}
