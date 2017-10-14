public class Card {

    private Suit suit;
    private String name;
    private int value;

    public Card(Suit suit, String name, int value) {
        this.suit = suit;
        this.name = name;
        this.value = value;
    }
    public String toString() {
        return this.name + " of " + this.suit;
    }
}
