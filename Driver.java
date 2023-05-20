import javax.swing.JOptionPane;

public class Driver {

  public static void main(String args[]) {

    // Ask the user for the total amount of goals required to win
    String goalsInput = JOptionPane.showInputDialog("Enter the total amount of goals required to win:");
    int totalGoalsToWin = Integer.parseInt(goalsInput);
    boolean gameState = true;
    // System.out.println(totalGoalsToWin);

    GameArena g1 = new GameArena(1500, 800);

    Rectangle r0 = new Rectangle(0, 0, 1500, 800, "BLACK", 0);
    Rectangle r1 = new Rectangle(250, 150, 1000, 500, "BLUE", 1);
    Rectangle r2 = new Rectangle(270, 170, 960, 460, "WHITE", 2);

    g1.addRectangle(r0);
    g1.addRectangle(r1);
    g1.addRectangle(r2);

    Line goal1 = new Line(275, 300, 275, 500, 10, "GREY", 3);
    Line goal2 = new Line(1225, 300, 1225, 500, 10, "GREY", 3);
    Line center = new Line(750, 170, 750, 630, 1, "BLUE", 3);

    g1.addLine(goal1);
    g1.addLine(goal2);
    g1.addLine(center);

    Ball centerA = new Ball(750, 400, 100, "BLUE", 4);
    Ball centerB = new Ball(750, 400, 98, "WHITE", 4);

    g1.addBall(centerA);
    g1.addBall(centerB);

    Ball userA = new Ball(400, 400, 50, "BLUE", 5);
    Ball userB = new Ball(1100, 400, 50, "BLUE", 5);
    Ball blackBall = new Ball(750, 400, 25, "BLACK", 5);

    g1.addBall(userA);
    g1.addBall(userB);
    g1.addBall(blackBall);

    Rectangle musicBox = new Rectangle(1300, 600, 172, 30, "GREEN", 1);
    Text musicText = new Text("Sound Effects", 25, 1303, 622, "BLACK", 3);

    g1.addRectangle(musicBox);
    g1.addText(musicText);

    Text gameText = new Text("Welcome to Air Hockey", 30, 250, 100, "WHITE", 1);

    double lastAXSpeed = 0;
    double lastAYSpeed = 0;

    double lastBXSpeed = 0;
    double lastBYSpeed = 0;

    double constantSpeed = 15;

    int playerOneScore = 0;
    int playerTwoScore = 0;

    Text player1 = new Text(String.valueOf(playerOneScore), 30, 200, 400, "WHITE", 1);
    Text player2 = new Text(String.valueOf(playerTwoScore), 30, 1285, 400, "WHITE", 1);

    SoundPlayer soundPlayer = new SoundPlayer();
    Cheats cheatEngine = new Cheats();

    soundPlayer.playIntro();

    while (true) {
      g1.pause();
      if (blackBall.getMoveState() == true) {
        blackBall.start();
      }

      blackBall.applyFriction();

      int lastPuckHit = 0;

      g1.addText(gameText);
      g1.addText(player1);
      g1.addText(player2);

      if (gameState == true) {
        if (g1.letterPressed('w') && userA.getYPosition() > 190) {
          userA.setYPosition(userA.getYPosition() - (constantSpeed - 5));
          lastAYSpeed = -constantSpeed;
        }

        if (g1.letterPressed('a') && userA.getXPosition() > 290) {
          userA.setXPosition(userA.getXPosition() - (constantSpeed - 5));
          lastAXSpeed = -constantSpeed;
        }

        // 50 is size of mallet (diameter)
        if (g1.letterPressed('s') && userA.getYPosition() + 50 < 650) {
          userA.setYPosition(userA.getYPosition() + (constantSpeed - 5));
          lastAYSpeed = constantSpeed;
        }

        if (g1.letterPressed('d') && userA.getXPosition() + 50 < 800) {
          userA.setXPosition(userA.getXPosition() + (constantSpeed - 5));
          lastAXSpeed = constantSpeed;
        }

        if (g1.upPressed() && userB.getYPosition() > 190) {
          userB.setYPosition(userB.getYPosition() - (constantSpeed - 5));
          lastBYSpeed = -constantSpeed;
        }

        if (g1.leftPressed() && (userB.getXPosition() > 750)) {
          userB.setXPosition(userB.getXPosition() - (constantSpeed - 5));
          lastBXSpeed = -constantSpeed;
        }

        if (g1.downPressed() && userB.getYPosition() + 50 < 650) {
          userB.setYPosition(userB.getYPosition() + (constantSpeed - 5));
          lastBYSpeed = constantSpeed;
        }

        if (g1.rightPressed() && userB.getXPosition() + 50 < 1250) {
          userB.setXPosition(userB.getXPosition() + (constantSpeed - 5));
          lastBXSpeed = constantSpeed;
        }

        if (g1.letterPressed('m')) {
          g1.pause();
          if (soundPlayer.getSoundState()) {
            musicBox.setColour("RED");
            soundPlayer.setSoundState(false);
          } else {
            musicBox.setColour("GREEN");
            soundPlayer.setSoundState(true);
          }
        }

        if ((g1.shiftPressed()) && (g1.letterPressed('i'))) {
          cheatEngine.giantMallet(userA, userB);
        }

        if ((g1.shiftPressed()) && (g1.letterPressed('o'))) {
          constantSpeed = 30;
        }

        if ((g1.shiftPressed()) && (g1.letterPressed('p'))) {
          cheatEngine.changeTableColor(r2);
        }

        if ((g1.shiftPressed()) && (g1.letterPressed('r'))) {
          cheatEngine.resetGiantMallet(userA, userB);

          constantSpeed = 15;

          cheatEngine.resetTableColor(r2);
        }
      }

      if (blackBall.collides(userA)) {
        blackBall.deflect(userA.getXPosition(), userA.getYPosition(), blackBall.getXPosition(),
            blackBall.getYPosition(), lastAXSpeed, lastAYSpeed, blackBall.getXSpeed(), blackBall.getYSpeed());
        lastPuckHit = 0;
        soundPlayer.playHit();
      }

      if (blackBall.collides(userB)) {
        blackBall.deflect(userB.getXPosition(), userB.getYPosition(), blackBall.getXPosition(),
            blackBall.getYPosition(), lastBXSpeed, lastBYSpeed, blackBall.getXSpeed(), blackBall.getYSpeed());
        lastPuckHit = 1;
        soundPlayer.playHit();
      }

      if ((blackBall.getYPosition() > 610) || (blackBall.getYPosition() < 190)) {
        blackBall.bounceUpDown();
        soundPlayer.playBounce();
      }

      if ((blackBall.getXPosition() < 290
          && (blackBall.getYPosition() < goal1.getYStart() || blackBall.getYPosition() > goal1.getYEnd())) ||
          (blackBall.getXPosition() > 1210
              && (blackBall.getYPosition() < goal2.getYStart() || blackBall.getYPosition() > goal2.getYEnd()))) {
        // Ball hits the side walls, bounce left or right
        blackBall.bounceLeftRight();
        soundPlayer.playBounce();
      } else if ((blackBall.getXPosition() <= goal1.getXEnd() - blackBall.getSize()
          && blackBall.getYPosition() >= goal1.getYStart() && blackBall.getYPosition() <= goal1.getYEnd())) {
        soundPlayer.playApplause();
        userA.resetLeftMallet();
        userB.resetRightMallet();
        if (lastPuckHit == 0) {
          blackBall.rightGoalReset();
          // System.out.println("GOAL FOR PLAYER 2 (RIGHT)");
          gameText.setText("Player 2 Wins that round");
          playerTwoScore += 1;
          player2.setText(String.valueOf(playerTwoScore));
        } else {
          blackBall.leftGoalReset();
          // System.out.println("GOAL FOR PLAYER 1 (LEFT)");
          gameText.setText("Player 1 Wins that round");
          playerOneScore += 1;
          player1.setText(String.valueOf(playerOneScore));
        }

      } else if ((blackBall.getXPosition() >= goal2.getXStart() + blackBall.getSize()
          && blackBall.getYPosition() >= goal2.getYStart() && blackBall.getYPosition() <= goal2.getYEnd())) {
        soundPlayer.playApplause();
        userA.resetLeftMallet();
        userB.resetRightMallet();
        if (lastPuckHit == 0) {
          blackBall.leftGoalReset();
          // System.out.println("GOAL FOR PLAYER 1 (LEFT)");
          gameText.setText("Player 1 Wins that round");
          playerOneScore += 1;
          player1.setText(String.valueOf(playerOneScore));
        } else {
          blackBall.rightGoalReset();
          // System.out.println("GOAL FOR PLAYER 2 (RIGHT)");
          gameText.setText("Player 2 Wins that round");
          playerTwoScore += 1;
          player2.setText(String.valueOf(playerTwoScore));
        }
      }

      if (playerOneScore == totalGoalsToWin) {
        gameText.setText("Player 1 Wins the game! Press SPACE to start another game or ESC to exit");
        soundPlayer.playDrumRoll();
        soundPlayer.setDrumRoll(false);
        gameState = false;

        if (g1.escPressed()) {
          g1.exit();
        }

        // System.out.println(g1.spacePressed());

        if (g1.spacePressed()) {
          playerOneScore = 0;
          playerTwoScore = 0;
          player1.setText(String.valueOf(playerOneScore));
          player2.setText(String.valueOf(playerTwoScore));
          gameText.setText("Welcome to Air Hockey");
          blackBall.goalReset();

          totalGoalsToWin = Integer
              .parseInt(JOptionPane.showInputDialog("Enter the total amount of goals required to win:"));

          gameState = true;
          g1.setSpacePressedFalse();

          soundPlayer.setDrumRoll(true);
        }
      } else if (playerTwoScore == totalGoalsToWin) {
        gameText.setText("Player 2 Wins the game! Press SPACE to start another game or ESC to exit");
        soundPlayer.playDrumRoll();
        soundPlayer.setDrumRoll(false);
        gameState = false;

        if (g1.escPressed()) {
          g1.exit();
        }

        if (g1.spacePressed()) {
          playerOneScore = 0;
          playerTwoScore = 0;
          player1.setText(String.valueOf(playerOneScore));
          player2.setText(String.valueOf(playerTwoScore));
          gameText.setText("Welcome to Air Hockey");
          blackBall.goalReset();

          totalGoalsToWin = Integer
              .parseInt(JOptionPane.showInputDialog("Enter the total amount of goals required to win:"));

          gameState = true;
          g1.setSpacePressedFalse();

          soundPlayer.setDrumRoll(true);
        }
      }

    }

  }
}
