package hr.alphacloud.server.model.dto.report_validate.bruto_supply;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BrutoSupplyValidateDTO {

    private Long brutoId;
    private Long supplyId;
    private SupplyInitBalanceDTO supplyInitBalance;
    private SupplyFinalBalanceDTO supplyFinalBalance;

    public static BrutoSupplyValidateDTO of(BrutoSupplyValidate brutoSupplyValidate) {
        BrutoSupplyValidateDTO cmd = new BrutoSupplyValidateDTO();
        cmd.brutoId = brutoSupplyValidate.getBrutoId();
        cmd.supplyId = brutoSupplyValidate.getSupplyId();
        cmd.supplyInitBalance = SupplyInitBalanceDTO.of(brutoSupplyValidate);
        cmd.supplyFinalBalance = SupplyFinalBalanceDTO.of(brutoSupplyValidate);

        return cmd;
    }

    public static List<BrutoSupplyValidateDTO> of(List<BrutoSupplyValidate> brutoSupplyValidateList) {
        return brutoSupplyValidateList.stream()
                .map(BrutoSupplyValidateDTO::of)
                .collect(Collectors.toList());
    }
}
