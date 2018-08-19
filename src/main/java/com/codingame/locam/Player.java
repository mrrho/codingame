package com.codingame.locam;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

class Player {
    public static final Card[] LISTING = {
            new Creature(1, -1, -1, 1, 2, 1, "------", 1, 0, 0),
            new Creature(2, -1, -1, 1, 1, 2, "------", 0, -1, 0),
            new Creature(3, -1, -1, 1, 2, 2, "------", 0, 0, 0),
            new Creature(4, -1, -1, 2, 1, 5, "------", 0, 0, 0),
            new Creature(5, -1, -1, 2, 4, 1, "------", 0, 0, 0),
            new Creature(6, -1, -1, 2, 3, 2, "------", 0, 0, 0),
            new Creature(7, -1, -1, 2, 2, 2, "-----W", 0, 0, 0),
            new Creature(8, -1, -1, 2, 2, 3, "------", 0, 0, 0),
            new Creature(9, -1, -1, 3, 3, 4, "------", 0, 0, 0),
            new Creature(10, -1, -1, 3, 3, 1, "--D---", 0, 0, 0),
            new Creature(11, -1, -1, 3, 5, 2, "------", 0, 0, 0),
            new Creature(12, -1, -1, 3, 2, 5, "------", 0, 0, 0),
            new Creature(13, -1, -1, 4, 5, 3, "------", 1, -1, 0),
            new Creature(14, -1, -1, 4, 9, 1, "------", 0, 0, 0),
            new Creature(15, -1, -1, 4, 4, 5, "------", 0, 0, 0),
            new Creature(16, -1, -1, 4, 6, 2, "------", 0, 0, 0),
            new Creature(17, -1, -1, 4, 4, 5, "------", 0, 0, 0),
            new Creature(18, -1, -1, 4, 7, 4, "------", 0, 0, 0),
            new Creature(19, -1, -1, 5, 5, 6, "------", 0, 0, 0),
            new Creature(20, -1, -1, 5, 8, 2, "------", 0, 0, 0),
            new Creature(21, -1, -1, 5, 6, 5, "------", 0, 0, 0),
            new Creature(22, -1, -1, 6, 7, 5, "------", 0, 0, 0),
            new Creature(23, -1, -1, 7, 7, 8, "------", 0, 0, 0),
            new Creature(24, -1, -1, 1, 1, 1, "------", 0, -1, 0),
            new Creature(25, -1, -1, 2, 3, 1, "------", -2, -2, 0),
            new Creature(26, -1, -1, 2, 3, 2, "------", 0, -1, 0),
            new Creature(27, -1, -1, 2, 2, 2, "------", 2, 0, 0),
            new Creature(28, -1, -1, 2, 1, 2, "------", 0, 0, 1),
            new Creature(29, -1, -1, 2, 2, 1, "------", 0, 0, 1),
            new Creature(30, -1, -1, 3, 4, 2, "------", 0, -2, 0),
            new Creature(31, -1, -1, 3, 3, 1, "------", 0, -1, 0),
            new Creature(32, -1, -1, 3, 3, 2, "------", 0, 0, 1),
            new Creature(33, -1, -1, 4, 4, 3, "------", 0, 0, 1),
            new Creature(34, -1, -1, 5, 3, 5, "------", 0, 0, 1),
            new Creature(35, -1, -1, 6, 5, 2, "B-----", 0, 0, 1),
            new Creature(36, -1, -1, 6, 6, 4, "------", 0, 0, 2),
            new Creature(37, -1, -1, 6, 5, 7, "------", 0, 0, 1),
            new Creature(38, -1, -1, 1, 1, 3, "--D---", 0, 0, 0),
            new Creature(39, -1, -1, 1, 2, 1, "--D---", 0, 0, 0),
            new Creature(40, -1, -1, 3, 2, 3, "--DG--", 0, 0, 0),
            new Creature(41, -1, -1, 3, 3, 2, "-CD---", 0, 0, 0),
            new Creature(42, -1, -1, 4, 4, 2, "--D---", 0, 0, 0),
            new Creature(43, -1, -1, 6, 5, 5, "--D---", 0, 0, 0),
            new Creature(44, -1, -1, 6, 3, 7, "--D-L-", 0, 0, 0),
            new Creature(45, -1, -1, 6, 6, 5, "B-D---", -3, 0, 0),
            new Creature(46, -1, -1, 9, 7, 7, "--D---", 0, 0, 0),
            new Creature(47, -1, -1, 2, 1, 5, "--D---", 0, 0, 0),
            new Creature(48, -1, -1, 1, 1, 1, "----L-", 0, 0, 0),
            new Creature(49, -1, -1, 2, 1, 2, "---GL-", 0, 0, 0),
            new Creature(50, -1, -1, 3, 3, 2, "----L-", 0, 0, 0),
            new Creature(51, -1, -1, 4, 3, 5, "----L-", 0, 0, 0),
            new Creature(52, -1, -1, 4, 2, 4, "----L-", 0, 0, 0),
            new Creature(53, -1, -1, 4, 1, 1, "-C--L-", 0, 0, 0),
            new Creature(54, -1, -1, 3, 2, 2, "----L-", 0, 0, 0),
            new Creature(55, -1, -1, 2, 0, 5, "---G--", 0, 0, 0),
            new Creature(56, -1, -1, 4, 2, 7, "------", 0, 0, 0),
            new Creature(57, -1, -1, 4, 1, 8, "------", 0, 0, 0),
            new Creature(58, -1, -1, 6, 5, 6, "B-----", 0, 0, 0),
            new Creature(59, -1, -1, 7, 7, 7, "------", 1, -1, 0),
            new Creature(60, -1, -1, 7, 4, 8, "------", 0, 0, 0),
            new Creature(61, -1, -1, 9, 10, 10, "------", 0, 0, 0),
            new Creature(62, -1, -1, 12, 12, 12, "B--G--", 0, 0, 0),
            new Creature(63, -1, -1, 2, 0, 4, "---G-W", 0, 0, 0),
            new Creature(64, -1, -1, 2, 1, 1, "---G-W", 0, 0, 0),
            new Creature(65, -1, -1, 2, 2, 2, "-----W", 0, 0, 0),
            new Creature(66, -1, -1, 5, 5, 1, "-----W", 0, 0, 0),
            new Creature(67, -1, -1, 6, 5, 5, "-----W", 0, -2, 0),
            new Creature(68, -1, -1, 6, 7, 5, "-----W", 0, 0, 0),
            new Creature(69, -1, -1, 3, 4, 4, "B-----", 0, 0, 0),
            new Creature(70, -1, -1, 4, 6, 3, "B-----", 0, 0, 0),
            new Creature(71, -1, -1, 4, 3, 2, "BC----", 0, 0, 0),
            new Creature(72, -1, -1, 4, 5, 3, "B-----", 0, 0, 0),
            new Creature(73, -1, -1, 4, 4, 4, "B-----", 4, 0, 0),
            new Creature(74, -1, -1, 5, 5, 4, "B--G--", 0, 0, 0),
            new Creature(75, -1, -1, 5, 6, 5, "B-----", 0, 0, 0),
            new Creature(76, -1, -1, 6, 5, 5, "B-D---", 0, 0, 0),
            new Creature(77, -1, -1, 7, 7, 7, "B-----", 0, 0, 0),
            new Creature(78, -1, -1, 8, 5, 5, "B-----", 0, -5, 0),
            new Creature(79, -1, -1, 8, 8, 8, "B-----", 0, 0, 0),
            new Creature(80, -1, -1, 8, 8, 8, "B--G--", 0, 0, 1),
            new Creature(81, -1, -1, 9, 6, 6, "BC----", 0, 0, 0),
            new Creature(82, -1, -1, 7, 5, 5, "B-D--W", 0, 0, 0),
            new Creature(83, -1, -1, 0, 1, 1, "-C----", 0, 0, 0),
            new Creature(84, -1, -1, 2, 1, 1, "-CD--W", 0, 0, 0),
            new Creature(85, -1, -1, 3, 2, 3, "-C----", 0, 0, 0),
            new Creature(86, -1, -1, 3, 1, 5, "-C----", 0, 0, 0),
            new Creature(87, -1, -1, 4, 2, 5, "-C-G--", 0, 0, 0),
            new Creature(88, -1, -1, 5, 4, 4, "-C----", 0, 0, 0),
            new Creature(89, -1, -1, 5, 4, 1, "-C----", 2, 0, 0),
            new Creature(90, -1, -1, 8, 5, 5, "-C----", 0, 0, 0),
            new Creature(91, -1, -1, 0, 1, 2, "---G--", 0, 1, 0),
            new Creature(92, -1, -1, 1, 0, 1, "---G--", 2, 0, 0),
            new Creature(93, -1, -1, 1, 2, 1, "---G--", 0, 0, 0),
            new Creature(94, -1, -1, 2, 1, 4, "---G--", 0, 0, 0),
            new Creature(95, -1, -1, 2, 2, 3, "---G--", 0, 0, 0),
            new Creature(96, -1, -1, 2, 3, 2, "---G--", 0, 0, 0),
            new Creature(97, -1, -1, 3, 3, 3, "---G--", 0, 0, 0),
            new Creature(98, -1, -1, 3, 2, 4, "---G--", 0, 0, 0),
            new Creature(99, -1, -1, 3, 2, 5, "---G--", 0, 0, 0),
            new Creature(100, -1, -1, 3, 1, 6, "---G--", 0, 0, 0),
            new Creature(101, -1, -1, 4, 3, 4, "---G--", 0, 0, 0),
            new Creature(102, -1, -1, 4, 3, 3, "---G--", 0, -1, 0),
            new Creature(103, -1, -1, 4, 3, 6, "---G--", 0, 0, 0),
            new Creature(104, -1, -1, 4, 4, 4, "---G--", 0, 0, 0),
            new Creature(105, -1, -1, 5, 4, 6, "---G--", 0, 0, 0),
            new Creature(106, -1, -1, 5, 5, 5, "---G--", 0, 0, 0),
            new Creature(107, -1, -1, 5, 3, 3, "---G--", 3, 0, 0),
            new Creature(108, -1, -1, 5, 2, 6, "---G--", 0, 0, 0),
            new Creature(109, -1, -1, 5, 5, 6, "------", 0, 0, 0),
            new Creature(110, -1, -1, 5, 0, 9, "---G--", 0, 0, 0),
            new Creature(111, -1, -1, 6, 6, 6, "---G--", 0, 0, 0),
            new Creature(112, -1, -1, 6, 4, 7, "---G--", 0, 0, 0),
            new Creature(113, -1, -1, 6, 2, 4, "---G--", 4, 0, 0),
            new Creature(114, -1, -1, 7, 7, 7, "---G--", 0, 0, 0),
            new Creature(115, -1, -1, 8, 5, 5, "---G-W", 0, 0, 0),
            new Creature(116, -1, -1, 12, 8, 8, "BCDGLW", 0, 0, 0),
            new ItemGreen(117, -1, -1, 1, 1, 1, "B-----", 0, 0, 0),
            new ItemGreen(118, -1, -1, 0, 0, 3, "------", 0, 0, 0),
            new ItemGreen(119, -1, -1, 1, 1, 2, "------", 0, 0, 0),
            new ItemGreen(120, -1, -1, 2, 1, 0, "----L-", 0, 0, 0),
            new ItemGreen(121, -1, -1, 2, 0, 3, "------", 0, 0, 1),
            new ItemGreen(122, -1, -1, 2, 1, 3, "---G--", 0, 0, 0),
            new ItemGreen(123, -1, -1, 2, 4, 0, "------", 0, 0, 0),
            new ItemGreen(124, -1, -1, 3, 2, 1, "--D---", 0, 0, 0),
            new ItemGreen(125, -1, -1, 3, 1, 4, "------", 0, 0, 0),
            new ItemGreen(126, -1, -1, 3, 2, 3, "------", 0, 0, 0),
            new ItemGreen(127, -1, -1, 3, 0, 6, "------", 0, 0, 0),
            new ItemGreen(128, -1, -1, 4, 4, 3, "------", 0, 0, 0),
            new ItemGreen(129, -1, -1, 4, 2, 5, "------", 0, 0, 0),
            new ItemGreen(130, -1, -1, 4, 0, 6, "------", 4, 0, 0),
            new ItemGreen(131, -1, -1, 4, 4, 1, "------", 0, 0, 0),
            new ItemGreen(132, -1, -1, 5, 3, 3, "B-----", 0, 0, 0),
            new ItemGreen(133, -1, -1, 5, 4, 0, "-----W", 0, 0, 0),
            new ItemGreen(134, -1, -1, 4, 2, 2, "------", 0, 0, 1),
            new ItemGreen(135, -1, -1, 6, 5, 5, "------", 0, 0, 0),
            new ItemGreen(136, -1, -1, 0, 1, 1, "------", 0, 0, 0),
            new ItemGreen(137, -1, -1, 2, 0, 0, "-----W", 0, 0, 0),
            new ItemGreen(138, -1, -1, 2, 0, 0, "---G--", 0, 0, 1),
            new ItemGreen(139, -1, -1, 4, 0, 0, "----LW", 0, 0, 0),
            new ItemGreen(140, -1, -1, 2, 0, 0, "-C----", 0, 0, 0),
            new ItemRed(141, -1, -1, 0, -1, -1, "------", 0, 0, 0),
            new ItemRed(142, -1, -1, 0, 0, 0, "BCDGLW", 0, 0, 0),
            new ItemRed(143, -1, -1, 0, 0, 0, "---G--", 0, 0, 0),
            new ItemRed(144, -1, -1, 1, 0, -2, "------", 0, 0, 0),
            new ItemRed(145, -1, -1, 3, -2, -2, "------", 0, 0, 0),
            new ItemRed(146, -1, -1, 4, -2, -2, "------", 0, -2, 0),
            new ItemRed(147, -1, -1, 2, 0, -1, "------", 0, 0, 1),
            new ItemRed(148, -1, -1, 2, 0, -2, "BCDGLW", 0, 0, 0),
            new ItemRed(149, -1, -1, 3, 0, 0, "BCDGLW", 0, 0, 1),
            new ItemRed(150, -1, -1, 2, 0, -3, "------", 0, 0, 0),
            new ItemRed(151, -1, -1, 5, 0, -99, "BCDGLW", 0, 0, 0),
            new ItemRed(152, -1, -1, 7, 0, -7, "------", 0, 0, 1),
            new ItemBlue(153, -1, -1, 2, 0, 0, "------", 5, 0, 0),
            new ItemBlue(154, -1, -1, 2, 0, 0, "------", 0, -2, 1),
            new ItemBlue(155, -1, -1, 3, 0, -3, "------", 0, -1, 0),
            new ItemBlue(156, -1, -1, 3, 0, 0, "------", 3, -3, 0),
            new ItemBlue(157, -1, -1, 3, 0, -1, "------", 1, 0, 1),
            new ItemBlue(158, -1, -1, 3, 0, -4, "------", 0, 0, 0),
            new ItemBlue(159, -1, -1, 4, 0, -3, "------", 3, 0, 0),
            new ItemBlue(160, -1, -1, 2, 0, 0, "------", 2, -2, 0)
    };
    private final GameSupport support;
    Actor me, opponent;
    Board board;
    ArtificialIntelligence ai;
    int opponentHand;
    InputStream is;
    PrintStream ps;
    Scanner s;
    double[][] draftWeights, battleWeights;

    public Player(GameSupport gameSupport, InputStream is, PrintStream ps, ArtificialIntelligence ai, String dw, String bw) {
        this.me = new Actor();
        this.opponent = new Actor();
        this.board = new Board();
        this.ai = ai;
        this.support = gameSupport;
        this.is = is;
        this.s = new Scanner(this.is);
        this.ps = ps;
        this.draftWeights = parseWeightsString(dw);
        this.battleWeights = parseWeightsString(bw);
    }

    public static double[][] parseWeightsString(String weights) {
        double[][] w;
        if(weights != null) {
            String[] inputs = weights.split("\\|");
            w = new double[inputs.length][];
            for (int i = 0; i < inputs.length; i++) {
                String[] biases = inputs[i].split(";");
                w[i] = new double[biases.length];
                for (int j = 0; j < biases.length; ++j) {
                    w[i][j] = Double.parseDouble(biases[j]);
                }
            }
            return w;
        }
        return new double[0][0];
    }

    public static String printWeightsString(double[][] weights) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < weights.length; ++i) {
            sb.append(i != 0 ? '|' : "");
            for(int j = 0; j < weights[i].length; ++j) {
                sb.append(j != 0 ? ',' : "").append(weights[i][j]);
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        GameSupport support = new Wood3GameSupport();
        Player game = new Player(support, System.in, System.out, new ArtificialIntelligence(support), null, null);
        game.run();
    }

    public void run() {
        try {
            // game loop
            while (true) {
                gameTurn();
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void gameTurn() {
        Action action;
        List<Card> inPlay = new ArrayList<>();
        System.err.println("Player reading cards");
        me.initialize(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), support);
        opponent.initialize(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt(), support);
        opponentHand = s.nextInt();
        int cardCount = s.nextInt();
        inPlay.clear();
        for (int i = 0; i < cardCount; i++) {
            int number = s.nextInt();
            int id = s.nextInt();
            int location = s.nextInt();
            int type = s.nextInt();
            int cost = s.nextInt();
            int attack = s.nextInt();
            int defense = s.nextInt();
            String abilities = s.next();
            int playerHp = s.nextInt();
            int enemyHp = s.nextInt();
            int draw = s.nextInt();
            inPlay.add(CardFactory.createCard(type, number, id, location, cost, attack, defense, abilities, playerHp, enemyHp, draw));
            if (board.phase != Board.PHASE_DRAFT) {
                System.err.println("Card: #" + number + " id:" + id + " loc:" + location + " cost:" + cost + " atk:" + attack + " def:" + defense);
            }
        }
        System.err.println("Player playing, read " + cardCount);

        if (board.phase == Board.PHASE_DRAFT) {
            action = ai.draftCard(inPlay);
            System.err.println(action.toString());
            board.add(Board.LOCATION_PLAYER_SIDE, inPlay.get(((PickAction) action).card));
        } else {
            board.clear();
            for (Card c : inPlay) {
                board.add(c.location, c);
            }
            action = ai.play(board, me);
        }

        if (board.deck.size() == support.deckSize() && board.phase == Board.PHASE_DRAFT) {
            board.phase = Board.PHASE_BATTLE;
        }
        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.err.println(action.toString());
        ps.println(action.toString());
        ps.flush();
    }

    interface Action {
    }

    interface GameSupport {
        int deckSize();

        int firstPlayerHandSize();

        int secondPlayerHandSize();

        int startingMana();

        int maximumMana();

        int[] allowedCardTypes();

        int startingHealth();

        int maxCreatures();

        int maxCardsInHand();

        String allowedAbilities();

        int draftCount();

        boolean uniqueCards();

        boolean affectPlayerHealth();

        boolean affectOpponentHealth();

        boolean drawCardsActive();

        int[] playerRunes();

        int deckEmptyTurns();
    }

    static class ArtificialIntelligence {
        private final GameSupport support;
        public List<LinkedCards> linkedCards = new ArrayList<>();
        double costScore = 0.0, attackScore = 0.0, defenseScore = 0.0;
        int deckCount = 0;

        public ArtificialIntelligence(GameSupport support) {
            this.support = support;
        }

        public Action draftCard(List<Card> options) {
            // nice and simple for now - highest attack per cost
            double maxScore = 0.0;
            double maxDefense = 0.0;
            int index = -1;
            int i = 0;
            double ratio, defRatio;
            for (Card c : options) {
                ratio = (double) c.attack / (double) c.cost;
                defRatio = (double) c.defense / (double) c.cost;
                if (ratio > maxScore) {
                    maxScore = ratio;
                    maxDefense = defRatio;
                    index = i;
                } else if (ratio == maxScore && defRatio > maxDefense) {
                    maxDefense = defRatio;
                    index = i;
                }
                ++i;
            }
            return new PickAction(index);
        }

        public Action play(Board board, Actor actor) {
            int mana = actor.mana;
            Action a;
            MultiAction ma = null;
            // attack
            List<Card> sortedEnemies = new ArrayList<>(board.opponent);
            System.err.println("Sorted attacking enemies: " + sortedEnemies.size());
            sortedEnemies.sort((o1, o2) -> {
                int cmp = Integer.compare(o2.attack, o1.attack);
                return cmp != 0 ? cmp : Integer.compare(o1.defense, o2.defense);
            });
            List<Card> sortedFriends = new ArrayList<>(board.deck);
            System.err.println("Sorted attacking friends: " + sortedFriends.size());
            sortedFriends.sort((o1, o2) -> Integer.compare(o2.attack, o1.attack));
            Card friend, enemy;
            while (sortedFriends.size() > 0 && sortedEnemies.size() > 0) {
                enemy = sortedEnemies.remove(0);
                System.err.println("Enemy: " + enemy.cost + " " + enemy.attack + " " + enemy.defense);
                for (int i = 0; i < sortedFriends.size(); ++i) {
                    friend = sortedFriends.get(i);
                    a = null;
                    if (friend.attack <= enemy.defense) {
                        a = new AttackAction(friend.id, enemy.id);
                    } else if (i == sortedFriends.size() - 1) {
                        a = new AttackAction(friend.id, enemy.id);
                    }
                    if (a != null) {
                        if (ma == null) {
                            ma = new MultiAction(a);
                        } else {
                            ma.actions.add(a);
                        }
                        enemy.defense -= friend.attack;
                        sortedFriends.remove(i--);
                    }
                }
            }
            System.err.println("Sorted remaining attacking friends: " + sortedFriends.size());
            while (sortedFriends.size() > 0) {
                friend = sortedFriends.remove(0);
                a = new AttackAction(friend.id, -1);
                if (ma == null) {
                    ma = new MultiAction(a);
                } else {
                    ma.actions.add(a);
                }
            }
            sortedFriends = new ArrayList<>(board.hand);
            sortedFriends.sort((o1, o2) -> Double.compare((double) o2.attack / (double) o2.cost, (double) o1.attack / (double) o1.cost));
            System.err.println("Sorted summonable friends: " + sortedFriends.size());
            int total = board.deck.size();
            System.err.println("Mana: " + mana);
            while (!sortedFriends.isEmpty()) {
                friend = sortedFriends.remove(0);
                if (friend.cost <= mana) {
                    a = new SummonAction(friend.id);
                    mana -= friend.cost;
                    if (ma == null) {
                        ma = new MultiAction(a);
                    } else {
                        ma.actions.add(a);
                    }
                    ++total;
                }
                if (total >= support.maxCreatures()) {
                    break;
                }
            }
            if (ma == null || ma.actions.isEmpty()) {
                return PassAction.INSTANCE;
            } else {
                return ma;
            }
        }
    }

    static class Board {
        static final int LOCATION_PLAYER_HAND = 0;
        static final int LOCATION_PLAYER_SIDE = 1;
        static final int LOCATION_OPPONENT_SIDE = -1;
        static final int PHASE_DRAFT = 0;
        static final int PHASE_BATTLE = 1;
        List<Card> opponent = new ArrayList<>();
        List<Card> deck = new ArrayList<>();
        List<Card> hand = new ArrayList<>();
        int phase = PHASE_DRAFT;
        int round = 0;

        public void add(int location, Card card) {
            switch (location) {
                case LOCATION_PLAYER_HAND:
                    hand.add(card);
                    break;
                case LOCATION_PLAYER_SIDE:
                    deck.add(card);
                    break;
                case LOCATION_OPPONENT_SIDE:
                    opponent.add(card);
                    break;
                default:
                    throw new RuntimeException("Invalid location value: " + location);
            }
        }

        public void transition() {
            if (phase != PHASE_DRAFT) {
                throw new RuntimeException("Can only transition from DRAFT phase");
            }
            round = 0;
        }

        public void clear() {
            this.opponent.clear();
            this.hand.clear();
            this.deck.clear();
        }
    }

    static class Actor {
        int health;
        int mana;
        int deck;
        int runes;
        int lowestHealth;
        GameSupport support;

        public void initialize(int health, int mana, int deck, int runes, GameSupport support) {
            this.health = health;
            this.mana = mana;
            this.deck = deck;
            this.runes = runes;
            this.lowestHealth = health;
            this.support = support;
        }

        @Override
        public String toString() {
            return "" + health + " " + mana + " " + deck + " " + runes;
        }

        public void takeDamage(int attack) {
            int h1 = health;
            int h2 = health - attack;
            if(h2 < lowestHealth) {
                int[] thresholds = support.playerRunes();
                for(int i = 0; i < thresholds.length; ++i) {
                    if(thresholds[i] > h2 && thresholds[i] <= lowestHealth) {
                        ++this.runes;
                    }
                }
                lowestHealth = h2;
            }
            health = h2;
        }
    }

    static class Creature extends Card {
        public Creature(int number, int id, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_CREATURE, location, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static class ItemGreen extends Item {
        public ItemGreen(int number, int id, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_ITEM_GREEN, location, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static class ItemRed extends Item {
        public ItemRed(int number, int id, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_ITEM_RED, location, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static class ItemBlue extends Item {
        public ItemBlue(int number, int id, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_ITEM_BLUE, location, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static abstract class Item extends Card {
        public Item(int number, int id, int type, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, type, location, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static abstract class Card {
        static final int TYPE_CREATURE = 0;
        static final int TYPE_ITEM_GREEN = 1; // todo change to actual value
        static final int TYPE_ITEM_RED = 2; // todo change to actual value
        static final int TYPE_ITEM_BLUE = 3; // todo change to actual value
        int number;
        int id;
        int location;
        int type;
        int cost;
        int attack;
        int defense;
        String abilities;
        int playerHp;
        int enemyHp;
        int cardDraw;

        public Card(int number, int id, int type, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            this.number = number;
            this.id = id;
            this.type = type;
            this.location = location;
            this.cost = cost;
            this.attack = attack;
            this.defense = defense;
            this.abilities = abilities;
            this.playerHp = playerHp;
            this.enemyHp = enemyHp;
            this.cardDraw = cardDraw;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Card card = (Card) o;
            return id == card.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "" + number + " " + id + " " + location + " " + type +
                    " " + cost + " " + attack + " " + defense + " " +
                    abilities + " " + playerHp + " " + enemyHp + " " + cardDraw;
        }

        public boolean hasAbility(String ability) {
            return this.abilities.contains(ability);
        }
    }

    static class CardFactory {
        public static Card createCard(Card card) {
            return createCard(card.type, card.number, card.id, card.location, card.cost, card.attack, card.defense, card.abilities, card.playerHp, card.enemyHp, card.cardDraw);
        }

        public static Card createCard(int type, int number, int id, int location, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int draw) {
            switch (type) {
                case Card.TYPE_CREATURE:
                    return new Creature(number, id, location, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                case Card.TYPE_ITEM_GREEN:
                    return new ItemGreen(number, id, location, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                case Card.TYPE_ITEM_RED:
                    return new ItemRed(number, id, location, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                case Card.TYPE_ITEM_BLUE:
                    return new ItemBlue(number, id, location, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                default:
                    throw new RuntimeException("Unexpected card type: " + type);
            }
        }
    }

    static class PassAction implements Action {
        public static final PassAction INSTANCE = new PassAction();

        @Override
        public String toString() {
            return "PASS";
        }
    }

    static class PickAction implements Action {
        private int card;

        public PickAction(int card) {
            this.card = card;
        }

        @Override
        public String toString() {
            return "PICK " + card;
        }
    }

    static class SummonAction implements Action {
        private int id;

        private SummonAction(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "SUMMON " + id;
        }
    }

    static class AttackAction implements Action {
        private int id1, id2;

        private AttackAction(int id1, int id2) {
            this.id1 = id1;
            this.id2 = id2;
        }

        @Override
        public String toString() {
            return "ATTACK " + id1 + " " + id2;
        }
    }

    static class MultiAction implements Action {
        private List<Action> actions = new ArrayList<>();

        private MultiAction(Action first) {
            this.actions.add(first);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(actions.get(0).toString());
            for (int i = 1; i < actions.size(); ++i) {
                sb.append(';').append(actions.get(i).toString());
            }
            sb.append('\n');
            return sb.toString();
        }
    }

    // represents the possible cards drawn by the opponent in the draft
    // phase
    static class LinkedCards {
        private int[] numbers = new int[3];

        public LinkedCards(int a, int b, int c) {
            this.numbers[0] = a;
            this.numbers[1] = b;
            this.numbers[2] = c;
        }
    }

    static abstract class StandardGameSupport implements GameSupport {
        @Override
        public int deckSize() {
            return 30;
        }

        @Override
        public int firstPlayerHandSize() {
            return 4;
        }

        @Override
        public int secondPlayerHandSize() {
            return 5;
        }

        @Override
        public int startingMana() {
            return 1;
        }

        @Override
        public int maximumMana() {
            return 12;
        }

        @Override
        public int[] allowedCardTypes() {
            return new int[]{Card.TYPE_CREATURE};
        }

        @Override
        public int startingHealth() {
            return 30;
        }

        @Override
        public int maxCreatures() {
            return 6;
        }

        @Override
        public int maxCardsInHand() {
            return 8;
        }

        @Override
        public String allowedAbilities() {
            return "------";
        }

        @Override
        public int draftCount() {
            return 3;
        }

        @Override
        public boolean uniqueCards() {
            return false;
        }

        @Override
        public boolean affectPlayerHealth() {
            return false;
        }

        @Override
        public boolean affectOpponentHealth() {
            return false;
        }

        @Override
        public boolean drawCardsActive() {
            return false;
        }

        @Override
        public int[] playerRunes() {
            return new int[0];
        }

        @Override
        public int deckEmptyTurns() {
            return Integer.MAX_VALUE - 1;
        }
    }

    static class Wood3GameSupport extends StandardGameSupport {
    }

    static class Wood2GameSupport extends Wood3GameSupport {
        @Override
        public boolean affectPlayerHealth() {
            return true;
        }

        @Override
        public boolean affectOpponentHealth() {
            return true;
        }

        @Override
        public boolean drawCardsActive() {
            return true;
        }

        @Override
        public String allowedAbilities() {
            return "BC-G--";
        }
    }

    // missed this league
    static class Wood1GameSupport extends Wood2GameSupport {

    }

    static class BronzeGameSupport extends Wood1GameSupport {
        @Override
        public String allowedAbilities() {
            return "BCDGLW";
        }

        @Override
        public int[] allowedCardTypes() {
            return new int[]{Card.TYPE_CREATURE, Card.TYPE_ITEM_BLUE, Card.TYPE_ITEM_GREEN, Card.TYPE_ITEM_RED};
        }

        @Override
        public int[] playerRunes() {
            return new int[]{25, 20, 15, 10, 5};
        }

        @Override
        public int deckEmptyTurns() {
            return 50;
        }
    }
}