package hr.alphacloud.server.model.dto.account;

import hr.alphacloud.server.model.entity.Company;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CompanyDTO {
    private Long id;
    private String shortName;
    private String email;
    private String fullName;
    private String mbs;
    private String oib;
    private String address;

    public static CompanyDTO fromEntity(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .fullName(company.getFullName())
                .shortName(company.getShortName())
                .oib(company.getOib())
                .email(company.getEmail())
                .mbs(company.getMbs())
                .address(company.getAddress())
                .build();
    }

    public static List<CompanyDTO> forCompanyDTOAccounts(List<Company> companies) {
        return companies.stream().map(CompanyDTO::fromEntity).collect(Collectors.toList());
    }
}
