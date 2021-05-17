package hr.alphacloud.server.model.command.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyFilter {
    private Long id;
    private String fullName;
    private String oib;
    private String mbs;
    private String email;
    private String address;
}
