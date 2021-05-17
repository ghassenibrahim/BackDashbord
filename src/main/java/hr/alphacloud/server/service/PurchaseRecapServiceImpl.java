package hr.alphacloud.server.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.purchase_recap.PurchaseRecapFilterCommand;
import hr.alphacloud.server.model.command.purchase_recap.PurchaseRecapSaveCommand;
import hr.alphacloud.server.model.dto.PurchaseRecapDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PivotTableDataDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PurchaseRecapPivotDTO;
import hr.alphacloud.server.model.entity.reporting.PurchaseRecap;
import hr.alphacloud.server.model.entity.reporting.PurchaseRecapProperties;
import hr.alphacloud.server.model.entity.reporting.SalesRecap;
import hr.alphacloud.server.model.entity.reporting.SalesRecapProperties;
import hr.alphacloud.server.repository.PurchaseRecapRepository;
import hr.alphacloud.server.repository.SalesRecapRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseRecapServiceImpl implements PurchaseRecapService {

    private final PurchaseRecapRepository purchaseRecapRepository;
    private final SalesRecapRepository salesRecapRepository;


    public PurchaseRecapServiceImpl(PurchaseRecapRepository purchaseRecapRepository, SalesRecapRepository salesRecapRepository) {
        this.purchaseRecapRepository = purchaseRecapRepository;
        this.salesRecapRepository = salesRecapRepository;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<PurchaseRecapDTO>> filterPurchaseRecaps(ApiBasePageCommand<PurchaseRecapFilterCommand> command) {
        List<PurchaseRecap> purchaseRecaps = this.purchaseRecapRepository.findAllByReportId(command.getCommand().getReportId());
        List<PurchaseRecapDTO> purchaseRecapDTOS = new ArrayList<>();
        purchaseRecaps.forEach(item -> purchaseRecapDTOS.add(PurchaseRecapDTO.of(item)));
        return ApiBaseDTO.generateSuccessResponse(purchaseRecapDTOS);
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<PivotTableDataDTO>> filterPivotTable(ApiBaseCommand<PurchaseRecapFilterCommand> command) {
        List<PurchaseRecap> purchaseRecaps = this.purchaseRecapRepository.findPurchaseForPivot(
                command.getCommand().getCompanyId(), command.getCommand().getPreviousYear(), command.getCommand().getCurrentYear());
        List<SalesRecap> salesRecaps = this.salesRecapRepository.findSalesForPivot(
                command.getCommand().getCompanyId(), command.getCommand().getPreviousYear(), command.getCommand().getCurrentYear());

        List<PurchaseRecapPivotDTO> purchaseRecapPivots = new ArrayList<>();
        List<SalesRecapProperties> salesRecapPropertiesList = new ArrayList<>();
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
                salesRecapPropertiesList.addAll(salesRecapProperties);
            }
        });

        purchaseRecaps.forEach(purchaseRecap -> {
            List<PurchaseRecapProperties> purchaseRecapProperties = purchaseRecap.getPurchaseRecapProperties();
            if (purchaseRecapProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(purchaseRecapProperties);
                    purchaseRecapProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                purchaseRecapProperties.forEach(item -> {
                    Iterator<SalesRecapProperties> sales = salesRecapPropertiesList.iterator();
                    while (sales.hasNext()) {
                        SalesRecapProperties sale = sales.next();
                        if (sale.getArticleCode().equals(item.getArticleCode()) && sale.getPartnerName().equals(item.getPartnerName())) {
                            PurchaseRecapPivotDTO purchaseRecapPivot = PurchaseRecapPivotDTO.of(item, sale, purchaseRecap);
                            purchaseRecapPivots.add(purchaseRecapPivot);
                            sales.remove();
                        }
                    }
                    PurchaseRecapPivotDTO purchaseRecapPivot = PurchaseRecapPivotDTO.of(item, purchaseRecap);
                    purchaseRecapPivots.add(purchaseRecapPivot);
                });
            }
        });
        purchaseRecapPivots.forEach(item -> {
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
    public ApiBaseDTO<Boolean> savePurchaseRecap(ApiBaseCommand<PurchaseRecapSaveCommand> command) {
        Optional<PurchaseRecap> optionalPurchaseRecap = purchaseRecapRepository.findById(command.getCommand().getId());
        if (optionalPurchaseRecap.isPresent()) {
            PurchaseRecap purchaseRecap = command.getCommand().convertToEntity();
            purchaseRecap.setImportSettings(optionalPurchaseRecap.get().getImportSettings());
            this.purchaseRecapRepository.save(purchaseRecap);

        } else {
            final PurchaseRecap purchaseRecap = command.getCommand().convertToEntity();
            this.purchaseRecapRepository.save(purchaseRecap);
        }

        return ApiBaseDTO.generateSuccessResponse(true);
    }

    @Override
    public ApiBaseDTO<Boolean> deletePurchaseRecap(ApiBaseCommand<DeleteCommand> command) {
        //TODO remove if delete happens only from report service
        return null;
    }

    @Override
    @Transactional
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(ApiBasePageCommand<PurchaseRecapFilterCommand> command) {
        List<PurchaseRecap> purchaseRecaps = this.purchaseRecapRepository.findAllByReportId(
                command.getCommand().getReportId());
        List<ReportTableDataDTO> reportTableDataListDTO = new ArrayList<>();
        purchaseRecaps.forEach(purchaseRecap -> {
            List<PurchaseRecapProperties> purchaseRecapProperties = purchaseRecap.getPurchaseRecapProperties();
            if (purchaseRecapProperties != null) {
                try {
                    String json = new ObjectMapper().writeValueAsString(purchaseRecapProperties);
                    purchaseRecapProperties = new ObjectMapper().readValue(json, new TypeReference<>() {
                    });
                } catch (Exception ignored) {
                }
                List<List<String>> finalList = new ArrayList<>();
                purchaseRecapProperties.forEach(item -> finalList.add(item.toList()));
                reportTableDataListDTO.add(ReportTableDataDTO.of(purchaseRecap, finalList));
            }
        });
        return ApiBaseDTO.generateSuccessResponse(reportTableDataListDTO);
    }

}
