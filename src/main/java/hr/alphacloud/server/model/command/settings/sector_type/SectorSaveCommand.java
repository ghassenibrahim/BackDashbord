package hr.alphacloud.server.model.command.settings.sector_type;

import hr.alphacloud.server.model.entity.reporting.settings.SectorType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SectorSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    @NotNull
    private String name;

    public SectorType convertToEntity() {
        SectorType sectorType = new SectorType();
        sectorType.setId(this.id);
        sectorType.setCompanyId(this.companyId);
        sectorType.setName(this.name);
        return sectorType;
    }
}
