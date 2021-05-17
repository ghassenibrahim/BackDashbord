package hr.alphacloud.server.model.dto.account;

import hr.alphacloud.server.model.entity.User;
import hr.alphacloud.server.model.enums.UserRole;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AccountDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String locale;
    private Boolean enabled;
    private String position;
    private String phone;
    private String email;
    private String address;
    private CompanyDTO company;
    private Boolean designatedUser;
    private UserRole userRole;

    private List<CompanyDTO> companyList;
    private Long selectedCompanyId;
    private String selectedCompanyName;

    public static AccountDTO formDTO(User account) {
        return AccountDTO
                .builder()
                .id(account.getId())
                .enabled(account.getEnabled())
                .phone(account.getPhone())
                .position(account.getPosition())
                .email(account.getEmail())
                .locale("HR")
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .address(account.getAddress())
                .designatedUser(account.getDesignatedUser())
                .company(CompanyDTO.fromEntity(account.getCompany()))
                .userRole(account.getRole())
                .build();
    }

    public static List<AccountDTO> formAccountsDTO(List<User> accounts) {
        return accounts.stream().map(AccountDTO::formDTO).collect(Collectors.toList());
    }
}
