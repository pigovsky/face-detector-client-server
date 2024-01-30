package com.pigovsky.face_detector.photo;

import com.pigovsky.face_detector.common.Encoding;
import com.pigovsky.face_detector.connection.Connection;

public class NetworkPhotoFetcher implements PhotoFetcher {
    private final Connection connection;

    public NetworkPhotoFetcher(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Photo fetch() {
        int fileSize = Encoding.decodeInt(connection.receiveBytes(4));
        byte[] data = connection.receiveBytes(fileSize);
        return Photo.fromBytes(data);
    }
}
