/**
 * Represents a deck of cards.
 *
 * @author MadHackers
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

    /**
     * Adds a card to the top of the deck.
     *
     * @param card the card to add
     * in the format "King of Spades"/"7 of Hearts".
     */
    public void addTopCard(String card) {
        deck.add(0, new Card(card, aceHigh));
    }

    /**
     * Removes the top card of the deck.
     *
     * @return the card removed.
     */
    public Card removeTopCard() {
        if (deck.size() != 0) {
            Card removedCard = deck.get(0);
            deck.remove(0);
            return removedCard;
        }
        else {
            //throws exception - implement later
            return new Card(Suit.SPADES, "King", 13);
        }
    }

    /**
     * Adds a card to the deck at
     * a specific spot.
     *
     * @param card the card to add
     * in the format "King of Spades"/"7 of Hearts".
     * @param index the spot to place the card.
     */
    public void addCard(String card, int index){
        deck.add(index, new Card(card, aceHigh));
    }

    /**
     * Removes a certain number of a certain card.
     *
     * @param card the card to be removed
     * in the format "King of Spades"/"7 of Hearts".
     * @param num the number of instances to remove.
     */
    public void removeCard(String card, int num){
        for (int i = 0; i < num; i++) {
            deck.remove(new Card(card, aceHigh));
        }
    }

    /**
     * Pulls a random card from the deck.
     *
     * @return the card pulled.
     */
    public Card pullRandomCard(){
        int ranNum = (int)Math.floor(Math.random() * (deck.size() - 1));
        Card card = deck.get(ranNum);
        deck.remove(ranNum);
        return card;
    }

    public static void main(String[] args) {
        Deck deck = new Deck(true);
        System.out.println(deck.deck);
        deck.shuffle();
        System.out.println(deck.deck);
        deck.addCard("King of Spades", 7);
        deck.addCard("King of Spades", 7);
        deck.addCard("King of Spades", 7);
        System.out.println(deck.deck);
        deck.removeCard("King of Spades", 2);
        System.out.println(deck.deck);
        System.out.println(deck.pullRandomCard());
        System.out.println(deck.deck);
            }
}
