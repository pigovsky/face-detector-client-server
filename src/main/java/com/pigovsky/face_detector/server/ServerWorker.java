package com.pigovsky.face_detector.server;

import com.pigovsky.face_detector.connection.Connection;
import com.pigovsky.face_detector.face.Face;
import com.pigovsky.face_detector.face.FaceDetector;
import com.pigovsky.face_detector.photo.FilesystemPhotoSender;
import com.pigovsky.face_detector.photo.NetworkPhotoFetcher;
import com.pigovsky.face_detector.photo.Photo;

public class ServerWorker implements Runnable {
    private final Connection connection;
    private final FaceDetector faceDetector;

    public ServerWorker(Connection connection, FaceDetector faceDetector) {
        this.connection = connection;
        this.faceDetector = faceDetector;
    }

    @Override
    public void run() {
        Photo photo = new NetworkPhotoFetcher(connection).fetch();
        new FilesystemPhotoSender("input.jpg").send(photo);
        Face face = faceDetector.detect(photo);
        System.out.println(face);
        connection.sendBytes(face.toBytes());
        connection.close();
    }
}
