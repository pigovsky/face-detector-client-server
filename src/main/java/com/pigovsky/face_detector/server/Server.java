package com.pigovsky.face_detector.server;

import com.pigovsky.face_detector.common.DefaultConfiguration;
import com.pigovsky.face_detector.connection.Connection;
import com.pigovsky.face_detector.connection.ServerConnectionListener;
import com.pigovsky.face_detector.connection.ServerSocketConnectionListener;
import com.pigovsky.face_detector.face.FaceDetector;
import com.pigovsky.face_detector.face.MockFaceDetector;
import com.pigovsky.face_detector.face.OpenCvFaceDetector;

public class Server implements Runnable {
    private final ServerConnectionListener serverConnectionListener;

    private final FaceDetector faceDetector;

    private final String[] args;

    public Server(String[] args) {
        this.args = args;
        int serverPort = args.length > 1 ? (
            Integer.parseInt(args[1])
        ) : DefaultConfiguration.serverPort;
        serverConnectionListener = new ServerSocketConnectionListener(serverPort);
        faceDetector = DefaultConfiguration.mock ? new MockFaceDetector() : new OpenCvFaceDetector();
    }

    @Override
    public void run() {
        System.err.println("Start listening for incoming connections");
        while (true) {
            Connection connection = serverConnectionListener.listen();
            System.err.println("Start a face-detecting worker");
            new Thread(new ServerWorker(connection, faceDetector)).start();
        }
    }
}
