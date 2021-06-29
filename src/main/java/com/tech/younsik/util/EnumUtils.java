package com.tech.younsik.util;

import com.tech.younsik.constant.JsonEnum;
import java.util.Objects;

public class EnumUtils {

    public static <T extends JsonEnum> T fromDbCode(T[] elems, String code) {

        if (code == null) return null;

        for (T e : elems) {
            if (code.equalsIgnoreCase((String) e.getDbCode())) {
                return e;
            }
        }

        return null;
    }

    public static <T extends JsonEnum> T fromJsonCode(T[] elems, Object code) {

        if (code == null) return null;

        for (T e : elems) {
            if (Objects.equals(code, e.getJsonCode())) {
                return e;
            }
        }

        return null;
    }
}
