package com.example.demo.io.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author zhoushenghua on
 */
public class TestChannelWrite {
    private static final byte message[] = {10,11,12,13,14,15,16};
    public static void main(String[] args) throws Exception{
        FileOutputStream fops = new FileOutputStream("xxx");
        FileChannel fc = fops.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        for(int i=0; i< message.length; i++){
            byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        fc.write(byteBuffer);
        fops.close();
    }
}
