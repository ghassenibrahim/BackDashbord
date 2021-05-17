package hr.alphacloud.server.model.dto.receipts;

import hr.alphacloud.server.model.entity.reporting.receipts.Receipt;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO implements Serializable {

    private Long id;
    private Long companyId;
    private String name;
    private LocalDate lastUpdate;
    private LocalDateTime addedOn;
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private String customerOib;
    private String paymentMethod;
    private BigDecimal paymentAmount;
    private LocalDate paymentDue;

    public static ReceiptDTO fromEntity(Receipt receipt){

        return builder()
                .id(receipt.getId())
                .companyId(receipt.getCompanyId())
                .name(receipt.getName())
                .lastUpdate(receipt.getLastUpdate())
                .addedOn(receipt.getAddedOn())
                .customerName(receipt.getCustomerName())
                .customerSurname(receipt.getCustomerSurname())
                .customerAddress(receipt.getCustomerAddress())
                .customerOib(receipt.getCustomerOib())
                .paymentMethod(receipt.getPaymentMethod())
                .paymentAmount(receipt.getPaymentAmount())
                .paymentDue(receipt.getPaymentDue())
                .build();
    }

    public static List<ReceiptDTO> of(List<Receipt> receiptList){
        return receiptList.stream()
                .map(ReceiptDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
