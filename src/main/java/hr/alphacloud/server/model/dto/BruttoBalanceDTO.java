package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.BruttoBalance;
import hr.alphacloud.server.model.entity.reporting.BruttoBalanceProperties;
import hr.alphacloud.server.model.entity.reporting.settings.BusinessType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BruttoBalanceDTO extends AbstractReportDTO implements Serializable {

    List<BruttoBalanceProperties> bruttoBalancePropertiesList;
    BusinessType businessType;


    public static BruttoBalanceDTO of(BruttoBalance bruttoBalance) {
        BruttoBalanceDTO cmd = new BruttoBalanceDTO();
        cmd.bruttoBalancePropertiesList = bruttoBalance.getBruttoBalanceProperties();
        cmd.id = bruttoBalance.getId();
        cmd.report = ReportDTO.of(bruttoBalance.getReport());
        cmd.businessType = bruttoBalance.getBusinessType();
        cmd.premises = PremisesDTO.fromEntity(bruttoBalance.getPremises());
        cmd.spendingLocation = SpendingLocationDTO.of(bruttoBalance.getSpendingLocation());
        cmd.sectorType = SectorTypeDTO.fromEntity(bruttoBalance.getSectorType());

        return cmd;

    }

    public static List<BruttoBalanceDTO> of(List<BruttoBalance> balanceList) {
        return balanceList.stream()
                .map(BruttoBalanceDTO::of)
                .collect(Collectors.toList());
    }


}
