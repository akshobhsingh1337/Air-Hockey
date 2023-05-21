/**
 * This class provides all functionality for handling the tables' boundaries.
 */
public class TableManager {
  private Ball puck;
  private Line leftGoal;
  private Line rightGoal;
  private SoundPlayer soundPlayer;
  private Ball leftMallet;
  private Ball rightMallet;
  private Text topText;
  private Text leftPlayerScore;
  private Text rightPlayerScore;

  /**
   * Constructor for the TableManager class, creates a new TableManager object
   * with all required elements.
   *
   * @param puck             Ball object representing the puck
   * @param leftGoal         Line object representing the left goal
   * @param rightGoal        Line object representing the right goal
   * @param soundPlayer      SoundPlayer object for handling sound playback
   * @param leftMallet       Ball object representing the left mallet
   * @param rightMallet      Ball object representing the right mallet
   * @param topText          Text object for displaying the top text
   * @param leftPlayerScore  Text object for displaying the score of the left
   *                         player
   * @param rightPlayerScore Text object for displaying the score of the right
   *                         player
   */
  public TableManager(Ball puck, Line leftGoal, Line rightGoal, SoundPlayer soundPlayer,
      Ball leftMallet, Ball rightMallet, Text topText, Text leftPlayerScore,
      Text rightPlayerScore) {
    this.puck = puck;
    this.leftGoal = leftGoal;
    this.rightGoal = rightGoal;
    this.soundPlayer = soundPlayer;
    this.leftMallet = leftMallet;
    this.rightMallet = rightMallet;
    this.topText = topText;
    this.leftPlayerScore = leftPlayerScore;
    this.rightPlayerScore = rightPlayerScore;
  }

  /**
   * Handles the puck bouncing off the walls of the table.
   *
   * @param lastPuckHit Value indicating which mallet last hit the puck (0 for
   *                    left mallet, 1 for right mallet)
   * @return An array containing the updated scores for both players
   */
  public int[] handleTableInteractions(int lastPuckHit) {
    if ((puck.getYPosition() > 610) || (puck.getYPosition() < 190)) {
      puck.bounceUpDown();
      soundPlayer.playBounce();
    }

    if ((puck.getXPosition() < 290
        && (puck.getYPosition() < leftGoal.getYStart() || puck.getYPosition() > leftGoal.getYEnd()))
        || (puck.getXPosition() > 1210
            && (puck.getYPosition() < rightGoal.getYStart() || puck.getYPosition() > rightGoal.getYEnd()))) {
      puck.bounceLeftRight();
      soundPlayer.playBounce();
    } else if (puck.getXPosition() <= leftGoal.getXEnd() - puck.getSize()
        && puck.getYPosition() >= leftGoal.getYStart() && puck.getYPosition() <= leftGoal.getYEnd()) {
      handleGoalScored(lastPuckHit, true);
    } else if (puck.getXPosition() >= rightGoal.getXStart() + puck.getSize()
        && puck.getYPosition() >= rightGoal.getYStart() && puck.getYPosition() <= rightGoal.getYEnd()) {
      handleGoalScored(lastPuckHit, false);
    }

    return new int[] { Integer.parseInt(leftPlayerScore.getText()), Integer.parseInt(rightPlayerScore.getText()) };
  }

  /**
   * Handles the mechanism after a goal is scored, including playing sounds and
   * resetting mallet positions.
   *
   * @param lastPuckHit Value indicating which mallet last hit the puck (0 for
   *                    left mallet, 1 for right mallet)
   * @param isLeftGoal  Flag indicating if the puck went into the left goal
   */
  private void handleGoalScored(int lastPuckHit, boolean isLeftGoal) {
    soundPlayer.playApplause();
    leftMallet.resetLeftMallet();
    rightMallet.resetRightMallet();

    if ((lastPuckHit == 0 && isLeftGoal) || (lastPuckHit == 1 && !isLeftGoal)) {
      puck.rightGoalReset();
      updateScoreAndText(false);
    } else {
      puck.leftGoalReset();
      updateScoreAndText(true);
    }
  }

  /**
   * Updates the score value and text based on the scoring player.
   *
   * @param isPlayerOne Flag indicating if the left player (playerOne) scored
   */
  private void updateScoreAndText(boolean isPlayerOne) {
    if (isPlayerOne) {
      int playerOneScore = Integer.parseInt(leftPlayerScore.getText()) + 1;
      leftPlayerScore.setText(String.valueOf(playerOneScore));
      topText.setText("Player 1 Wins that round");
    } else {
      int playerTwoScore = Integer.parseInt(rightPlayerScore.getText()) + 1;
      rightPlayerScore.setText(String.valueOf(playerTwoScore));
      topText.setText("Player 2 Wins that round");
    }
  }
}
