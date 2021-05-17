package hr.alphacloud.server.model.dto.dashboard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FinancialIndicatorsDTO {

    List<FinancialIndicatorsPropertiesDTO> liquidityIndicatorsList = new ArrayList<>();
    List<FinancialIndicatorsPropertiesDTO> indebtednessIndicatorsList = new ArrayList<>();
    List<FinancialIndicatorsPropertiesDTO> activityIndicatorsList = new ArrayList<>();
    List<FinancialIndicatorsPropertiesDTO> costEffectivenessIndicatorsList = new ArrayList<>();
    List<FinancialIndicatorsPropertiesDTO> profitabilityIndicatorsList = new ArrayList<>();
    List<String> forDate;


    public static FinancialIndicatorsDTO of(FinancialIndicatorsDTO financialIndicatorsDTO) {
        FinancialIndicatorsDTO cmd = new FinancialIndicatorsDTO();
        cmd.liquidityIndicatorsList = financialIndicatorsDTO.liquidityIndicatorsList;
        cmd.indebtednessIndicatorsList = financialIndicatorsDTO.indebtednessIndicatorsList;
        cmd.activityIndicatorsList = financialIndicatorsDTO.activityIndicatorsList;
        cmd.costEffectivenessIndicatorsList = financialIndicatorsDTO.costEffectivenessIndicatorsList;
        cmd.profitabilityIndicatorsList = financialIndicatorsDTO.profitabilityIndicatorsList;
        cmd.forDate = financialIndicatorsDTO.forDate;

        return cmd;
    }

}
