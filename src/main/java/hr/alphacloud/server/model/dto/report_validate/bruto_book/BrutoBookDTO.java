package hr.alphacloud.server.model.dto.report_validate.bruto_book;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class BrutoBookDTO {

    private Integer konto;
    private String kontoName;

    private List<BigDecimal> values;

    public static BrutoBookDTO of(BrutoBook brutoBook) {
        BrutoBookDTO cmd = new BrutoBookDTO();
        cmd.konto = brutoBook.getKonto();
        cmd.kontoName = brutoBook.getKontoName();

        return cmd;
    }

    public static BrutoBookDTO of(Integer konto, String kontoName) {
        BrutoBookDTO cmd = new BrutoBookDTO();
        cmd.konto = konto;
        cmd.kontoName = kontoName;

        return cmd;
    }

    public static BrutoBookDTO of(BrutoBookDTO brutoBook, List<BigDecimal> numberList) {
        BrutoBookDTO cmd = new BrutoBookDTO();
        cmd.konto = brutoBook.getKonto();
        cmd.kontoName = brutoBook.getKontoName();
        cmd.values = numberList;
        return cmd;
    }

    public static List<BrutoBookDTO> of(List<BrutoBook> brutoBookList) {
        return brutoBookList.stream().map(BrutoBookDTO::of).collect(Collectors.toList());
    }

    public static List<BrutoBookDTO> of(Map<BrutoBookDTO, List<BigDecimal>> map) {
        return map.entrySet().stream().map(item -> BrutoBookDTO.of(item.getKey(), item.getValue())).collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        BrutoBookDTO dto = (BrutoBookDTO) o;

        if (this.konto.equals(dto.getKonto())) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + konto.hashCode();
        return result;
    }
}
