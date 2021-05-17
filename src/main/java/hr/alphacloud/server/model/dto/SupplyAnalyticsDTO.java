package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.*;
import hr.alphacloud.server.model.entity.reporting.SupplyAnalytics;
import hr.alphacloud.server.model.entity.reporting.SupplyAnalyticsProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SupplyAnalyticsDTO extends AbstractReportDTO implements Serializable {

    List<SupplyAnalyticsProperties> supplyAnalyticsPropertiesList;
    WarehouseTypeDTO warehouseType;
    BusinessTypeDTO businessType;

    public static SupplyAnalyticsDTO of(SupplyAnalytics supplyAnalytics) {
        SupplyAnalyticsDTO cmd = new SupplyAnalyticsDTO();
        cmd.id = supplyAnalytics.getId();
        cmd.importType = supplyAnalytics.getImportType();
        cmd.report = ReportDTO.of(supplyAnalytics.getReport());
        cmd.premises = PremisesDTO.fromEntity(supplyAnalytics.getPremises());
        cmd.sectorType = SectorTypeDTO.fromEntity(supplyAnalytics.getSectorType());
        cmd.spendingLocation = SpendingLocationDTO.of(supplyAnalytics.getSpendingLocation());
        cmd.warehouseType = WarehouseTypeDTO.of(supplyAnalytics.getWarehouseType());
        cmd.businessType = BusinessTypeDTO.of(supplyAnalytics.getBusinessType());
        cmd.accountBook = AccountBookDTO.of(supplyAnalytics.getAccountBook());
        cmd.supplyAnalyticsPropertiesList = supplyAnalytics.getSupplyAnalyticsProperties();

        return cmd;
    }

    public static List<SupplyAnalyticsDTO> of(List<SupplyAnalytics> supplyAnalyticsList) {
        return supplyAnalyticsList.stream()
                .map(SupplyAnalyticsDTO::of)
                .collect(Collectors.toList());
    }
}
