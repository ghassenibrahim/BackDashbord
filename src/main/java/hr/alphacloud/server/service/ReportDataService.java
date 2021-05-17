package hr.alphacloud.server.service;

import hr.alphacloud.server.model.command.ReportDataFilterCommand;
import hr.alphacloud.server.model.command.base.ApiBasePageCommand;
import hr.alphacloud.server.model.dto.ReportSettingsDataDTO;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;

public interface ReportDataService {

    ApiBaseDTO<ReportSettingsDataDTO> getReportSettingsData(ApiBasePageCommand<ReportDataFilterCommand> command);

}
