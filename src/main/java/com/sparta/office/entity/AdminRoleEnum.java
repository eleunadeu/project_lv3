package com.sparta.office.entity;

public enum AdminRoleEnum {
    // 아무리 머리를 짜봐도 방법이 없어서 변경했습니다.
    STAFF(Authority.STAFF),
    MANAGER(Authority.MANAGER);

    private final String authority;

    AdminRoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String STAFF = "ROLE_USER";
        public static final String MANAGER = "ROLE_ADMIN";
    }
}
