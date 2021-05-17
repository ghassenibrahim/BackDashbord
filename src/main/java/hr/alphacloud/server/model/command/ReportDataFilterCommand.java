package hr.alphacloud.server.model.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportDataFilterCommand {
    private String locale;
    private Long companyId;
}
