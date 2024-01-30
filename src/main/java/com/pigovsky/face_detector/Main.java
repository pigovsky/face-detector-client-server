package com.pigovsky.face_detector;

import com.pigovsky.face_detector.client.Client;
import com.pigovsky.face_detector.common.DefaultConfiguration;
import com.pigovsky.face_detector.server.Server;
import nu.pattern.OpenCV;

public class Main {
    public static final String usage = "Please use either `send` or `listen` command";

    public static void main(String[] args) {
        if (!DefaultConfiguration.mock) {
            OpenCV.loadShared();
        }
        if (args.length < 1) {
            System.err.println(usage);
            System.exit(1);
        }
        String cmd = args[0];
        switch (cmd) {
            case "send":
                new Client(args).run();
                break;
            case "listen":
                new Server(args).run();
            default:
                System.err.printf("Unknown command %s. %s instead", cmd, usage);
        }
    }
}
