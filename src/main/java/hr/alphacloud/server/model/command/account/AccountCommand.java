package hr.alphacloud.server.model.command.account;

import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountCommand {
    private Long id;
    private String firstName;
    private String position;
    private String phone;
    private String lastName;
    private Boolean designatedUser;
    private Boolean enabled;
    private String address;
    CompanyCommand company;


    public User createAccount() {
        User user = new User();
        return updateAccount(user);
    }

    public User updateAccount(User account) {
        account.setEnabled(enabled);
        account.setPhone(phone);
        account.setPosition(position);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setDesignatedUser(designatedUser);
        account.setAddress(address);
        return account;
    }

    public User updateAccount(User account, Company company) {
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setAddress(address);
        account.setCompany(company);
        return account;
    }
}
