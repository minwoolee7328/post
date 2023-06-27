package com.example.post.user.entity;

import lombok.Getter;

@Getter
public enum StatusEnum {

//    OK(200, "OK"),
//    BAD_REQUEST(400, "BAD_REQUEST"),
//    NOT_FOUND(404, "NOT_FOUND"),
//    INTERNAL_SERER_ERROR(500, "INTERNAL_SERVER_ERROR");
//
//    int statusCode;
//    String code;
//
//    StatusEnum(int statusCode, String code) {
//        this.statusCode = statusCode;
//        this.code = code;
//    }

    OK(Authority.OK),
    BAD_REQUEST(Authority.BAD_REQUEST);

    private final String authority;

    StatusEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return this.authority;
    }

    public static class Authority {
        public static final String OK = "200";
        public static final String BAD_REQUEST = "400";
    }
}
