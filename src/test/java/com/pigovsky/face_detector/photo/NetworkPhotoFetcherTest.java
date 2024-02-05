package com.pigovsky.face_detector.photo;
import com.pigovsky.face_detector.connection.ConnectionMock;
import nu.pattern.OpenCV;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;


public class NetworkPhotoFetcherTest {
    @BeforeClass
    public static void classSetUp() {
        OpenCV.loadShared();
    }

    @Test
    public void testFetchPhotoFromNetwork(){
        Photo expectedPhoto = getPhoto();
        ConnectionMock connectionFromClientToServer = new ConnectionMock(new byte[0]);
        PhotoSender photoSender = new NetworkPhotoSender(connectionFromClientToServer);
        photoSender.send(expectedPhoto);

        ConnectionMock connectionFromServerToClient = new ConnectionMock(connectionFromClientToServer.getOutputData());
        PhotoFetcher photoFetcher = new NetworkPhotoFetcher(connectionFromServerToClient);
        Photo actualPhoto = photoFetcher.fetch();
        Assert.assertArrayEquals(expectedPhoto.toBytes(), actualPhoto.toBytes());
    }

    private Photo getPhoto() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("test.png").getFile());
        return new FilesystemPhotoFetcher(file.getAbsolutePath()).fetch();
    }
}
