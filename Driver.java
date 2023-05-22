import javax.swing.JOptionPane;

/**
 * The main class that drives the Air Hockey game.
 */

public class Driver {

  /**
   * The main method that starts the Air Hockey game.
   *
   * @param args Command-line arguments (not used)
   */
  public static void main(String args[]) {

    // Ask the user for the total amount of goals required to win
    String goalsInput = JOptionPane.showInputDialog("Enter the total amount of goals required to win:");
    int totalGoalsToWin = Integer.parseInt(goalsInput);

    boolean gameState = true;

    // Creating Screen and getting all objects
    CreateScreen screenObject = new CreateScreen();

    GameArena mainScreen = screenObject.getGamearena();
    Rectangle table = screenObject.getTable();
    Line leftGoal = screenObject.getLeftGoal();
    Line rightGoal = screenObject.getRighGoal();
    Ball leftMallet = screenObject.getLeftMallet();
    Ball rightMallet = screenObject.getRightMallet();
    Ball puck = screenObject.getPuck();
    Rectangle musicBox = screenObject.getMusicBox();
    Text topText = screenObject.getTopText();
    Text leftPlayerScore = screenObject.getLeftPlayerText();
    Text rightPlayerScore = screenObject.getRightPlayerText();

    double leftMalletXSpeed = 0;
    double leftMalletYSpeed = 0;

    double rightMalletXSpeed = 0;
    double rightMalletYSpeed = 0;

    double constantSpeed = 7;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    SoundPlayer soundPlayer = new SoundPlayer();
    Cheats cheatEngine = new Cheats();
    KeyboardInputs keyboardHandler = new KeyboardInputs();
    // TableManager tableManager = new TableManager();

    soundPlayer.playIntro();

    while (true) {
      mainScreen.pause();

      // Start the puck movement
      puck.start();

      // Apply friction to the puck
      puck.applyFriction();

      int lastPuckHit = 0;

      if (gameState == true) {

        // Handles all Left Mallet Keyboard Inputs and updates speed
        double[] updatedSpeedsLeftMallet = keyboardHandler.handleLeftMalletInput(mainScreen,
            leftMallet, constantSpeed);
        leftMalletYSpeed = updatedSpeedsLeftMallet[0];
        leftMalletXSpeed = updatedSpeedsLeftMallet[1];

        // Handles all Right Mallet Keyboard Inputs and updates speed
        double[] updatedSpeedRightMallet = keyboardHandler.handleRightMalletInput(mainScreen, rightMallet,
            constantSpeed);
        rightMalletYSpeed = updatedSpeedRightMallet[0];
        rightMalletXSpeed = updatedSpeedRightMallet[1];

        // Handles Music On/Off Input
        keyboardHandler.HandleMusicInput(mainScreen, soundPlayer, musicBox);

        // Handles all Cheats Keyboard Inputs and updates speed
        constantSpeed = keyboardHandler.handleCheatInputs(mainScreen, cheatEngine, leftMallet, rightMallet, table,
            constantSpeed);
      }

      // Handles Collision between Mallets and Puck

      // Collision between Left Mallet and Puck
      if (puck.collides(leftMallet)) {
        puck.deflect(leftMallet.getXPosition(), leftMallet.getYPosition(), puck.getXPosition(),
            puck.getYPosition(), leftMalletXSpeed, leftMalletYSpeed, puck.getXSpeed(), puck.getYSpeed());
        lastPuckHit = 0;
        soundPlayer.playHit();
      }

      // Collision between Right Mallet and Puck
      if (puck.collides(rightMallet)) {
        puck.deflect(rightMallet.getXPosition(), rightMallet.getYPosition(), puck.getXPosition(),
            puck.getYPosition(), rightMalletXSpeed, rightMalletYSpeed, puck.getXSpeed(), puck.getYSpeed());
        lastPuckHit = 1;
        soundPlayer.playHit();
      }

      // Manage interactions with the game table, goals, mallets, and scores
      TableManager tableManager = new TableManager(puck, leftGoal, rightGoal, soundPlayer, leftMallet, rightMallet,
          topText, leftPlayerScore, rightPlayerScore);

      // Handle table interactions and update scores
      int[] newScores = tableManager.handleTableInteractions(lastPuckHit);
      playerOneScore = newScores[0];
      playerTwoScore = newScores[1];

      // Check if either player has reached the total goals required to win
      if (playerOneScore == totalGoalsToWin) {
        topText.setText("Player 1 Wins the game! Press SPACE to start another game or ESC to exit");
        soundPlayer.playDrumRoll();
        soundPlayer.setDrumRoll(false);
        gameState = false;

        if (mainScreen.escPressed()) {
          // Exit the game if ESC key is pressed
          mainScreen.exit();
          break;
        }

        if (mainScreen.spacePressed()) {
          // Start a new game if SPACE key is pressed
          startNewGame(mainScreen, playerOneScore, playerTwoScore, leftPlayerScore, rightPlayerScore, topText,
              puck, soundPlayer);

          // Ask the user for the new total amount of goals required to win
          totalGoalsToWin = Integer
              .parseInt(JOptionPane.showInputDialog("Enter the total amount of goals required to win:"));

          gameState = true;
        }
      } else if (playerTwoScore == totalGoalsToWin) {
        topText.setText("Player 2 Wins the game! Press SPACE to start another game or ESC to exit");
        soundPlayer.playDrumRoll();
        soundPlayer.setDrumRoll(false);
        gameState = false;

        if (mainScreen.escPressed()) {
          mainScreen.exit();
          break;
        }

        if (mainScreen.spacePressed()) {
          // Start a new game if SPACE key is pressed
          startNewGame(mainScreen, playerOneScore, playerTwoScore, leftPlayerScore, rightPlayerScore, topText,
              puck, soundPlayer);

          // Ask the user for the new total amount of goals required to win
          totalGoalsToWin = Integer
              .parseInt(JOptionPane.showInputDialog("Enter the total amount of goals required to win:"));

          gameState = true;
        }
      }
    }
  }

  /**
   * Resets the game state for a new game.
   *
   * @param mainScreen       The main game arena screen.
   * @param playerOneScore   The score of player one.
   * @param playerTwoScore   The score of player two.
   * @param leftPlayerScore  The text component displaying the score of player
   *                         one.
   * @param rightPlayerScore The text component displaying the score of player
   *                         two.
   * @param topText          The text component displaying the game status.
   * @param puck             The puck object.
   * @param soundPlayer      The sound player for game sounds.
   */
  private static void startNewGame(GameArena mainScreen, int playerOneScore, int playerTwoScore,
      Text leftPlayerScore, Text rightPlayerScore, Text topText, Ball puck, SoundPlayer soundPlayer) {
    playerOneScore = 0;
    playerTwoScore = 0;
    leftPlayerScore.setText(String.valueOf(playerOneScore));
    rightPlayerScore.setText(String.valueOf(playerTwoScore));
    topText.setText("Welcome to Air Hockey");
    puck.goalReset();

    // Reset game state and clear key press flags
    mainScreen.setSpacePressedFalse();
    soundPlayer.setDrumRoll(true);
  }
}
