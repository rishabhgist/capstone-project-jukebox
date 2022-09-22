package com.niit.jdp.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SongPlayer {
    AudioInputStream audioInputStream;
    Clip clip;

    String filePath;
    private boolean clipStatus;

    public boolean isClipStatus() {
        return clipStatus;
    }

    public void setClipStatus(boolean clipStatus) {
        this.clipStatus = clipStatus;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void play() {
        File url = new File(getFilePath());
        try {
            audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        if (clipStatus) {
            clip.stop();
            play();
        } else {
            setClipStatus(true);
            play();
        }
    }

}
