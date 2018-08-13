package com.codingame.locam;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

class Player {
    private final GameSupport support;
    Actor me, opponent;
    Board board;
    ArtificialIntelligence ai;
    int opponentHand;
    InputStream is;
    PrintStream ps;

    public Player(GameSupport gameSupport, InputStream is, PrintStream ps) {
        this.me = new Actor();
        this.opponent = new Actor();
        this.board = new Board();
        this.ai = new ArtificialIntelligence();
        this.support = gameSupport;
        this.is = is;
        this.ps = ps;
    }

    public static void main(String args[]) {
        Player game = new Player(new Wood3GameSupport(), System.in, System.out);
        game.run();
    }

    public void run() {
        try {
            Scanner s = new Scanner(this.is);
            // game loop
            while (true) {
                System.out.println("Reading line");
                me.initialize(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
                opponent.initialize(s.nextInt(), s.nextInt(), s.nextInt(), s.nextInt());
                System.out.println("In round");
                opponentHand = s.nextInt();
                int cardCount = s.nextInt();
                board.clear();
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
                    board.add(location, CardFactory.createCard(type, number, id, cost, attack, defense, abilities, playerHp, enemyHp, draw));
                }

                // Write an action using System.out.println()
                // To debug: System.err.println("Debug messages...");

//            System.out.println("PASS");
                break;
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    static class Board {
        static final int LOCATION_PLAYER_HAND = 0;
        static final int LOCATION_PLAYER_SIDE = 1;
        static final int LOCATION_OPPONENT_SIDE = 2;
        static final int PHASE_DRAFT = 0;
        static final int PHASE_BATTLE = 1;
        Set<Card> opponent = new HashSet<>();
        Set<Card> deck = new HashSet<>();
        Set<Card> hand = new HashSet<>();
        int phase = PHASE_DRAFT;
        int round = 0;

        public void add(int location, Card card) {
            if(phase != PHASE_BATTLE) {
                return;
            }
            switch (location) {
                case LOCATION_PLAYER_HAND:
                    if(!hand.contains(card)) {
                        hand.add(card);
                    }
                    break;
                case LOCATION_PLAYER_SIDE:
                    if(!deck.contains(card)) {
                        deck.add(card);
                    }
                    break;
                case LOCATION_OPPONENT_SIDE:
                    opponent.add(card);
                    break;
                default:
                    throw new RuntimeException("Invalid location value: " + location);
            }
        }

        public void transition() {
            if(phase != PHASE_DRAFT) {
                throw new RuntimeException("Can only transition from DRAFT phase");
            }
            round = 0;
        }

        public void clear() {
            this.opponent.clear();
        }
    }

    static class Actor {
        int health;
        int mana;
        int deck;
        int runes;

        public void initialize(int health, int mana, int deck, int runes) {
            this.health = health;
            this.mana = mana;
            this.deck = deck;
            this.runes = runes;
        }

        @Override
        public String toString() {
            return "" + health + " " + mana + " " + deck + " " + runes;
        }
    }

    static class Creature extends Card {
        public Creature(int number, int id, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_CREATURE, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static class ItemGreen extends Item {
        public ItemGreen(int number, int id, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_ITEM_GREEN, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static class ItemRed extends Item {
        public ItemRed(int number, int id, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_ITEM_RED, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static class ItemBlue extends Item {
        public ItemBlue(int number, int id, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, Card.TYPE_ITEM_BLUE, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static abstract class Item extends Card {
        public Item(int number, int id, int type, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            super(number, id, type, cost, attack, defense, abilities, playerHp, enemyHp, cardDraw);
        }
    }

    static abstract class Card {
        static final int TYPE_CREATURE = 0;
        static final int TYPE_ITEM_GREEN = 1; // todo change to actual value
        static final int TYPE_ITEM_RED = 2; // todo change to actual value
        static final int TYPE_ITEM_BLUE = 3; // todo change to actual value
        int number;
        int id;
        int location = Board.LOCATION_PLAYER_SIDE;
        int type;
        int cost;
        int attack;
        int defense;
        String abilities;
        int playerHp;
        int enemyHp;
        int cardDraw;

        public Card(int number, int id, int type, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int cardDraw) {
            this.number = number;
            this.id = id;
            this.type = type;
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
            return number == card.number &&
                    id == card.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, id);
        }

        @Override
        public String toString() {
            return "" + number + " " + id + " " + location + " " + type +
                    " " + cost + " " + attack + " " + defense + " " +
                    abilities + " " + playerHp + " " + enemyHp + " " + cardDraw;
        }
    }

    static class CardFactory {
        public static Card createCard(int type, int number, int id, int cost, int attack, int defense, String abilities, int playerHp, int enemyHp, int draw) {
            switch(type) {
                case Card.TYPE_CREATURE:
                    return new Creature(number, id, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                case Card.TYPE_ITEM_GREEN:
                    return new ItemGreen(number, id, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                case Card.TYPE_ITEM_RED:
                    return new ItemRed(number, id, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                case Card.TYPE_ITEM_BLUE:
                    return new ItemBlue(number, id, cost, attack, defense, abilities, playerHp, enemyHp, draw);
                default:
                    throw new RuntimeException("Unexpected card type: " + type);
            }
        }
    }

    static class PassAction implements Action {
        @Override
        public void execute() {
            System.out.println("PASS");
        }
    }

    static class PickAction implements Action {
        private int card;

        public PickAction(int card) {
            this.card = card;
        }

        @Override
        public void execute() {
            System.out.println("PICK " + card);
        }
    }

    static class SummonAction implements Action {
        private int id;

        private SummonAction(int id) {
            this.id = id;
        }

        @Override
        public void execute() {
            System.out.println("SUMMON " + id);
        }
    }

    static class AttackAction implements Action {
        private int id1, id2;

        private AttackAction(int id1, int id2) {
            this.id1 = id1;
            this.id2 = id2;
        }

        @Override
        public void execute() {
            System.out.println("ATTACK " + id1 + " " + id2);
        }
    }

    interface Action {
        void execute();
    }

    static class ArtificialIntelligence {
        public List<LinkedCards> linkedCards = new ArrayList<>();

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
            return new int[] {Card.TYPE_CREATURE};
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
            return true;
        }
    }

    static class Wood3GameSupport extends StandardGameSupport {
    }

    public static final Card[] LISTING = {
            new Creature(  1, -1,  1,  2,  1, "------",  1,  0, 0),
            new Creature(  2, -1,  1,  1,  2, "------",  0, -1, 0),
            new Creature(  3, -1,  1,  2,  2, "------",  0,  0, 0),
            new Creature(  4, -1,  2,  1,  5, "------",  0,  0, 0),
            new Creature(  5, -1,  2,  4,  1, "------",  0,  0, 0),
            new Creature(  6, -1,  2,  3,  2, "------",  0,  0, 0),
            new Creature(  7, -1,  2,  2,  2, "-----W",  0,  0, 0),
            new Creature(  8, -1,  2,  2,  3, "------",  0,  0, 0),
            new Creature(  9, -1,  3,  3,  4, "------",  0,  0, 0),
            new Creature( 10, -1,  3,  3,  1, "--D---",  0,  0, 0),
            new Creature( 11, -1,  3,  5,  2, "------",  0,  0, 0),
            new Creature( 12, -1,  3,  2,  5, "------",  0,  0, 0),
            new Creature( 13, -1,  4,  5,  3, "------",  1, -1, 0),
            new Creature( 14, -1,  4,  9,  1, "------",  0,  0, 0),
            new Creature( 15, -1,  4,  4,  5, "------",  0,  0, 0),
            new Creature( 16, -1,  4,  6,  2, "------",  0,  0, 0),
            new Creature( 17, -1,  4,  4,  5, "------",  0,  0, 0),
            new Creature( 18, -1,  4,  7,  4, "------",  0,  0, 0),
            new Creature( 19, -1,  5,  5,  6, "------",  0,  0, 0),
            new Creature( 20, -1,  5,  8,  2, "------",  0,  0, 0),
            new Creature( 21, -1,  5,  6,  5, "------",  0,  0, 0),
            new Creature( 22, -1,  6,  7,  5, "------",  0,  0, 0),
            new Creature( 23, -1,  7,  7,  8, "------",  0,  0, 0),
            new Creature( 24, -1,  1,  1,  1, "------",  0, -1, 0),
            new Creature( 25, -1,  2,  3,  1, "------", -2, -2, 0),
            new Creature( 26, -1,  2,  3,  2, "------",  0, -1, 0),
            new Creature( 27, -1,  2,  2,  2, "------",  2,  0, 0),
            new Creature( 28, -1,  2,  1,  2, "------",  0,  0, 1),
            new Creature( 29, -1,  2,  2,  1, "------",  0,  0, 1),
            new Creature( 30, -1,  3,  4,  2, "------",  0, -2, 0),
            new Creature( 31, -1,  3,  3,  1, "------",  0, -1, 0),
            new Creature( 32, -1,  3,  3,  2, "------",  0,  0, 1),
            new Creature( 33, -1,  4,  4,  3, "------",  0,  0, 1),
            new Creature( 34, -1,  5,  3,  5, "------",  0,  0, 1),
            new Creature( 35, -1,  6,  5,  2, "B-----",  0,  0, 1),
            new Creature( 36, -1,  6,  6,  4, "------",  0,  0, 2),
            new Creature( 37, -1,  6,  5,  7, "------",  0,  0, 1),
            new Creature( 38, -1,  1,  1,  3, "--D---",  0,  0, 0),
            new Creature( 39, -1,  1,  2,  1, "--D---",  0,  0, 0),
            new Creature( 40, -1,  3,  2,  3, "--DG--",  0,  0, 0),
            new Creature( 41, -1,  3,  3,  2, "-CD---",  0,  0, 0),
            new Creature( 42, -1,  4,  4,  2, "--D---",  0,  0, 0),
            new Creature( 43, -1,  6,  5,  5, "--D---",  0,  0, 0),
            new Creature( 44, -1,  6,  3,  7, "--D-L-",  0,  0, 0),
            new Creature( 45, -1,  6,  6,  5, "B-D---", -3,  0, 0),
            new Creature( 46, -1,  9,  7,  7, "--D---",  0,  0, 0),
            new Creature( 47, -1,  2,  1,  5, "--D---",  0,  0, 0),
            new Creature( 48, -1,  1,  1,  1, "----L-",  0,  0, 0),
            new Creature( 49, -1,  2,  1,  2, "---GL-",  0,  0, 0),
            new Creature( 50, -1,  3,  3,  2, "----L-",  0,  0, 0),
            new Creature( 51, -1,  4,  3,  5, "----L-",  0,  0, 0),
            new Creature( 52, -1,  4,  2,  4, "----L-",  0,  0, 0),
            new Creature( 53, -1,  4,  1,  1, "-C--L-",  0,  0, 0),
            new Creature( 54, -1,  3,  2,  2, "----L-",  0,  0, 0),
            new Creature( 55, -1,  2,  0,  5, "---G--",  0,  0, 0),
            new Creature( 56, -1,  4,  2,  7, "------",  0,  0, 0),
            new Creature( 57, -1,  4,  1,  8, "------",  0,  0, 0),
            new Creature( 58, -1,  6,  5,  6, "B-----",  0,  0, 0),
            new Creature( 59, -1,  7,  7,  7, "------",  1, -1, 0),
            new Creature( 60, -1,  7,  4,  8, "------",  0,  0, 0),
            new Creature( 61, -1,  9, 10, 10, "------",  0,  0, 0),
            new Creature( 62, -1, 12, 12, 12, "B--G--",  0,  0, 0),
            new Creature( 63, -1,  2,  0,  4, "---G-W",  0,  0, 0),
            new Creature( 64, -1,  2,  1,  1, "---G-W",  0,  0, 0),
            new Creature( 65, -1,  2,  2,  2, "-----W",  0,  0, 0),
            new Creature( 66, -1,  5,  5,  1, "-----W",  0,  0, 0),
            new Creature( 67, -1,  6,  5,  5, "-----W",  0, -2, 0),
            new Creature( 68, -1,  6,  7,  5, "-----W",  0,  0, 0),
            new Creature( 69, -1,  3,  4,  4, "B-----",  0,  0, 0),
            new Creature( 70, -1,  4,  6,  3, "B-----",  0,  0, 0),
            new Creature( 71, -1,  4,  3,  2, "BC----",  0,  0, 0),
            new Creature( 72, -1,  4,  5,  3, "B-----",  0,  0, 0),
            new Creature( 73, -1,  4,  4,  4, "B-----",  4,  0, 0),
            new Creature( 74, -1,  5,  5,  4, "B--G--",  0,  0, 0),
            new Creature( 75, -1,  5,  6,  5, "B-----",  0,  0, 0),
            new Creature( 76, -1,  6,  5,  5, "B-D---",  0,  0, 0),
            new Creature( 77, -1,  7,  7,  7, "B-----",  0,  0, 0),
            new Creature( 78, -1,  8,  5,  5, "B-----",  0, -5, 0),
            new Creature( 79, -1,  8,  8,  8, "B-----",  0,  0, 0),
            new Creature( 80, -1,  8,  8,  8, "B--G--",  0,  0, 1),
            new Creature( 81, -1,  9,  6,  6, "BC----",  0,  0, 0),
            new Creature( 82, -1,  7,  5,  5, "B-D--W",  0,  0, 0),
            new Creature( 83, -1,  0,  1,  1, "-C----",  0,  0, 0),
            new Creature( 84, -1,  2,  1,  1, "-CD--W",  0,  0, 0),
            new Creature( 85, -1,  3,  2,  3, "-C----",  0,  0, 0),
            new Creature( 86, -1,  3,  1,  5, "-C----",  0,  0, 0),
            new Creature( 87, -1,  4,  2,  5, "-C-G--",  0,  0, 0),
            new Creature( 88, -1,  5,  4,  4, "-C----",  0,  0, 0),
            new Creature( 89, -1,  5,  4,  1, "-C----",  2,  0, 0),
            new Creature( 90, -1,  8,  5,  5, "-C----",  0,  0, 0),
            new Creature( 91, -1,  0,  1,  2, "---G--",  0,  1, 0),
            new Creature( 92, -1,  1,  0,  1, "---G--",  2,  0, 0),
            new Creature( 93, -1,  1,  2,  1, "---G--",  0,  0, 0),
            new Creature( 94, -1,  2,  1,  4, "---G--",  0,  0, 0),
            new Creature( 95, -1,  2,  2,  3, "---G--",  0,  0, 0),
            new Creature( 96, -1,  2,  3,  2, "---G--",  0,  0, 0),
            new Creature( 97, -1,  3,  3,  3, "---G--",  0,  0, 0),
            new Creature( 98, -1,  3,  2,  4, "---G--",  0,  0, 0),
            new Creature( 99, -1,  3,  2,  5, "---G--",  0,  0, 0),
            new Creature(100, -1,  3,  1,  6, "---G--",  0,  0, 0),
            new Creature(101, -1,  4,  3,  4, "---G--",  0,  0, 0),
            new Creature(102, -1,  4,  3,  3, "---G--",  0, -1, 0),
            new Creature(103, -1,  4,  3,  6, "---G--",  0,  0, 0),
            new Creature(104, -1,  4,  4,  4, "---G--",  0,  0, 0),
            new Creature(105, -1,  5,  4,  6, "---G--",  0,  0, 0),
            new Creature(106, -1,  5,  5,  5, "---G--",  0,  0, 0),
            new Creature(107, -1,  5,  3,  3, "---G--",  3,  0, 0),
            new Creature(108, -1,  5,  2,  6, "---G--",  0,  0, 0),
            new Creature(109, -1,  5,  5,  6, "------",  0,  0, 0),
            new Creature(110, -1,  5,  0,  9, "---G--",  0,  0, 0),
            new Creature(111, -1,  6,  6,  6, "---G--",  0,  0, 0),
            new Creature(112, -1,  6,  4,  7, "---G--",  0,  0, 0),
            new Creature(113, -1,  6,  2,  4, "---G--",  4,  0, 0),
            new Creature(114, -1,  7,  7,  7, "---G--",  0,  0, 0),
            new Creature(115, -1,  8,  5,  5, "---G-W",  0,  0, 0),
            new Creature(116, -1, 12,  8,  8, "BCDGLW",  0,  0, 0),
            new ItemGreen(117, -1, 1, 1, 1, "B-----", 0, 0, 0),
            new ItemGreen(118, -1, 0, 0, 3, "------", 0, 0, 0),
            new ItemGreen(119, -1, 1, 1, 2, "------", 0, 0, 0),
            new ItemGreen(120, -1, 2, 1, 0, "----L-", 0, 0, 0),
            new ItemGreen(121, -1, 2, 0, 3, "------", 0, 0, 1),
            new ItemGreen(122, -1, 2, 1, 3, "---G--", 0, 0, 0),
            new ItemGreen(123, -1, 2, 4, 0, "------", 0, 0, 0),
            new ItemGreen(124, -1, 3, 2, 1, "--D---", 0, 0, 0),
            new ItemGreen(125, -1, 3, 1, 4, "------", 0, 0, 0),
            new ItemGreen(126, -1, 3, 2, 3, "------", 0, 0, 0),
            new ItemGreen(127, -1, 3, 0, 6, "------", 0, 0, 0),
            new ItemGreen(128, -1, 4, 4, 3, "------", 0, 0, 0),
            new ItemGreen(129, -1, 4, 2, 5, "------", 0, 0, 0),
            new ItemGreen(130, -1, 4, 0 ,6, "------", 4, 0, 0),
            new ItemGreen(131, -1, 4, 4, 1, "------", 0, 0, 0),
            new ItemGreen(132, -1, 5, 3, 3, "B-----", 0, 0, 0),
            new ItemGreen(133, -1, 5, 4, 0, "-----W", 0, 0, 0),
            new ItemGreen(134, -1, 4, 2, 2, "------", 0, 0, 1),
            new ItemGreen(135, -1, 6, 5, 5, "------", 0, 0, 0),
            new ItemGreen(136, -1, 0, 1, 1, "------", 0, 0, 0),
            new ItemGreen(137, -1, 2, 0, 0, "-----W", 0, 0, 0),
            new ItemGreen(138, -1, 2, 0, 0, "---G--", 0, 0, 1),
            new ItemGreen(139, -1, 4, 0, 0, "----LW", 0, 0, 0),
            new ItemGreen(140, -1, 2, 0, 0, "-C----", 0, 0, 0),
            new ItemRed(141, -1, 0, -1,  -1, "------", 0,  0, 0),
            new ItemRed(142, -1, 0,  0,   0, "BCDGLW", 0,  0, 0),
            new ItemRed(143, -1, 0,  0,   0, "---G--", 0,  0, 0),
            new ItemRed(144, -1, 1,  0,  -2, "------", 0,  0, 0),
            new ItemRed(145, -1, 3, -2,  -2, "------", 0,  0, 0),
            new ItemRed(146, -1, 4, -2,  -2, "------", 0, -2, 0),
            new ItemRed(147, -1, 2,  0,  -1, "------", 0,  0, 1),
            new ItemRed(148, -1, 2,  0,  -2, "BCDGLW", 0,  0, 0),
            new ItemRed(149, -1, 3,  0,   0, "BCDGLW", 0,  0, 1),
            new ItemRed(150, -1, 2,  0,  -3, "------", 0,  0, 0),
            new ItemRed(151, -1, 5,  0, -99, "BCDGLW", 0,  0, 0),
            new ItemRed(152, -1, 7,  0,  -7, "------", 0,  0, 1),
            new ItemBlue(153, -1, 2, 0,  0, "------", 5,  0, 0),
            new ItemBlue(154, -1, 2, 0,  0, "------", 0, -2, 1),
            new ItemBlue(155, -1, 3, 0, -3, "------", 0, -1, 0),
            new ItemBlue(156, -1, 3, 0,  0, "------", 3, -3, 0),
            new ItemBlue(157, -1, 3, 0, -1, "------", 1,  0, 1),
            new ItemBlue(158, -1, 3, 0, -4, "------", 0,  0, 0),
            new ItemBlue(159, -1, 4, 0, -3, "------", 3,  0, 0),
            new ItemBlue(160, -1, 2, 0,  0, "------", 2, -2, 0)
    };
}