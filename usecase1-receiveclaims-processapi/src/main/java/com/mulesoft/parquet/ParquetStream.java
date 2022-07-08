package com.mulesoft.parquet;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.parquet.io.DelegatingSeekableInputStream;
import org.apache.parquet.io.InputFile;
import org.apache.parquet.io.SeekableInputStream;

public class ParquetStream implements InputFile {
    private final String streamId;
    private final byte[] data;

    private static class SeekableByteArrayInputStream extends ByteArrayInputStream {
        public SeekableByteArrayInputStream(byte[] buf) {
            super(buf);
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getPos() {
            return this.pos;
        }
    }

    public ParquetStream(String streamId, byte[] streamData) {
        this.streamId = streamId;
        this.data = streamData;
    }

    @Override
    public long getLength() throws IOException {
        return this.data.length;
    }

    @Override
    public SeekableInputStream newStream() throws IOException {
        return new DelegatingSeekableInputStream(new SeekableByteArrayInputStream(this.data)) {
            @Override
            public void seek(long newPos) throws IOException {
                ((SeekableByteArrayInputStream) this.getStream()).setPos((int) newPos);
            }

            @Override
            public long getPos() throws IOException {
                return ((SeekableByteArrayInputStream) this.getStream()).getPos();
            }
        };
    }

    @Override
    public String toString() {
        return "ParquetStream[" + streamId + "]";
    }    
}