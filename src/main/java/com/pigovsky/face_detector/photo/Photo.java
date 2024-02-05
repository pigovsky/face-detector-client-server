package com.pigovsky.face_detector.photo;

import com.pigovsky.face_detector.common.DefaultConfiguration;
import com.pigovsky.face_detector.face.Face;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

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
        Imgcodecs.imencode(".png", bitmap, bytes);
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

    public void highlightFace(Face face) {
        Rect faceRect = new Rect(face.getLeft(), face.getTop(), face.getWidth(), face.getHeight());
        Imgproc.rectangle(getBitmap(), faceRect.tl(), faceRect.br(), new Scalar(0, 0, 255), 3);
    }
}
