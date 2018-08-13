package com.codingame;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputOutputStream {
    private static final int BUFFER_SIZE = 1 << 20; // MB
    private byte[] buffer = new byte[BUFFER_SIZE];
    private final Object lock = new Object();
    private InputOutputPositions positions = new InputOutputPositions();
    private IOInputStream is;
    private IOOutputStream os;

    public InputOutputStream() {
        this.is = new IOInputStream(buffer, positions, lock);
        this.os = new IOOutputStream(buffer, positions, lock);
    }

    public InputStream getInputStream() {
        return is;
    }

    public OutputStream getOutputStream() {
        return os;
    }

    private static class IOInputStream extends InputStream {
        private final byte[] buffer;
        private final InputOutputPositions pos;
        private final Object lock;

        public IOInputStream(final byte[] buffer, final InputOutputPositions positions, final Object lock) {
            this.buffer = buffer;
            this.lock = lock;
            this.pos = positions;
        }

        @Override
        public boolean markSupported() {
            return false;
        }

        @Override
        public int available() throws IOException {
            if(pos.readPos < pos.writePos) {
                return pos.writePos - pos.readPos;
            } else if(pos.readPos > pos.writePos){
                return BUFFER_SIZE - pos.readPos + pos.writePos;
            } else {
                return 0;
            }
        }

        @Override
        public int read() throws IOException {
            if(available() == 0) {
                return -1;
            }
            synchronized (lock) {
                if (pos.readPos == buffer.length) {
                    pos.readPos = 0;
                }
                return buffer[pos.readPos++];
            }
        }
    }

    private static class IOOutputStream extends OutputStream {
        private final byte[] buffer;
        private InputOutputPositions pos;
        private final Object lock;

        public IOOutputStream(final byte[] buffer, final InputOutputPositions positions, final Object lock) {
            this.buffer = buffer;
            this.lock = lock;
            this.pos = positions;
        }

        @Override
        public void write(int b) throws IOException {
            synchronized (lock) {
                if(this.pos.writePos == buffer.length) {
                    this.pos.writePos = 0;
                }
                buffer[pos.writePos++] = (byte)b;
            }
        }
    }

    private static class InputOutputPositions {
        public int readPos = 0;
        public int writePos = 0;
    }
}
