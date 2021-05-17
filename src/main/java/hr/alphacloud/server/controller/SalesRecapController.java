package hr.alphacloud.server.controller;


import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.sales_recap.SalesRecapFilterCommand;
import hr.alphacloud.server.model.command.sales_recap.SalesRecapSaveCommand;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.SalesRecapDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.customer_analysis.PivotTableDataDTO;
import hr.alphacloud.server.service.SalesRecapService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/sales-recap")
public class SalesRecapController {

    private final SalesRecapService salesRecapService;

    public SalesRecapController(SalesRecapService salesRecapService) {
        this.salesRecapService = salesRecapService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<SalesRecapDTO>> filterSalesRecaps(@RequestBody @Valid ApiBasePageCommand<SalesRecapFilterCommand> command) {
        return salesRecapService.filterSalesRecaps(command);
    }

    @PostMapping("/pivot-table-filter")
    public ApiBaseDTO<List<PivotTableDataDTO>> filterPivotTable(@RequestBody @Valid ApiBaseCommand<SalesRecapFilterCommand> command) {
        return salesRecapService.filterPivotTable(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> saveSalesRecaps(@RequestBody @Valid ApiBaseCommand<SalesRecapSaveCommand> command) {
        return this.salesRecapService.saveSalesRecap(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> deleteSalesRecaps(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.salesRecapService.deleteSalesRecap(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<SalesRecapFilterCommand> command) {
        return this.salesRecapService.getDataTable(command);
    }
}
