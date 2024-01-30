package com.pigovsky.face_detector.connection;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerSocketConnectionListener implements ServerConnectionListener {
    private final ServerSocket serverSocket;
    private final int serverPort;

    public ServerSocketConnectionListener(int serverPort) {
        this.serverPort = serverPort;
        try {
            this.serverSocket = new ServerSocket(serverPort);
        } catch (IOException e) {
            throw new ConnectionError(e);
        }
    }

    @Override
    public Connection listen() {
        try {
            return new SocketConnection(serverSocket.accept());
        } catch (IOException e) {
            throw new ConnectionError(e);
        }
    }
}
