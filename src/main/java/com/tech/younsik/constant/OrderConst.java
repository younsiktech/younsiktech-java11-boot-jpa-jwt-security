package com.tech.younsik.constant;

import com.tech.younsik.util.EnumUtils;

public class OrderConst {

    public enum Status implements JsonEnum<String, String> {
        PURCHASED("purchase"),
        CANCEL("cancel");

        private final String code;

        Status(String code) {
            this.code = code;
        }

        public static Status fromDbCode(String code) {
            return EnumUtils.fromDbCode(Status.values(), code);
        }

        public static Status fromJsonCode(String jsonCode) {
            return EnumUtils.fromJsonCode(Status.values(), jsonCode);
        }

        @Override
        public String getDbCode() {
            return this.code;
        }

        @Override
        public String getJsonCode() {
            return this.code;
        }
    }

}
