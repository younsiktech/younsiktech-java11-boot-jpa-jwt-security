package com.tech.younsik.constant;

import com.fasterxml.jackson.annotation.JsonValue;

public interface JsonEnum<V, J> {
    V getDbCode();

    @JsonValue
    J getJsonCode();
}
