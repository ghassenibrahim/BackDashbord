package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.PurchaseRecap;
import hr.alphacloud.server.model.entity.reporting.PurchaseRecapProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PurchaseRecapDTO extends AbstractReportDTO implements Serializable {

    List<PurchaseRecapProperties> purchaseRecapPropertiesList;


    public static PurchaseRecapDTO of(PurchaseRecap purchaseRecap) {
        PurchaseRecapDTO cmd = new PurchaseRecapDTO();
        cmd.id = purchaseRecap.getId();
        cmd.importType = purchaseRecap.getImportType();
        cmd.report = ReportDTO.of(purchaseRecap.getReport());
        cmd.premises = PremisesDTO.fromEntity(purchaseRecap.getPremises());
        cmd.sectorType = SectorTypeDTO.fromEntity(purchaseRecap.getSectorType());
        cmd.spendingLocation = SpendingLocationDTO.of(purchaseRecap.getSpendingLocation());
        cmd.accountBook = AccountBookDTO.of(purchaseRecap.getAccountBook());
        cmd.purchaseRecapPropertiesList = purchaseRecap.getPurchaseRecapProperties();
        return cmd;
    }

    public static List<PurchaseRecapDTO> of(List<PurchaseRecap> purchaseRecapList) {
        return purchaseRecapList.stream()
                .map(PurchaseRecapDTO::of)
                .collect(Collectors.toList());
    }
}
