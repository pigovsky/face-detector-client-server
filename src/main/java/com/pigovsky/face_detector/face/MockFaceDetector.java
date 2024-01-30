package com.pigovsky.face_detector.face;

import com.pigovsky.face_detector.photo.Photo;

public class MockFaceDetector implements FaceDetector {
    @Override
    public Face detect(Photo photo) {
        return new Face(0, 1, 2, 3);
    }
}
