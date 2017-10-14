public class Card {

    private Suit suit;
    private String name;
    private int value;

    public Card(Suit suit, String name, int value) {
        this.suit = suit;
        this.name = name;
        this.value = value;
    }

    public Card(String card, boolean aceHigh) {
        String[] parts = card.split(" ");
        this.suit = Suit.valueOf(parts[2].toUpperCase());
        this.name = parts[0].substring(0, 1).toUpperCase() + parts[0].substring(1);
        this.value = convertValue(name, aceHigh);
    }

    public String toString() {
        return this.name + " of " + this.suit;
    }

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
