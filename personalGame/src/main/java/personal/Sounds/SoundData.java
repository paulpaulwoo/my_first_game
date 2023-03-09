package personal.Sounds;

import java.util.ArrayList;

import java.util.concurrent.Semaphore;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundData {
    public int id;
    public byte[] data;
    public AudioFormat format;
    public static ArrayList<SoundData> soundQueue = new ArrayList<>();
    public static Semaphore sema = new Semaphore(0);
    public static Thread soundThread = new Thread(() -> {
        while (true) {            
            try {
                sema.acquire();
                playSound();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    

    public SoundData(int id, byte[] data, AudioFormat format) {
        this.id = id;
        this.data = data;
        this.format = format;
    }

    public static synchronized void addQueue(SoundData data) {
        sema.release(1);
        soundQueue.add(data);
    }

    public static synchronized void playSound() {
        SoundData currentData = soundQueue.remove(0);
        currentData.play();
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