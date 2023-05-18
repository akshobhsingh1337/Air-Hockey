public class CreateScreen {

  private Ball userA;
  private Ball userB;
  private Ball blackBall;
  private GameArena g1;

  CreateScreen() {

    g1 = new GameArena(1000, 500);

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


    userA = new Ball(150, 250, 50, "BLUE", 4);
    userB = new Ball(850, 250, 50, "BLUE", 4);
    blackBall = new Ball(500, 250, 25, "BLACK", 4);

    g1.addBall(userA);
    g1.addBall(userB);
    g1.addBall(blackBall);

  }

  public Ball getBall1() {
    return userA;
  }

  public Ball getBall2() {
    return userB;
  }

  public Ball getPuck() {
    return blackBall;
  }

  public GameArena getGA() {
    return g1;
  }

}
