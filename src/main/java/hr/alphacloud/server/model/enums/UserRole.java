package hr.alphacloud.server.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("A"),
    COMPANY_ADMIN("C"),
    USER("U");

    private final String role;

    private static final Map<String, UserRole> BY_USER_ROLE = new HashMap<>();

    static {
        for (UserRole e : values()) {
            BY_USER_ROLE.put(e.role, e);
        }
    }

    public static UserRole valueOfRole(String userRoleValue) {
        return BY_USER_ROLE.get(userRoleValue);
    }
}
