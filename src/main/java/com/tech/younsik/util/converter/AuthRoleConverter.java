package com.tech.younsik.util.converter;

import com.tech.younsik.constant.AuthConst.Role;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AuthRoleConverter implements AttributeConverter<Role, String> {
    
    @Override
    public String convertToDatabaseColumn(Role attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getDbCode();
    }
    
    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.fromDbCode(dbData);
    }
}
