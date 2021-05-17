package hr.alphacloud.server.controller.reporting;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.report_validate.ReportValidateBaseDTO;
import hr.alphacloud.server.model.dto.report_validate.quantity_control.QuantityControlDTO;
import hr.alphacloud.server.service.report_validate.ReportValidateService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/validate")
public class ReportValidateController {

    private final ReportValidateService reportValidateService;

    public ReportValidateController(ReportValidateService reportValidateService) {
        this.reportValidateService = reportValidateService;
    }


    @PostMapping("/gross-balance")
    public ApiBaseDTO<ReportValidateBaseDTO> grossBalanceControl(@RequestBody ApiBaseCommand<BaseReportingFilterCommand> command) {
        return reportValidateService.grossBalanceControl(command);
    }

    @PostMapping("/quantity-control")
    public ApiBaseDTO<List<QuantityControlDTO>> quantityControl(@RequestBody ApiBaseCommand<BaseReportingFilterCommand> command) {
        return reportValidateService.quantityControl(command);
    }

}
