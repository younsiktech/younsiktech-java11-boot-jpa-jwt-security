package com.tech.younsik.util.converter;

import com.tech.younsik.constant.UserConst;
import com.tech.younsik.constant.UserConst.Gender;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserGenderConverter implements AttributeConverter<UserConst.Gender, String> {

    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        if(attribute==null) {
            return null;
        }
        return attribute.getDbCode();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        return UserConst.Gender.fromDbCode(dbData);
    }
}
