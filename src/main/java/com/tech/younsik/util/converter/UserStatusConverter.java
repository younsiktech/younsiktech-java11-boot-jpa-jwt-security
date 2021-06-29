package com.tech.younsik.util.converter;

import com.tech.younsik.constant.UserConst.Status;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status attribute) {
        if(attribute==null) {
            return null;
        }
        return attribute.getDbCode();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        return Status.fromDbCode(dbData);
    }
}
