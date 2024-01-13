package me.chnu.apiwrapper.util.exception;


import lombok.Getter;

@Getter
public class GlobalServerException extends RuntimeException {

    private final String message;

    public GlobalServerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
