package hr.alphacloud.server.service.report_validate;

import hr.alphacloud.server.model.command.base.ApiBaseCommand;
import hr.alphacloud.server.model.command.validate.BaseReportingFilterCommand;
import hr.alphacloud.server.model.dto.base.ApiBaseDTO;
import hr.alphacloud.server.model.dto.report_validate.ReportValidateBaseDTO;
import hr.alphacloud.server.model.dto.report_validate.base_control.BaseControlDTO;
import hr.alphacloud.server.model.dto.report_validate.bruto_sales_recap.BrutoSalesRepValidateDTO;
import hr.alphacloud.server.model.dto.report_validate.quantity_control.QuantityControlDTO;
import hr.alphacloud.server.repository.report_validate.ReportValidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportValidateServiceImpl implements ReportValidateService {

    private final ReportValidateRepository reportValidateRepository;

    public ReportValidateServiceImpl(ReportValidateRepository reportValidateRepository) {
        this.reportValidateRepository = reportValidateRepository;
    }

    /**
     * Control for bruto balance -> supply analytics, sale recap, customer and suppliers with returns included.
     */
    @Transactional
    public ApiBaseDTO<ReportValidateBaseDTO> grossBalanceControl(ApiBaseCommand<BaseReportingFilterCommand> command) {
        return ApiBaseDTO.generateSuccessResponse(ReportValidateBaseDTO.of(
                BrutoSalesRepValidateDTO.of(this.reportValidateRepository.validateBrutoSalesRecap(command.getCommand().getReportId())),
                BaseControlDTO.of(this.reportValidateRepository.validateExpenditureControl(command.getCommand().getReportId())),
                BaseControlDTO.of(this.reportValidateRepository.validateObligationsToSuppliersControl(command.getCommand().getReportId())),
                BaseControlDTO.of(this.reportValidateRepository.validateCustomerReceivablesControl(command.getCommand().getReportId()))));
    }

    /**
     * Main control sheet
     */
    @Transactional
    public ApiBaseDTO<List<QuantityControlDTO>> quantityControl(ApiBaseCommand<BaseReportingFilterCommand> command) {
        return ApiBaseDTO.generateSuccessResponse(
                QuantityControlDTO.of(this.reportValidateRepository.validateQuantityControl(command.getCommand().getReportId())));
    }


}
