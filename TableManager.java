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
