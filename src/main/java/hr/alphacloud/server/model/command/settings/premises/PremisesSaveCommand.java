package hr.alphacloud.server.model.command.settings.premises;

import hr.alphacloud.server.model.entity.reporting.settings.Premises;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PremisesSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    @NotNull
    private String name;

    public Premises convertToEntity() {
        Premises premises = new Premises();
        premises.setId(this.id);
        premises.setCompanyId(this.companyId);
        premises.setName(this.name);
        return premises;
    }

}
