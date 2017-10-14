/**
 * Represents a deck of cards.
 *
 * @author jzhang879
 */
import java.util.ArrayList;

public class Deck {

    boolean aceHigh;
    private ArrayList<Card> deck = new ArrayList<Card>();

    /**
     * Creates an empty deck.
     */
    public Deck() {
        deck.clear(); //Doesn't actually do anything I just needed a body lol.
    }

    /**
     * Creates a standard deck of cards with no Jokers.
     *
     * @param aceHigh whether the ace is the highest or lowest card
     */
    public Deck(boolean aceHigh) {
        this.aceHigh = aceHigh;
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Suit[] suits = Suit.values();
                Suit currentSuit = suits[i];
                String name;
                int value = j;
                if (j == 11) {
                    name = "Jack";
                } else if (j == 12) {
                    name = "Queen";
                } else if (j == 13) {
                    name = "King";
                } else if (j == 14) {
                    name = "Ace";
                    if (!(aceHigh)) {
                        value = 1;                    }
                } else {
                    name = "" + j;
                }
                deck.add(new Card(currentSuit, name, value));
            }
        }
    }

    /**
     * Shuffles a deck of cards.
     */
    public void shuffle() {

        ArrayList<Card> newDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int ranNum = (int)Math.floor(Math.random() * (deck.size() - 1));
            newDeck.add(deck.get(ranNum));
            deck.remove(ranNum);
        }
        deck = newDeck;
    }

    public void addTopCard(String card) {
        deck.add(0, new Card(card, aceHigh));
    }

    public Card removeTopCard(){return new Card(Suit.SPADES, "King", 13);}

    public void addCard(String card, int index){
        deck.add(index, new Card(card, aceHigh));
    }

    public void removeCard(String card, int num){;}

    public Card pullRandomCard(){return new Card(Suit.SPADES, "King", 13);}


    public static void main(String[] args) {
        Deck deck = new Deck(true);
        System.out.println(deck.deck);
        deck.shuffle();
        System.out.println(deck.deck);
        deck.addCard("King of Spades", 7);
        System.out.println(deck.deck);
    }
}
