package com.pigovsky.face_detector.photo;

import com.pigovsky.face_detector.common.DefaultConfiguration;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

public class Photo {
    private final Mat bitmap;

    public Photo(Mat bitmap) {
        this.bitmap = bitmap;
    }

    public static Photo fromBytes(byte[] data) {
        Mat bitmap = DefaultConfiguration.mock ? null : Imgcodecs.imdecode(new MatOfByte(data), Imgcodecs.IMREAD_COLOR);
        return new Photo(bitmap);
    }

    public byte[] toBytes() {
        if (DefaultConfiguration.mock) {
            return new byte[0];
        }
        MatOfByte bytes = new MatOfByte();
        Imgcodecs.imencode(".jpg", bitmap, bytes);
        return bytes.toArray();
    }

    public int getWidth() {
        return bitmap.width();
    }

    public int getHeight() {
        return bitmap.height();
    }

    public Mat getBitmap() {
        return bitmap;
    }
}
