package hr.alphacloud.server.model.command.account;

import hr.alphacloud.server.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanySaveCommand {
    Long id;
    @NotNull
    String fullName;
    @NotNull
    String shortName;
    @NotNull
    String oib;
    @NotNull
    String address;
    @NotNull
    String mbs;
    @NotNull
    String email;
    RegisterUserCommand account;

    public Company toEntity() {
        Company company = new Company();
        company.setId(id);
        company.setFullName(fullName);
        company.setShortName(shortName);
        company.setOib(oib);
        company.setMbs(mbs);
        company.setEmail(email);
        company.setAddress(address);
        return company;
    }
}
