public class Driver {
  public static void main(String args[]) {

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

      // blackBall.start();
      // g1.pause();
      // System.out.println(blackBall.getXPosition());

      if (g1.letterPressed('w') && userA.getYPosition() > 190) {
        userA.setYPosition(userA.getYPosition() - 10);
        lastAYSpeed = -constantSpeed;
      }

      if (g1.letterPressed('a') && userA.getXPosition() > 290) {
        userA.setXPosition(userA.getXPosition() - 10);
        lastAXSpeed = -constantSpeed;
      }

      if (g1.letterPressed('s') && userA.getYPosition() + userA.getSize() < 650) {
        userA.setYPosition(userA.getYPosition() + 10);
        lastAYSpeed = constantSpeed;
      }

      if (g1.letterPressed('d') && userA.getXPosition() + userA.getSize() < 780) {
        userA.setXPosition(userA.getXPosition() + 10);
        lastAXSpeed = constantSpeed;
      }

      if (g1.upPressed() && userB.getYPosition() > 190) {
        userB.setYPosition(userB.getYPosition() - 10);
        lastBYSpeed = -constantSpeed;
      }

      if (g1.leftPressed() && (userB.getXPosition() > 750)) {
        userB.setXPosition(userB.getXPosition() - 10);
        lastBXSpeed = -constantSpeed;
      }

      if (g1.downPressed() && userB.getYPosition() + userB.getSize() < 650) {
        userB.setYPosition(userB.getYPosition() + 10);
        lastBYSpeed = constantSpeed;
      }

      if (g1.rightPressed() && userB.getXPosition() + userB.getSize() < 1250) {
        userB.setXPosition(userB.getXPosition() + 10);
        lastBXSpeed = constantSpeed;
      }

      if (blackBall.collides(userA)) {
        blackBall.deflect(userA.getXPosition(), userA.getYPosition(), blackBall.getXPosition(),
            blackBall.getYPosition(), lastAXSpeed, lastAYSpeed, blackBall.getXSpeed(), blackBall.getYSpeed());
            // blackBall.applyFriction();
        lastPuckHit = 0;
      }

      if (blackBall.collides(userB)) {
        blackBall.deflect(userB.getXPosition(), userB.getYPosition(), blackBall.getXPosition(),
            blackBall.getYPosition(), lastBXSpeed, lastBYSpeed, blackBall.getXSpeed(), blackBall.getYSpeed());
            // blackBall.applyFriction();
        lastPuckHit = 1;
      }

      if (g1.letterPressed('p')) {
        System.out.println(userA.getXPosition());
      }

      if (g1.letterPressed('p')) {
        System.out.println(userA.getYPosition());
      }

      if ((blackBall.getYPosition() > 610) || (blackBall.getYPosition() < 190)) {
        // blackBall.applyFriction();
        blackBall.bounceUpDown();
      }

      if ((blackBall.getXPosition() < 290
          && (blackBall.getYPosition() < goal1.getYStart() || blackBall.getYPosition() > goal1.getYEnd())) ||
          (blackBall.getXPosition() > 1210
              && (blackBall.getYPosition() < goal2.getYStart() || blackBall.getYPosition() > goal2.getYEnd()))) {
        // Ball hits the side walls, bounce left or right
        blackBall.bounceLeftRight();
        // blackBall.applyFriction();
      } else if ((blackBall.getXPosition() <= goal1.getXEnd() - blackBall.getSize()
          && blackBall.getYPosition() >= goal1.getYStart() && blackBall.getYPosition() <= goal1.getYEnd())) {
        if (lastPuckHit == 0) {
          blackBall.rightGoalReset();
          System.out.println("GOAL FOR PLAYER 2 (RIGHT)");
          gameText.setText("Player 2 Wins that round");
          playerTwoScore += 1;
          player2.setText(String.valueOf(playerTwoScore));
        } else {
          blackBall.leftGoalReset();
          System.out.println("GOAL FOR PLAYER 1 (LEFT)");
          gameText.setText("Player 1 Wins that round");
          playerOneScore += 1;
          player1.setText(String.valueOf(playerOneScore));
        }

      } else if ((blackBall.getXPosition() >= goal2.getXStart() + blackBall.getSize()
          && blackBall.getYPosition() >= goal2.getYStart() && blackBall.getYPosition() <= goal2.getYEnd())) {
        if (lastPuckHit == 0) {
          blackBall.leftGoalReset();
          System.out.println("GOAL FOR PLAYER 1 (LEFT)");
          gameText.setText("Player 1 Wins that round");
          playerOneScore += 1;
          player1.setText(String.valueOf(playerOneScore));
        } else {
          blackBall.rightGoalReset();
          System.out.println("GOAL FOR PLAYER 2 (RIGHT)");
          gameText.setText("Player 2 Wins that round");
          playerTwoScore += 1;
          player2.setText(String.valueOf(playerTwoScore));
        }
      }

    }
  }
}
