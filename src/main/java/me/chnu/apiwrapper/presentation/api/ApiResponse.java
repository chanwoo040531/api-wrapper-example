package me.chnu.apiwrapper.presentation.api;

import lombok.Getter;

@Getter
public final class ApiResponse<T> {
    private final T body;

    private final String message;

    private ApiResponse(T body, String message) {
        this.body = body;
        this.message = message;
    }

    public static <T> ApiResponse<T> ok(T body) {
        return new ApiResponse<>(body, null);
    }

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(null, message);
    }
}
