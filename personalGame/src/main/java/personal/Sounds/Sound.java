package personal.Sounds;

import java.util.HashMap;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

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
    public static HashMap<Integer, SoundData> soundData = new HashMap<>();
    public static boolean wasInit = false;

    public static void init() {
      soundIds.put(0, "swosh-01.wav");
      soundIds.put(1, "Hit 3.wav");
    }

    public static void loadSoud(int soundId, String url) {
      try {
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sound.class.getResourceAsStream(url));
        byte[] data = inputStream.readAllBytes();
        AudioFormat format = inputStream.getFormat();
        SoundData dataRun = new SoundData(soundId, data, format);
        soundData.put(Integer.valueOf(soundId), dataRun);
      } catch (Exception e) {
        System.err.println(e.getMessage() + "COULDNT LOAD SOUND");
      }
    }
    
    public static void playSound(int soundId) {
      soundData.get(Integer.valueOf(soundId)).play();
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
