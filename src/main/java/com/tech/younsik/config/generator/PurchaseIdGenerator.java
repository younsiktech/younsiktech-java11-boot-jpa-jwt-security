package com.tech.younsik.config.generator;

import com.tech.younsik.exception.DatabaseException;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class PurchaseIdGenerator implements IdentifierGenerator, Configurable {

    private final static int KEY_LIMIT = 12;

    private final static long START_ID_VALUE = LocalDateTime
        .of(2020, 12, 31, 23, 59, 59)
        .toInstant(ZoneOffset.UTC).toEpochMilli();

    private final static int PADDING_MULTIPLE_VALUE = 100000;

    private final static String BASE_CHAR_35 = "123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // 0=패딩
    private final static String PADDING_CHAR = "0";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
        throws HibernateException {
        return generateUniqueKey();
    }


    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
        throws MappingException {

    }

    private String encodingBase35exceptZero(long param) {
        StringBuffer sb = new StringBuffer();
        long value = param;
        while (value > 0) {
            sb.append(BASE_CHAR_35.charAt((int) (value % BASE_CHAR_35.length())));
            value /= BASE_CHAR_35.length();
        }
        return sb.reverse().toString();
    }

    private String generateUniqueKey() {

        final Instant current = Instant.now();

        final long value = ((current.toEpochMilli() - START_ID_VALUE) * PADDING_MULTIPLE_VALUE) + (
            current.getNano() % PADDING_MULTIPLE_VALUE);

        String prefix = encodingBase35exceptZero(value);

        int pow = KEY_LIMIT - prefix.length();

        if (pow < 0) {
            throw new DatabaseException("Key limit exceed",
                DatabaseException.Type.GENERATE_KEY_ERROR);
        } else if (pow == 0) {
            return prefix;
        }

        StringBuffer stringBuffer = new StringBuffer();

        while (pow > 0) {
            stringBuffer.append(PADDING_CHAR);
            pow--;
        }

        return stringBuffer.append(prefix).toString();
    }

}
