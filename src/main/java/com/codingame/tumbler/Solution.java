package com.codingame.tumbler;

import java.util.Scanner;

class Solution {
    static String[] history = new String[4];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt();
        int height = in.nextInt();
        int count = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        StringBuilder grid = new StringBuilder();
        for (int i = 0; i < height; i++) {
            String raster = in.nextLine();
            grid.append(raster);
        }
        System.out.println(calculateTumble(width, height, count, grid.toString()));
    }

    static String calculateTumble(int width, int height, int count, String grid) {
        history[0] = grid;
        byte[] b = history[0].getBytes();
        Vectori extents = new Vectori(width, height);
        Vectori down = new Vectori(0, 1);
        String calculated;
        for(int i = 0; i < count; ++i) {
            tumble(b, down = down.rotate(90), extents);
            calculated = new String(b);
            if(calculated.equals(history[i % 4])) {
                break;
            } else {
                history[i % 4] = calculated;
            }
        }
        return history[count % 4];
    }

    private static void tumble(byte[] b, Vectori down, Vectori extents) {
        int n = Math.abs(down.x * extents.y + down.y * extents.x);
        int[] heavies = new int[n];
        for(int i = 0; i < extents.y; ++i) {
            for(int j = 0; j < extents.x; ++j) {
                if(b[i * extents.x + j] == '#') {
                    ++heavies[Math.abs(i * down.x + j * down.y)];
                }
            }
        }
        float half = 0.5f * (float) ((extents.x - 1) * down.x +
                (extents.y - 1) * down.y);
        int start = (int)(half + (float)Math.abs(((extents.x - 1) * down.x + (extents.y - 1) * down.y)));
        int x = start * Math.abs(down.x), y = start * Math.abs(down.y);
        int count;
        int j;
        for(int i = 0; i < heavies.length; ++i) {
            j = y * extents.y + x;
            count = 0;
            while(count < Math.abs(down.x * extents.x + down.y * extents.y)) {
                b[j] = (byte) ((count++ < heavies[i]) ? '#' : '.');
                x += -down.x;
                y += -down.y;
            }
            x += Math.abs(down.x);
            y += Math.abs(down.y);
        }
    }

    static class Vectori {
        final int x, y;

        public Vectori(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Vectori rotate(int theta) {
            if((theta % 360) == 0) {
                return new Vectori(this.x, this.y);
            } else if(((theta - 90) % 360) == 0) {
                return new Vectori(-this.y, this.x);
            } else if(((theta - 180) % 360) == 0) {
                return new Vectori(-this.x, -this.y);
            } else if(((theta - 270) % 360) == 0) {
                return new Vectori(this.y, -this.x);
            }
            return null;
        }

    }
}
