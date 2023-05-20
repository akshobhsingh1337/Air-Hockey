public class KeyboardInputs {
  public double[] handleLeftMalletInput(GameArena mainScreen, Ball leftMallet, double constantSpeed) {
    double updatedAYSpeed = 0;
    double updatedAXSpeed = 0;

    if (mainScreen.letterPressed('w') && leftMallet.getYPosition() > 190) {
      leftMallet.setYPosition(leftMallet.getYPosition() - (constantSpeed - 5));
      updatedAYSpeed = -constantSpeed;
    }

    if (mainScreen.letterPressed('a') && leftMallet.getXPosition() > 290) {
      leftMallet.setXPosition(leftMallet.getXPosition() - (constantSpeed - 5));
      updatedAXSpeed = -constantSpeed;
    }

    // 50 is the size of the mallet (diameter)
    if (mainScreen.letterPressed('s') && leftMallet.getYPosition() + 50 < 650) {
      leftMallet.setYPosition(leftMallet.getYPosition() + (constantSpeed - 5));
      updatedAYSpeed = constantSpeed;
    }

    if (mainScreen.letterPressed('d') && leftMallet.getXPosition() + 50 < 800) {
      leftMallet.setXPosition(leftMallet.getXPosition() + (constantSpeed - 5));
      updatedAXSpeed = constantSpeed;
    }

    return new double[] { updatedAYSpeed, updatedAXSpeed };
  }

  public double[] handleRightMalletInput(GameArena mainScreen, Ball rightMallet, double constantSpeed) {
    double updatedBXSpeed = 0;
    double updatedBYSpeed = 0;

    if (mainScreen.upPressed() && rightMallet.getYPosition() > 190) {
      rightMallet.setYPosition(rightMallet.getYPosition() - (constantSpeed - 5));
      updatedBYSpeed = -constantSpeed;
    }

    if (mainScreen.leftPressed() && (rightMallet.getXPosition() > 750)) {
      rightMallet.setXPosition(rightMallet.getXPosition() - (constantSpeed - 5));
      updatedBXSpeed = -constantSpeed;
    }

    if (mainScreen.downPressed() && rightMallet.getYPosition() + 50 < 650) {
      rightMallet.setYPosition(rightMallet.getYPosition() + (constantSpeed - 5));
      updatedBYSpeed = constantSpeed;
    }

    if (mainScreen.rightPressed() && rightMallet.getXPosition() + 50 < 1250) {
      rightMallet.setXPosition(rightMallet.getXPosition() + (constantSpeed - 5));
      updatedBXSpeed = constantSpeed;
    }

    return new double[] { updatedBYSpeed, updatedBXSpeed };
  }

  public void HandleMusicInput(GameArena mainScreen, SoundPlayer soundPlayer, Rectangle musicBox) {
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
  }

  public double handleCheatInputs(GameArena mainScreen, Cheats cheatEngine, Ball leftMallet, Ball rightMallet, Rectangle table, double originalConstantSpeed) {
    double newConstantSpeed = originalConstantSpeed;
  
    if (mainScreen.shiftPressed() && mainScreen.letterPressed('i')) {
      cheatEngine.giantMallet(leftMallet, rightMallet);
    }
  
    if (mainScreen.shiftPressed() && mainScreen.letterPressed('o')) {
      newConstantSpeed = 30;
    }
  
    if (mainScreen.shiftPressed() && mainScreen.letterPressed('p')) {
      cheatEngine.changeTableColor(table);
    }
  
    if (mainScreen.shiftPressed() && mainScreen.letterPressed('r')) {
      cheatEngine.resetGiantMallet(leftMallet, rightMallet);
      newConstantSpeed = 15;
      cheatEngine.resetTableColor(table);
    }
  
    return newConstantSpeed;
  }
  
}
