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


    double lastAXSpeed = 0;
    double lastAYSpeed = 0;

    double lastBXSpeed = 0;
    double lastBYSpeed = 0;

    double constantSpeed = 15;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    SoundPlayer soundPlayer = new SoundPlayer();
    Cheats cheatEngine = new Cheats();

    soundPlayer.playIntro();

    while (true) {
      mainScreen.pause();
      if (puck.getMoveState() == true) {
        puck.start();
      }

      puck.applyFriction();

      int lastPuckHit = 0;

      if (gameState == true) {
        if (mainScreen.letterPressed('w') && leftMallet.getYPosition() > 190) {
          leftMallet.setYPosition(leftMallet.getYPosition() - (constantSpeed - 5));
          lastAYSpeed = -constantSpeed;
        }

        if (mainScreen.letterPressed('a') && leftMallet.getXPosition() > 290) {
          leftMallet.setXPosition(leftMallet.getXPosition() - (constantSpeed - 5));
          lastAXSpeed = -constantSpeed;
        }

        // 50 is size of mallet (diameter)
        if (mainScreen.letterPressed('s') && leftMallet.getYPosition() + 50 < 650) {
          leftMallet.setYPosition(leftMallet.getYPosition() + (constantSpeed - 5));
          lastAYSpeed = constantSpeed;
        }

        if (mainScreen.letterPressed('d') && leftMallet.getXPosition() + 50 < 800) {
          leftMallet.setXPosition(leftMallet.getXPosition() + (constantSpeed - 5));
          lastAXSpeed = constantSpeed;
        }

        if (mainScreen.upPressed() && rightMallet.getYPosition() > 190) {
          rightMallet.setYPosition(rightMallet.getYPosition() - (constantSpeed - 5));
          lastBYSpeed = -constantSpeed;
        }

        if (mainScreen.leftPressed() && (rightMallet.getXPosition() > 750)) {
          rightMallet.setXPosition(rightMallet.getXPosition() - (constantSpeed - 5));
          lastBXSpeed = -constantSpeed;
        }

        if (mainScreen.downPressed() && rightMallet.getYPosition() + 50 < 650) {
          rightMallet.setYPosition(rightMallet.getYPosition() + (constantSpeed - 5));
          lastBYSpeed = constantSpeed;
        }

        if (mainScreen.rightPressed() && rightMallet.getXPosition() + 50 < 1250) {
          rightMallet.setXPosition(rightMallet.getXPosition() + (constantSpeed - 5));
          lastBXSpeed = constantSpeed;
        }

        if (mainScreen.letterPressed('m')) {
          mainScreen.pause();
          if (soundPlayer.getSoundState()) {
            musicBox.setColour("RED");
            soundPlayer.setSoundState(false);
          } else {
            musicBox.setColour("GREEN");
            soundPlayer.setSoundState(true);
          }
        }

        if ((mainScreen.shiftPressed()) && (mainScreen.letterPressed('i'))) {
          cheatEngine.giantMallet(leftMallet, rightMallet);
        }

        if ((mainScreen.shiftPressed()) && (mainScreen.letterPressed('o'))) {
          constantSpeed = 30;
        }

        if ((mainScreen.shiftPressed()) && (mainScreen.letterPressed('p'))) {
          cheatEngine.changeTableColor(table);
        }

        if ((mainScreen.shiftPressed()) && (mainScreen.letterPressed('r'))) {
          cheatEngine.resetGiantMallet(leftMallet, rightMallet);

          constantSpeed = 15;

          cheatEngine.resetTableColor(table);
        }
      }

      if (puck.collides(leftMallet)) {
        puck.deflect(leftMallet.getXPosition(), leftMallet.getYPosition(), puck.getXPosition(),
            puck.getYPosition(), lastAXSpeed, lastAYSpeed, puck.getXSpeed(), puck.getYSpeed());
        lastPuckHit = 0;
        soundPlayer.playHit();
      }

      if (puck.collides(rightMallet)) {
        puck.deflect(rightMallet.getXPosition(), rightMallet.getYPosition(), puck.getXPosition(),
            puck.getYPosition(), lastBXSpeed, lastBYSpeed, puck.getXSpeed(), puck.getYSpeed());
        lastPuckHit = 1;
        soundPlayer.playHit();
      }

      if ((puck.getYPosition() > 610) || (puck.getYPosition() < 190)) {
        puck.bounceUpDown();
        soundPlayer.playBounce();
      }

      if ((puck.getXPosition() < 290
          && (puck.getYPosition() < leftGoal.getYStart() || puck.getYPosition() > leftGoal.getYEnd())) ||
          (puck.getXPosition() > 1210
              && (puck.getYPosition() < rightGoal.getYStart() || puck.getYPosition() > rightGoal.getYEnd()))) {
        // Ball hits the side walls, bounce left or right
        puck.bounceLeftRight();
        soundPlayer.playBounce();
      } else if ((puck.getXPosition() <= leftGoal.getXEnd() - puck.getSize()
          && puck.getYPosition() >= leftGoal.getYStart() && puck.getYPosition() <= leftGoal.getYEnd())) {
        soundPlayer.playApplause();
        leftMallet.resetLeftMallet();
        rightMallet.resetRightMallet();
        if (lastPuckHit == 0) {
          puck.rightGoalReset();
          // System.out.println("GOAL FOR PLAYER 2 (RIGHT)");
          topText.setText("Player 2 Wins that round");
          playerTwoScore += 1;
          rightPlayerScore.setText(String.valueOf(playerTwoScore));
        } else {
          puck.leftGoalReset();
          // System.out.println("GOAL FOR PLAYER 1 (LEFT)");
          topText.setText("Player 1 Wins that round");
          playerOneScore += 1;
          leftPlayerScore.setText(String.valueOf(playerOneScore));
        }

      } else if ((puck.getXPosition() >= rightGoal.getXStart() + puck.getSize()
          && puck.getYPosition() >= rightGoal.getYStart() && puck.getYPosition() <= rightGoal.getYEnd())) {
        soundPlayer.playApplause();
        leftMallet.resetLeftMallet();
        rightMallet.resetRightMallet();
        if (lastPuckHit == 0) {
          puck.leftGoalReset();
          // System.out.println("GOAL FOR PLAYER 1 (LEFT)");
          topText.setText("Player 1 Wins that round");
          playerOneScore += 1;
          leftPlayerScore.setText(String.valueOf(playerOneScore));
        } else {
          puck.rightGoalReset();
          // System.out.println("GOAL FOR PLAYER 2 (RIGHT)");
          topText.setText("Player 2 Wins that round");
          playerTwoScore += 1;
          rightPlayerScore.setText(String.valueOf(playerTwoScore));
        }
      }

      if (playerOneScore == totalGoalsToWin) {
        topText.setText("Player 1 Wins the game! Press SPACE to start another game or ESC to exit");
        soundPlayer.playDrumRoll();
        soundPlayer.setDrumRoll(false);
        gameState = false;

        if (mainScreen.escPressed()) {
          mainScreen.exit();
          break;
        }

        // System.out.println(mainScreen.spacePressed());

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
