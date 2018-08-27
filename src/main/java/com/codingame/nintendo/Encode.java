package com.codingame.nintendo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Encode {
    public static int[] encode(int size, int[] a) throws IOException {
        try(PrintStream ps = new PrintStream(new FileOutputStream(File.createTempFile("nintendo", null)))) {
            int[] b = new int[size >> 4];
            int A, B, C, D, E, F, G, H, I, J, K, L, M, N;
            for (int i = 0; i < size; ++i) {
                for (int j = 0; j < size; ++j) {
                    A = i + j;
                    B = i >> 5;
                    C = j >> 5;
                    D = size >> 5;
                    E = i & 0x1F;
                    F = j & 0x1F;
                    G = A & 0x1F;
                    H = a[C + D];
                    I = H >>> F;
                    J = (a[B] >>> E);
                    K = J & I;
                    L = K & 0x01;
                    M = L << G;
                    N = A >> 5;
                    ps.println(String.format("b[(%d + %d) >> 5] ^= ((a[%d >> 5] >>> (%d & 0x1F)) & (a[(%d >> 5) + (size >> 5)] >>> (%d & 0x1F)) & 0x01) << ((%d + %d) & 0x1F)", i, j, i, i, j, j, i, j));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d >> 5] >>> (%d & 0x1F)) & (a[(%d >> 5) + (%d >> 5)] >>> (%d & 0x1F)) & 0x01) << (%d & 0x1F)", A, i, i, j, size, j, A));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d] >>> (%d & 0x1F)) & (a[(%d >> 5) + (%d >> 5)] >>> (%d & 0x1F)) & 0x01) << (%d & 0x1F)", A, B, i, j, size, j, A));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d] >>> (%d & 0x1F)) & (a[%d + (%d >> 5)] >>> (%d & 0x1F)) & 0x01) << (%d & 0x1F)", A, B, i, C, size, j, A));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d] >>> (%d & 0x1F)) & (a[%d + %d] >>> (%d & 0x1F)) & 0x01) << (%d & 0x1F)", A, B, A, C, D, j, A));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d] >>> %d) & (a[%d + %d] >>> %d) & 0x01) << (%d & 0x1F)", A, B, E, C, D, F, A));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d] >>> %d) & (a[%d] >>> %d) & 0x01) << %d", A, B, E, C + D, F, G));
                    ps.println(String.format("b[%d >> 5] ^= ((a[%d] >>> %d) & %08X & 0x01) << %d", A, B, E, I, G));
                    ps.println(String.format("b[%d >> 5] ^= (%08X & %08X & 0x01) << %d", A, J, I, G));
                    ps.println(String.format("b[%d >> 5] ^= (%08X & 0x01) << %d", A, K, G));
                    ps.println(String.format("b[%d >> 5] ^= %d << %d", A, L, G));
                    ps.println(String.format("b[%d >> 5] ^= %X", A, M));
                    ps.println(String.format("b[%d] -> %08X ^= %X -> %08X", N, b[N], M, b[N] ^ M));
                    ps.println();

                    b[(i + j) >> 5] ^= ((a[i >> 5] >>> (i & 0x1F)) & (a[(j >> 5) + (size >> 5)] >>> (j & 0x1F)) & 0x01) << ((i + j) & 0x1F);   // Magic centaurian operation


                }
            }
            return b;
        }
    }
}
