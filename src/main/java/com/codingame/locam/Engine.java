package com.codingame.locam;

import com.codingame.InputOutputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Engine {
    public static void main(String[] args) {
        new Engine().start();
    }

    private void start() {
        InputOutputStream in1, in2, out1, out2;
        in1 = new InputOutputStream();
        in2 = new InputOutputStream();
        out1 = new InputOutputStream();
        out2 = new InputOutputStream();
        Player.GameSupport support = new Player.Wood3GameSupport();
        Player client1 = new Player(support, in1.getInputStream(), new PrintStream(out1.getOutputStream()));
        Player client2 = new Player(support, in2.getInputStream(), new PrintStream(out2.getOutputStream()));

        PrintStream data1 = new PrintStream(in1.getOutputStream()), data2 = new PrintStream(in2.getOutputStream());
        BufferedReader input1 = new BufferedReader(new InputStreamReader(out1.getInputStream())),
                input2 = new BufferedReader(new InputStreamReader(out2.getInputStream()));

        Player.Actor p1 = new Player.Actor();
        p1.initialize(support.startingHealth(), 0, 0, 0);
        Player.Actor p2 = new Player.Actor();
        p2.initialize(support.startingHealth(), 0, 0, 0);

        Random random = new Random(System.currentTimeMillis());

        new Thread(client1::run).start();
        new Thread(client2::run).start();

        int phase = Player.Board.PHASE_DRAFT;
        List<Player.Card> availables = new ArrayList<>();
        Set<Integer> allowedTypes = new HashSet<>();
        for(int v: support.allowedCardTypes()) {
            allowedTypes.add(v);
        }
        for(int i = 0; i < Player.LISTING.length; ++i) {
            if(allowedTypes.contains(Player.LISTING[i].type)) {
                availables.add(Player.LISTING[i]);
            }
        }
        if(support.deckSize() * support.draftCount() > availables.size() && support.uniqueCards()) {
            throw new RuntimeException("Not enough cards");
        }
//        for(int i = 0; i < support.deckSize(); ++i) {
//
        data1.println(p1.toString());
        data1.println(p2.toString());
        data2.println(p1.toString());
        data2.println(p2.toString());
//        data1.flush();
//        data2.flush();
        data1.println(0);
        data2.println(3);
        data1.println(0);
        data2.println(3);
            for(int j = 0; j < support.draftCount(); ++j) {
                Player.Card c = support.uniqueCards() ?
                        availables.remove(random.nextInt(availables.size())) :
                        availables.get(random.nextInt(availables.size()));
                data1.println(c.toString());
                data2.println(c.toString());
            }
//        }
    }
}
