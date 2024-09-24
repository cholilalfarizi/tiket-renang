package com.cholildev.tiket_renang.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBodyDTO {
    private String status;
    private int code;
    private String message;
    private Object data;
}