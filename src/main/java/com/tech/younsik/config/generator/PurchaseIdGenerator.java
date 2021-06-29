package com.tech.younsik.config.generator;

import java.io.Serializable;
import java.time.Instant;
import java.util.Properties;
import org.apache.commons.lang3.RandomUtils;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class PurchaseIdGenerator implements IdentifierGenerator, Configurable {

    private final String BASE_CHAR="123456789ABCDEFGHIJKLMNOPQRSTUVW"; // 0=패딩
    private final int KEY_LIMIT = 12;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object)
        throws HibernateException {

        String prefix = encoding(Instant.now().toEpochMilli());

        int pow = KEY_LIMIT - prefix.length();

        if (pow < 0) {
            throw new RuntimeException("");
        } else if (pow==0) {
            return prefix;
        }

        String postfix = encoding(RandomUtils.nextInt(0, ((int)(Math.pow(BASE_CHAR.length(), pow)))-1));

        StringBuffer stringBuffer = new StringBuffer();

        while (pow > 0) {
            stringBuffer.append("0");
            pow--;
        }

        return prefix + stringBuffer.toString().substring(postfix.length()) + postfix;
    }

    private String encoding(long param) {
        StringBuffer sb = new StringBuffer();
        long value = param;
        while(value > 0) {
            sb.append(BASE_CHAR.charAt((int)(value % BASE_CHAR.length())));
            value /= BASE_CHAR.length();
        }
        return sb.reverse().toString();
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
        throws MappingException {

    }
}
