package com.sparta.office.entity;

public enum AdminRoleEnum {
    STAFF(Authority.ADMIN),
    MANAGER(Authority.ADMIN);

    private final String authority;

    AdminRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
