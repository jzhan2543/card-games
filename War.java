/**
 * A Java Game of War
 *
 * @author jzhan30
 */
import java.util.*;

public class War {

    public static void main(String[] args) {
        Scanner user = new Scanner();

        //Creates and shuffles a new deck, seperates the deck evenly to two decks;
        boolean acehigh = true;
        Deck deck = new Deck(acehigh);
        deck.shuffle();
        Deck userDeck = new Deck();
        for (int i = 0; i < 26; i++) {
            userDeck.addCard(deck.deck.get(i),i);
        }
        Deck computerDeck = new Deck();
        for (int i = 26; i < 52; i++) {
            userDeck.addCard(deck.deck.get(i),i);
        }

        //Plays War as long as either Deck's size is not 0
        while (userDeck.deck.size() != 0 || computerDeck.deck.size() != 0) {
            playWar(userDeck, computerDeck);
            System.out.println("Continue? y/n")
            if (scan.nextChar().equals('n') || scan.nextChar().equals('N')) {
                break;
            }
        }
        if (userDeck.deck.size() == 0) {
            System.out.println("Game Over: You Lose!");
        } else {
            System.out.println()
        }
    }

    public void playWar(Deck uDeck, Deck cDeck) {
        int count = 3;
        //Create two new temporary decks to be assigned after War
        Deck newUserDeck = new Deck();
        Deck newComputerDeck = new Deck();
        //Creates two Cards that are the top cards of the user's and computer's deck
        Card u = uDeck.deck.get(0);
        Card c = cDeck.deck.get(0);
        //If the user's top card is larger
        if (u.value > c.value) {
            System.out.println("Your " + u.toString() + " is greater than " + c.toString());
            uDeck.removeCard(u,0);
            cDeck.removeCard(c,0);
            uDeck.addCard(u,uDeck.deck.size()-1);
            uDeck.addCard(c,uDeck.deck.size());
        }
        //If the computer's top card is larger
        else if (u.value < c.value) {
            System.out.println("The Computer's " + c.toString() + " is greater than " + u.toString());
            uDeck.removeCard(u,0);
            cDeck.removeCard(c,0);
            cDeck.addCard(u,cDeck.deck.size()-1);
            cDeck.addCard(c,cDeck.deck.size());
        }
        //If the user's and computer's top card have the same value
        else {
            while(u.value == c.value) {
                count = count + 3;
                if (uDeck.deck.size() >= count || cDeck.deck.size() >= count) {
                    u = uDeck.deck.get(count);
                    c = cDeck.deck.get(count);
                }
                //Occurs if either deck does not have the sufficient cards to "mill"
                else {
                    if (uDeck.deck.size() < count) {
                        u = uDeck.deck.get(uDeck.deck.size());
                        c = cDeck.deck.get(uDeck.deck.size());
                    } else {
                        u = uDeck.deck.get(cDeck.deck.size());
                        c = cDeck.deck.get(cDeck.deck.size());
                    }
                }
            }
            if (u.value > c.value) {
                for (int i = 0; i <= count; i++) {
                    uDeck.removeCard(u,i);
                    cDeck.removeCard(c,i);
                    uDeck.addCard(u,uDeck.deck.size()-1);
                    uDeck.addCard(c,uDeck.deck.size());
                }
            } else {
                for (int i = 0; i <= count; i++) {
                    uDeck.removeCard(u,i);
                    cDeck.removeCard(c,i);
                    cDeck.addCard(u,cDeck.deck.size()-1);
                    cDeck.addCard(c,cDeck.deck.size());
                }
            }
        }
        userDeck = uDeck;
        computerDeck = cDeck;
    }
}