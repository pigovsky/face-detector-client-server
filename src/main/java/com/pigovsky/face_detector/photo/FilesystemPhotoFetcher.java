package com.pigovsky.face_detector.photo;

import com.pigovsky.face_detector.common.DefaultConfiguration;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class FilesystemPhotoFetcher implements PhotoFetcher {
    private final String filePath;

    public FilesystemPhotoFetcher(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Photo fetch() {
        Imgcodecs imageCodecs = new Imgcodecs();
        Mat bitmap = DefaultConfiguration.mock ? null : imageCodecs.imread(filePath);
        return new Photo(bitmap);
    }
}
