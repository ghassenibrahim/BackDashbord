package hr.alphacloud.server.controller.dashboard;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.dashboard.BalanceSheetRdgFilterCommand;
import hr.alphacloud.server.model.command.dashboard.MultiChartFilterCommand;
import hr.alphacloud.server.model.command.dashboard.SpinAnalyticsFilterCommand;
import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import hr.alphacloud.server.model.dto.ReceivableMaturityDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.dashboard.ChartsDTO;
import hr.alphacloud.server.model.dto.dashboard.FinancialIndicatorsDTO;
import hr.alphacloud.server.model.dto.report_validate.bruto_book.BrutoBookReportDTO;
import hr.alphacloud.server.model.dto.settings.position_template.PositionDTO;
import hr.alphacloud.server.service.dashboard.DashboardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;


    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }


    @PostMapping("/gross-balance")
    public ApiBaseDTO<BrutoBookReportDTO> fetchBrutoBooks(@RequestBody ApiBaseCommand<BaseReportingFilterCommand> command) {
        return dashboardService.fetchBrutoBooks(command);
    }

    @PostMapping("/balance-profit-loss")
    public ApiBaseDTO<PositionDTO> fetchBalanceSheet(@RequestBody ApiBaseCommand<BalanceSheetRdgFilterCommand> command) {
        return dashboardService.fetchBalanceSheet(command);
    }

    @PostMapping("/ebitda")
    public ApiBaseDTO<PositionDTO> fetchEbitda(@RequestBody ApiBaseCommand<BalanceSheetRdgFilterCommand> command) {
        return dashboardService.fetchEbitda(command);
    }

    @PostMapping("/financial-indicators")
    public ApiBaseDTO<FinancialIndicatorsDTO> fetchFinancialIndicators(@RequestBody ApiBaseCommand<BalanceSheetRdgFilterCommand> command) {
        return dashboardService.fetchFinancialIndicators(command);
    }

    @PostMapping("/spin-analytics")
    public ApiBaseDTO<List<ReceivableMaturityDTO>> fetchSpinAnalytics(@RequestBody ApiBaseCommand<SpinAnalyticsFilterCommand> command) {
        return dashboardService.fetchSpinAnalytics(command);
    }

    @PostMapping("/multi-charts")
    public ApiBaseDTO<List<ChartsDTO>> fetchGenericChartsData(@RequestBody ApiBaseCommand<MultiChartFilterCommand> command) {
        return dashboardService.fetchGenericChartsData(command);
    }
}
