package hr.alphacloud.server.model.command.receipts;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReceiptFilterCommand {
    private Long id;
    private String name;
    private Long companyId;
    private Date dateFrom;
    private Date dateTo;
}
