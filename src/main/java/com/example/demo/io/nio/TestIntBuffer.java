package com.example.demo.io.nio;

import java.nio.IntBuffer;

/**
 * @Author zhoushenghua on
 */
public class TestIntBuffer {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(8);

        for(int i=0; i < buffer.capacity(); i++){
            int j = i+1;
            buffer.put(j);
        }

        buffer.flip();

        while(buffer.hasRemaining()){
            int j = buffer.get();
            System.out.print(j + " ");
        }


    }

}
