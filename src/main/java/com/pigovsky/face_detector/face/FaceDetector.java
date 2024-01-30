package com.pigovsky.face_detector.face;

import com.pigovsky.face_detector.photo.Photo;

public interface FaceDetector {
    Face detect(Photo photo);
}
