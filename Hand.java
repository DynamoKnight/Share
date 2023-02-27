
/*
In the Hand class, the maximum hand size possible is 11. A House rule is if you get 5 cards without going over, you automatically get a blackjack. The reason the hand and player is seperate is because they hand is only for blackjack. The Player can play several other games, and if they decide on blackjack, a hand will be created representing them. 
*/
import java.util.Scanner;
import java.util.Arrays;

public class Hand {
  ////////////////////
  // PROPERTIES
  ////////////////////
  private boolean isDealer;
  private Card[] cards;
  private String name; // Based on player
  private Player player; // The player of the hand
  private int betCash; // The amount of cash the Player withdrew
  private int total;

  // Cool colors
  final String RESET = "\u001B[0;0m"; 
  final String RED = "\u001B[1;31m";
  final String GREEN = "\u001B[1;32m";
  final String BLUE = "\u001B[1;34m";

  ////////////////////
  // CONSTRUCTOR
  ////////////////////

  public Hand(Player player) {
    this.player = player;
    this.name = player.getName();
    this.total = 0;

    // The cards in the hand 
    cards = new Card[5]; //Max of 5
    for (int c = 0; c < cards.length; c++) {
      cards[c] = null;
    }
  }
  ////////////////////
  // METHODS
  ////////////////////
  
  // Prints out all cards in the hand
  public void printHand(){
    if(isDealer){
      System.out.println(RED + "\nThe Dealer has " + RESET);
    }
    else{
      System.out.println(BLUE + "\nHere are your cards:" + RESET);
    }
    for (int c = 0; c < cards.length; c++) {
      if (cards[c] != null) {
        System.out.println(cards[c]);
      }
    }
  }

  // Adds a card to the hand, would need a deck to hit from
  public void hit(Card dealt) {
    total += dealt.getNum();
    for (int c = 0; c < cards.length; c++) {
      if (cards[c] == null) {
        cards[c] = dealt;
        break; //Card has found space
      }
    }
  }
  // Standing Advances game without hiting

  // Bets money
  public void bet() {

  }

  // Busts by going over 21 :(
  public boolean bust() {
    int tempTotal = 0;
    // Adds together all cards to get total
    for (int t = 0; t < cards.length; t++){
      if(cards[t] == null){
        break;
      }
      int value = cards[t].getNum();
      // Face cards are worth 10
      if(value > 10){ 
        value = 10;
      }
      tempTotal += value;
    }
    total = tempTotal;
    //Bad news
    if (tempTotal > 21){
      if(isDealer()){
        System.out.println(RED + "The Dealer BUSTED!" + RESET);
      }
      else{
        System.out.println(BLUE + "You BUSTED!" + RESET);
      }
      return true;
    }
    else{
      return false;
    }
  }

  // Searches for an Ace and will increase its value
  public void checkForAce(){
    for (int c = 0; c < cards.length; c++){
      // Iterated through available cards
      if(cards[c] == null){
        break;
      }
      else if (cards[c].getNum() == 1){
        cards[c].setNum(11); // Increases total
        total += 10; 
      }
    }
  }
  // GETTERS AND SETTERS
  public boolean isDealer() {
    return isDealer;
  }

  public void setDealer(boolean isDealer) {
    this.isDealer = isDealer;
  }

  public Card[] getCards() {
    return cards;
  }

  public void setCards(Card[] cards) {
    this.cards = cards;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

}