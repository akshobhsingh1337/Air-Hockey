public class Driver {
  public static void main(String args[]) {

    GameArena g1 = new GameArena(1000, 500);

    Rectangle r1 = new Rectangle(0, 0, 1000, 500, "BLUE", 0);

    Rectangle r2 = new Rectangle(20, 20, 960, 460, "WHITE", 1);

    g1.addRectangle(r1);
    g1.addRectangle(r2);

    Line goal1 = new Line(25, 150, 25, 350, 10, "GREY", 2);

    Line goal2 = new Line(975, 150, 975, 350, 10, "GREY", 2);

    Line center = new Line(500, 20, 500, 480, 1, "BLUE", 2);

    g1.addLine(goal1);
    g1.addLine(goal2);
    g1.addLine(center);

    Ball centerA = new Ball(500, 250, 100, "BLUE", 3);
    Ball centerB = new Ball(500, 250, 98, "WHITE", 3);

    g1.addBall(centerA);
    g1.addBall(centerB);

    Ball userA = new Ball(150, 250, 50, "BLUE", 4);
    Ball userB = new Ball(850, 250, 50, "BLUE", 4);
    Ball blackBall = new Ball(500, 250, 25, "BLACK", 4);

    g1.addBall(userA);
    g1.addBall(userB);
    g1.addBall(blackBall);

    while (true) {
      blackBall.start();
      g1.pause();
      // g1.pause();
      // System.out.println(blackBall.getXPosition());

      double lastAXSpeed = 0;
      double lastAYSpeed = 0;

      double lastBXSpeed = 0;
      double lastBYSpeed = 0;

      if (g1.letterPressed('w') && userA.getYPosition() > 40) {
        userA.setYPosition(userA.getYPosition() - 7);
        lastAYSpeed = -7;
      }

      if (g1.letterPressed('a') && userA.getXPosition() > 40) {
        userA.setXPosition(userA.getXPosition() - 7);
        lastAXSpeed = -7;
      }

      if (g1.letterPressed('s') && userA.getYPosition() + userA.getSize() < g1.getArenaHeight()) {
        userA.setYPosition(userA.getYPosition() + 7);
        lastAYSpeed = 7;
      }

      if (g1.letterPressed('d') && userA.getXPosition() + userA.getSize() < g1.getArenaWidth() / 2 ) {
        userA.setXPosition(userA.getXPosition() + 7);
        lastAXSpeed = 7;
      }

      if (g1.upPressed() && userB.getYPosition() > 40) {
        userB.setYPosition(userB.getYPosition() - 7);
        lastBYSpeed = -7;
      }

      if (g1.leftPressed() && (userB.getXPosition() > 550)) {
        userB.setXPosition(userB.getXPosition() - 7);
        lastBXSpeed = -7;
      }

      if (g1.downPressed() && userB.getYPosition() + userB.getSize() < g1.getArenaHeight()) {
        userB.setYPosition(userB.getYPosition() + 7);
        lastBYSpeed = 7;
      }

      if (g1.rightPressed() && userB.getXPosition() + userB.getSize() < g1.getArenaWidth()) {
        userB.setXPosition(userB.getXPosition() + 7);
        lastBXSpeed = 7;
      }

      // if ((blackBall.collides(userA)) || (blackBall.collides(userB))) {
      //   System.out.println("COLLISION");
      // }

      // if ((blackBall.collides(userA)) || (blackBall.collides(userB))) {
      //   // blackBall.bounceLeftRight();
      //   blackBall
      // }

      if (blackBall.collides(userA)) {
        blackBall.deflect(userA.getXPosition(), userA.getYPosition(), blackBall.getXPosition(), blackBall.getYPosition(), lastAXSpeed, lastAYSpeed, blackBall.getXSpeed(), blackBall.getYSpeed());
      }

      if (blackBall.collides(userB)) {
        blackBall.deflect(userB.getXPosition(), userB.getYPosition(), blackBall.getXPosition(), blackBall.getYPosition(), lastBXSpeed, lastBYSpeed, blackBall.getXSpeed(), blackBall.getYSpeed());
      }

      if (g1.letterPressed('p')) {
        System.out.println(userB.getXPosition());
      }

      if (blackBall.getYPosition() > 460) {
        blackBall.bounceUpDown();
      } 

      if (blackBall.getYPosition() < 40) {
        blackBall.bounceUpDown();
      }

      if (blackBall.getXPosition() < 40) {
        blackBall.bounceLeftRight();
      }

      if (blackBall.getXPosition() > 960) {
        blackBall.bounceLeftRight();
      }

    }
  }
}
