package hr.alphacloud.server.model.dto.report_validate;

import hr.alphacloud.server.model.dto.report_validate.base_control.BaseControlDTO;
import hr.alphacloud.server.model.dto.report_validate.bruto_sales_recap.BrutoSalesRepValidateDTO;
import lombok.Data;

import java.util.List;

@Data
public class ReportValidateBaseDTO {

    private List<BrutoSalesRepValidateDTO> brutoSalesRepValidate;
    private List<BaseControlDTO> expenditureControl;
    private List<BaseControlDTO> obligationsToSuppliersControls;
    private List<BaseControlDTO> customerReceivablesControl;

    public static ReportValidateBaseDTO of(List<BrutoSalesRepValidateDTO> brutoSalesRepValidateList,
                                           List<BaseControlDTO> expenditureControlList,
                                           List<BaseControlDTO> obligationsToSuppliersControlsList,
                                           List<BaseControlDTO> customerReceivablesControlList) {
        ReportValidateBaseDTO cmd = new ReportValidateBaseDTO();
        cmd.brutoSalesRepValidate = brutoSalesRepValidateList;
        cmd.expenditureControl = expenditureControlList;
        cmd.obligationsToSuppliersControls = obligationsToSuppliersControlsList;
        cmd.customerReceivablesControl = customerReceivablesControlList;

        return cmd;
    }

}
