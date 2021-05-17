package hr.alphacloud.server.model.command.account;

import hr.alphacloud.server.model.entity.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCommand {
    private Long id;
    private String shortName;
    private String fullName;
    private String email;
    private String oib;
    private String mbs;
    private String address;
    private List<AccountCommand> accounts = new ArrayList<>();

    public Company updateCompany(Company company) {
        company.setId(id);
        company.setShortName(shortName);
        company.setFullName(fullName);
        company.setOib(oib);
        company.setEmail(email);
        company.setAddress(address);
        company.getAccounts().clear();
        company.setMbs(mbs);
        company.getAccounts().addAll(accounts.stream().map(AccountCommand::createAccount).collect(Collectors.toList()));
        company.getAccounts().forEach(n -> n.setCompany(company));
        return company;
    }

    public Company fromCommand() {
        Company company = new Company();
        return updateCompany(company);
    }

}
