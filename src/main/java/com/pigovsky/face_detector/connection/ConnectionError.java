package com.pigovsky.face_detector.connection;

public class ConnectionError extends RuntimeException {
    public ConnectionError(Throwable throwable) {
        super(throwable);
    }
}
