package com.pigovsky.face_detector.client;

public class ClientError extends RuntimeException {
    public ClientError(Throwable throwable) {
        super(throwable);
    }

    public ClientError(String message) {
        super(message);
    }
}
