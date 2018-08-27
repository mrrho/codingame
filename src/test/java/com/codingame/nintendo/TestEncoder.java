package com.codingame.nintendo;

import org.junit.Test;

import java.io.IOException;

public class TestEncoder {
    @Test
    public void testEncode1() throws IOException {
        int[] input = new int[] { 0x00000001, 0x000073af };
        int[] output = Encode.encode(32, input);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < output.length; ++i) {
            if(i != 0) {
                sb.append(' ');
            }
            sb.append(String.format("%08X", output[i]));
        }
        System.out.println(sb.toString());
    }

    @Test
    public void testEncode6() throws IOException {
        int[] input = new int[] {
                0x320a18d5, 0xb61b13f6, 0x1aaaa61c, 0x0afe2a41,
                0x1a4ff107, 0x84cc2efc, 0x956ff31d, 0xfa595299,
                0x33749a7f, 0x6cc9659d, 0xdc503569, 0xef4d0ef5,
                0x73b746c5, 0xb8fb36d3, 0x7616e9d6, 0xb21251c4 };
        int[] output = Encode.encode(256, input);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < output.length; ++i) {
            if(i != 0) {
                sb.append(' ');
            }
            sb.append(String.format("%08X", output[i]));
        }
        System.out.println(sb.toString());
    }
}
