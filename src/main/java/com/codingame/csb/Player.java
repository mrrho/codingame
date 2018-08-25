package com.codingame.csb;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

class Player {
    private final GameSupport support;
    private final InputStream is;
    private final PrintStream os;
    private final PrintStream es;
    int px = -1, py = -1;
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
        Vector current;
        Vector desired;
        Vector output;
        float thd, tha;
        double dx, dy, dxd, dyd;
        int nx, ny;
        float orient;
        float acc = 100.f;
        float a1 = 50.f; float a2 = 100.f - a1;
        float p1 = (float) (0.333333 * Math.PI), p2 = (float) (0.666667 * Math.PI);
        if(px != -1) {
            current = new Vector(input.x - px, input.y - py).normalize();
            es.println("Current: " + current);
            desired = new Vector(input.nx - input.x, input.ny - input.y).normalize();
            es.println("Desired: " + desired);
            thd = (float) Math.acos(current.dot(desired));
            orient = current.cross(desired);
            tha = Math.abs(thd);
            es.println(String.format("Angle: %f", 180. * tha / Math.PI));
            acc = tha < p1 ? 100.f : tha > p2 ? a1 : ((p2 - tha) * (a2 - a1) / (p2 - p1) + a1);
            thd = orient >= 0 ? thd : -thd;
            thd *= 1.25;
            if(thd > Math.PI) {
                thd = (float) Math.PI;
            }
            output = current.rotate(thd);
            es.println(String.format("o,th,or: %s %f %f", output, thd, orient));
            nx = input.x + (int)(output.x * 100.f);
            ny = input.y + (int)(output.y * 100.f);
            es.println(String.format("nx,ny,acc: %d %d %.3f", nx, ny, acc));
        } else {
            nx = input.nx;
            ny = input.ny;
        }
        px = input.x;
        py = input.y;
        es.println(String.format("%d %d %d %d %d %d %d %d %d", input.x, input.y, input.nx, input.ny, input.d, input.angle, input.ox, input.oy, (int)acc));
        os.println("" + nx + " " + ny + " " + (int) acc);
    }

    private int calcAcceleration(Input input) {
        // 600 : -3
        // 1200 : 3
        double rem = (input.d - 600.) / 600. - 1.;
        double a = rem < 3 ? 100. / (1. + Math.exp(-rem)) : 100;
        return (int) a;
    }

    static class Vector {
        final float x, y;
        final float l;

        public Vector(int x, int y) {
            this.x = x;
            this.y = y;
            this.l = (float) Math.sqrt(this.x * this.x + this.y * this.y);
        }

        public Vector(float x, float y) {
            this.x = x;
            this.y = y;
            this.l = (float) Math.sqrt(this.x * this.x + this.y * this.y);
        }

        public Vector normalize() {
            return new Vector(this.x / this.l, this.y / this.l);
        }

        @Override
        public String toString() {
            return String.format("%.3f %.3f", this.x, this.y);
        }

        public float cross(Vector v) {
            return this.x * v.y - this.y * v.x;
        }

        public double dot(Vector v) {
            return this.x * v.x + this.y * v.y;
        }

        public Vector rotate(float theta) {
            return new Vector((float)(this.x * Math.cos(theta) - this.y * Math.sin(theta)),
                    (float)(this.x * Math.sin(theta) + this.y * Math.cos(theta)));
        }
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
