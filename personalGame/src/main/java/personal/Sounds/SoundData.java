package personal.Sounds;

import java.util.Queue;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundData {
    public int id;
    public byte[] data;
    public AudioFormat format;

    public SoundData(int id, byte[] data, AudioFormat format) {
        this.id = id;
        this.data = data;
        this.format = format;
    }



    public void play() {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(format, data, 0, data.length);
            clip.start();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}