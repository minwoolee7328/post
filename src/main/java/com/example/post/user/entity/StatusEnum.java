package com.example.post.user.entity;

import lombok.Getter;

@Getter
public enum StatusEnum {

    OK(Status.OK),
    BAD_REQUEST(Status.BAD_REQUEST);

    private final String authority;

    StatusEnum(String authority) {
        this.authority = authority;
    }

    public String getStatus() {
        return this.authority;
    }

    public static class Status {
        public static final String OK = "200";
        public static final String BAD_REQUEST = "400";
    }
}
