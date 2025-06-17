package com.pigovsky.face_detector.face;

import com.pigovsky.face_detector.photo.Photo;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class OpenCvFaceDetector implements FaceDetector {
    private final CascadeClassifier cascadeClassifier;

    public OpenCvFaceDetector() {
        cascadeClassifier = new CascadeClassifier();
        cascadeClassifier.load("./src/main/resources/haarcascades/haarcascade_frontalface_alt.xml");
    }

    @Override
    public Face detect(Photo photo) {
        MatOfRect facesDetected = new MatOfRect();
        int minFaceSize = Math.round(photo.getBitmap().rows() * 0.1f);
        cascadeClassifier.detectMultiScale(photo.getBitmap(),
            facesDetected,
            1.1,
            3,
            Objdetect.CASCADE_SCALE_IMAGE,
            new Size(minFaceSize, minFaceSize),
            new Size()
        );
        Rect headFace = facesDetected.toArray().length > 0 ?
            facesDetected.toArray()[0] :
            new Rect(0,0,0,0);
        return new Face(
            headFace.x,
            headFace.y,
            headFace.width,
            headFace.height
        );
    }
}
