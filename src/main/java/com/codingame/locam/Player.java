package com.codingame.locam;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    static final double[] CREATURE_DRAW_P;

    static {
        int max = -Integer.MAX_VALUE;
        for (int i = 0; i < LISTING.length; ++i) {
            if (LISTING[i].type != Card.TYPE_CREATURE) {
                continue;
            }
            if (LISTING[i].cost > max) {
                max = LISTING[i].cost;
            }
        }
        CREATURE_DRAW_P = new double[max + 1];
        for (int i = 0; i < LISTING.length; ++i) {
            if (LISTING[i].type != Card.TYPE_CREATURE) {
                continue;
            }
            CREATURE_DRAW_P[LISTING[i].cost] += 1.0;
        }
        for (int i = 0; i < CREATURE_DRAW_P.length; ++i) {
            CREATURE_DRAW_P[i] /= (double) LISTING.length;
        }
    }

    private final GameSupport support;
    Actor me, opponent;
    Board board;
    ArtificialIntelligence ai;
    int opponentHand;
    InputStream is;
    PrintStream ps;
    Scanner s;

    public Player(GameSupport gameSupport, InputStream is, PrintStream ps, ArtificialIntelligence ai) {
        this.me = new Actor();
        this.opponent = new Actor();
        this.board = new Board();
        this.ai = ai;
        this.support = gameSupport;
        this.is = is;
        this.s = new Scanner(this.is);
        this.ps = ps;
    }

    public static double[][] parseWeightsString(String weights) {
        double[][] w;
        if (weights != null) {
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
        for (int i = 0; i < weights.length; ++i) {
            sb.append(i != 0 ? '|' : "");
            for (int j = 0; j < weights[i].length; ++j) {
                sb.append(j != 0 ? ',' : "").append(weights[i][j]);
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        GameSupport support = new BronzeGameSupport();
        Player game = new Player(support, System.in, System.out, new ArtificialIntelligence(support, new double[] {
                0, 1.5, 1, 1, 1.5, 0.5, 1.5, 0.25, 0.5, 1, 1, 0.33,
                0, 0.5, 0.5, 0.1, 0.15, 0.1, 0.1, 0.1, 0.1, 0.25, 0.25, 0.33,
                0, 0.5, 0.5, 0.5, 1.5, 0.5, 0.75, 1, 1, 0.25, 1.0, 0.33,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 3., 4., 0.33,
                0, 5, 3, 2, 2.5, 2.5, 2, 1.5, 1, 0.5, 0.5, 0.5, 1
        }));
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
                System.err.println("Card: #" + number + " id:" + id + " loc:" + location + " cost:" + cost + " atk:" + attack + " def:" + defense + " " + inPlay.get(inPlay.size() - 1).getClass().getSimpleName());
            }
        }
        System.err.println("Player playing, read " + cardCount);

        board.playerSide.clear();
        board.playerHand.clear();
        board.opponentSide.clear();
        if (board.phase == Board.PHASE_DRAFT) {
            action = ai.draftCard(inPlay);
            board.add(Board.LOCATION_PLAYER_DECK, inPlay.get(((PickAction) action).card));
            board.add(Board.LOCATION_OPPONENT_DECK, new Player.PossibilityCard(inPlay));
        } else {
            board.removeDead(inPlay);
            for (Card c : inPlay) {
                board.add(c.location, c);
            }
            action = ai.play(board, me);
        }

        if (board.playerDeck.size() == support.deckSize() && board.phase == Board.PHASE_DRAFT) {
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
        double[] weights;

        public ArtificialIntelligence(GameSupport support, double[] weights) {
            this.support = support;
            this.weights = weights;
        }

        public Action draftCard(List<Card> options) {
            // nice and simple for now - highest attack per cost
            double maxScore = 0.0;
            int index = -1;
            int i = 0;
            double score;
            for (Card c : options) {
                score = calculateScore(c, weights);
                if (score > maxScore) {
                    maxScore = score;
                    index = i;
                }
                ++i;
            }
            return new PickAction(index);
        }

        private double calculateScore(Card c, double[] weights) {
            double score = 0.0;
            if(c.type == Card.TYPE_CREATURE) {
                score = weights[0] * c.cost +
                        weights[1] * c.attack +
                        weights[2] * c.defense +
                        weights[3] * (c.hasAbility("B") ? 1 : 0) +
                        weights[4] * (c.hasAbility("C") ? 1 : 0) +
                        weights[5] * (c.hasAbility("D") ? 1 : 0) +
                        weights[6] * (c.hasAbility("G") ? 1 : 0) +
                        weights[7] * (c.hasAbility("L") ? 1 : 0) +
                        weights[8] * (c.hasAbility("W") ? 1 : 0) +
                        weights[9] * c.playerHp +
                        weights[10] * -c.enemyHp +
                        weights[11] * c.cardDraw +
                        weights[48 + c.cost] * c.cost
                ;
            } else if(c.type == Card.TYPE_ITEM_GREEN) {
                score = weights[12] * c.cost +
                        weights[13] * c.attack +
                        weights[14] * c.defense +
                        weights[15] * (c.hasAbility("B") ? 1 : 0) +
                        weights[16] * (c.hasAbility("C") ? 1 : 0) +
                        weights[17] * (c.hasAbility("D") ? 1 : 0) +
                        weights[18] * (c.hasAbility("G") ? 1 : 0) +
                        weights[19] * (c.hasAbility("L") ? 1 : 0) +
                        weights[20] * (c.hasAbility("W") ? 1 : 0) +
                        weights[21] * c.playerHp +
                        weights[22] * -c.enemyHp +
                        weights[23] * c.cardDraw;
            } else if(c.type == Card.TYPE_ITEM_RED) {
                score = weights[24] * c.cost +
                        weights[25] * -c.attack +
                        weights[26] * -c.defense +
                        weights[27] * (c.hasAbility("B") ? 1 : 0) +
                        weights[28] * (c.hasAbility("C") ? 1 : 0) +
                        weights[29] * (c.hasAbility("D") ? 1 : 0) +
                        weights[30] * (c.hasAbility("G") ? 1 : 0) +
                        weights[31] * (c.hasAbility("L") ? 1 : 0) +
                        weights[32] * (c.hasAbility("W") ? 1 : 0) +
                        weights[33] * c.playerHp +
                        weights[34] * -c.enemyHp +
                        weights[35] * c.cardDraw;
            } else if(c.type == Card.TYPE_ITEM_BLUE) {
                score = weights[36] * c.cost +
                        weights[37] * c.attack +
                        weights[38] * c.defense +
                        weights[39] * (c.hasAbility("B") ? 1 : 0) +
                        weights[40] * (c.hasAbility("C") ? 1 : 0) +
                        weights[41] * (c.hasAbility("D") ? 1 : 0) +
                        weights[42] * (c.hasAbility("G") ? 1 : 0) +
                        weights[43] * (c.hasAbility("L") ? 1 : 0) +
                        weights[44] * (c.hasAbility("W") ? 1 : 0) +
                        weights[45] * c.playerHp +
                        weights[46] * -c.enemyHp +
                        weights[47] * c.cardDraw;
            }
            return score;
        }

        public Action play(Board board, Actor actor) {
            int mana = actor.mana;
            Action a;
            MultiAction ma = null;
            // summon
            List<Card> sortedFriends;
            List<Card> summoned = new ArrayList<>();
            Card friend, enemy;
            sortedFriends = board.playerHand.stream().filter((card -> card.type == Card.TYPE_CREATURE)).collect(Collectors.toList());
            sortedFriends.sort(new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    return Integer.compare(o2.cost, o1.cost);
                }
            });
            Collections.reverse(sortedFriends);
            System.err.println("Sorted summonable friends: " + sortedFriends.size());
            int total = board.playerSide.size();
            System.err.println("Mana: " + mana);
            while (!sortedFriends.isEmpty()) {
                friend = sortedFriends.remove(0);
                if (friend.cost <= mana) {
                    a = new SummonAction(friend.id);
                    if(!friend.hasAbility("C")) {
                        summoned.add(friend);
                    }
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
            // check items
            int finalMana = mana;
            List<Card> sortedItems = board.playerHand.stream().filter(card -> card.type != Card.TYPE_CREATURE && card.cost <= finalMana).collect(Collectors.toList());
            sortedItems.sort(Comparator.comparingDouble(o3 -> calculateScore(o3, weights)));
            List<Card> validTargets = new ArrayList<>();
            a = null;
            for(Card c: sortedItems) {
                if(c.type == Card.TYPE_ITEM_RED) {
                    for(Card o: board.opponentSide) {
                        if(o.defense <= 0) {
                            continue;
                        }
                        if(c.hasAbility("B") && o.hasAbility("B")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("C") && o.hasAbility("C")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("D") && o.hasAbility("D")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("G") && o.hasAbility("G")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("L") && o.hasAbility("L")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("W") && o.hasAbility("W")) {
                            validTargets.add(o);
                            continue;
                        }
                        validTargets.add(o);
                    }
                    validTargets.sort(Comparator.comparingDouble(c2 -> calculateScore(c2, weights)));
                } else if(c.type == Card.TYPE_ITEM_GREEN) {
                    for(Card o: board.playerSide) {
                        if(c.hasAbility("B") && !o.hasAbility("B")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("C") && !o.hasAbility("C")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("D") && !o.hasAbility("D")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("G") && !o.hasAbility("G")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("L") && !o.hasAbility("L")) {
                            validTargets.add(o);
                            continue;
                        }
                        if(c.hasAbility("W") && !o.hasAbility("W")) {
                            validTargets.add(o);
                            continue;
                        }
                        validTargets.add(o);
                    }
                    validTargets.sort(Comparator.comparingDouble(c2 -> calculateScore(c2, weights)));
                    Collections.reverse(validTargets);
                }
                Card o;
                if(!validTargets.isEmpty()) {
                    a = new UseAction(c.id, validTargets.get(0).id);
                    o = validTargets.get(0);
                    o.defense += c.defense;
                    mana -= c.cost;
                } else if(c.type == Card.TYPE_ITEM_BLUE) {
                    a = new UseAction(c.id, -1);
                    mana -= c.cost;
                }
                if(a != null) {
                    if (ma == null) {
                        ma = new MultiAction(a);
                    } else {
                        ma.actions.add(a);
                    }
                }
                board.playerHand.remove(c);
                validTargets.clear();
            }
            // attack
            List<Card> sortedEnemies = board.opponentSide.stream().filter(c -> c.hasAbility("G")).collect(Collectors.toList());
            if(sortedEnemies.size() == 0) {
                sortedEnemies = new ArrayList<>(board.opponentSide);
            }
            System.err.println("Sorted attacking enemies: " + sortedEnemies.size());
            sortedEnemies.sort(new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    return Integer.compare(o1.attack, o2.attack);
                }
            });
            sortedFriends = board.playerSide.stream().filter(c -> !summoned.contains(c)).collect(Collectors.toList());
            System.err.println("Sorted attacking friends: " + sortedFriends.size());
            sortedFriends.sort(Comparator.comparingDouble(o3 -> calculateScore(o3, weights)));
            Collections.reverse(sortedFriends);
            while (sortedFriends.size() > 0 && sortedEnemies.size() > 0) {
                enemy = sortedEnemies.get(0);
                if(enemy.hasAbility("W") && sortedFriends.size() > 1) {
                    // attack with lowest attack creature first
                    sortedFriends.sort(new Comparator<Card>() {
                        @Override
                        public int compare(Card o1, Card o2) {
                            return Integer.compare(o2.attack, o1.attack);
                        }
                    });
                    friend = sortedFriends.get(0);
                    a = new AttackAction(friend.id, enemy.id);
                    if(ma == null) {
                        ma = new MultiAction(a);
                    } else {
                        ma.actions.add(a);
                    }
                    sortedFriends.remove(friend);
                    sortedFriends.sort(new Comparator<Card>() {
                        @Override
                        public int compare(Card o1, Card o2) {
                            return Double.compare(calculateScore(o2, weights), calculateScore(o1, weights));
                        }
                    });
                } else {
                    sortedEnemies.remove(enemy);
                    if(sortedEnemies.size() == 0) {
                        sortedEnemies = board.opponentSide.stream().filter(c -> !c.hasAbility("G")).collect(Collectors.toList());
                    }
                    if(sortedEnemies.size() == 0) {
                        break;
                    }
                }
                int i = 0;
                for (i = 0; i < sortedFriends.size(); i++) {
                    friend = sortedFriends.get(0);
                    if(friend.attack >= enemy.attack) {
                        break;
                    }
                }
                if(i == sortedFriends.size()) {
                    i = sortedFriends.size() - 1;
                }
                friend = sortedFriends.get(i);
                enemy.defense -= friend.attack;
                a = new AttackAction(friend.id, enemy.id);
                if(ma == null) {
                    ma = new MultiAction(a);
                } else {
                    ma.actions.add(a);
                }
                sortedFriends.remove(friend);

//                enemy = sortedEnemies.remove(0);
//                System.err.println("Enemy: " + enemy.cost + " " + enemy.attack + " " + enemy.defense);
//                for (int i = 0; i < sortedFriends.size(); ++i) {
//                    friend = sortedFriends.get(i);
//                    a = null;
//                    if (friend.attack <= enemy.defense) {
//                        a = new AttackAction(friend.id, enemy.id);
//                    } else if (i == sortedFriends.size() - 1) {
//                        a = new AttackAction(friend.id, enemy.id);
//                    }
//                    if (a != null) {
//                        if (ma == null) {
//                            ma = new MultiAction(a);
//                        } else {
//                            ma.actions.add(a);
//                        }
//                        enemy.defense -= friend.attack;
//                        sortedFriends.remove(i--);
//                    }
//                    if(enemy.defense <= 0) {
//                        break;
//                    }
//                }
//                if(sortedEnemies.size() == 0) {
//                    sortedEnemies = board.opponentSide.stream().filter(c -> c.defense > 0).collect(Collectors.toList());
//                    sortedEnemies.sort(new Comparator<Card>() {
//                        @Override
//                        public int compare(Card o1, Card o2) {
//                            return Integer.compare(o2.attack, o1.attack);
//                        }
//                    });
//                }
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
            if (ma == null || ma.actions.isEmpty()) {
                return PassAction.INSTANCE;
            } else {
                return ma;
            }
        }
    }

    static class NeuralNetwork {
        static Random random = new Random(System.currentTimeMillis());
        NeuronLayer layers[];

        public NeuralNetwork(String weightString) {
            String[] layerStrings = weightString.split("\\|");
            layers = new NeuronLayer[layerStrings.length];
            for (int i = 0; i < layerStrings.length; i++) {
                layers[i] = new NeuronLayer(stringToWeights(layerStrings[i]));
            }
        }

        public NeuralNetwork(int... layerSpec) {
            layers = new NeuronLayer[layerSpec.length >> 1];
            for (int i = 0; i < layers.length; i++) {
                layers[i] = new NeuronLayer(layerSpec[i << 1], layerSpec[i * 2 + 1]);
            }
        }

        public static String weightsToString(double[][] w) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < w.length; i++) {
                sb.append(i == 0 ? "" : ';');
                for (int j = 0; j < w[0].length; j++) {
                    sb.append(j == 0 ? "" : ',').append(w[i][j]);
                }
            }
            return sb.toString();
        }

        public double[][] think(double[][] inputs) {
            double[][] v = inputs;
            for (NeuronLayer l : layers) {
                l.outputs = l.compute(v);
                v = l.outputs;
            }
            return v;
        }

        public String train(double[][] inputs, double[][] outputs) {
            double learningRate = 0.2;
            double avge;
            for (int a = 0; a < 10000; ++a) {
                think(inputs);
                for (int i = layers.length - 1; i >= 0; --i) {
                    if (i != layers.length - 1) {
                        layers[i].errors = NNMath.matrixMultiply(layers[i + 1].deltas, NNMath.matrixTranspose(layers[i + 1].weights));
                    } else {
                        layers[i].errors = NNMath.matrixSubtract(outputs, layers[i].outputs);
                    }
                    layers[i].deltas = NNMath.scalarMultily(layers[i].errors, NNMath.apply(layers[i].outputs, NNMath::sigmoidDerivative));
                }
                avge = averageError(layers);
                for (int i = 0; i < layers.length; ++i) {
                    if (i != 0) {
                        layers[i].adjusts = NNMath.apply(NNMath.matrixMultiply(NNMath.matrixTranspose(layers[i - 1].outputs), layers[i].deltas), (x) -> learningRate * x);
                    } else {
                        layers[i].adjusts = NNMath.apply(NNMath.matrixMultiply(NNMath.matrixTranspose(inputs), layers[i].deltas), (x) -> learningRate * x);
                    }
                    layers[i].adjustWeights();
                }
                if(avge < 1E-40) {
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < layers.length; ++i) {
                sb.append(i != 0 ? '|' : "").append(weightsToString(layers[i].weights));
            }
            return sb.toString();
        }

        private double averageError(NeuronLayer[] layers) {
            double e = 0.0;
            int n = 0;
            for (NeuronLayer layer : layers) {
                for (int j = 0; j < layer.errors.length; j++) {
                    for (int k = 0; k < layer.errors[j].length; k++) {
                        e += layer.errors[j][k];
                        ++n;
                    }
                }
            }
            return e / (double)n;
        }

        public static double[][] stringToWeights(String s) {
            double[][] w;
            String[] neurons = s.split(";");
            w = new double[neurons.length][];
            for (int i = 0; i < w.length; ++i) {
                String[] inputs = neurons[i].split(",");
                w[i] = new double[inputs.length];
                for (int j = 0; j < inputs.length; j++) {
                    w[i][j] = Double.parseDouble(inputs[j]);
                }
            }
            return w;
        }

        static class NeuronLayer {
            double[][] weights;
            double[][] outputs;
            double[][] errors;
            double[][] deltas;
            double[][] adjusts;

            public NeuronLayer(int inputsPerNeuron, int neuronCount) {
                weights = new double[inputsPerNeuron][];
                for (int i = 0; i < weights.length; ++i) {
                    weights[i] = new double[neuronCount];
                    for (int j = 0; j < neuronCount; j++) {
                        weights[i][j] = 2. * random.nextDouble() - 1.0;
                    }
                }
            }

            public NeuronLayer(double[][] weights) {
                this.weights = weights;
            }

            double[][] compute(double[][] inputs) {
                return NNMath.apply(NNMath.matrixMultiply(inputs, weights), NNMath::sigmoid);
            }

            public void adjustWeights() {
                NNMath.matrixAdd(weights, adjusts);
            }
        }

        static class NNMath {
            public static double sigmoid(double x) {
                return 1. / (1. + Math.exp(-x));
            }

            public static double sigmoidDerivative(double x) {
                return x * (1. - x);
            }

            public static double[][] matrixMultiply(double[][] a, double[][] b) {
                if (a[0].length != b.length) {
                    throw new IllegalArgumentException();
                }
                int n = a.length;
                int m = a[0].length;
                int p = b[0].length;

                double[][] r = new double[n][p];
                double v;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < p; j++) {
                        v = 0;
                        for (int k = 0; k < m; k++) {
                            v += a[i][k] * b[k][j];
                        }
                        r[i][j] = v;
                    }
                }
                return r;
            }

            public static double[][] matrixSubtract(double[][] a, double[][] b) {
                double[][] r = new double[a.length][a[0].length];
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < a[0].length; j++) {
                        r[i][j] = a[i][j] - b[i][j];
                    }
                }
                return r;
            }

            public static double[][] scalarMultily(double[][] v1, double[][] v2) {
                double[][] r = new double[v1.length][v1[0].length];
                for (int i = 0; i < v1.length; i++) {
                    for (int j = 0; j < v1[0].length; j++) {
                        r[i][j] = v1[i][j] * v2[i][j];
                    }
                }
                return r;
            }

            public static double[][] matrixTranspose(double[][] m) {
                double[][] r = new double[m[0].length][m.length];
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                        r[j][i] = m[i][j];
                    }
                }
                return r;
            }

            public static double[][] apply(double[][] m, Function<Double, Double> fn) {
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[0].length; j++) {
                        m[i][j] = fn.apply(m[i][j]);
                    }
                }
                return m;
            }

            public static void matrixAdd(double[][] a, double[][] b) {
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < a[0].length; j++) {
                        a[i][j] += b[i][j];
                    }
                }
            }
        }
    }

    static class Board {
        static final int LOCATION_PLAYER_HAND = 0;
        static final int LOCATION_PLAYER_SIDE = 1;
        static final int LOCATION_PLAYER_DECK = 2;
        static final int LOCATION_PLAYER_DEAD = 3;
        static final int LOCATION_OPPONENT_SIDE = -1;
        static final int LOCATION_OPPONENT_HAND = -2;
        static final int LOCATION_OPPONENT_DECK = -3;
        static final int LOCATION_OPPONENT_DEAD = -4;
        static final int PHASE_DRAFT = 0;
        static final int PHASE_BATTLE = 1;
        List<Card> playerSide = new ArrayList<>();
        List<Card> playerHand = new ArrayList<>();
        List<Card> playerDeck = new ArrayList<>();
        List<Card> playerDead = new ArrayList<>();
        List<Card> opponentSide = new ArrayList<>();
        List<Card> opponentHand = new ArrayList<>();
        List<Card> opponentDeck = new ArrayList<>();
        List<Card> opponentDead = new ArrayList<>();
        int phase = PHASE_DRAFT;
        int round = 0;

        public void add(int location, Card card) {
            switch (location) {
                case LOCATION_PLAYER_HAND:
                    if(!playerHand.contains(card)) {
                        playerHand.add(card);
                        removeByNumber(playerDeck, card);
                    }
                    break;
                case LOCATION_PLAYER_SIDE:
                    if(!playerSide.contains(card)) {
                        playerSide.add(card);
                        playerHand.remove(card);
                    } else {
                        updateCard(playerSide, card);
                    }
                    break;
                case LOCATION_OPPONENT_SIDE:
                    if(!opponentSide.contains(card)) {
                        opponentSide.add(card);
                        removeByNumber(opponentDeck, card);
                    } else {
                        updateCard(opponentSide, card);
                    }
                    break;
                case LOCATION_PLAYER_DECK:
                    playerDeck.add(card);
                    break;
                case LOCATION_OPPONENT_DECK:
                    opponentDeck.add(card);
                    break;
                case LOCATION_OPPONENT_HAND:
                    opponentHand.add(card);
                    break;
                default:
                    throw new RuntimeException("Invalid location value: " + location);
            }
        }

        private void removeByNumber(List<Card> list, Card card) {
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).number == card.number) {
                    list.remove(i);
                    break;
                }
            }
        }

        private void updateCard(List<Card> list, Card card) {
            for(Card c: list) {
                if(card.id == c.id) {
                    c.attack = card.attack;
                    c.defense = card.defense;
                    c.abilities = card.abilities;
                    break;
                }
            }
        }

        public void transition() {
            if (phase != PHASE_DRAFT) {
                throw new RuntimeException("Can only transition from DRAFT phase");
            }
            round = 0;
        }

        public void clear() {
        }

        public void removeDead(List<Card> inPlay) {
            Card c;
            for (int i = 0; i < this.playerSide.size(); i++) {
                c = this.playerSide.get(i);
                if(!inPlay.contains(c)) {
                    System.err.println("Removed " + c.id + " because it's dead");
                    this.playerDead.add(c);
                    this.playerSide.remove(c);
                    --i;
                }
            }
            for (int i = 0; i < this.opponentSide.size(); i++) {
                c = this.opponentSide.get(i);
                if(!inPlay.contains(c)) {
                    System.err.println("Removed (other) " + c.id + " because it's dead");
                    this.opponentDead.add(c);
                    this.opponentSide.remove(c);
                    --i;
                }
            }
        }
    }

    static class Actor {
        int health;
        int mana;
        int deck;
        int runes;
        int lowestHealth;
        int draws;
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

        public void adjustHealth(int adjustment) {
            int newHealth = health + adjustment;
            if (newHealth < lowestHealth) {
                int[] thresholds = support.playerRunes();
                for (int i = 0; i < thresholds.length; ++i) {
                    if (thresholds[i] > newHealth && thresholds[i] <= lowestHealth) {
                        ++this.runes;
                    }
                }
                lowestHealth = newHealth;
            }
            health = newHealth;
        }

        public void addDraws(int draw) {
            this.draws = draw;
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

    static class PossibilityCard extends Card {
        private final List<Card> possibilities;

        public PossibilityCard(List<Card> possibilities) {
            super(0, 0, 0, 0, 0, 0, 0, "------", 0, 0, 0);
            this.possibilities = possibilities;
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

    static class UseAction implements Action {
        private int id1, id2;

        private UseAction(int id1, int id2) {
            this.id1 = id1;
            this.id2 = id2;
        }

        @Override
        public String toString() {
            return "USE " + id1 + " " + id2;
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
