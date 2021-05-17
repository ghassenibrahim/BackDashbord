package hr.alphacloud.server.model.command.settings.spending_location;

import hr.alphacloud.server.model.entity.reporting.settings.SpendingLocation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SpendingLocationSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    @NotNull
    private String name;

    public SpendingLocation convertToEntity() {
        SpendingLocation spendingLocation = new SpendingLocation();
        spendingLocation.setId(this.id);
        spendingLocation.setCompanyId(this.companyId);
        this.updateEntity(spendingLocation);
        return spendingLocation;
    }

    public void updateEntity(SpendingLocation spendingLocation) {
        spendingLocation.setName(this.name);
    }
}
