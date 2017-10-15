/**
 * Represents a poker card.
 *
 * @author MadHackers
 */

public class Card {

    private Suit suit;
    private Value name;
    private int value;

    /**
     * Creates a card with a specific suit,
     * name, and value.
     *
     * @param suit the suit of the card
     * (Spades, Hearts, Diamonds, Clubs)
     * @param name the name of the card
     * (King, 7, Ace, etc.)
     * @param value the value of the card
     * (1-14)
     */
    public Card(Suit suit, Value name, int value) {
        this.suit = suit;
        this.name = name;
        this.value = value;
    }

    /**
     * Creates a card based on the name
     * and whether ace is high or low.
     *
     * @param card the name of the card
     * in the format "King of Spades"/"Seven of Hearts".
     * @param aceHigh whether ace is high or low.
     */
    public Card(String card, boolean aceHigh) {
        String[] parts = card.split(" ");
        suit = Suit.valueOf(parts[2].toUpperCase());
        name = Value.valueOf(parts[0].toUpperCase());
        value = name.getValue();
        if (name == Value.ACE && (!(aceHigh))) {
            value = 1;
        }
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    /**
     * Changes the value of
     * a card to a new one.
     *
     * @param value the new value
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String temp = name.toString();
        temp = temp.toLowerCase();
        String output = temp.substring(0, 1).toUpperCase() + temp.substring(1) + " of ";
        temp = suit.toString();
        temp = temp.toLowerCase();
        output += temp.substring(0, 1).toUpperCase() + temp.substring(1);
            return output;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Card)) {
            return false;
        }
        Card that = (Card)other;
        if (this.name.equals(that.name) &&
            this.suit == that.suit &&
            this.value == that.value) {
            return true;
        }
        return false;
    }
}
