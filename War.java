/**
 * A Java Game of War
 *
 * @author MadHackers
 */
import java.util.*;

public class War {
    public static Deck userDeck = new Deck(true);
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
        while (userDeck.deck().size() != 0 || computerDeck.deck().size() != 0) {
            playWar(userDeck, computerDeck);
            System.out.println("Continue? Y/N");
            String continues = scan.next();
            if (continues.equalsIgnoreCase("n")) {
                break;
            }
        }
        if (computerDeck.deck().size() == 0) {
            System.out.println("Game Over: You Win!");
        } else {
            System.out.println("Game Over: You Lose!");
        }
    }

    public static void playWar(Deck uDeck, Deck cDeck) {
        int count = 3;
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
            uDeck.addCard(u,uDeck.deck().size()-1);
            uDeck.addCard(c,uDeck.deck().size());
        }
        //If the computer's top card is larger
        else if (u.getValue() < c.getValue()) {
            System.out.println("The Computer's " + c.toString() + " is greater than " + u.toString());
            uDeck.removeCard(0);
            cDeck.removeCard(0);
            cDeck.addCard(u,cDeck.deck().size()-1);
            cDeck.addCard(c,cDeck.deck().size());
        }
        //If the user's and computer's top card have the same value
        else {
            System.out.println("The Cards are Equal!");
            while(u.getValue() == c.getValue()) {
                count = count + 3;
                if (uDeck.deck().size() >= count || cDeck.deck().size() >= count) {
                    u = uDeck.getCard(count);
                    c = cDeck.getCard(count);
                }
                //Occurs if either deck does not have the sufficient cards to "mill"
                else {
                    if (uDeck.deck().size() < count) {
                        u = uDeck.getCard(uDeck.deck().size());
                        c = cDeck.getCard(uDeck.deck().size());
                    } else {
                        u = uDeck.getCard(cDeck.deck().size());
                        c = cDeck.getCard(cDeck.deck().size());
                    }
                }
            }
            if (u.getValue() > c.getValue()) {
                for (int i = 0; i <= count; i++) {
                    uDeck.removeCard(i);
                    cDeck.removeCard(i);
                    uDeck.addCard(u,uDeck.deck().size()-1);
                    uDeck.addCard(c,uDeck.deck().size());
                }
            } else {
                for (int i = 0; i <= count; i++) {
                    uDeck.removeCard(i);
                    cDeck.removeCard(i);
                    cDeck.addCard(u,cDeck.deck().size()-1);
                    cDeck.addCard(c,cDeck.deck().size());
                }
            }
        }
        userDeck = uDeck;
        computerDeck = cDeck;
    }
}
