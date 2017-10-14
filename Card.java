/**
 * Represents a poker card.
 *
 * @author MadHackers
 */

public class Card {

    private Suit suit;
    private String name;
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
    public Card(Suit suit, String name, int value) {
        this.suit = suit;
        this.name = name;
        this.value = value;
    }

    /**
     * Creates a card based on the name
     * and whether ace is high or low.
     *
     * @param card the name of the card
     * in the format "King of Spades"/"7 of Hearts".
     * @param aceHigh whether ace is high or low.
     */
    public Card(String card, boolean aceHigh) {
        String[] parts = card.split(" ");
        this.suit = Suit.valueOf(parts[2].toUpperCase());
        this.name = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
        this.value = convertValue(name, aceHigh);
    }

    public String toString() {
        return this.name + " of " + this.suit;
    }

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

    /**
     * Finds the value of a card based
     * on the name and whether ace is high or low.
     *
     * @param name the name of the card
     * @param aceHigh whether ace is high or low
     */
    public int convertValue(String name, boolean aceHigh) {
        if (name.equalsIgnoreCase("Ace")) {
            if (aceHigh) {
                return 14;
            } else {
                return 1;
            }
        } else if (name.equalsIgnoreCase("King")) {
            return 13;
        } else if (name.equalsIgnoreCase("Queen")) {
            return 12;
        } else if (name.equalsIgnoreCase("Jack")) {
            return 11;
        } else {
            return Integer.parseInt(name);
        }
    }
}
