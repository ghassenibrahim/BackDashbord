package hr.alphacloud.server.model.command;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class ReportFilterCommand {
    private Long id;
    @NotNull
    private Long companyId;
    private String name;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate addedOn;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate reportDateFrom;
    @NotNull
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate reportDateTo;
    private String reportTypeCode;
}
