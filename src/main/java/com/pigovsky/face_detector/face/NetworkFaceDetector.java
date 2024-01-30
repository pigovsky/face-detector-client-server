package com.pigovsky.face_detector.face;

import com.pigovsky.face_detector.common.Encoding;
import com.pigovsky.face_detector.connection.Connection;
import com.pigovsky.face_detector.photo.NetworkPhotoSender;
import com.pigovsky.face_detector.photo.Photo;
import com.pigovsky.face_detector.photo.PhotoSender;

public class NetworkFaceDetector implements FaceDetector {
    private final Connection clientToServerConnection;

    public NetworkFaceDetector(Connection clientToServerConnection) {
        this.clientToServerConnection = clientToServerConnection;
    }

    @Override
    public Face detect(Photo photo) {
        PhotoSender photoSender = new NetworkPhotoSender(clientToServerConnection);
        photoSender.send(photo);
        return new Face(
            clientToServerConnection.receiveInt(),
            clientToServerConnection.receiveInt(),
            clientToServerConnection.receiveInt(),
            clientToServerConnection.receiveInt()
        );
    }
}
