public class Cheats {

  private String[] colors = {
      "BLACK", "BLUE", "CYAN", "DARKGREY", "GREY", "GREEN",
      "LIGHTGREY", "MAGENTA", "ORANGE", "PINK", "RED", "WHITE", "YELLOW"
  };

  public void giantMallet(Ball mallet1, Ball mallet2) {
    mallet1.setSize(200);
    mallet2.setSize(200);
  }

  public void resetGiantMallet(Ball mallet1, Ball mallet2) {
    mallet1.setSize(50);
    mallet2.setSize(50);
  }

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

  public void resetTableColor(Rectangle table) {
    table.setColour("WHITE");
  }

}
