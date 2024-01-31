package com.pigovsky.face_detector.photo;

import org.opencv.imgcodecs.Imgcodecs;

public class FilesystemPhotoSender implements PhotoSender {
    private final String targetPath;

    public FilesystemPhotoSender(String targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public void send(Photo photo) {
        Imgcodecs imgcodecs = new Imgcodecs();
        imgcodecs.imwrite(targetPath, photo.getBitmap());
    }
}
