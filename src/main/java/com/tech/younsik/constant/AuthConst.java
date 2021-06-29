package com.tech.younsik.constant;

import com.tech.younsik.util.EnumUtils;

public class AuthConst {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String AUTHORIZATION_TYPE = "Bearer";

    public static final String AUTHORIZATION_ROLES = "roles";

    public enum Role implements JsonEnum<String, String> {
        USER("ROLE_USER"),
        ADMIN("ROLE_ADMIN");

        private final String code;

        Role(String code) {
            this.code = code;
        }

        public static Role fromDbCode(String code) {
            return EnumUtils.fromDbCode(Role.values(), code);
        }

        public static Role fromJsonCode(String jsonCode) {
            return EnumUtils.fromJsonCode(Role.values(), jsonCode);
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
