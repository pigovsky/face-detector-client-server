import com.pigovsky.face_detector.connection.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConnectionMock extends Connection {

    private final byte[] inputData;

    private final byte[] outputData;

    private int readPos = 0;

    private int writePos = 0;

    public ConnectionMock(byte[] inputData) {
        this.inputData = inputData;
        outputData = new byte[1350460];
    }

    @Override
    public void sendBytes(byte[] data) {
        System.arraycopy(data, 0, outputData, writePos, data.length);
        writePos += data.length;
    }

    @Override
    public byte[] receiveBytes(int length) {
        byte[] result = Arrays.copyOfRange(inputData, readPos, readPos + length);
        readPos += length;
        return result;
    }

    @Override
    public void close() {

    }

    public byte[] getOutputData() {
        return Arrays.copyOfRange(outputData, 0, writePos);
    }
}
