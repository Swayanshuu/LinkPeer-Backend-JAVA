package com.swynx.linkpeer_backend.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message,T data) {
        return new ApiResponse<>(true, message, data);
    }
}
