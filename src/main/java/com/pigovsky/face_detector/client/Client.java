package com.pigovsky.face_detector.client;

import com.pigovsky.face_detector.common.DefaultConfiguration;
import com.pigovsky.face_detector.connection.Connection;
import com.pigovsky.face_detector.connection.SocketConnection;
import com.pigovsky.face_detector.face.Face;
import com.pigovsky.face_detector.face.FaceDetector;
import com.pigovsky.face_detector.face.NetworkFaceDetector;
import com.pigovsky.face_detector.photo.*;

public class Client implements Runnable {
    private final String[] args;

    private final String serverHost;

    private final int serverPort;

    public Client(String[] args) {
        this.args = args;
        if (args.length > 2) {
            String[] hostPort = args[2].split(":");
            serverHost = hostPort[0];
            serverPort = Integer.parseInt(hostPort[1]);
        } else {
            serverHost = DefaultConfiguration.serverHost;
            serverPort = DefaultConfiguration.serverPort;
        }
    }

    @Override
    public void run() {
        if (args.length < 2) {
            throw new ClientError("You have to specify photo path as the first argument");
        }

        PhotoFetcher photoFetcher = new FilesystemPhotoFetcher(args[1]);
        Photo photo = photoFetcher.fetch();
        Connection clientToServerConnection = SocketConnection.clientConnection(serverHost, serverPort);
        FaceDetector faceDetector = new NetworkFaceDetector(clientToServerConnection);
        Face face = faceDetector.detect(photo);
        clientToServerConnection.close();
        System.out.println(face);

        photo.highlightFace(face);
        new FilesystemPhotoSender("result.jpg").send(photo);
    }
}
