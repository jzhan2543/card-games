import java.util.*;
public class BlackJack {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean acehigh = true;

        //Creates a new blackjack and assigns a value of 10 to face cards and a default value of 11 to Aces
        Deck deck = new Deck(acehigh);
        for (int i = 0; i < deck.getSize(); i++) {
            if (deck.getCard(i).getValue() == 11 || deck.getCard(i).getValue() == 12
                    || deck.getCard(i).getValue() == 13) {
                deck.getCard(i).setValue(10);
            }
            if (deck.getCard(i).getValue() == 14) {
                deck.getCard(i).setValue(11);
            }
        }
        //Play Blackjack
        System.out.println("Welcome to the MadHackers Casino.");
        String plays;
        do {
            deck.shuffle();
            playBlackJack(deck);
            //play another round?
            System.out.println("Would you like to continue playing BlackJack? y/n");
            plays = scan.next();
        } while (plays.equalsIgnoreCase("y"));
        System.out.println("Thank you for Playing!");
    }

    //play blackJack
    public static void playBlackJack(Deck deck) {
        boolean userHasAceHidden = false;
        boolean userHasAceShown = false;
        boolean compHasAceHidden = false;
        boolean compHasAceShown = false;
        boolean userBust = false;
        boolean dealerBust = false;
        Card userHidden = deck.removeTopCard();
            if (userHidden.getValue() == 11)
                userHasAceHidden = true;
        Card dealerHidden = deck.removeTopCard();
            if (dealerHidden.getValue() == 11)
                compHasAceHidden = true;
        Card userShown = deck.removeTopCard();
            if (userShown.getValue() == 11)
                userHasAceShown = true;
        Card dealerShown = deck.removeTopCard();
            if (dealerShown.getValue() == 11)
                compHasAceShown = true;

        //total value of the two cards
        int dealerValue = dealerHidden.getValue() + dealerShown.getValue();
        int userValue = userHidden.getValue() + userShown.getValue();

        //prints the visible cards
        System.out.println("The dealer's faceup is " + dealerShown.toString());
        //uncomment the next line when necessary
        System.out.println("The dealer's facedown is " + dealerHidden.toString());
        System.out.println("Your cards are " + userHidden.toString() + " and the " + userShown.toString());

        //if neither player has BlackJack
        if (dealerValue != 21 && userValue != 21) {

            //asks if player would like to "hit" for another card
            System.out.println("Your current value is "  + userValue);
            System.out.println("Would you like to hit? y/n");
            Scanner hit = new Scanner(System.in);
            String userHit = "";
            int newUserValue = userValue;
            boolean yeshit = hit.next().equalsIgnoreCase("y");

            //while the user enters "y"
            while (yeshit) {

                //plays user blackjack
                newUserValue = userBlackJack(userValue, deck);
                //if after the hit the value of the hand is less than 21
                if (newUserValue <= 21) {
                    System.out.println("Your new value is "  + newUserValue);
                    System.out.println("Would you like to hit? y/n");
                    userHit = hit.next();
                    userValue = newUserValue;

                    //if user enters anything but "y" break the loop
                    if (!userHit.equalsIgnoreCase("y")) {
                        break;
                    }
                } else if (newUserValue > 21 && (userHasAceHidden)) {
                    userHidden.setValue(1);
                    newUserValue = newUserValue - 10;
                    System.out.println("Your new value is "  + newUserValue);
                    System.out.println("Would you like to hit? y/n");
                    userHit = hit.next();
                    userValue = newUserValue;
                } else if ((newUserValue > 21) && userHasAceShown) {
                    userShown.setValue(1);
                    newUserValue = newUserValue - 10;
                    System.out.println("Your new value is "  + newUserValue);
                    System.out.println("Would you like to hit? y/n");
                    userHit = hit.next();
                    userValue = newUserValue;
                }
                //if after the hit the value of the hand is greater than 21
                else {
                    System.out.println("Your hand is a bust!");
                    userBust = true;
                    break;
                }
            }
            //prints final user value
            System.out.println("Your hand's final value is: " + newUserValue);

            //if the user doesn't bust
            int newComputerValue = dealerValue;
            if (!userBust){
                while (newComputerValue < 17) {
                    //dealer hits if the value of the hand is less than 17
                    newComputerValue = computerBlackJack(dealerValue, deck);
                    dealerValue = newComputerValue;
                    if (newComputerValue > 21 && (compHasAceHidden)) {
                        dealerHidden.setValue(1);
                        newComputerValue = newComputerValue - 10;
                        System.out.println("The Computer's new value is "  + newUserValue);
                    }
                    if ((newComputerValue > 21) && compHasAceShown) {
                        dealerShown.setValue(1);
                        newComputerValue = newComputerValue - 10;
                        System.out.println("The Computer's new value is "  + newComputerValue);
                    }
                }
                //prints out the final value of the computer's hand
                System.out.println("The Computer's final value is " + newComputerValue);
            }

            // if the computer value > 21
            if (newComputerValue > 21) {
                dealerBust = true;
            }

            //Scoring
            if (userBust) {
                System.out.println("You Lose!");
            } else if (dealerBust) {
                System.out.println("You Win!");
            } else if (newUserValue > newComputerValue) {
                System.out.println("You Win!");
            } else if (newComputerValue >= newUserValue) {
                System.out.println("You Lose!");
            } else {
                System.out.println("Error!");
            }
        }
        //Dealer BlackJack
        else if (dealerValue == 21) {
            System.out.println("Dealer has BlackJack!");
            System.out.println("You Lose!");
        }
        //User BlackJack
        else {
            System.out.println("You have BlackJack!");
            System.out.println("You Win!");
        }
    }

    //UserBlackJack
    public static int userBlackJack(int value, Deck deck) {
        Card nextCard = deck.removeTopCard();
        int newValue = value;
        if (nextCard.getValue() != 11 || ((nextCard.getValue() == 11) && (nextCard.getValue() + value) < 21)) {
            System.out.println("Your new card is " + nextCard.toString());
            int cardValue = nextCard.getValue();
            newValue = value + cardValue;
        } else {
            System.out.println("Your new card is " + nextCard.toString());
            nextCard.setValue(1);
            int cardValue = nextCard.getValue();
            newValue = value + cardValue;
        }
        return newValue;
    }

    //ComputerBlackJack
    public static int computerBlackJack(int value, Deck deck) {
        Card nextCard = deck.removeTopCard();
        int newValue = value;
        if (nextCard.getValue() != 11 || ((nextCard.getValue() == 11) && (nextCard.getValue() + value) < 21)) {
            System.out.println("The Computer's next card is " + nextCard.toString());
            int cardValue = nextCard.getValue();
            newValue = value + cardValue;
        } else {
            System.out.println("The Computer's next card is " + nextCard.toString());
            nextCard.setValue(1);
            int cardValue = nextCard.getValue();
            newValue = value + cardValue;
        }
        System.out.println("The Computer's new value: " + newValue);
        return newValue;
    }
}