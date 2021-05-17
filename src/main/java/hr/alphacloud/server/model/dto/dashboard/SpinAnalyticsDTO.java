package hr.alphacloud.server.model.dto.dashboard;

import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturityProperties;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class SpinAnalyticsDTO {
    private List<ReceivableMaturityProperties> receivableMaturityPropertiesList;

    public static SpinAnalyticsDTO of(ReceivableMaturity receivableMaturity) {
        SpinAnalyticsDTO cmd = new SpinAnalyticsDTO();
        cmd.receivableMaturityPropertiesList = receivableMaturity.getReceivableMaturityProperties();

        return cmd;
    }

    public static List<SpinAnalyticsDTO> of(List<ReceivableMaturity> receivableMaturities) {
        return receivableMaturities.stream()
                .map(SpinAnalyticsDTO::of)
                .collect(Collectors.toList());
    }

}
