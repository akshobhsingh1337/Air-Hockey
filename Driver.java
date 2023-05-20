import javax.swing.JOptionPane;

public class Driver {

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

    double constantSpeed = 15;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    SoundPlayer soundPlayer = new SoundPlayer();
    Cheats cheatEngine = new Cheats();
    KeyboardInputs keyboardHandler = new KeyboardInputs();
    // TableManager tableManager = new TableManager();

    soundPlayer.playIntro();

    while (true) {
      mainScreen.pause();

      puck.start();

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

      TableManager tableManager = new TableManager(puck, leftGoal, rightGoal, soundPlayer, leftMallet, rightMallet,
          topText, leftPlayerScore, rightPlayerScore);

      int[] newScores = tableManager.handleTableInteractions(lastPuckHit);
      playerOneScore = newScores[0];
      playerTwoScore = newScores[1];

      if (playerOneScore == totalGoalsToWin) {
        topText.setText("Player 1 Wins the game! Press SPACE to start another game or ESC to exit");
        soundPlayer.playDrumRoll();
        soundPlayer.setDrumRoll(false);
        gameState = false;

        if (mainScreen.escPressed()) {
          mainScreen.exit();
          break;
        }

        if (mainScreen.spacePressed()) {
          playerOneScore = 0;
          playerTwoScore = 0;
          leftPlayerScore.setText(String.valueOf(playerOneScore));
          rightPlayerScore.setText(String.valueOf(playerTwoScore));
          topText.setText("Welcome to Air Hockey");
          puck.goalReset();

          totalGoalsToWin = Integer
              .parseInt(JOptionPane.showInputDialog("Enter the total amount of goals required to win:"));

          gameState = true;
          mainScreen.setSpacePressedFalse();

          soundPlayer.setDrumRoll(true);
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
          playerOneScore = 0;
          playerTwoScore = 0;
          leftPlayerScore.setText(String.valueOf(playerOneScore));
          rightPlayerScore.setText(String.valueOf(playerTwoScore));
          topText.setText("Welcome to Air Hockey");
          puck.goalReset();

          totalGoalsToWin = Integer
              .parseInt(JOptionPane.showInputDialog("Enter the total amount of goals required to win:"));

          gameState = true;
          mainScreen.setSpacePressedFalse();

          soundPlayer.setDrumRoll(true);
        }
      }

    }

  }
}
