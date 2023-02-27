/*
In the Player class, a player is born. The player doesn't have the card, the player's hand has the cards. 
*/
public class Player {
  ////////////////////
  // PROPERTIES
  ////////////////////
  private String name;
  private int cash;

  ////////////////////
  // CONSTRUCTOR
  ////////////////////
  public Player() {
    this.name = "No one";
    this.cash = 10;
  }

  public Player(String name) {
    this.name = name;
    this.cash = 20;
  }
  ////////////////////
  // METHODS
  ////////////////////

  // GETTERS AND SETTERS
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCash() {
    return cash;
  }

  public void setCash(int cash) {
    this.cash = cash;
  }
}