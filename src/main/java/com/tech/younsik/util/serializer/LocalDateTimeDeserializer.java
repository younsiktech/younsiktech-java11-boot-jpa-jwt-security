package com.tech.younsik.util.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {
    
    public LocalDateTimeDeserializer() {
        this(null);
    }
    
    protected LocalDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }
    
    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Instant instant = Instant.parse(p.getText());
        return LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
    }
}
