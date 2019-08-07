package com.example.demo.io.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author zhoushenghua on
 */
public class TestChannelRead {
    public static void main(String[] args) throws Exception{
        FileInputStream fis = new FileInputStream("xxx");
        FileChannel fc = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        fc.read(buffer);
        buffer.flip();
        while (buffer.remaining() > 0){
            byte b = buffer.get();
            System.out.print((char)b);
        }
        fis.close();
    }

}
