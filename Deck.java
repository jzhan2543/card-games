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
            for (int j = 0; j < 13; j++) {
                Suit[] suits = Suit.values();
                Suit currentSuit = suits[i];
                Value[] values = Value.values();
                Value name = values[j];
                int value = values[j].getValue();
                if (j == 12) {
                    if (!(aceHigh)) {
                        value = 1;
                    }
                }
                deck.add(new Card(currentSuit, name, value));
            }
        }
    }

    /**
     * Gets the size of the deck.
     *
     * @return the size of the deck
     */
    public int getSize() {
        return deck.size();
    }

    /**
     * Shuffles a deck of cards.
     */
    public void shuffle() {

        ArrayList<Card> newDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int ranNum = (int)Math.floor(Math.random() * deck.size());
            newDeck.add(deck.get(ranNum));
            deck.remove(ranNum);
        }
        deck = newDeck;
    }

    /**
     * Adds a card to the top of the deck.
     *
     * @param card the card to add
     * in the format "King of Spades"/"Seven of Hearts".
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
            return new Card(Suit.SPADES, Value.KING, 13);
        }
    }

    /**
     * Adds a card to the deck at
     * a specific spot.
     *
     * @param card the card to add
     * in the format "King of Spades"/"Seven of Hearts".
     * @param index the spot to place the card.
     */
    public void addCard(String card, int index){
        deck.add(index, new Card(card, aceHigh));
    }

    /**
     * Adds the specified card to
     * a specific spot.
     *
     * @param card the card object to add.
     * @param index the spot to place the card.
     */
    public void addCard(Card card, int index) {
        deck.add(index, card);
    }

    /**
     * Adds the specified card to
     * the bottom of the deck.
     *
     * @param card the card object to add.
     */
    public void addCard(Card card) {
        deck.add(card);
    }

    /**
     * Removes the card at the specified
     * instance and returns it.
     *
     * @param index the spot to remove a card from
     * @return the card removed
     */
    public Card removeCard(int index) {
        Card temp = deck.get(index);
        deck.remove(index);
        return temp;
    }

    /**
     * Returns the card
     * at the specified index.
     *
     * @param index the spot to get
     * @return the card at the index
     */
    public Card getCard(int index) {
        return deck.get(index);
    }

    /**
     * Removes the topmost instances of a certain card.
     *
     * @param card the card to be removed
     * in the format "King of Spades"/"Seven of Hearts".
     * @param num the number of instances to remove.
     */
    public void removeInstance(String card, int num){
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

    @Override
    public String toString() {
        return deck.toString();
    }

    public static void main(String[] args) {
        Deck deck = new Deck(true);
        System.out.println(deck.deck);
        deck.shuffle();
        System.out.println(deck.deck);
        deck.addCard("King of Spades", 7);
        deck.addCard("King of Spades", 7);
        deck.addCard("Six of Clubs", 7);
        System.out.println(deck.deck);
        deck.removeInstance("King of Spades", 2);
        System.out.println(deck.deck);
        System.out.println(deck.pullRandomCard());
        System.out.println(deck.deck);
        System.out.println(deck.removeCard(2));
        System.out.println(deck.deck);
            }
}
