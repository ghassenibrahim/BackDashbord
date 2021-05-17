package hr.alphacloud.server.model.dto;

import hr.alphacloud.server.model.dto.settings.AccountBookDTO;
import hr.alphacloud.server.model.dto.settings.PremisesDTO;
import hr.alphacloud.server.model.dto.settings.SectorTypeDTO;
import hr.alphacloud.server.model.dto.settings.SpendingLocationDTO;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturityProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReceivableMaturityDTO extends AbstractReportDTO implements Serializable {

    private Date dateFrom;
    private Date dateTo;
    private Date dateCompleted;
    private List<ReceivableMaturityProperties> receivableMaturityProperties;

    public static ReceivableMaturityDTO of(ReceivableMaturity receivableMaturity) {
        ReceivableMaturityDTO cmd = new ReceivableMaturityDTO();
        cmd.id = receivableMaturity.getId();
        cmd.dateFrom = receivableMaturity.getDateFrom();
        cmd.dateTo = receivableMaturity.getDateTo();
        cmd.dateCompleted = receivableMaturity.getDateCompleted();
        cmd.receivableMaturityProperties = receivableMaturity.getReceivableMaturityProperties();

        cmd.importType = receivableMaturity.getImportType();
        cmd.report = ReportDTO.of(receivableMaturity.getReport());
        cmd.premises = PremisesDTO.fromEntity(receivableMaturity.getPremises());
        cmd.sectorType = SectorTypeDTO.fromEntity(receivableMaturity.getSectorType());
        cmd.spendingLocation = SpendingLocationDTO.of(receivableMaturity.getSpendingLocation());
        cmd.accountBook = AccountBookDTO.of(receivableMaturity.getAccountBook());
        return cmd;
    }

    public static List<ReceivableMaturityDTO> of(List<ReceivableMaturity> receivableMaturities) {
        return receivableMaturities.stream()
                .map(ReceivableMaturityDTO::of)
                .collect(Collectors.toList());
    }
}
