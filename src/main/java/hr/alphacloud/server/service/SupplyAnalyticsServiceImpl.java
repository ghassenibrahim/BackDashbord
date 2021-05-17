package hr.alphacloud.server.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.supply_analytics.SupplyAnalyticsFilterCommand;
import hr.alphacloud.server.model.command.supply_analytics.SupplyAnalyticsSaveCommand;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.SupplyAnalyticsDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.entity.reporting.SupplyAnalytics;
import hr.alphacloud.server.model.entity.reporting.SupplyAnalyticsProperties;
import hr.alphacloud.server.repository.SupplyAnalyticsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SupplyAnalyticsServiceImpl implements SupplyAnalyticsService {

    private final SupplyAnalyticsRepository supplyAnalyticsRepository;

    public SupplyAnalyticsServiceImpl(SupplyAnalyticsRepository supplyAnalyticsRepository) {
        this.supplyAnalyticsRepository = supplyAnalyticsRepository;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<SupplyAnalyticsDTO>> filterSupplyAnalytics(ApiBasePageCommand<SupplyAnalyticsFilterCommand> command) {
        List<SupplyAnalytics> supplyAnalytics = this.supplyAnalyticsRepository.findAllByReportId(command.getCommand().getReportId());
        List<SupplyAnalyticsDTO> supplyAnalyticsDTOS = new ArrayList<>();
        supplyAnalytics.forEach(item -> {
            supplyAnalyticsDTOS.add(SupplyAnalyticsDTO.of(item));
        });

        return ApiBaseDTO.generateSuccessResponse(supplyAnalyticsDTOS);
    }

    @Override
    @Transactional
    public ApiBaseDTO<Boolean> saveSupplyAnalytic(ApiBaseCommand<SupplyAnalyticsSaveCommand> command) {
        Optional<SupplyAnalytics> optionalSupplyAnalytics = this.supplyAnalyticsRepository.findById(command.getCommand().getId());
        if (optionalSupplyAnalytics.isPresent()) {
            SupplyAnalytics supplyAnalytics = command.getCommand().convertToEntity();
            supplyAnalytics.setImportSettings(optionalSupplyAnalytics.get().getImportSettings());
            this.supplyAnalyticsRepository.save(supplyAnalytics);

        } else {
            final SupplyAnalytics supplyAnalytics = command.getCommand().convertToEntity();
            this.supplyAnalyticsRepository.save(supplyAnalytics);
        }

        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBaseDTO<Boolean> deleteSupplyAnalytic(ApiBaseCommand<DeleteCommand> command) {
        //TODO remove if delete happens only from report service
        return null;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<SupplyAnalyticsFilterCommand> command) {
        List<SupplyAnalytics> supplyAnalyticsList = this.supplyAnalyticsRepository.findAllByReportId(
                command.getCommand().getReportId());
        List<ReportTableDataDTO> reportTableDataListDTO = new ArrayList<>();
        supplyAnalyticsList.forEach(supplyAnalytics -> {
            List<SupplyAnalyticsProperties> supplyAnalyticsProperties = supplyAnalytics.getSupplyAnalyticsProperties();
            if (supplyAnalyticsProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(supplyAnalyticsProperties);
                    supplyAnalyticsProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                List<List<String>> finalList = new ArrayList<>();
                supplyAnalyticsProperties.forEach(item -> {
                    finalList.add(item.toList());
                });
                reportTableDataListDTO.add(ReportTableDataDTO.of(supplyAnalytics, finalList));
            }
        });
        return ApiBaseDTO.generateSuccessResponse(reportTableDataListDTO);
    }

}

