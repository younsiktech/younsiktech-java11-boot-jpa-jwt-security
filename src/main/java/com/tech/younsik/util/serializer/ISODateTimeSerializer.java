package com.tech.younsik.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.tech.younsik.util.DateUtils;
import java.io.IOException;
import java.time.LocalDateTime;

public class ISODateTimeSerializer extends StdSerializer<LocalDateTime> {
    
    public ISODateTimeSerializer() {
        this(null);
    }
    
    public ISODateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }
    
    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        gen.writeString(DateUtils.LocalDateTimeToISOString(value));
    }
}