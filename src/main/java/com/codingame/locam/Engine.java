package com.codingame.locam;

import com.codingame.InputOutputStream;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class Engine {
    public static void main(String[] args) throws InterruptedException {
        new Engine().start();
    }

    private void start() throws InterruptedException {
        try {
            InputOutputStream in1, in2, out1, out2;
            in1 = new InputOutputStream();
            in2 = new InputOutputStream();
            out1 = new InputOutputStream();
            out2 = new InputOutputStream();
            Player.GameSupport support = new Player.Wood3GameSupport();
            String dw1 = null, dw2 = null, bw1 = null, bw2 = null;
            Player client1 = new Player(support, in1.getInputStream(), new PrintStream(out1.getOutputStream()), new Player.ArtificialIntelligence(support), dw1, bw1);
            Player client2 = new Player(support, in2.getInputStream(), new PrintStream(out2.getOutputStream()), new Player.ArtificialIntelligence(support), dw2, bw2);

            PrintStream data1 = new PrintStream(in1.getOutputStream()), data2 = new PrintStream(in2.getOutputStream());
            Scanner input1 = new Scanner(out1.getInputStream()),
                    input2 = new Scanner(out2.getInputStream());

            Player.Actor p1 = new Player.Actor();
            p1.initialize(support.startingHealth(), 0, 0, 0);
            Player.Actor p2 = new Player.Actor();
            p2.initialize(support.startingHealth(), 0, 0, 0);

            Random random = new Random(System.currentTimeMillis());

            int phase = Player.Board.PHASE_DRAFT;
            List<Player.Card> availables = new ArrayList<>();
            Set<Integer> allowedTypes = new HashSet<>();
            for (int v : support.allowedCardTypes()) {
                allowedTypes.add(v);
            }
            for (int i = 0; i < Player.LISTING.length; ++i) {
                if (allowedTypes.contains(Player.LISTING[i].type)) {
                    availables.add(Player.LISTING[i]);
                }
            }
            if (support.deckSize() * support.draftCount() > availables.size() && support.uniqueCards()) {
                throw new RuntimeException("Not enough cards");
            }
            List<Player.Card> deck1 = new ArrayList<>();
            List<Player.Card> deck2 = new ArrayList<>();
            List<Player.Card> draftChoice = new ArrayList<>();
            for (int i = 0; i < support.deckSize(); ++i) {
                System.err.println("Engine printing");
                data1.println(p1.toString());
                data1.println(p2.toString());
                data2.println(p1.toString());
                data2.println(p2.toString());
                data1.println(0);
                data2.println(0);
                data1.println(3);
                data2.println(3);
                Player.Card c;
                for (int j = 0; j < support.draftCount(); ++j) {
                    c = availables.remove(random.nextInt(availables.size()));
                    draftChoice.add(c);
                    data1.println(c.toString());
                    data2.println(c.toString());
                }
                System.err.println("Engine Printed");
                availables.addAll(draftChoice);

                client1.gameTurn();
                client2.gameTurn();

                deck1.add(processDraftRound(input1, deck1.size(), draftChoice, support));
                deck2.add(processDraftRound(input2, deck2.size(), draftChoice, support));
                draftChoice.clear();
            }
            // playerXCards are the total number of cards for each layer
            Collections.shuffle(deck1);
            Collections.shuffle(deck2);
            // handX are the cards drawable by the player
            List<Player.Card> hand1 = new ArrayList<>();
            List<Player.Card> hand2 = new ArrayList<>();
            for (int i = 0; i < support.firstPlayerHandSize(); i++) {
                hand1.add(deck1.remove(0));
            }
            for (int i = 0; i < support.secondPlayerHandSize(); i++) {
                hand2.add(deck2.remove(0));
            }
            // sideX are the cards in play
            List<Player.Card> side1 = new ArrayList<>();
            List<Player.Card> side2 = new ArrayList<>();
            int maxMana = support.startingMana();
            for(;;) {
                p1.mana = maxMana;
                p2.mana = maxMana;
                data1.println(p1.toString());
                data1.println(p2.toString());
                data2.println(p1.toString());
                data2.println(p2.toString());
                data1.println(hand2.size());
                data2.println(hand1.size());
                data1.println(hand1.size() + side1.size() + side2.size());
                data2.println(hand2.size() + side1.size() + side2.size());
                for (Player.Card c: hand1) {
                    data1.println(String.format("%d %d 0 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                for (Player.Card c: hand2) {
                    data2.println(String.format("%d %d 0 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                for(Player.Card c: side1) {
                    data1.println(String.format("%d %d 1 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                    data2.println(String.format("%d %d -1 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                for(Player.Card c: side2) {
                    data2.println(String.format("%d %d 1 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                    data1.println(String.format("%d %d -1 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                client1.gameTurn();
                client2.gameTurn();
                processBattleRound(input1, p1, p2, hand1, side1, deck1, side2, support);
                processBattleRound(input2, p1, p2, hand2, side2, deck2, side1, support);
                if(maxMana < support.maximumMana()) {
                    p1.mana = maxMana;
                    p2.mana = maxMana;
                }
                break;
            }
        } finally {
        }
    }

    private void processBattleRound(Scanner scanner, Player.Actor player1,
                                    Player.Actor player2, List<Player.Card> hand,
                                    List<Player.Card> side, List<Player.Card> deck,
                                    List<Player.Card> other, Player.GameSupport support) {
        String line = scanner.nextLine();
        if(line.trim().isEmpty()) {
            line = scanner.nextLine();
        }
        String[] commands = line.toLowerCase().split("\\s+;\\s+");
        String[] tokens;
        int id1, id2;
        List<Player.Card> summoned = new ArrayList<>();
        List<Player.Card> enemyWardsUsed = new ArrayList<>();
        for(String command: commands) {
            if (command.startsWith("summon")) {
                tokens = command.split("\\s+", 3);
                if(tokens.length < 2) {
                    throw new IllegalArgumentException("SUMMON requires id");
                }
                id1 = Integer.parseInt(tokens[1]);
                for(Player.Card c: hand) {
                    if(c.id == id1 && player1.mana >= c.cost && countSideCreatures(side) < support.maxCreatures()) {
                        player1.mana -= c.cost;
                        side.add(c);
                        hand.remove(c);
                        if(!c.hasAbility("C")) {
                            summoned.add(c);
                        }
                    } else if(c.id == id1) {
                        System.err.println("Could not summon creature " + id1 + " due to constraints");
                        if(player1.mana < c.cost) {
                            System.err.println("Mana too low: " + c.cost + " > " + player1.mana);
                        } else if(countSideCreatures(side) >= support.maxCreatures()) {
                            System.err.println("Maximum creatures (" + support.maxCreatures() + ") already summoned");
                        }
                    }
                }
                if(tokens.length > 2) {
                    System.err.println(tokens[2]);
                }
            } else if (command.startsWith("attack")) {

            } else if (command.startsWith("use")) {

            } else if (command.startsWith("pass")) {
                // nothing to do
                break;
            } else if(command.trim().isEmpty()) {
                throw new IllegalArgumentException("No command specified - use pass");
            } else {
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
    }

    private int countSideCreatures(List<Player.Card> side) {
        int n = 0;
        for(Player.Card c: side) {
            n += c.type == Player.Card.TYPE_CREATURE ? 1 : 0;
        }
        return n;
    }

    public Player.Card processDraftRound(Scanner scanner, int id, List<Player.Card> draftChoice, Player.GameSupport support) {
//        if (scanner.hasNextLine()) {
//            scanner.nextLine();
//        }
        String command = scanner.next().toLowerCase();
        Player.Card c;
        if (command.startsWith("pass")) {
            c = draftChoice.get(0);
        } else if (command.startsWith("pick")) {
            int idx = scanner.nextInt();
            if ((idx < 0) || idx >= support.draftCount()) {
                throw new IllegalArgumentException("Invalid pick index: " + idx);
            }
            c = draftChoice.get(idx);
        } else {
            throw new IllegalArgumentException("Invalid command: " + command + "\nexpected PASS or PICK");
        }
        c = Player.CardFactory.createCard(c);
        c.id = id;
        return c;
    }
}
