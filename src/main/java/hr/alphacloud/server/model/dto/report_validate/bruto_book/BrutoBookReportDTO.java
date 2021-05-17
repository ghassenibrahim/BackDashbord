package hr.alphacloud.server.model.dto.report_validate.bruto_book;

import hr.alphacloud.server.model.dto.BruttoBalanceDTO;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class BrutoBookReportDTO {

    private List<String> forDate;
    private List<BrutoBookDTO> data;

    public static BrutoBookReportDTO of(List<BrutoBookDTO> book, List<BruttoBalanceDTO> bruto) {
        BrutoBookReportDTO cmd = new BrutoBookReportDTO();
        cmd.forDate = bruto.stream().map(date -> date.getReport().getReportDateTo()
                .toString()).collect(Collectors.toList());
        cmd.data = book;

        return cmd;
    }
}

