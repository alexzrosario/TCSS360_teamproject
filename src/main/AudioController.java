package main;

import javax.sound.sampled.*;
import java.io.*;

public class AudioController implements Serializable {

    private transient Clip backgroundClip;
    private transient Clip battleClip;
    private transient Clip bossClip;

    private static final long serialVersionUID = 3536060713340084481L;

    public void playAudio(String audioFile) {
        try {
            File musicPath = new File(audioFile);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.f);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBackgroundAudio() {
        try {
            File musicPath = new File("src/backgroundmusic2.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInput);
            FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-35.f);
            backgroundClip.start();
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBattleAudio() {
        try {
            File musicPath = new File("src/battlemusic2.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            battleClip = AudioSystem.getClip();
            battleClip.open(audioInput);
            FloatControl gainControl = (FloatControl) battleClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-35.f);
            battleClip.start();
            battleClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playBossAudio() {
        try {
            File musicPath = new File("src/finalboss.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            bossClip = AudioSystem.getClip();
            bossClip.open(audioInput);
            FloatControl gainControl = (FloatControl) bossClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.f);
            bossClip.start();
            bossClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBackgroundAudio() {
        this.backgroundClip.stop();
    }

    public void setBackgroundClip() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File musicPath = new File("src/backgroundmusic2.wav");
        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
        backgroundClip = AudioSystem.getClip();
        backgroundClip.open(audioInput);
        FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-35.f);
        backgroundClip.start();
        backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void startBackgroundAudio() {
        backgroundClip.start();
    }

    public void stopBattleAudio() {
        battleClip.stop();
    }

    public void stopBossAudio() {
        bossClip.stop();
    }

}
