package hr.alphacloud.server.util;

import hr.alphacloud.server.model.dto.dashboard.FinancialIndicatorsDTO;
import hr.alphacloud.server.model.dto.dashboard.FinancialIndicatorsPropertiesDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionDTO;
import hr.alphacloud.server.model.entity.reporting.dashboard.FinancialIndicatorProperties;
import hr.alphacloud.server.model.entity.reporting.dashboard.FinancialIndicatorTemplate;
import hr.alphacloud.server.model.entity.reporting.settings.AdditionalSettings;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class FinancialIndicatorUtil {

    public static void calculateFinancialIndicator(AdditionalSettings additionalSettings,
                                                   FinancialIndicatorsDTO financialIndicatorsDTO,
                                                   FinancialIndicatorTemplate financialIndicatorTemplate,
                                                   List<PositionDTO> positionDTO,
                                                   PositionDTO ebitdaPositionsDTO,
                                                   int years) {

//         Liquidity Indicators
        for (int i = 0; i < financialIndicatorTemplate.getLiquidityIndicators().getPropertiesList().size(); i++) {
            var financialIndicatorProperties = financialIndicatorTemplate.getLiquidityIndicators().getPropertiesList().get(i);
            financialIndicatorsDTO.getLiquidityIndicatorsList().add(calculateValues(additionalSettings, financialIndicatorProperties, positionDTO, ebitdaPositionsDTO, years));
        }

        // Indebtdness Indicators
        for (int i = 0; i < financialIndicatorTemplate.getIndebtednessIndicators().getPropertiesList().size(); i++) {
            var financialIndicatorProperties = financialIndicatorTemplate.getIndebtednessIndicators().getPropertiesList().get(i);
            financialIndicatorsDTO.getIndebtednessIndicatorsList().add(calculateValues(additionalSettings, financialIndicatorProperties, positionDTO, ebitdaPositionsDTO, years));
        }

        // Activity Indicators
        for (int i = 0; i < financialIndicatorTemplate.getActivityIndicators().getPropertiesList().size(); i++) {
            var financialIndicatorProperties = financialIndicatorTemplate.getActivityIndicators().getPropertiesList().get(i);
            financialIndicatorsDTO.getActivityIndicatorsList().add(calculateValues(additionalSettings, financialIndicatorProperties, positionDTO, ebitdaPositionsDTO, years));
        }
        FinancialIndicatorsPropertiesDTO FPActivity = new FinancialIndicatorsPropertiesDTO();
        for (int year = 0; year < years; year++) {
            FPActivity.setName("Novčani jaz");
            FPActivity.setPercentage(false);
            FPActivity.getTotalValue()
                    .add(financialIndicatorsDTO.getActivityIndicatorsList().get(6).getTotalValue().get(year)
                            .add(financialIndicatorsDTO.getActivityIndicatorsList().get(4).getTotalValue().get(year))
                            .subtract(financialIndicatorsDTO.getActivityIndicatorsList().get(8).getTotalValue().get(year)));
        }
        financialIndicatorsDTO.getActivityIndicatorsList().add(FPActivity);

        // Cost Effective Indicators
        for (int i = 0; i < financialIndicatorTemplate.getCostEffectivenessIndicators().getPropertiesList().size(); i++) {
            var financialIndicatorProperties = financialIndicatorTemplate.getCostEffectivenessIndicators().getPropertiesList().get(i);
            financialIndicatorsDTO.getCostEffectivenessIndicatorsList().add(calculateValues(additionalSettings, financialIndicatorProperties, positionDTO, ebitdaPositionsDTO, years));
        }
        FinancialIndicatorsPropertiesDTO FPCost = new FinancialIndicatorsPropertiesDTO();
        for (int year = 0; year < years; year++) {
            FPCost.setName("Ekonomičnost poslovnih aktivnosti");
            FPCost.setPercentage(false);
            if (ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year).compareTo(BigDecimal.ZERO) != 0) {
                FPCost.getTotalValue()
                        .add(ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .divide(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year), 2, RoundingMode.HALF_UP));
            } else {
                FPCost.getTotalValue().add(BigDecimal.ZERO);
            }
        }
        financialIndicatorsDTO.getCostEffectivenessIndicatorsList().add(FPCost);

        // Profitability Indicators
        for (int i = 0; i < financialIndicatorTemplate.getProfitabilityIndicators().getPropertiesList().size(); i++) {
            var financialIndicatorProperties = financialIndicatorTemplate.getProfitabilityIndicators().getPropertiesList().get(i);
            financialIndicatorsDTO.getProfitabilityIndicatorsList().add(calculateValues(additionalSettings, financialIndicatorProperties, positionDTO, ebitdaPositionsDTO, years));
        }
    }

    // store value from recursion and give it back to parent method
    public static FinancialIndicatorsPropertiesDTO calculateValues(AdditionalSettings additionalSettings,
                                                                   FinancialIndicatorProperties financialIndicatorProperties,
                                                                   List<PositionDTO> positionDTO,
                                                                   PositionDTO ebitdaPositionsDTO,
                                                                   int years) {

        FinancialIndicatorsPropertiesDTO financialIndicatorsPropertiesDTO = new FinancialIndicatorsPropertiesDTO();
        for (int year = 0; year < years; year++) {
            BigDecimal firstValue = new BigDecimal(BigInteger.ZERO);
            BigDecimal secondValue = new BigDecimal(BigInteger.ZERO);
            BigDecimal thirdValue = new BigDecimal(BigInteger.ZERO);
            BigDecimal fourthValue = new BigDecimal(BigInteger.ZERO);
            List<String> splitAction = new ArrayList<String>();
            List<String> splitLetters = new ArrayList<String>();
            String[] splitLettersTemp = financialIndicatorProperties.getAction().split("[-|\\+|\\*|\\/\\|365\\|100]");
            String[] splitActionTemp = financialIndicatorProperties.getAction().split("[a-zA-Z\\s]");
            for (String data : splitLettersTemp) {
                if (data.length() != 0) {
                    splitLetters.add(data);
                }
            }
            for (String data : splitActionTemp) {
                if (data.length() != 0) {
                    splitAction.add(data);
                }
            }
            var financialTotalValue = financialIndicatorsPropertiesDTO.getTotalValue();

            // assign values
            for (int splitIndex = 0; splitIndex < splitLetters.size(); splitIndex++) {
                if (splitLetters.get(splitIndex).equals("BS")) {
                    var item = positionDTO.stream().filter(carnet -> carnet.getSheetType().equals("balanceSheet")).findFirst().orElse(null);
                    if (splitIndex == 0) {
                        PositionDTO tempPosition = new PositionDTO();
                        firstValue = recursion(item, tempPosition, financialIndicatorProperties.getFirstIndex(), 0, year, firstValue);
                    } else if (splitIndex == 1) {
                        PositionDTO tempPosition = new PositionDTO();
                        secondValue = recursion(item, tempPosition, financialIndicatorProperties.getSecondIndex(), 0, year, secondValue);
                    } else if (splitIndex == 2) {
                        PositionDTO tempPosition = new PositionDTO();
                        thirdValue = recursion(item, tempPosition, financialIndicatorProperties.getThirdIndex(), 0, year, thirdValue);
                    } else if (splitIndex == 3) {
                        PositionDTO tempPosition = new PositionDTO();
                        fourthValue = recursion(item, tempPosition, financialIndicatorProperties.getThirdIndex(), 0, year, fourthValue);
                    }
                } else if (splitLetters.get(splitIndex).equals("PL")) {
                    var item = positionDTO.stream().filter(carnet -> carnet.getSheetType().equals("profitLoss")).findFirst().orElse(null);
                    if (splitIndex == 0) {
                        PositionDTO tempPosition = new PositionDTO();
                        firstValue = recursion(item, tempPosition, financialIndicatorProperties.getFirstIndex(), 0, year, firstValue);
                    } else if (splitIndex == 1) {
                        PositionDTO tempPosition = new PositionDTO();
                        secondValue = recursion(item, tempPosition, financialIndicatorProperties.getSecondIndex(), 0, year, secondValue);
                    } else if (splitIndex == 2) {
                        PositionDTO tempPosition = new PositionDTO();
                        thirdValue = recursion(item, tempPosition, financialIndicatorProperties.getThirdIndex(), 0, year, thirdValue);
                    } else if (splitIndex == 3) {
                        PositionDTO tempPosition = new PositionDTO();
                        fourthValue = recursion(item, tempPosition, financialIndicatorProperties.getThirdIndex(), 0, year, fourthValue);
                    }
                } else if (splitLetters.get(splitIndex).equals("EBITDA")) {
                    if (splitIndex == 0) {
                        firstValue = ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year));
                    } else if (splitIndex == 1) {
                        secondValue = ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year));
                    } else if (splitIndex == 2) {
                        thirdValue = ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year));
                    } else if (splitIndex == 3) {
                        fourthValue = ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year));
                    }
                } else if (splitLetters.get(splitIndex).equals("EBIT")) {
                    if (splitIndex == 0) {
                        firstValue = (ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year)))
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(2).getTotalValue().get(year));
                    } else if (splitIndex == 1) {
                        secondValue = (ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year)))
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(2).getTotalValue().get(year));
                    } else if (splitIndex == 2) {
                        thirdValue = (ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year)))
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(2).getTotalValue().get(year));
                    } else if (splitIndex == 3) {
                        fourthValue = (ebitdaPositionsDTO.getPositionProperties().get(0).getTotalValue().get(year)
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(1).getTotalValue().get(year)))
                                .subtract(ebitdaPositionsDTO.getPositionProperties().get(2).getTotalValue().get(year));
                    }
                } else if (splitLetters.get(splitIndex).equals("INTEREST")) {
                    if (splitIndex == 0) {
                        firstValue = BigDecimal.valueOf(additionalSettings.getPrincipal() + additionalSettings.getInterest());
                    } else if (splitIndex == 1) {
                        secondValue = BigDecimal.valueOf(additionalSettings.getPrincipal() + additionalSettings.getInterest());
                    } else if (splitIndex == 2) {
                        thirdValue = BigDecimal.valueOf(additionalSettings.getPrincipal() + additionalSettings.getInterest());
                    } else if (splitIndex == 3) {
                        fourthValue = BigDecimal.valueOf(additionalSettings.getPrincipal() + additionalSettings.getInterest());
                    }
                } else if (splitLetters.get(splitIndex).equals("RATEPD")) {
                    if (splitIndex == 0) {
                        firstValue = BigDecimal.valueOf(1 - additionalSettings.getRatePd());
                    } else if (splitIndex == 1) {
                        secondValue = BigDecimal.valueOf(1 - additionalSettings.getRatePd());
                    } else if (splitIndex == 2) {
                        thirdValue = BigDecimal.valueOf(1 - additionalSettings.getRatePd());
                    } else if (splitIndex == 3) {
                        fourthValue = BigDecimal.valueOf(1 - additionalSettings.getRatePd());
                    }
                }
            }

            //calculate values
            if (splitAction.size() == 1) {
                if (splitAction.get(0).equals("-")) {
                    financialTotalValue.add(
                            firstValue.subtract(secondValue));
                } else if (splitAction.get(0).equals("+")) {
                    financialTotalValue.add(
                            firstValue.add(secondValue));
                } else if (splitAction.get(0).equals("*")) {
                    financialTotalValue.add(
                            firstValue.multiply(secondValue));
                } else if (splitAction.get(0).equals("/")) {
                    if (secondValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(
                                firstValue.divide(secondValue, 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(BigDecimal.ZERO);
                    }
                } else if (splitAction.get(0).equals("*100/")) {
                    if (secondValue.compareTo(BigDecimal.ZERO) != 0) {
                        BigDecimal oneHundred = new BigDecimal(100);
                        financialTotalValue.add(firstValue.multiply(oneHundred).divide(secondValue, 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(firstValue);
                    }
                }
            } else if (splitAction.size() == 2) {
                if (splitAction.get(0).equals("-") && splitAction.get(1).equals("/")) {
                    if (thirdValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(firstValue.subtract(secondValue).divide(thirdValue, 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(BigDecimal.ZERO);
                    }
                } else if (splitAction.get(0).equals("+") && splitAction.get(1).equals("/")) {
                    if (thirdValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(firstValue.add(secondValue).divide(thirdValue, 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(firstValue);
                    }
                } else if (splitAction.get(0).equals("+") && splitAction.get(1).equals("-")) {
                    financialTotalValue.add(firstValue.add(secondValue).subtract(thirdValue));
                } else if (splitAction.get(0).equals("/(") && splitAction.get(1).equals("+")) {
                    if ((secondValue.add(thirdValue)).compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(firstValue.divide(((secondValue).add(thirdValue)), 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(BigDecimal.ZERO);
                    }
                } else if (splitAction.get(0).equals("/") && splitAction.get(1).equals("+")) {
                    if (secondValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(firstValue.divide(secondValue, 2, RoundingMode.HALF_UP).add(thirdValue));
                    } else {
                        financialTotalValue.add(thirdValue);
                    }
                } else if (splitAction.get(0).equals("*") && splitAction.get(1).equals("/")) {
                    if (thirdValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(firstValue.multiply(secondValue).divide(thirdValue, 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(firstValue);
                    }
                } else if (splitAction.get(0).equals("365/") && splitAction.get(1).equals("/")) {
                    BigDecimal daysInYear = new BigDecimal(365);
                    if (firstValue.compareTo(BigDecimal.ZERO) != 0 && secondValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(daysInYear.divide(firstValue, 2, RoundingMode.HALF_UP).divide(secondValue, 2, RoundingMode.HALF_UP));
                    } else {
                        financialTotalValue.add(BigDecimal.ZERO);
                    }
                }
            } else if (splitAction.size() == 3) {
                if (splitAction.get(0).equals("+") && splitAction.get(1).equals("-") && splitAction.get(1).equals("/")) {
                    if (fourthValue.compareTo(BigDecimal.ZERO) != 0) {
                        financialTotalValue.add(firstValue.add(secondValue).subtract(thirdValue).divide(fourthValue, 2, RoundingMode.HALF_UP));
                    }
                }
            } else {
                // Test this, could be prone to errors, only for single values without calculation
                financialTotalValue.add(firstValue);
            }
        }
        financialIndicatorsPropertiesDTO.setName(financialIndicatorProperties.getName());
        financialIndicatorsPropertiesDTO.setPercentage(financialIndicatorProperties.getPercentage());
        return financialIndicatorsPropertiesDTO;
    }

    // go deep inside nested list and find value of [x,y,z]
    public static BigDecimal recursion(PositionDTO positionDTO, PositionDTO tempPosition, List<Integer> index,
                                       int counter, int year, BigDecimal value) {
        if (counter + 1 < index.size()) {
            tempPosition.setPositionProperties(positionDTO.getPositionProperties().get(index.get(counter)).getPositionProperties());
            positionDTO = tempPosition;
            return recursion(positionDTO, tempPosition, index, ++counter, year, value);
        } else if (index.size() != 0) {
            value = value.add(positionDTO.getPositionProperties().get(index.get(counter)).getTotalValue().get(year));
        }
        return value;
    }

}


