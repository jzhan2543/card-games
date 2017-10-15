import java.util.*;
// hearts for only one hand

public class Hearts{
    public static Deck North = new Deck();
    public static Deck East = new Deck();
    public static Deck South = new Deck();
    public static Deck West = new Deck();
    public static boolean heartBreak = false;
    public static ArrayList<Card> playedCards = new ArrayList<Card>(4);
    public static int turncount = 1;
    public static ArrayList<Card> N = new ArrayList<Card>();
    public static ArrayList<Card> E = new ArrayList<Card>();
    public static ArrayList<Card> S = new ArrayList<Card>();
    public static ArrayList<Card> W = new ArrayList<Card>();
    public static int playercount = 0;
    public static String kms;
    public static String kys;
    public static String[] list = {"North", "East", "South", "West"};
    public static int[] points = new int[4];

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        initilize();
        start();
        determineOrder(kms);
        turncount++;
        while (turncount <= 13) {
            playedCards.clear();
            determineStuff(kys);
            turncount++;
            }
    int temp = 0, index = 0;
    for(int i = 0; i < 4; i++) {
        if(points[i] > temp){
            temp = points[i];
            index = i;
        }
    }
    System.out.println(list[index] + " is the biggest loser");

    }
    public static void initilize() {
        Deck hello = new Deck(true);
        hello.shuffle();

    for(int i = 0; i < 13; i++) {
        North.addCard(hello.getCard(i),i);
    }

    for(int i = 13; i < 26; i++) {
        East.addCard(hello.getCard(i),i - 13);
    }

    for(int i = 26; i < 39; i++) {
        South.addCard(hello.getCard(i),i - 26);
    }

    for( int i = 39; i < 52; i++) {
        West.addCard(hello.getCard(i),i - 39);
    }

    }
    public static void start(){
        Card nihao = new Card(Suit.CLUBS, Value.TWO,2);
        for(int i = 0; i < North.deck().size();i++) {
            if (North.getCard(i).equals(nihao)){
                System.out.println("North starts. Play Two of Clubs");
                turn(North, "North");
                play(North);
                kms = "North";
                break;
            } else if (East.getCard(i).equals(nihao)) {
                System.out.println("East starts. Play Two of Clubs");
                turn(East, "East");
                play(East);
                kms = "East";
                break;
            } else if (South.getCard(i).equals(nihao)) {
                System.out.println("South starts. Play Two of Clubs");
                turn(South, "South");
                play(South);
                kms = "South";
                break;
            } else if (West.getCard(i).equals(nihao)) {
                System.out.println("West starts. Play Two of Clubs");
                turn(West, "West");
                play(West);
                kms = "West";
                break;
            }
        }
    }
    public static void turn(Deck deck, String name){
            System.out.println(name + "'s turn");
            System.out.println("Cards in middle:" + playedCards);
            System.out.println();
            System.out.println("Your deck: ");
            System.out.println();
            sort(deck);
            System.out.println(deck.deck());
    }
    public static void play(Deck deck) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Play your card");
            String hi = scan.nextLine();
            Card pick = new Card(hi,true);
            int index = findIndex(pick, deck);
            while(index < 0){
                System.out.println("Invalid move");
                System.out.println("try again");
                String bruh = scan.nextLine();
                pick = new Card(bruh, true);
                index = findIndex(pick, deck);
            }
            if(playedCards.size() == 0 && !heartBreak) {
                deck.removeCard(index);
                playedCards.add(pick);
                System.out.println();
            } else {
                while(!checkMoves(pick, deck)) {
                    System.out.println("Invalid Moves. Play Card of Same Suit");
                    String ruh = scan.nextLine();
                    pick = new Card(ruh, true);
                    index = findIndex(pick, deck);
                }
                deck.removeCard(index);
                playedCards.add(pick);
                System.out.println();
        }
    }
    public static void sort(Deck deck) {
        Deck hearts = new Deck();
        int count = 0;
        Deck diamonds = new Deck();
        int count2 =0;
        Deck spades = new Deck();
        int count3 = 0;
        Deck clubs = new Deck();
        int count4 = 0;
        int temp;
        for(int i = 0; i < deck.deck().size(); i++) {
            if(deck.getCard(i).getSuit() == Suit.HEARTS){
                hearts.addCard(deck.getCard(i),count);
                count++;
            }
            if(deck.getCard(i).getSuit() == Suit.DIAMONDS){
                diamonds.addCard(deck.getCard(i),count2);
                count2++;
            }
            if(deck.getCard(i).getSuit() == Suit.SPADES){
                spades.addCard(deck.getCard(i),count3);
                count3++;
            }
            if(deck.getCard(i).getSuit() == Suit.CLUBS){
                clubs.addCard(deck.getCard(i),count4);
                count4++;
            }
        }
        int count5 = 0;
        for(int i = 0; i < hearts.deck().size();i++){
            deck.removeCard(count5);
            deck.addCard(hearts.getCard(i),count5);
            count5++;
        }
        for(int i = 0; i < diamonds.deck().size();i++){
            deck.removeCard(count5);
            deck.addCard(diamonds.getCard(i),count5);
            count5++;
        }
        for(int i = 0; i < spades.deck().size();i++){
            deck.removeCard(count5);
            deck.addCard(spades.getCard(i),count5);
            count5++;
        }
         for(int i = 0; i < clubs.deck().size();i++){
            deck.removeCard(count5);
            deck.addCard(clubs.getCard(i),count5);
            count5++;
        }
    }
    public static void heartbroken(Card hi){
        //if()
    }
    public static int findIndex(Card hello, Deck deck) {
        for(int i = 0; i < deck.deck().size();i++){
            if(deck.getCard(i).equals(hello)){
                return i;
            }
        }
        return -1;

    }
    public static boolean checkMoves(Card hi, Deck deck) {
        Suit s = playedCards.get(0).getSuit();
        if(!s.equals(hi.getSuit())){
            for(int i = 0; i < deck.deck().size();i++) {
                if(deck.getCard(i).getSuit().equals(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void winhand(String[] me){
        int index = 0, max = playedCards.get(0).getValue(), counter = 0;
        if(playedCards.get(0).getSuit().equals(Suit.HEARTS)){
            counter++;
            System.out.println("hi");
        }
        if(playedCards.get(0).getSuit().equals(Suit.SPADES) && playedCards.get(0).getName().equals(Value.QUEEN)){
            counter = counter + 13;
        }
        System.out.println("Cards in Middle: " + playedCards);
        for (int i = 1; i < playedCards.size(); i++) {
            if(playedCards.get(0).getSuit().equals(playedCards.get(i).getSuit())) {
                if(playedCards.get(i).getValue() > max){
                    max = playedCards.get(i).getValue();
                    index = i;
                }
            }
            if(playedCards.get(i).getSuit().equals(Suit.HEARTS))
                counter++;
             if(playedCards.get(i).getSuit().equals(Suit.SPADES) && playedCards.get(i).getName().equals(Value.QUEEN)){
                counter = counter + 13;
            }
        }
        System.out.println(me[index] + " took the pot of " + counter);
        kys = me[index];
        int aids = 0;
        for(int i = 0; i < 4; i++) {
            if(me[index].equals(list[i])){
                aids = i;
            }
        }
        points[aids] += counter;
        System.out.println("Point Distribution: ");
        for(int i = 0; i < 4; i ++) {
            System.out.println(list[i] + ": " + points[i] + " points");
        }
        System.out.println();

    }
    public static void determineOrder(String name){
        if(name.equals("North")){
            Deck[] ord = {North,East,South,West};
            String[] order = {"North", "East", "South","West"};
            int[] count = new int[4];
            for(int i = 1; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
        if(name.equals("East")){
            Deck[] ord = {East,South,West,North};
            String[] order = {"East", "South","West","North"};
            int[] count = new int[4];
            for(int i = 1; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
        if(name.equals("South")){
            Deck[] ord = {South,West,North,East};
            String[] order = {"South","West","North","East"};
            int[] count = new int[4];
            for(int i = 1; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
        if(name.equals("West")){
            Deck[] ord = {West,North,East,South};
            String[] order = {"West","North","East","South"};
            int[] count = new int[4];
            for(int i = 1; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
    }
    public static void determineStuff(String name) {
        if(name.equals("North")){
            Deck[] ord = {North,East,South,West};
            String[] order = {"North", "East", "South","West"};
            int[] count = new int[4];
            for(int i = 0; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
        if(name.equals("East")){
            Deck[] ord = {East,South,West,North};
            String[] order = {"East", "South","West","North"};
            int[] count = new int[4];
            for(int i = 0; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
        if(name.equals("South")){
            Deck[] ord = {South,West,North,East};
            String[] order = {"South","West","North","East"};
            int[] count = new int[4];
            for(int i = 0; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }
        if(name.equals("West")){
            Deck[] ord = {West,North,East,South};
            String[] order = {"West","North","East","South"};
            int[] count = new int[4];
            for(int i = 0; i < 4;i++) {
                turn(ord[i],order[i]);
                play(ord[i]);
            }
             winhand(order);
        }

    }
}
