
/*
In the Deck class, it creates a series of 52 unique cards. Multiple methods can be used to shuffle or take a card from the deck. 
*/
import java.util.Random;
import java.util.Arrays;

public class Deck {
  ////////////////////
  // PROPERTIES
  ////////////////////

  private Card[] cards;
  Random rand = new Random();

  ////////////////////
  // CONSTRUCTOR
  ////////////////////

  public Deck() {
    this.cards = new Card[52];
    for (int c = 0; c < cards.length; c++) {
      cards[c] = new Card(c);
    }
  }

  ////////////////////
  // METHODS
  ////////////////////

  // Randomizes order of deck
  public void shuffle() {
    for (int i = 0; i < cards.length; i++) { // Goes through every card
      int shuf = rand.nextInt(cards.length); // Gets a random pos
      Card temp = cards[i]; // Stores the ith card
      cards[i] = cards[shuf]; // Swaps ith card
      cards[shuf] = temp; // Swaps other card
    }
  }

  // Gives player a card
  // Should instead just keep count of position to save cards
  public Card deal() {
    Card card = null; // Temporary storage
    for (int c = 0; c < cards.length; c++) {
      if (cards[c] != null) { // Moves to next card until true
        card = cards[c];
        cards[c] = null; // Gone but not forgotten
        return card;
      }
    }
    return card;
  }

  // GETTERS AND SETTERS

  public void getCards() {
    System.out.println(Arrays.toString(cards));
  }
}