package hr.alphacloud.server.model.command.account;

import hr.alphacloud.server.model.entity.Company;
import hr.alphacloud.server.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterUserCommand {
    private Long id;
    private Long companyId;
    private String email;
    private String firstName;
    private String lastName;
    private Boolean enabled;
    private String position;
    private String phone;
    private Boolean designatedUser;

    public User formUser(Company company) {
        User user = new User();
        if (email != null) {
            user.setEmail(email);
        }
        user.setEnabled(enabled);
        user.setPosition(position);
        user.setPhone(phone);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setDesignatedUser(designatedUser);
        user.setCompany(company);
        return user;
    }

    public User updateUser(Company company, User user) {
        if (email != null) {
            user.setEmail(email);
        }
        user.setEnabled(enabled);
        user.setPosition(position);
        user.setPhone(phone);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setDesignatedUser(designatedUser);
        user.setCompany(company);
        return user;
    }
}
