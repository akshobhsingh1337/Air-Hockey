import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * This class handles all the sounds to be played.
 */
public class SoundPlayer {
  private boolean soundState = true;
  private boolean drumRollPlayed = true;

  /**
   * Constructor for SoundPlayer, creates a new SoundPlayer object.
   */
  public SoundPlayer() {
  }

  /**
   * Plays the specified sound file.
   *
   * @param filePath The location of the sound file.
   */
  public void playSound(String filePath) {
    try {
      File soundFile = new File(filePath);
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      clip.start();
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//       e.printStackTrace();
    }
  }

  /**
   * Obtains the state of the sound (on/off).
   *
   * @return The value of the sound state.
   */
  public boolean getSoundState() {
    return this.soundState;
  }

  /**
   * Sets the state of the sound (on/off).
   *
   * @param value The value to set the sound state to.
   */
  public void setSoundState(boolean value) {
    this.soundState = value;
  }

  /**
   * Plays the intro sound.
   * Note: The sound will only play if the sound state is true.
   */
  public void playIntro() {
    if (soundState) {
      new Thread(() -> playSound("sounds/fanfare.wav")).start();
    }
  }

  /**
   * Plays the hit sound.
   * Note: The sound will only play if the sound state is true.
   */
  public void playHit() {
    if (soundState) {
      new Thread(() -> playSound("sounds/hit.wav")).start();
    }
  }

  /**
   * Plays the bounce sound.
   * Note: The sound will only play if the sound state is true.
   */
  public void playBounce() {
    if (soundState) {
      new Thread(() -> playSound("sounds/bounce.wav")).start();
    }
  }

  /**
   * Plays the applause sound.
   * Note: The sound will only play if the sound state is true.
   */
  public void playApplause() {
    if (soundState) {
      new Thread(() -> playSound("sounds/applause.wav")).start();
    }
  }

  /**
   * Plays the drum roll sound.
   * Note: The sound will only play if the sound state is true and the drum roll
   * hasn't been played yet.
   */
  public void playDrumRoll() {
    if (soundState && drumRollPlayed) {
      new Thread(() -> playSound("sounds/drumroll.wav")).start();
    }
  }

  /**
   * Sets the drum roll state.
   *
   * @param value The value to set the drum roll state to.
   */
  public void setDrumRoll(boolean value) {
    drumRollPlayed = value;
  }

  /**
   * Obtains the drum roll state.
   *
   * @return The value of the drum roll state.
   */
  public boolean getDrumRoll() {
    return drumRollPlayed;
  }
}
