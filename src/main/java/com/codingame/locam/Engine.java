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
            Player.GameSupport support = new Player.BronzeGameSupport();
            String dw1 = null, dw2 = null, bw1 = null, bw2 = null;
            Player client1 = new Player(support, in1.getInputStream(), new PrintStream(out1.getOutputStream()), new Player.ArtificialIntelligence(support), dw1, bw1);
            Player client2 = new Player(support, in2.getInputStream(), new PrintStream(out2.getOutputStream()), new Player.ArtificialIntelligence(support), dw2, bw2);

            PrintStream data1 = new PrintStream(in1.getOutputStream()), data2 = new PrintStream(in2.getOutputStream());
            Scanner input1 = new Scanner(out1.getInputStream()),
                    input2 = new Scanner(out2.getInputStream());

            Player.Actor p1 = new Player.Actor();
            p1.initialize(support.startingHealth(), 0, 0, 0, support);
            Player.Actor p2 = new Player.Actor();
            p2.initialize(support.startingHealth(), 0, 0, 0, support);

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
            for (; ; ) {
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
                for (Player.Card c : hand1) {
                    data1.println(String.format("%d %d 0 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                for (Player.Card c : hand2) {
                    data2.println(String.format("%d %d 0 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                for (Player.Card c : side1) {
                    data1.println(String.format("%d %d 1 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                    data2.println(String.format("%d %d -1 %d %d %d %d %s %d %d %d",
                            c.number, c.id, c.type, c.cost, c.attack, c.defense,
                            c.abilities, c.playerHp, c.enemyHp, c.cardDraw));
                }
                for (Player.Card c : side2) {
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
                processBattleRound(input2, p2, p1, hand2, side2, deck2, side1, support);
                if (maxMana < support.maximumMana()) {
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
        if (line.trim().isEmpty()) {
            line = scanner.nextLine();
        }
        String[] commands = line.toLowerCase().split("\\s+;\\s+");
        String[] tokens;
        int id1, id2;
        List<Player.Card> summoned = new ArrayList<>();
        for (String command : commands) {
            if (command.startsWith("summon")) {
                tokens = command.split("\\s+", 3);
                if (tokens.length < 2) {
                    System.err.println("Malformed summon (SUMMON id): " + command);
                    continue;
                }
                id1 = Integer.parseInt(tokens[1]);
                Player.Card c = fetchCard(id1, hand);
                if(c == null) {
                    System.err.println("Invalid creature id: " + id1);
                    continue;
                }
                if(player1.mana < c.cost) {
                    System.err.println("Player mana too low: " + player1.mana + " < " + c.cost + " creature cost");
                    continue;
                }
                if(countSideCreatures(side) >= support.maxCreatures()) {
                    System.err.println("Cannot summon - too many creatures already on side (max=" + support.maxCreatures() + ")");
                    continue;
                }
                player1.mana -= c.cost;
                side.add(c);
                hand.remove(c);
                if (!hasAbility(support.allowedAbilities(), "C") || !c.hasAbility("C")) {
                    summoned.add(c);
                }
                if(support.affectPlayerHealth()) {
                    player1.adjustHealth(c.playerHp);
                }
                if(support.affectOpponentHealth()) {
                    player2.adjustHealth(c.enemyHp);
                }
                if(support.drawCardsActive()) {
                    player1.draws += c.cardDraw;
                }
                if (tokens.length > 2) {
                    System.err.println(tokens[2]);
                }
            } else if (command.startsWith("attack")) {
                tokens = command.split("\\s+", 4);
                if (tokens.length < 3) {
                    System.err.println("Malformed attack (ATTACK id1 id2|-1): " + command);
                    continue;
                }
                id1 = Integer.parseInt(tokens[1]);
                id2 = Integer.parseInt(tokens[2]);
                Player.Card o;
                for (Player.Card c : side) {
                    if (c.id == id1 && c.type == Player.Card.TYPE_CREATURE && !summoned.contains(c)) {
                        o = fetchCard(id2, other);
                        if (hasAbility(support.allowedAbilities(), "G") && opponentHasGuards(other) && !hasAbility(o.abilities, "G")) {
                            System.err.println("Opponent has live Guard creature(s) in play but one of them wasn't attacked");
                            continue;
                        }
                        if (o == null) {
                            if (id2 == -1) {
                                System.err.println("Opponent hit: " + c.attack + ", " + player2.health + " hp left");
                                if(support.affectPlayerHealth() && hasAbility(support.allowedAbilities(), "D") && c.hasAbility("D")) {
                                    System.err.println("" + c.attack + " drained hp added to player");
                                    player1.adjustHealth(c.attack);
                                }
                                if(support.affectOpponentHealth()) {
                                    player2.adjustHealth(c.attack);
                                }
                            } else {
                                System.err.println("Invalid attack target id: " + id2);
                            }
                        } else if (hasAbility(support.allowedAbilities(), "W") && o.hasAbility("W")) {
                            System.err.println("Creature " + id2 + " ward broken!");
                            o.abilities = o.abilities.substring(0, 5) + "-";
                        } else {
                            if(support.affectPlayerHealth() && hasAbility(support.allowedAbilities(), "D") && c.hasAbility("D")) {
                                System.err.println("" + (c.attack > o.defense ? o.defense : c.attack) + " drained hp added to player");
                                player1.adjustHealth(c.attack > o.defense ? o.defense : c.attack);
                            }
                            if(hasAbility(support.allowedAbilities(), "L") && c.hasAbility("L")) {
                                if(hasAbility(support.allowedAbilities(), "B") && c.hasAbility("B")) {
                                    if(support.affectOpponentHealth() && c.attack > o.defense) {
                                        System.err.println("Breakthrough hit opponent for " + (c.attack - o.defense) + " hp");
                                        player2.adjustHealth(o.defense - c.attack);
                                    }
                                }
                                System.err.println("Creature " + id2 + " hit lethally for " + o.defense + " hp damage");
                                o.defense = 0;
                            } else {
                                System.err.println("Creature " + id2 + " hit for " + (c.attack > o.defense ? o.defense : c.attack) + " hp damage");
                                o.defense -= c.attack > o.defense ? o.defense : c.attack;
                            }
                        }
                    } else {
                        System.err.println("Could not fulfil attack command: " + command);
                        if (c.type != Player.Card.TYPE_CREATURE) {
                            System.err.println("Card specified in attack is not a creature");
                        } else if (summoned.contains(c)) {
                            System.err.println("Card specified was summoned this turn and doesn't have Charge");
                        }
                    }
                }
                if(tokens.length > 3) {
                    System.err.println(tokens[3]);
                }
            } else if (command.startsWith("use")) {
                tokens = command.split("\\s+", 4);
                if(tokens.length < 3) {
                    System.err.println("Malformed attack (USE id1 id2|-1): " + command);
                    continue;
                }
                id1 = Integer.parseInt(tokens[1]);
                id2 = Integer.parseInt(tokens[2]);
                Player.Card o;
                Player.Card c;
                c = fetchCard(id1, hand);
                if(c == null) {
                    System.err.println("Invalid item id: " + id1);
                    continue;
                }
                o = fetchCard(id2, side);
                if(o == null) {
                    o = fetchCard(id2, other);
                }
                if(o == null && id2 != -1) {
                    System.err.println("Invalid target id: " + id2);
                    continue;
                }
                if(o != null && o.type != Player.Card.TYPE_CREATURE) {
                    System.err.println("Target is not a creature: " + id2);
                    continue;
                }
                if(c.type == Player.Card.TYPE_CREATURE) {
                    System.err.println("Card specified is not an item");
                    continue;
                }
                if(c.cost > player1.mana) {
                    System.err.println("Not enough mana to use item " + id1 + " " + player1.mana + " < " + c.cost + " cost");
                    continue;
                }
                if(support.affectPlayerHealth() && c.playerHp != 0) {
                    System.err.println("Item heals player for " + c.playerHp + " hp");
                    player1.adjustHealth(c.playerHp);
                }
                if(support.affectOpponentHealth() && c.enemyHp > 0) {
                    System.err.println("Item damages opponent for " + c.enemyHp + " hp");
                    player2.adjustHealth(c.enemyHp);
                }
                if(support.drawCardsActive() && c.cardDraw != 0) {
                    System.err.println("Item adds " + c.cardDraw + " draws to the next round");
                    player1.addDraws(c.cardDraw);
                }
                if(o != null) {
                    StringBuilder sb = new StringBuilder();
                    if(c.type == Player.Card.TYPE_ITEM_RED) {
                        sb.append(c.hasAbility("B") ? '-' : o.abilities.charAt(0));
                        sb.append(c.hasAbility("C") ? '-' : o.abilities.charAt(1));
                        sb.append(c.hasAbility("D") ? '-' : o.abilities.charAt(2));
                        sb.append(c.hasAbility("G") ? '-' : o.abilities.charAt(3));
                        sb.append(c.hasAbility("L") ? '-' : o.abilities.charAt(4));
                        sb.append(c.hasAbility("W") ? '-' : o.abilities.charAt(5));
                    } else {
                        sb.append(c.hasAbility("B") ? 'B' : o.abilities.charAt(0));
                        sb.append(c.hasAbility("C") ? 'C' : o.abilities.charAt(1));
                        sb.append(c.hasAbility("D") ? 'D' : o.abilities.charAt(2));
                        sb.append(c.hasAbility("G") ? 'G' : o.abilities.charAt(3));
                        sb.append(c.hasAbility("L") ? 'L' : o.abilities.charAt(4));
                        sb.append(c.hasAbility("W") ? 'W' : o.abilities.charAt(5));
                    }
                    o.abilities = sb.toString();
                    o.attack += c.attack;
                    if(c.defense < 0 && hasAbility(support.allowedAbilities(), "W") && o.hasAbility("W")) {
                        o.abilities = o.abilities.substring(0, 5) + "-";
                    } else {
                        o.defense += c.defense;
                    }
                }
                hand.remove(c);
                if(tokens.length > 3) {
                    System.err.println(tokens[3]);
                }
            } else if (command.startsWith("pass")) {
                // nothing to do
                break;
            } else if (command.trim().isEmpty()) {
                throw new IllegalArgumentException("No command specified - use pass");
            } else {
                throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
    }

    private boolean opponentHasGuards(List<Player.Card> other) {
        for (Player.Card o : other) {
            if (o.defense > 0 && hasAbility(o.abilities, "G")) {
                return true;
            }
        }
        return false;
    }

    private boolean hasAbility(String abilities, String ability) {
        return abilities.contains(ability);
    }

    private Player.Card fetchCard(int id, List<Player.Card> other) {
        for (Player.Card o : other) {
            if (o.id == id) {
                return o;
            }
        }
        return null;
    }

    private int countSideCreatures(List<Player.Card> side) {
        int n = 0;
        for (Player.Card c : side) {
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
