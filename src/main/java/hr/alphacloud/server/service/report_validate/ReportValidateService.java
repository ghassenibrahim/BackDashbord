package hr.alphacloud.server.service.report_validate;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.report_validate.ReportValidateBaseDTO;
import hr.alphacloud.server.model.dto.report_validate.quantity_control.QuantityControlDTO;

import java.util.List;

public interface ReportValidateService {

    ApiBaseDTO<ReportValidateBaseDTO> grossBalanceControl(ApiBaseCommand<BaseReportingFilterCommand> command);

    ApiBaseDTO<List<QuantityControlDTO>> quantityControl(ApiBaseCommand<BaseReportingFilterCommand> command);

}
