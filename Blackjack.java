/*
In the Game class, it takes a deck and simulates the game.

BLACK JACK RULES:
1) Dealer deals 2 cards per person, everyone shows one of his cards.
2) If you Hit: Draw a card that must not go over 21, but get closest.
3) If you Stand: You are confident with deck.
4) Always be able to bet.
5) After everyone Stands, reveal cards and find winner.
6) If Dealer has best deck, they win. If you have best deck, you win. If you push(tie), then dealer wins. 
7) If Dealer busts(goes over), everyone wins. Dealer has to hit when below 16.
8) Aces are worth 1 or 11, All face cards are worth 10
*/
import java.util.Scanner;
import java.util.Arrays;

public class Blackjack {
  ////////////////////
  // PROPERTIES
  ////////////////////
  private Player[] players;
  private Hand[] hands;
  private Scanner user = new Scanner(System.in);
  private Deck deck;
  private Hand dealer;

  // Cool colors
  final String RESET = "\u001B[0;0m"; 
  final String RED = "\u001B[1;31m";
  final String GREEN = "\u001B[1;32m";
  final String BLUE = "\u001B[1;34m";
  final String red = "\u001B[0;31m";
  
  ////////////////////
  // CONSTRUCTOR
  ////////////////////
  public Blackjack(Deck deck, Player[] players) {
    this.deck = deck;
    this.players = players;

    // Creates a hand for each player
    hands = new Hand[players.length];
    for (int h = 0; h < players.length; h++) {
      hands[h] = new Hand(players[h]);
    }
    // The dealer is born!
    dealer = new Hand(new Player("The Dealer"));
    dealer.setDealer(true); //Signifies the hand is a dealer
  }

  ////////////////////
  // METHODS
  ////////////////////
  // Plays the game
  public void playGame() {
    clearScreen(); // Just google it
    dealer.hit(deck.deal()); //Revealed dealer card
    
    // Deals 2 cards for each Player
    for (int h = 0; h < hands.length; h++) {
      System.out.println(BLUE + "\nIt is " + hands[h].getName() + "'s turn." + RESET);
      System.out.print("\nREADY? "); // To reveal cards privately
      user.next(); // Doesn't matter what player types

      hands[h].hit(deck.deal());
      hands[h].hit(deck.deal());
      hands[h].printHand(); // Shows avaliable cards

      System.out.print("\nCONTINUE? ");
      user.next();
      clearScreen();
    }

    // Gets Input of each player to commence turn
    for (int h = 0; h < hands.length; h++) {
      playerTurn(hands[h]);
    }
    
    // Check for Aces
    // It would first find Ace through iteration, and if the sum is less than 11, than it would add 10.
    for (int h = 0; h < hands.length; h++){
      if (hands[h].getTotal() < 11){
        hands[h].checkForAce();
      }
    }
    // Compares everyones sums
    Hand bestHand = hands[0];
    int bestPos = 0;
    for (int h = 1; h < hands.length; h++) {
      //Greatest hand but not over 21
      if(hands[h].getTotal() > bestHand.getTotal() && hands[h].getTotal() <= 21){
        bestHand = hands[h];
        bestPos = h;
      }
    }
    
    // Makes sure there isn't a tie
    for (int i = 0; i < hands.length; i++){
      if(hands[i].getTotal() == bestHand.getTotal() && i != bestPos){
        bestPos = -1;
        break;
      }
    }
    
    // Time to compare with the DEALER
    // Dealer has to keep hitting
    while (dealer.getTotal() < 16){
      dealer.hit(deck.deal());
      dealer.bust(); // Checks if they went over
    }
    // Outputs the total points of everyone
    for (int h = 0; h < hands.length; h++){
      if(hands[h].getTotal() > 21){
        System.out.println(red + hands[h].getName() + " has a total of " + hands[h].getTotal() + ". They BUSTED!" + RESET);
      }
      else if (hands[h].getTotal() == 21){
        System.out.println(GREEN + hands[h].getName() + " has a total of " + hands[h].getTotal() + ". They have a BLACKJACK!" + RESET);
      }
      else {
        System.out.println(hands[h].getName() + " has a total of " + hands[h].getTotal() + ".");
      }
      
    }
    // Dealers outcome
    dealer.printHand();
    System.out.println("\n" + dealer.getName() + " has a total of " + dealer.getTotal() + ".");

    // Final Results
    if(dealer.getTotal() > 21){
      System.out.println(GREEN + "\nEverybody wins! Take your money back" + RESET);
    }
    // If everyone is over 21, it will default to hand 0, so also check again
    else if(dealer.getTotal() > bestHand.getTotal() || bestHand.getTotal() > 21){
      System.out.println(RED + "\nThe Dealer wins..." + RESET);
    }
    else if (bestPos == -1){
      System.out.println(GREEN + "\nNobody wins! There was a tie" + RESET);
    }
    else{
      System.out.println(BLUE + "\n" + bestHand.getName() + " wins!" + RESET);
    }
  }

  // Gets choice from User to continue
  public void playerTurn(Hand guy){
    dealer.printHand();
    guy.printHand();
    int choice = 0;
    
    boolean over = false;
    
    
    while(choice != 2 && over == false){
      //Keep going until Player stands or busts
      System.out.println("\nAlright " + guy.getName() + ", what would you like to do?");
      System.out.print("1) Hit \n2) Stand \n3) Cry :( \nChoice: ");
      choice = user.nextInt();
  
      if(choice == 1){ //Hit
        guy.hit(deck.deal());
        guy.printHand();
        over = guy.bust();
      }
      // Little Cheat command
      else if (choice == 357){
        deck.getCards();
      }
      //Every other choice is Stand
    }
    System.out.print("\nCONTINUE? ");
    user.next();
    clearScreen();
  }
  
  // Clears console lines
  public static void clearScreen() {
    System.out.print("\033[H\033[2J"); // ANSI escape code
    System.out.flush();
  }

  // GETTERS AND SETTERS

}