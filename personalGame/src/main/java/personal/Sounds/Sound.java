package personal.Sounds;

import java.util.HashMap;
import java.util.LinkedList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Sound {


    public static HashMap<Integer, String> soundIds = new HashMap<>();
    public static HashMap<Integer, SoundData> soundData = new HashMap<>();
    public static boolean wasInit = false;
    public static LinkedList<Integer> queue;
    public static final int [] combatSounds = {0, 1};
    //EACH Sound's id and file name
    public static void init() {
      queue = new LinkedList<>();
      soundIds.put(0, "swosh-01.wav");
      soundIds.put(1, "Hit 3.wav");
    }


    public static void loadSound(int soundId, String url) {
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

    public static void unLoadSound(int soundId) {
      soundData.remove(Integer.valueOf(soundId));
    }
    
    public static void playSound(int soundId) {
      soundData.get(Integer.valueOf(soundId)).play();
    } 

    public static void loadSounds(int [] soundidints) {
      if (!wasInit) {
        init();
      }
      for (int i = 0; i < soundidints.length; i++) {
        loadSound(soundidints[i], soundIds.get(soundidints[i]));
      }
    }

    public static void unLoadSounds(int [] soundidints) {
      for (int i = 0; i < soundidints.length; i++) {
        unLoadSound(soundidints[i]);
      }
    }


}
