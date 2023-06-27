package com.example.post.user.dto;


import com.example.post.user.entity.StatusEnum;
import lombok.Data;

@Data
public class EnumDto {
    private String status;
    private String message;

    public EnumDto(StatusEnum status, String message) {
        this.status = status.getAuthority();
        this.message =message;
    }

}
