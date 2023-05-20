import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
  private boolean soundState = true;
  private boolean drumRollPlayed = true;

  public void playSound(String filePath) {
    try {
      File soundFile = new File(filePath);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public boolean getSoundState() {
    return this.soundState;
  }

  public void setSoundState(boolean value) {
    this.soundState = value;
  }

  public void playIntro() {
    if (soundState) {
      new Thread(() -> playSound("sounds/fanfare.wav")).start();
    }
  }

  public void playHit() {
    if (soundState) {
      new Thread(() -> playSound("sounds/hit.wav")).start();
    }
  }

  public void playBounce() {
    if (soundState) {
      new Thread(() -> playSound("sounds/bounce.wav")).start();
    }
  }

  public void playApplause() {
    if (soundState) {
      new Thread(() -> playSound("sounds/applause.wav")).start();
    }
  }

  public void playDrumRoll() {
    if (soundState && drumRollPlayed) {
      new Thread(() -> {
        playSound("sounds/drumroll.wav");
      }).start();
    }
  }

  public void setDrumRoll(boolean value) {
    drumRollPlayed = value;
  }

  public boolean getDrumRoll() {
    return drumRollPlayed;
  }

}
