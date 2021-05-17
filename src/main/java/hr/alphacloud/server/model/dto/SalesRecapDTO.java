package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.SalesRecap;
import hr.alphacloud.server.model.entity.reporting.SalesRecapProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SalesRecapDTO extends AbstractReportDTO implements Serializable {

    List<SalesRecapProperties> salesRecapPropertiesList;


    public static SalesRecapDTO of(SalesRecap salesRecap) {
        SalesRecapDTO cmd = new SalesRecapDTO();
        cmd.id = salesRecap.getId();
        cmd.importType = salesRecap.getImportType();
        cmd.report = ReportDTO.of(salesRecap.getReport());
        cmd.premises = PremisesDTO.fromEntity(salesRecap.getPremises());
        cmd.sectorType = SectorTypeDTO.fromEntity(salesRecap.getSectorType());
        cmd.spendingLocation = SpendingLocationDTO.of(salesRecap.getSpendingLocation());
        cmd.accountBook = AccountBookDTO.of(salesRecap.getAccountBook());
        cmd.salesRecapPropertiesList = salesRecap.getSalesRecapProperties();
        return cmd;
    }

    public static List<SalesRecapDTO> of(List<SalesRecap> salesRecapsList) {
        return salesRecapsList.stream()
                .map(SalesRecapDTO::of)
                .collect(Collectors.toList());
    }
}
