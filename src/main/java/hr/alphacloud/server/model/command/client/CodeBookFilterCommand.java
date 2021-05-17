package hr.alphacloud.server.model.command.client;

import hr.alphacloud.server.model.enums.client.CodeBookType;
import lombok.Getter;

@Getter
public class CodeBookFilterCommand {
    private final CodeBookType codeBookType;
    private String value;
    private String code;
    private final String locale;

    public CodeBookFilterCommand(CodeBookType codeBookType, String locale) {
        this.codeBookType = codeBookType;
        this.locale = locale;
    }
}
