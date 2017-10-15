import java.util.*;
public class BlackJack {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean acehigh = true;



        //Play Blackjack
        System.out.println();
        System.out.println("Welcome to the MadHackers Casino.");
        String plays;
        do {
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
        boolean userHasAce = false;
        boolean compHasAceHidden = false;
        boolean compHasAceShown = false;
        boolean userBust = false;
        boolean dealerBust = false;
        Card userHidden = deck.removeTopCard();
            if (userHidden.getValue() == 11) {
                userHasAceHidden = true;
                userHasAce = true;
            }
        Card dealerHidden = deck.removeTopCard();
            if (dealerHidden.getValue() == 11)
                compHasAceHidden = true;
        Card userShown = deck.removeTopCard();
            if (userShown.getValue() == 11) {
                userHasAceShown = true;
                userHasAce = true;
            }
        Card dealerShown = deck.removeTopCard();
            if (dealerShown.getValue() == 11)
                compHasAceShown = true;

        //total value of the two cards
        int dealerValue = dealerHidden.getValue() + dealerShown.getValue();
        int userValue = userHidden.getValue() + userShown.getValue();

        //prints the visible cards
        System.out.println("The dealer's faceup is " + dealerShown.toString());
        //uncomment the next line when necessary
        // System.out.println("The dealer's facedown is " + dealerHidden.toString());
        System.out.println("Your cards are the " + userHidden.toString() + " and the " + userShown.toString());

        //if neither player has BlackJack
        if (dealerValue != 21 && userValue != 21) {

            //asks if player would like to "hit" for another card
            //obscure opening of 2 Aces, adjust one of the ace's value's
            if (userShown.getValue() == 11 && userHidden.getValue() == 11) {
                userShown.setValue(1);
                userValue = 12;
            }
            if (dealerShown.getValue() == 11 && dealerHidden.getValue() == 11) {
                dealerShown.setValue(1);
                dealerValue = 12;
            }
            System.out.println("Your current value is "  + userValue);
            System.out.println(probability(userValue, dealerShown, userHasAce));
            System.out.println("Would you like to hit? y/n");
            Scanner hit = new Scanner(System.in);
            int newUserValue = userValue;
            boolean yeshit = hit.next().equalsIgnoreCase("y");

            int count = 0;
            int counts = 0;
            //while the user enters "y"
            while (yeshit) {
                //plays user blackjack
                newUserValue = userBlackJack(userValue, deck);
                //if after the hit the value of the hand is less than 21
                if (newUserValue <= 21) {
                    System.out.println("Your new value is "  + newUserValue);
                    System.out.println(probability(newUserValue, dealerShown));
                    System.out.println("Would you like to hit? y/n");
                    yeshit = hit.next().equalsIgnoreCase("y");
                    userValue = newUserValue;
                } else if (newUserValue > 21 && userHasAceHidden && (count < 1)) {
                    userHidden.setValue(1);
                    newUserValue = newUserValue - 10;
                    System.out.println("Your new value is "  + newUserValue);
                    System.out.println(probability(newUserValue, dealerShown));
                    System.out.println("Would you like to hit? y/n");
                    yeshit = hit.next().equalsIgnoreCase("y");
                    userValue = newUserValue;
                    count++;
                } else if ((newUserValue > 21) && userHasAceShown && (counts < 1)) {
                    userShown.setValue(1);
                    newUserValue = newUserValue - 10;
                    System.out.println("Your new value is "  + newUserValue);
                    System.out.println(probability(newUserValue, dealerShown));
                    System.out.println("Would you like to hit? y/n");
                    yeshit = hit.next().equalsIgnoreCase("y");
                    userValue = newUserValue;
                    counts++;
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
                    }

                    if ((newComputerValue > 21) && (compHasAceShown)) {
                        dealerShown.setValue(1);
                        newComputerValue = newComputerValue - 10;
                    }
                }
                //prints out the final value of the computer's hand
                System.out.println("The Computer's final value is: " + newComputerValue);
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
            } else if (newComputerValue > newUserValue) {
                System.out.println("You Lose!");
            } else if (newComputerValue == newUserValue) {
                System.out.println("Push!");
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
        return newValue;
    }

    public static String probability(int value, Card dShown, Boolean ace) {
        String advice = "";
        boolean hasAce = ace;
        int valueIndex = indexPlayerValue(value);
        int valueAceIndex = indexPlayerAceValue(value);
        int dealerValue = indexDealerValue(dShown.getValue());
        String adviceGrid [][] = {{"S","S","S","S","S","S","S","S","S","S"},
                                  {"S","S","S","S","S","H","H","s","s","s"},
                                  {"S","S","S","S","S","H","H","H","s","H"},
                                  {"S","S","S","S","S","H","H","H","H","H"},
                                  {"H","H","S","S","S","H","H","H","H","H"},
                                  {"h","h","h","h","h","h","h","h","h","h"},
                                  {"h","h","h","h","h","h","h","h","H","H"},
                                  {"H","h","h","h","h","H","H","H","H","H"},
                                  {"H","H","H","H","H","H","H","H","H","H"},};
        String adviceAceGrid[][] = {{"S","S","S","S","S","S","S","S","S","S"},
                                    {"S","S","S","S","s","S","S","S","S","S"},
                                    {"s","s","s","s","s","S","S","H","H","H"},
                                    {"H","h","h","h","h","H","H","H","H","H"},
                                    {"H","H","h","h","h","H","H","H","H","H"},
                                    {"H","H","H","h","h","H","H","H","H","H"},};
        if (!hasAce) {
            if (adviceGrid[valueIndex][dealerValue].equalsIgnoreCase("S")) {
                advice = "stand";
            } else {
                advice = "hit";
            }
        } else {
            if (adviceAceGrid[valueAceIndex][dealerValue].equalsIgnoreCase("S")) {
                advice = "stand";
            } else {
                advice = "hit";
            }
        }
        String bestAdvice = "Probability says you should: " + advice;
        return bestAdvice;
    }

    public static String probability(int value, Card dShown) {
        String advice = "";
        int valueIndex = indexPlayerValue(value);
        int dealerValue = indexDealerValue(dShown.getValue());
        String adviceGrid [][] = {{"S","S","S","S","S","S","S","S","S","S"},
                                  {"S","S","S","S","S","H","H","s","s","s"},
                                  {"S","S","S","S","S","H","H","H","s","H"},
                                  {"S","S","S","S","S","H","H","H","H","H"},
                                  {"H","H","S","S","S","H","H","H","H","H"},
                                  {"h","h","h","h","h","h","h","h","h","h"},
                                  {"h","h","h","h","h","h","h","h","H","H"},
                                  {"H","h","h","h","h","H","H","H","H","H"},
                                  {"H","H","H","H","H","H","H","H","H","H"},};
        if (adviceGrid[valueIndex][dealerValue].equalsIgnoreCase("S")) {
            advice = "stand";
        } else {
            advice = "hit";
        }
        String bestAdvice = "Probability says you should: " + advice;
        return bestAdvice;
    }

    public static int indexPlayerAceValue(int value) {
        int adviceIndex = 0;
        if (value >= 20 ) {
            adviceIndex = 0;
        } else if (value == 19 ) {
            adviceIndex = 1;
        } else if (value == 18 ) {
            adviceIndex = 2;
        } else if (value == 17 ) {
            adviceIndex = 3;
        } else if (value == 16 || value == 15) {
            adviceIndex = 4;
        } else {
            adviceIndex = 5;
        }
        return adviceIndex;
    }

    public static int indexPlayerValue(int value) {
        int adviceIndex = 0;
        if (value >= 17 && value <= 21) {
            adviceIndex = 0;
        } else if (value == 16) {
            adviceIndex = 1;
        } else if (value == 15) {
            adviceIndex = 2;
        } else if (value == 14 || value == 13) {
            adviceIndex = 3;
        } else if (value == 12) {
            adviceIndex = 4;
        } else if (value == 11) {
            adviceIndex = 5;
        } else if (value == 10) {
            adviceIndex = 6;
        } else if (value == 9) {
            adviceIndex = 7;
        } else {
            adviceIndex = 8;
        }
        return adviceIndex;
    }

    public static int indexDealerValue(int name) {
        int adviceIndex = 0;
        if (name == 2) {
            adviceIndex = 0;
        } else if (name == 3) {
            adviceIndex = 1;
        } else if (name == 4) {
            adviceIndex = 2;
        } else if (name == 5) {
            adviceIndex = 3;
        } else if (name == 6) {
            adviceIndex = 4;
        } else if (name == 7) {
            adviceIndex = 5;
        } else if (name == 8) {
            adviceIndex = 6;
        } else if (name == 9) {
            adviceIndex = 7;
        } else if (name == 10 || name == 11 || name == 12 || name == 13) {
            adviceIndex = 8;
        } else {
            adviceIndex = 9;
        }
        return adviceIndex;
    }

}