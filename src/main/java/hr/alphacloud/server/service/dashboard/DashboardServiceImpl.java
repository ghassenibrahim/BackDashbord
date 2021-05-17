package hr.alphacloud.server.service.dashboard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.dashboard.BalanceSheetRdgFilterCommand;
import hr.alphacloud.server.model.command.dashboard.MultiChartFilterCommand;
import hr.alphacloud.server.model.command.dashboard.SpinAnalyticsFilterCommand;
import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import hr.alphacloud.server.model.dto.BruttoBalanceDTO;
import hr.alphacloud.server.model.dto.ReceivableMaturityDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.base.ErrorInfo;
import hr.alphacloud.server.model.dto.dashboard.ChartsDTO;
import hr.alphacloud.server.model.dto.dashboard.FinancialIndicatorsDTO;
import hr.alphacloud.server.model.dto.report_validate.bruto_book.BrutoBookDTO;
import hr.alphacloud.server.model.dto.report_validate.bruto_book.BrutoBookReportDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionPropertiesDTO;
import hr.alphacloud.server.model.entity.reporting.BruttoBalanceProperties;
import hr.alphacloud.server.model.entity.reporting.ReceivableMaturity;
import hr.alphacloud.server.model.entity.reporting.dashboard.FinancialIndicatorTemplate;
import hr.alphacloud.server.model.entity.reporting.settings.AdditionalSettings;
import hr.alphacloud.server.model.entity.reporting.settings.position_template.Positions;
import hr.alphacloud.server.model.enums.report.ExcelType;
import hr.alphacloud.server.repository.BruttoBalanceRepository;
import hr.alphacloud.server.repository.ReceivableMaturityRepository;
import hr.alphacloud.server.repository.dashboard.DashboardTextualRepository;
import hr.alphacloud.server.repository.settings.AdditionalSettingsRepository;
import hr.alphacloud.server.repository.settings.FinancialIndicatorTemplateRepository;
import hr.alphacloud.server.repository.settings.PositionsRepository;
import hr.alphacloud.server.specification.DashboardSpecification;
import hr.alphacloud.server.util.DashboardReportUtil;
import hr.alphacloud.server.util.FinancialIndicatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final DashboardTextualRepository dashboardTextualRepository;
    private final BruttoBalanceRepository bruttoBalanceRepository;
    private final PositionsRepository positionsRepository;
    private final FinancialIndicatorTemplateRepository financialIndicatorTemplateRepository;
    private final ReceivableMaturityRepository receivableMaturityRepository;
    private final AdditionalSettingsRepository additionalSettingsRepository;

    public DashboardServiceImpl(DashboardTextualRepository dashboardTextualRepository,
                                BruttoBalanceRepository bruttoBalanceRepository,
                                PositionsRepository positionsRepository,
                                FinancialIndicatorTemplateRepository financialIndicatorTemplateRepository,
                                ReceivableMaturityRepository receivableMaturityRepository, AdditionalSettingsRepository additionalSettingsRepository) {
        this.dashboardTextualRepository = dashboardTextualRepository;
        this.bruttoBalanceRepository = bruttoBalanceRepository;
        this.positionsRepository = positionsRepository;
        this.financialIndicatorTemplateRepository = financialIndicatorTemplateRepository;
        this.receivableMaturityRepository = receivableMaturityRepository;
        this.additionalSettingsRepository = additionalSettingsRepository;
    }

    @Override
    public ApiBaseDTO<List<ReceivableMaturityDTO>> fetchSpinAnalytics(ApiBaseCommand<SpinAnalyticsFilterCommand> command) {
        List<ReceivableMaturity> receivableMaturityList =
                this.receivableMaturityRepository.findAll(DashboardSpecification.getSpinAnalyticFilterSpecification(command.getCommand()));
        return ApiBaseDTO.generateSuccessResponse(ReceivableMaturityDTO.of(receivableMaturityList));
    }

    @Transactional
    public ApiBaseDTO<BrutoBookReportDTO> fetchBrutoBooks(ApiBaseCommand<BaseReportingFilterCommand> command) {
        List<BrutoBookDTO> brutoBook = BrutoBookDTO.of(
                this.dashboardTextualRepository.findDistinctBrutoCodes(command.getCommand().getCompanyId(), command.getCommand().getReportType(),
                        command.getCommand().getDateFrom(), command.getCommand().getDateTo()));
        List<BruttoBalanceDTO> bruttoBalanceList = BruttoBalanceDTO.of(
                this.bruttoBalanceRepository.findBruttoBooks(command.getCommand().getCompanyId(), command.getCommand().getReportType(),
                        command.getCommand().getDateFrom(), command.getCommand().getDateTo()));
        Map<BrutoBookDTO, List<BigDecimal>> map = new HashMap<>();
        for (BrutoBookDTO brutoBookDTO : brutoBook) {
            map.put(brutoBookDTO, new ArrayList<>());
        }
        ObjectMapper mapper = new ObjectMapper();
        List<BrutoBookDTO> data = new ArrayList<>();
        // Loop through balance list to find all the properties (order of loop is important for date!)
        for (int i = 0; i < bruttoBalanceList.size(); i++) {
            List<BruttoBalanceProperties> properties = mapper.convertValue(bruttoBalanceList.get(i).getBruttoBalancePropertiesList(), new TypeReference<>() {
            });
            // Assign value to map key
            for (BruttoBalanceProperties property : properties) {
                if (!property.getKonto().equals(0)) {
                    BrutoBookDTO dto = BrutoBookDTO.of(property.getKonto(), property.getKontoName().replaceAll("^\"|\"$", ""));
                    if (property.getFinishedBalance() != null) {
                        map.get(dto).add(property.getFinishedBalance());
                    } else {
                        map.get(dto).add(BigDecimal.valueOf(0));
                    }
                }
            }
            // Some of the values might night be assigned as there will always be more distinct keys then keys from balanceList.
            // Based on index i, if the key does not have a value on k index, assign 0 to it to preserve the order of the list.
            for (int k = i; k <= i; k++) {
                int finalK = k;
                map.forEach((key, value) -> {
                    List<BigDecimal> tempValues;
                    tempValues = value;
                    if (finalK >= tempValues.size()) {
                        tempValues.add(finalK, BigDecimal.valueOf(0));
                        map.replace(key, tempValues);
                    }
                });
            }
            // Each BrutoBookDto has values from different balanceList, which allows assigment of dates to each list on frontend.
            data = BrutoBookDTO.of(map);
        }
        return ApiBaseDTO.generateSuccessResponse(BrutoBookReportDTO.of(data, bruttoBalanceList));
    }

    @Transactional
    public ApiBaseDTO<List<ChartsDTO>> fetchGenericChartsData(ApiBaseCommand<MultiChartFilterCommand> command) {
        List<ChartsDTO> chartsDTO = new ArrayList<>();
        if (!command.getCommand().getChartColumn().isEmpty()) {
            var excelType = ExcelType.getKey(command.getCommand().getExcelType());
            chartsDTO.addAll(DashboardReportUtil.fetchData(excelType,
                    command.getCommand().getChartColumn(),
                    command.getCommand().getChartRow(),
                    command.getCommand().getChartValue()));
        }
        return ApiBaseDTO.generateSuccessResponse(chartsDTO);
    }

    @Transactional
    public ApiBaseDTO<PositionDTO> fetchBalanceSheet(ApiBaseCommand<BalanceSheetRdgFilterCommand> command) {
        Positions positions = this.positionsRepository.findByCompanyIdAndSheetType(
                command.getCommand().getCompanyId(), command.getCommand().getSheetType());
        // properties
        List<BruttoBalanceDTO> bruttoBalanceList = BruttoBalanceDTO.of(
                this.bruttoBalanceRepository.findBruttoBooks(command.getCommand().getCompanyId(), command.getCommand().getReportType(),
                        command.getCommand().getDateFrom(), command.getCommand().getDateTo()));
        if (bruttoBalanceList.isEmpty()) {
            return ApiBaseDTO.generateErrorResponse(ErrorInfo.NO_DATA);
        }
        // reportDate is used in frontend as columns
        List<String> reportDate = new ArrayList<>();
        bruttoBalanceList.forEach(report -> reportDate.add(report.getReport().getReportDateTo().toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        PositionDTO positionDTO = objectMapper.convertValue(positions,
                new TypeReference<>() {
                });
        // add value to each final konto
        for (int i = 0; i < bruttoBalanceList.size(); i++) {
            List<BruttoBalanceProperties> properties = objectMapper.convertValue(bruttoBalanceList.get(i).getBruttoBalancePropertiesList(), new TypeReference<>() {
            });
            for (BruttoBalanceProperties property : properties) {
                if (!property.getKonto().equals(0)) {
                    if (property.getFinishedBalance() != null) {
                        int finalI = i;
                        positionDTO.getPositionProperties().forEach(item -> fillKontos(property, item, finalI));
                    }
                }
            }
        }
        sumKontos(positionDTO.getPositionProperties(), bruttoBalanceList.size() - 1);
        positionDTO.setForDate(reportDate);

        return ApiBaseDTO.generateSuccessResponse(PositionDTO.of(positionDTO));
    }

    @Transactional
    public ApiBaseDTO<PositionDTO> fetchEbitda(ApiBaseCommand<BalanceSheetRdgFilterCommand> command) {

        Positions positions = this.positionsRepository.findByCompanyIdAndSheetType(
                command.getCommand().getCompanyId(), command.getCommand().getSheetType());
        // properties
        List<BruttoBalanceDTO> bruttoBalanceList = BruttoBalanceDTO.of(
                this.bruttoBalanceRepository.findBruttoBooks(command.getCommand().getCompanyId(), command.getCommand().getReportType(),
                        command.getCommand().getDateFrom(), command.getCommand().getDateTo()));
        // reportDate is used in frontend as columns
        List<String> reportDate = new ArrayList<>();
        bruttoBalanceList.forEach(report -> reportDate.add(report.getReport().getReportDateTo().toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        PositionDTO positionDTO = objectMapper.convertValue(positions,
                new TypeReference<PositionDTO>() {
                });
        positionDTO.getPositionProperties().subList(2, positionDTO.getPositionProperties().size()).clear();
        positionDTO.getPositionProperties().add(2, positionDTO.getPositionProperties().get(1).getPositionProperties().get(3));
        positionDTO.getPositionProperties().get(1).getPositionProperties().subList(3, 4).clear();

        // add value to each final konto
        for (int i = 0; i < bruttoBalanceList.size(); i++) {
            List<BruttoBalanceProperties> properties = objectMapper.convertValue(bruttoBalanceList.get(i).getBruttoBalancePropertiesList(), new TypeReference<>() {
            });
            for (BruttoBalanceProperties property : properties) {
                if (!property.getKonto().equals(0)) {
                    if (property.getFinishedBalance() != null) {
                        int finalI = i;
                        positionDTO.getPositionProperties().forEach(item -> fillKontos(property, item, finalI));
                    }
                }
            }
        }
        sumKontos(positionDTO.getPositionProperties(), bruttoBalanceList.size() - 1);
        positionDTO.setForDate(reportDate);
        return ApiBaseDTO.generateSuccessResponse(PositionDTO.of(positionDTO));
    }

    @Transactional
    public ApiBaseDTO<FinancialIndicatorsDTO> fetchFinancialIndicators(ApiBaseCommand<BalanceSheetRdgFilterCommand> command) {
        List<Positions> positions = this.positionsRepository.findAllByCompanyId(
                command.getCommand().getCompanyId());
        AdditionalSettings additionalSettings = this.additionalSettingsRepository.findByCompanyId(command.getCommand().getCompanyId());
        // properties
        List<BruttoBalanceDTO> bruttoBalanceList = BruttoBalanceDTO.of(
                this.bruttoBalanceRepository.findBruttoBooks(command.getCommand().getCompanyId(), command.getCommand().getReportType(),
                       command.getCommand().getDateFrom(), command.getCommand().getDateTo()));
        FinancialIndicatorTemplate financialIndicatorTemplate = this.financialIndicatorTemplateRepository.findFirstById(1L);
        // reportDate is used in frontend as columns
        List<String> reportDate = new ArrayList<>();
        bruttoBalanceList.forEach(report -> reportDate.add(report.getReport().getReportDateTo().toString()));
        ObjectMapper objectMapper = new ObjectMapper();
        List<PositionDTO> positionDTO = positions.stream().map(item ->
                objectMapper.convertValue(item,
                        new TypeReference<PositionDTO>() {
                        })).collect(Collectors.toList());
        // add value to each final konto
        for (int i = 0; i < bruttoBalanceList.size(); i++) {
            List<BruttoBalanceProperties> properties = objectMapper.convertValue(bruttoBalanceList.get(i).getBruttoBalancePropertiesList(), new TypeReference<>() {
            });
            for (BruttoBalanceProperties property : properties) {
                if (!property.getKonto().equals(0)) {
                    if (property.getFinishedBalance() != null) {
                        int finalI = i;
                        positionDTO.forEach(position -> {
                            if (position.getSheetType().equals("balanceSheet")) {
                                position.getPositionProperties().forEach(item -> {
                                    fillKontos(property, item, finalI);
                                    position.setForDate(reportDate);
                                });
                            }
                            if (position.getSheetType().equals("profitLoss")) {
                                position.getPositionProperties().forEach(item -> {
                                    fillKontos(property, item, finalI);
                                    position.setForDate(reportDate);
                                });
                            }
                        });
                    }
                }
            }
        }
        command.getCommand().setSheetType("profitLoss");
        PositionDTO ebitdaPositionsDTO = this.fetchEbitda(command).getResponse();
        positionDTO.forEach(position -> sumKontos(position.getPositionProperties(), bruttoBalanceList.size() - 1));
        FinancialIndicatorsDTO financialIndicatorsDTO = new FinancialIndicatorsDTO();
        FinancialIndicatorUtil.calculateFinancialIndicator(additionalSettings, financialIndicatorsDTO, financialIndicatorTemplate, positionDTO, ebitdaPositionsDTO, reportDate.size());
        financialIndicatorsDTO.setForDate(reportDate);
        return ApiBaseDTO.generateSuccessResponse(FinancialIndicatorsDTO.of(financialIndicatorsDTO));
    }

    /**
     * fill last child with kontos from bruttoBalanceList
     */
    private void fillKontos(BruttoBalanceProperties property, PositionPropertiesDTO positionProperties, int index) {
        if (positionProperties.getPositionProperties() != null) {
            positionProperties.getPositionProperties().forEach(item -> {
                if (item.getKonto() != null && item.getKonto().equals(property.getKonto())) {
                    item.getTotalValue().add(property.getFinishedBalance());
                } else if (item.getTotalValue().size() < index) {
                    item.getTotalValue().add(BigDecimal.ZERO);
                } else if (positionProperties.getTotalValue().size() <= index) {
                    positionProperties.getTotalValue().add(index, BigDecimal.ZERO);
                }
                fillKontos(property, item, index);
            });
        }
    }

    /**
     * sum final kontos and add values to nested parents
     */
    private void sumKontos(List<PositionPropertiesDTO> array, int index) {
        if (array.size() > 0) {
            for (int i = 0; i < array.size(); i++) {
                if (array.get(i).getPositionProperties().size() > 0) {
                    sumKontos(array.get(i).getPositionProperties(), index);
                    for (int j = array.get(i).getTotalValue().size(); j <= index; j++) {
                        array.get(i).getTotalValue().add(BigDecimal.ZERO);
                    }
                    for (int k = 0; k <= index; k++) {
                        array.get(i).getTotalValue().set(k, sumValues(array.get(i), k).stream().reduce(BigDecimal.ZERO, BigDecimal::add));
                    }
                }
            }
        }
    }

    /**
     * sum total value of child
     */
    private List<BigDecimal> sumValues(PositionPropertiesDTO parent, int index) {
        List<BigDecimal> finalList = new ArrayList<>();
        List<BigDecimal> fillData = new ArrayList<>();
        if (parent.getPositionProperties() != null) {
            for (PositionPropertiesDTO child : parent.getPositionProperties()) {
                if (child.getTotalValue().size() <= index) {
                    child.getTotalValue().add(BigDecimal.ZERO);
                }
                fillData.add(child.getTotalValue().get(index));
            }
        }
        finalList.add(fillData.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
        return finalList;
    }

}
