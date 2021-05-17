package hr.alphacloud.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.sales_recap.SalesRecapFilterCommand;
import hr.alphacloud.server.model.command.sales_recap.SalesRecapSaveCommand;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.SalesRecapDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PivotTableDataDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.SalesRecapPivotDTO;
import hr.alphacloud.server.model.entity.reporting.SalesRecap;
import hr.alphacloud.server.model.entity.reporting.SalesRecapProperties;
import hr.alphacloud.server.repository.SalesRecapRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalesRecapServiceImpl implements SalesRecapService {

    private final SalesRecapRepository salesRecapRepository;

    public SalesRecapServiceImpl(SalesRecapRepository salesRecapRepository) {
        this.salesRecapRepository = salesRecapRepository;
    }


    @Override
    @Transactional
    public ApiBaseDTO<List<SalesRecapDTO>> filterSalesRecaps(ApiBasePageCommand<SalesRecapFilterCommand> command) {
        List<SalesRecap> salesRecaps = this.salesRecapRepository.findAllByReportId(command.getCommand().getReportId());
        List<SalesRecapDTO> salesRecapDTOS = new ArrayList<>();
        salesRecaps.forEach(item -> salesRecapDTOS.add(SalesRecapDTO.of(item)));
        return ApiBaseDTO.generateSuccessResponse(salesRecapDTOS);
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<PivotTableDataDTO>> filterPivotTable(ApiBaseCommand<SalesRecapFilterCommand> command) {
        List<SalesRecap> salesRecaps = this.salesRecapRepository.findSalesForPivot(
                command.getCommand().getCompanyId(), command.getCommand().getPreviousYear(), command.getCommand().getCurrentYear());
        List<SalesRecapPivotDTO> salesRecapPivotDTOS = new ArrayList<>();
        List<PivotTableDataDTO> pivotTableData = new ArrayList<>();
        salesRecaps.forEach(salesRecap -> {
            List<SalesRecapProperties> salesRecapProperties = salesRecap.getSalesRecapProperties();
            if (salesRecapProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(salesRecapProperties);
                    salesRecapProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                salesRecapProperties.forEach(item -> {
                    SalesRecapPivotDTO salesRecapPivotDTO = SalesRecapPivotDTO.of(item, salesRecap);
                    salesRecapPivotDTOS.add(salesRecapPivotDTO);
                });
            }
        });
        salesRecapPivotDTOS.forEach(item -> {
            Field[] fields = item.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("date") && !field.getName().equals("partnerName")
                        && !field.getName().equals("articleName") && !field.getName().equals("dateByYear")) {
                    PivotTableDataDTO pivotTableDataDTO = PivotTableDataDTO.of(item, field);
                    pivotTableData.add(pivotTableDataDTO);
                }
            }
        });
        return ApiBaseDTO.generateSuccessResponse(pivotTableData);
    }

    @Override
    @Transactional
    public ApiBaseDTO<Boolean> saveSalesRecap(ApiBaseCommand<SalesRecapSaveCommand> command) {
        Optional<SalesRecap> optionalSalesRecap = salesRecapRepository.findById(command.getCommand().getId());
        if (optionalSalesRecap.isPresent()) {
            SalesRecap salesRecap = command.getCommand().convertToEntity();
            salesRecap.setImportSettings(optionalSalesRecap.get().getImportSettings());
            this.salesRecapRepository.save(salesRecap);

        } else {
            final SalesRecap salesRecap = command.getCommand().convertToEntity();
            this.salesRecapRepository.save(salesRecap);
        }

        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBaseDTO<Boolean> deleteSalesRecap(ApiBaseCommand<DeleteCommand> command) {
        //TODO remove if delete happens only from report service
        return null;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<SalesRecapFilterCommand> command) {
        List<SalesRecap> salesRecaps = this.salesRecapRepository.findAllByReportId(
                command.getCommand().getReportId());
        List<ReportTableDataDTO> reportTableDataListDTO = new ArrayList<>();
        salesRecaps.forEach(salesRecap -> {
            List<SalesRecapProperties> salesRecapProperties = salesRecap.getSalesRecapProperties();
            if (salesRecapProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(salesRecapProperties);
                    salesRecapProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                List<List<String>> finalList = new ArrayList<>();
                salesRecapProperties.forEach(item -> finalList.add(item.toList()));
                reportTableDataListDTO.add(ReportTableDataDTO.of(salesRecap, finalList));
            }
        });
        return ApiBaseDTO.generateSuccessResponse(reportTableDataListDTO);
    }

}
