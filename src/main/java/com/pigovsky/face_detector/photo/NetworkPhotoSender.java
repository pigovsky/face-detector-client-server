package com.pigovsky.face_detector.photo;

import com.pigovsky.face_detector.common.Encoding;
import com.pigovsky.face_detector.connection.Connection;

public class NetworkPhotoSender implements PhotoSender {
    private final Connection connection;

    public NetworkPhotoSender(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void send(Photo photo) {
        byte[] bytes = photo.toBytes();
        byte[] encodedFileSize = Encoding.encodeInt(bytes.length);
        connection.sendBytes(encodedFileSize);
        connection.sendBytes(bytes);
    }
}
