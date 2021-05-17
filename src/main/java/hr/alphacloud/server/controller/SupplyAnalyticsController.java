package hr.alphacloud.server.controller;

import hr.alphacloud.server.model.command.DeleteCommand;
import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.command.supply_analytics.SupplyAnalyticsFilterCommand;
import hr.alphacloud.server.model.command.supply_analytics.SupplyAnalyticsSaveCommand;
import hr.alphacloud.server.model.dto.ReportTableDataDTO;
import hr.alphacloud.server.model.dto.SupplyAnalyticsDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.service.SupplyAnalyticsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/supply-analytics")
public class SupplyAnalyticsController {

    private final SupplyAnalyticsService supplyAnalyticsService;

    public SupplyAnalyticsController(SupplyAnalyticsService supplyAnalyticsService) {
        this.supplyAnalyticsService = supplyAnalyticsService;
    }

    @PostMapping("/filter")
    public ApiBaseDTO<List<SupplyAnalyticsDTO>> filterSupplyAnalytics(@RequestBody @Valid ApiBasePageCommand<SupplyAnalyticsFilterCommand> command) {
        return supplyAnalyticsService.filterSupplyAnalytics(command);
    }

    @PostMapping("/save")
    public ApiBaseDTO<Boolean> saveSupplyAnalytics(@RequestBody @Valid ApiBaseCommand<SupplyAnalyticsSaveCommand> command) {
        return this.supplyAnalyticsService.saveSupplyAnalytic(command);
    }

    @PostMapping("/delete")
    public ApiBaseDTO<Boolean> deleteSupplyAnalytics(@RequestBody @Valid ApiBaseCommand<DeleteCommand> command) {
        return this.supplyAnalyticsService.deleteSupplyAnalytic(command);
    }

    @PostMapping("/table")
    public ApiBaseDTO<List<ReportTableDataDTO>> getDataTable(@RequestBody @Valid ApiBasePageCommand<SupplyAnalyticsFilterCommand> command) {
        return this.supplyAnalyticsService.getDataTable(command);
    }
}
