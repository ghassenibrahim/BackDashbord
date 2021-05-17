package hr.alphacloud.server.model.command.receipts;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ReceiptDeleteCommand {
    @NotNull
    private long id;
    @NotNull
    private long companyId;
}
