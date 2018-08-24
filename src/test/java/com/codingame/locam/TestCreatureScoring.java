package com.codingame.locam;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestCreatureScoring {
    static final String soln = null;
    static double[][] input = new double[1][13];

    @Test
    public void testScoring() {
        System.out.println(Player.NeuralNetwork.weightsToString(new Player.NeuralNetwork(13, 30, 30, 1)
                .think(new double[1][13])));
    }

    @Test
    public void testTraining() {
        Player.NeuralNetwork nn = new Player.NeuralNetwork(13, 30, 30, 1);
        double[][] targets = Player.NeuralNetwork.stringToWeights("1.0;1.0;0.0;1.0");
        double[][] inputs = Player.NeuralNetwork.stringToWeights(
                "0,12,12,12,1,0,0,1,0,0,0,0,0;" +
                        "0,12,8,8,1,1,1,1,1,1,0,0,0;" +
                        "1,0,1,1,0,0,0,0,0,0,0,0,0;" +
                        "2,5,0,-99,0,0,0,0,0,0,0,0,0" +
                "");
        System.out.println(nn.train(inputs, targets));
        Player.Card c;
        for (int i = 0; i < Player.LISTING.length; i++) {
            c = Player.LISTING[i];
            input[0][0] = c.type;
            input[0][1] = c.cost;
            input[0][2] = c.attack;
            input[0][3] = c.defense;
            input[0][4] = c.hasAbility("B") ? 1 : 0;
            input[0][5] = c.hasAbility("C") ? 1 : 0;
            input[0][6] = c.hasAbility("D") ? 1 : 0;
            input[0][7] = c.hasAbility("G") ? 1 : 0;
            input[0][8] = c.hasAbility("L") ? 1 : 0;
            input[0][9] = c.hasAbility("W") ? 1 : 0;
            input[0][10] = c.playerHp;
            input[0][11] = c.enemyHp;
            input[0][12] = c.cardDraw;
            System.out.println(c.toString() + ":" + Player.NeuralNetwork.weightsToString(nn.think(input)));
        }
    }

    @Test
    public void randomCards() {
        List<Player.Card> choices = new ArrayList<>(Arrays.asList(Player.LISTING));
        List<Player.Card> drawn = new ArrayList<>();
        Random r = new Random(System.currentTimeMillis());
        Player.Card c;
        for(int i = 0; i < 1; ++i) {
            for(int k = 0; k < 3; ++k) {
                c = choices.remove(r.nextInt(choices.size()));
                drawn.add(c);
                System.out.println(c.toString());
            }
            System.out.println();
            choices.addAll(drawn);
            choices.clear();
        }
    }
}
