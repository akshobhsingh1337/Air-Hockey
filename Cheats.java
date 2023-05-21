/**
 * This class provides special modifications to the properties of the Air
 * Hockey game.
 * 
 */

public class Cheats {

  private String[] colors = {
      "BLACK", "BLUE", "CYAN", "DARKGREY", "GREY", "GREEN",
      "LIGHTGREY", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW"
  };

  /**
   * Constructor for Cheats class, creates a new cheats object.
   */
  public Cheats() {
  }

  /**
   * Changes the size of the Mallets(balls) to 200 pixels (diameter)
   * 
   * @param mallet1 Ball one to be changed
   * @param mallet2 Ball two to be changed
   */
  public void giantMallet(Ball mallet1, Ball mallet2) {
    mallet1.setSize(200);
    mallet2.setSize(200);
  }

  /**
   * Resets the size of the Mallets(balls) to 50 pixels (diameter)
   * 
   * @param mallet1 Ball one to be changed
   * @param mallet2 Ball two to be changed
   */
  public void resetGiantMallet(Ball mallet1, Ball mallet2) {
    mallet1.setSize(50);
    mallet2.setSize(50);
  }

  /**
   * Swaps the colors of the table
   * In this order: "BLACK", "BLUE", "CYAN", "DARKGREY", "GREY", "GREEN",
   * "LIGHTGREY", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW"
   * 
   * @param table Rectangle object to be changed
   */
  public void changeTableColor(Rectangle table) {
    String currentColor = table.getColour();
    int indexOfCurrentColor = -1;

    for (int i = 0; i < colors.length; i++) {
      if (currentColor.equals(colors[i])) {
        indexOfCurrentColor = i;
        break;
      }
    }

    if (indexOfCurrentColor == colors.length - 1) {
      table.setColour(colors[0]);
    } else {
      table.setColour(colors[indexOfCurrentColor + 1]);
    }
  }

  /**
   * Resets the table color to default (white)
   * 
   * @param table Rectangle object to be reset
   */
  public void resetTableColor(Rectangle table) {
    table.setColour("WHITE");
  }

}
