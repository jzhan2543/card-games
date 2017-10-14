/**
 * A Java Game of War
 *
 * @author MadHackers
 */
import java.util.*;

public class War {
    public static Deck userDeck = new Deck();
    public static Deck computerDeck = new Deck();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //Creates and shuffles a new deck, seperates the deck evenly to two decks;
        boolean acehigh = true;
        Deck deck = new Deck(acehigh);
        deck.shuffle();
        //Deck userDeck = new Deck();
        for (int i = 0; i < 26; i++) {
            userDeck.addCard(deck.getCard(i),i);
        }
        //Deck computerDeck = new Deck();
        for (int i = 26; i < 52; i++) {
            computerDeck.addCard(deck.getCard(i),i - 26);
        }

        //Plays War as long as either Deck's size is not 0
        while (userDeck.getSize() != 0 && computerDeck.getSize() != 0) {
            playWar(userDeck, computerDeck);
            // System.out.println("Continue? Y/N");
            // String continues = scan.next();
            // if (continues.equalsIgnoreCase("n")) {
            //     break;
            // }
        }
        if (computerDeck.getSize() == 0) {
            System.out.println("Game Over: You Win!");
        } else {
            System.out.println("Game Over: You Lose!");
        }
    }

    public static void playWar(Deck uDeck, Deck cDeck) {
        int count = 0;
        //Create two new temporary decks to be assigned after War
        Deck newUserDeck = new Deck();
        Deck newComputerDeck = new Deck();
        //Creates two Cards that are the top cards of the user's and computer's deck
        Card u = uDeck.getCard(0);
        Card c = cDeck.getCard(0);
        //If the user's top card is larger
        if (u.getValue() > c.getValue()) {
            System.out.println("Your " + u.toString() + " is greater than " + c.toString());
            uDeck.removeCard(0);
            cDeck.removeCard(0);
            uDeck.addCard(u);
            uDeck.addCard(c);
            System.out.println("Your Deck: " + uDeck.getSize());
            System.out.println("Computer's Deck: " + cDeck.getSize());
            System.out.println(uDeck);
            System.out.println(cDeck);
        }
        //If the computer's top card is larger
        else if (u.getValue() < c.getValue()) {
            System.out.println("The Computer's " + c.toString() + " is greater than " + u.toString());
            uDeck.removeCard(0);
            cDeck.removeCard(0);
            cDeck.addCard(u);
            cDeck.addCard(c);
            System.out.println("Your Deck: " + uDeck.getSize());
            System.out.println("Computer's Deck: " + cDeck.getSize());
            System.out.println(uDeck);
            System.out.println(cDeck);
        }
        //If the user's and computer's top card have the same value
        else {
            System.out.println("The Cards are Equal!");
            while(u.getValue() == c.getValue()) {
                count = count + 3;
                if (uDeck.getSize() > count && cDeck.getSize() > count) {
                    u = uDeck.getCard(count);
                    c = cDeck.getCard(count);
                }
                //Occurs if either deck does not have the sufficient cards to "mill"
                else {
                    if (uDeck.getSize() <= count) {
                        u = uDeck.getCard(uDeck.getSize() - 1);
                        c = cDeck.getCard(uDeck.getSize() - 1);
                        count = uDeck.getSize();
                    }
                    if (cDeck.getSize() <= count) {
                        u = uDeck.getCard(cDeck.getSize() - 1);
                        c = cDeck.getCard(cDeck.getSize() - 1);
                        count = cDeck.getSize();
                    }
                }
            }
            if (u.getValue() > c.getValue()) {
                for (int i = 0; i < count; i++) {
                    uDeck.addCard(uDeck.removeCard(0));
                    uDeck.addCard(cDeck.removeCard(0));
                }
            } else {
                for (int i = 0; i < count; i++) {
                    cDeck.addCard(uDeck.removeCard(0));
                    cDeck.addCard(cDeck.removeCard(0));
                }
            }
        }
        uDeck.shuffle();
        cDeck.shuffle();
        userDeck = uDeck;
        computerDeck = cDeck;
    }
}
