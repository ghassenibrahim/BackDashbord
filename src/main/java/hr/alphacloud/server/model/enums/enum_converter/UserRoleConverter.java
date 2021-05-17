package hr.alphacloud.server.model.enums.enum_converter;

import hr.alphacloud.server.model.enums.UserRole;

import javax.persistence.AttributeConverter;

public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        String userRoleValue = null;
        if (userRole != null) {
            userRoleValue = userRole.getRole();
        }
        return userRoleValue;
    }

    @Override
    public UserRole convertToEntityAttribute(String surveillanceRoleValue) {
        UserRole userRole = null;
        if (surveillanceRoleValue != null) {
            userRole = UserRole.valueOfRole(surveillanceRoleValue);
        }
        return userRole;
    }
}
