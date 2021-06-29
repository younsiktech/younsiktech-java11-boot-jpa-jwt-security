package com.tech.younsik.constant;

import com.tech.younsik.util.EnumUtils;

public class UserConst {


    public enum Gender implements JsonEnum<String, String> {
        MALE("male"),
        FEMALE("female"),
        UNDEFINED("undefined");

        private final String code;

        Gender(String code) {
            this.code = code;
        }

        public static Gender fromDbCode(String code) {
            return EnumUtils.fromDbCode(Gender.values(), code);
        }

        public static Gender fromJsonCode(String jsonCode) {
            return EnumUtils.fromJsonCode(Gender.values(), jsonCode);
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

    public enum Status implements JsonEnum<String, String> {
        ACTIVE("active"),
        INACTIVE("inactive"),
        DELETED("deleted");

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
