/*
In the Game class, it creates players who can select from a series of games. 
*/
import java.util.Scanner;
import java.util.Arrays;
public class Game{
  ////////////////////
  // PROPERTIES
  ////////////////////
  private Player[] players;
  private int numPlayers;
  Scanner house = new Scanner(System.in);

  // Cool colors
  final String RESET = "\u001B[0;0m"; 
  final String RED = "\u001B[1;31m";
  final String GREEN = "\u001B[1;32m";
  final String YELLOW = "\u001B[1;33m";
  final String BLUE = "\u001B[1;34m";
  ////////////////////
  // CONSTRUCTOR
  ////////////////////
  public Game(){
    
  }
  ////////////////////
  // METHODS
  ////////////////////

  //The Welcome screen to choose games
  public void startup(){
    System.out.println(GREEN + "WELCOME TO THE COOL CASINO!\n" + RESET);
    System.out.print("How many Players: ");
    numPlayers = house.nextInt();
    this.players = new Player[numPlayers]; //Max of 5 players for now
    
    //Creates and sets Name of each Player
    for(int p = 0; p < numPlayers; p++){
      System.out.print("What is name of Player " + (p+1) + ": ");
      players[p] = new Player(house.next());
    }
    
    System.out.println("\nWhat would you like to play?");
    System.out.println(RED + "1) Play Blackjack" + RESET);
    System.out.println(YELLOW + "2) Nothing" + RESET);
    System.out.print("\nCHOICE: ");
    
    if(house.nextInt() == 1){
      playBlackjack();
    }
    else{
      
    }
  }

  //Creates a Deck and a Blackjack
  public void playBlackjack(){
    Deck reg = new Deck(); //Entire deck of cards
    reg.shuffle();
    //reg.getCards(); //Prints all cards
    Blackjack bj = new Blackjack(reg, players);
    bj.playGame();
  }
  //GETTERS AND SETTERS
}