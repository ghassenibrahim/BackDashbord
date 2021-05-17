package hr.alphacloud.server.model.command.receipts;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import hr.alphacloud.server.model.entity.reporting.receipts.Receipt;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ReceiptSaveCommand {
    private Long id;
    @NotNull
    private Long companyId;
    private String name;
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private String customerOib;
    private String paymentMethod;
    private LocalDate paymentDue;
    private BigDecimal paymentAmount;


    private String locale;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate lastUpdate;

    public Receipt convertToEntity() {
        Receipt receipt = new Receipt();
        receipt.setId(this.id);
        receipt.setCompanyId(this.companyId);
        receipt.setName(this.name);
        receipt.setLastUpdate(this.lastUpdate);
        receipt.setCustomerName(this.customerName);
        receipt.setCustomerSurname(this.customerSurname);
        receipt.setCustomerAddress(this.customerAddress);
        receipt.setCustomerOib(this.customerOib);
        receipt.setPaymentMethod(this.paymentMethod);
        receipt.setPaymentDue(this.paymentDue);
        receipt.setPaymentAmount(this.paymentAmount);

        return receipt;
    }
}
