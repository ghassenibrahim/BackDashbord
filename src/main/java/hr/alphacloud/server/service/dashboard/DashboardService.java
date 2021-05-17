package hr.alphacloud.server.service.dashboard;

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

import java.util.List;

public interface DashboardService {

    ApiBaseDTO<BrutoBookReportDTO> fetchBrutoBooks(ApiBaseCommand<BaseReportingFilterCommand> command);

    ApiBaseDTO<PositionDTO> fetchBalanceSheet(ApiBaseCommand<BalanceSheetRdgFilterCommand> command);

    ApiBaseDTO<PositionDTO> fetchEbitda(ApiBaseCommand<BalanceSheetRdgFilterCommand> command);

    ApiBaseDTO<FinancialIndicatorsDTO> fetchFinancialIndicators(ApiBaseCommand<BalanceSheetRdgFilterCommand> command);

    ApiBaseDTO<List<ReceivableMaturityDTO>> fetchSpinAnalytics(ApiBaseCommand<SpinAnalyticsFilterCommand> command);

    ApiBaseDTO<List<ChartsDTO>> fetchGenericChartsData(ApiBaseCommand<MultiChartFilterCommand> command);

}
