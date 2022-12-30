package personal.Sounds;

import java.applet.AudioClip;
import java.util.HashMap;
import java.util.HashSet;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Sound {
  /* 
    public static synchronized void playSound(final String url) {
        new Thread(new Runnable() {
        // The wrapper thread is unnecessary, unless it blocks on the
        // Clip finishing; see comments.
          public void run() {
            try {
              Clip clip = AudioSystem.getClip();
              AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                getClass().getResourceAsStream(url));
              clip.open(inputStream);
              clip.start(); 
            } catch (Exception e) {
              System.err.println(e.getMessage());
            }
          }
        }).start();
    }
    */
    public static HashMap<Integer, String> soundIds = new HashMap<>();
    public static HashMap<Integer, Clip> sounds = new HashMap<>();
    public static boolean wasInit = false;

    public static void init() {
      soundIds.put(0, "swosh-01.wav");
      soundIds.put(1, "Hit 3.wav");
    }

    public static void loadSoud(int soundId, String url) {
      try {
        Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(url));
        clip.open(inputStream);
        sounds.put(Integer.valueOf(soundId), clip);
      } catch (Exception e) {
        System.err.println(e.getMessage() + "COULDNT LOAD SOUND");
      }
    }
    public static void playSound(int soundId) {
      sounds.get(Integer.valueOf(soundId)).setMicrosecondPosition(0);
      sounds.get(Integer.valueOf(soundId)).start();
    }

    public static void loadSounds(int [] soundidints) {
      if (!wasInit) {
        init();
      }
      for (int i = 0; i < soundidints.length; i++) {
        loadSoud(soundidints[i], soundIds.get(soundidints[i]));
      }
    }

}
