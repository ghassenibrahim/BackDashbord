package hr.alphacloud.server.controller;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.purchase_recap.PurchaseRecapFilterCommand;
import hr.alphacloud.server.model.command.purchase_recap.PurchaseRecapSaveCommand;
import hr.alphacloud.server.model.dto.PurchaseRecapDTO;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PivotTableDataDTO;
import hr.alphacloud.server.service.PurchaseRecapService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/purchase-recap")
public class PurchaseRecapController {

    private final PurchaseRecapService purchaseRecapService;

    public PurchaseRecapController(PurchaseRecapService purchaseRecapService) {
        this.purchaseRecapService = purchaseRecapService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<PurchaseRecapDTO>> filterPurchaseRecaps(@RequestBody @Valid ApiBasePageCommand<PurchaseRecapFilterCommand> command) {
        return purchaseRecapService.filterPurchaseRecaps(command);
    }

    @PostMapping("/pivot-table-filter")
    public ApiBaseDTO<List<PivotTableDataDTO>> filterPivotTable(@RequestBody @Valid ApiBaseCommand<PurchaseRecapFilterCommand> command) {
        return purchaseRecapService.filterPivotTable(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> savePurchaseRecaps(@RequestBody @Valid ApiBaseCommand<PurchaseRecapSaveCommand> command) {
        return this.purchaseRecapService.savePurchaseRecap(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> deletePurchaseRecaps(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.purchaseRecapService.deletePurchaseRecap(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<PurchaseRecapFilterCommand> command) {
        return this.purchaseRecapService.getDataTable(command);
    }
}
