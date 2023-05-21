/**
 * This class provides the required code to create the Air Hockey Screen.
 */

public class CreateScreen {

  private Ball leftMallet;
  private Ball rightMallet;
  private Ball puck;
  private GameArena gameScreen;
  private Rectangle whiteScreen;
  private Line leftGoal;
  private Line rightGoal;
  private Rectangle musicBox;
  private Text topText;
  private Text leftPlayerScore;
  private Text rightPlayerScore;

  /**
   * Constructor for CreateScreen class, creates all the screen objects required
   * by the game.
   */
  public CreateScreen() {

    gameScreen = new GameArena(1500, 800);

    Rectangle blackScreen = new Rectangle(0, 0, 1500, 800, "BLACK", 0);
    Rectangle blueScreen = new Rectangle(250, 150, 1000, 500, "BLUE", 1);
    whiteScreen = new Rectangle(270, 170, 960, 460, "WHITE", 2);

    gameScreen.addRectangle(blackScreen);
    gameScreen.addRectangle(blueScreen);
    gameScreen.addRectangle(whiteScreen);

    leftGoal = new Line(275, 300, 275, 500, 10, "GREY", 3);
    rightGoal = new Line(1225, 300, 1225, 500, 10, "GREY", 3);
    Line centerLine = new Line(750, 170, 750, 630, 1, "BLUE", 3);

    gameScreen.addLine(leftGoal);
    gameScreen.addLine(rightGoal);
    gameScreen.addLine(centerLine);

    Ball blueCenter = new Ball(750, 400, 100, "BLUE", 4);
    Ball whiteCenter = new Ball(750, 400, 98, "WHITE", 4);

    gameScreen.addBall(blueCenter);
    gameScreen.addBall(whiteCenter);

    leftMallet = new Ball(400, 400, 50, "BLUE", 5);
    rightMallet = new Ball(1100, 400, 50, "BLUE", 5);
    puck = new Ball(750, 400, 25, "BLACK", 5);

    gameScreen.addBall(leftMallet);
    gameScreen.addBall(rightMallet);
    gameScreen.addBall(puck);

    musicBox = new Rectangle(1300, 600, 172, 30, "GREEN", 1);
    Text musicText = new Text("Sound Effects", 25, 1303, 622, "BLACK", 3);

    gameScreen.addRectangle(musicBox);
    gameScreen.addText(musicText);

    topText = new Text("Welcome to Air Hockey", 30, 250, 100, "WHITE", 1);
    leftPlayerScore = new Text(String.valueOf(0), 30, 200, 400, "WHITE", 1);
    rightPlayerScore = new Text(String.valueOf(0), 30, 1285, 400, "WHITE", 1);

    gameScreen.addText(topText);
    gameScreen.addText(leftPlayerScore);
    gameScreen.addText(rightPlayerScore);

  }

  /**
   * Obtains the Left Mallet object.
   * 
   * @return The left mallet - ball object.
   */
  public Ball getLeftMallet() {
    return leftMallet;
  }

  /**
   * Obtains the Right Mallet object.
   * 
   * @return The right mallet - ball object.
   */
  public Ball getRightMallet() {
    return rightMallet;
  }

  /**
   * Obtains the Puck object.
   * 
   * @return The puck - ball object.
   */
  public Ball getPuck() {
    return puck;
  }

  /**
   * Obtains the Game arena object.
   * 
   * @return The game arena object.
   */
  public GameArena getGamearena() {
    return gameScreen;
  }

  /**
   * Obtains the Main table object.
   * 
   * @return The table - rectangle object.
   */
  public Rectangle getTable() {
    return whiteScreen;
  }

  /**
   * Obtains the Left Goal line object.
   * 
   * @return The left goal - line object.
   */
  public Line getLeftGoal() {
    return leftGoal;
  }

  /**
   * Obtains the Right Goal line object.
   * 
   * @return The right goal - line object.
   */
  public Line getRighGoal() {
    return rightGoal;
  }

  /**
   * Obtains the Music box object.
   * 
   * @return The Music box - rectangle object.
   */
  public Rectangle getMusicBox() {
    return musicBox;
  }

  /**
   * Obtains the Text at the top of the table object.
   * 
   * @return The Top - text object.
   */
  public Text getTopText() {
    return topText;
  }

  /**
   * Obtains the Score of the left player object.
   * 
   * @return The left player score - text object.
   */
  public Text getLeftPlayerText() {
    return leftPlayerScore;
  }

  /**
   * Obtains the Score of the right player object.
   * 
   * @return The right player score - text object.
   */
  public Text getRightPlayerText() {
    return rightPlayerScore;
  }

}
