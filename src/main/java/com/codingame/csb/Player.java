package com.codingame.csb;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Player {
    private final GameSupport support;
    private final InputStream is;
    private final PrintStream os;
    private final PrintStream es;
    Scanner s;

    public Player(GameSupport support, InputStream is, PrintStream os, PrintStream es) {
        this.support = support;
        this.is = is;
        this.os = os;
        this.es = es;
    }

    public static void main(String[] args) {
        new Player(new Wood2Support(), System.in, System.out, System.err).play();
    }

    private void play() {
        s = new Scanner(System.in);
        for(;;) {
            gameTurn(s);
        }
    }

    private void gameTurn(Scanner s) {
        Input input = new Input(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
        if(support.distanceAngleOpponent()) {
            es.println("Reading distance and angle");
            input.setDistanceAndAngle(s.nextInt(), s.nextInt());
            input.setOpponentPos(s.nextInt(), s.nextInt());
        } else {
            input.calcDistanceAndAngle();
        }
        int acc = (int) calcAcceleration(input);
        es.println(String.format("%d %d %d %d %d %d %d %d %d", input.x, input.y, input.nx, input.ny, input.d, input.angle, input.ox, input.oy, acc));
        os.println("" + input.nx + " " + input.ny + " " + acc);
    }

    private int calcAcceleration(Input input) {
        // 600 : -3
        // 1200 : 3
        double rem = ((double) input.d - 600.) / 600. - 1.;
        double a = rem < 3 ? 100. / (1. + Math.exp(-rem)) : 100;
        return (int) a;
    }

    static class Input {
        int x, y, nx, ny, d, angle;
        int ox, oy;

        public Input(int x, int y, int nx, int ny) {
            this.x = x;
            this.y = y;
            this.nx = nx;
            this.ny = ny;
        }

        public void setDistanceAndAngle(int d, int a) {
            this.d = d;
            this.angle = angle;
        }

        public void calcDistanceAndAngle() {
            this.d = (int) Math.sqrt((nx - x)*(nx - x) + (ny - y)*(ny - y));
            this.angle = 0;
        }

        public void setOpponentPos(int x, int y) {
            this.ox = x;
            this.oy = y;
        }
    }

    static class Wood2Support extends Wood3Support {
        @Override
        public boolean distanceAngleOpponent() {
            return true;
        }
    }

    static class Wood3Support implements GameSupport {
        @Override
        public boolean distanceAngleOpponent() {
            return false;
        }
    }

    interface GameSupport {
        boolean distanceAngleOpponent();
    }
}
