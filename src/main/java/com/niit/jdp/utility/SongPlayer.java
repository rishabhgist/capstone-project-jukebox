package com.niit.jdp.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SongPlayer {
    AudioInputStream audioInputStream;
    Clip clip;

    long clipTime;

    String filePath;
    private boolean clipStatus;

    private boolean pauseStatus;


    public boolean isClipStatus() {
        return clipStatus;
    }

    public void setClipStatus(boolean clipStatus) {
        this.clipStatus = clipStatus;
    }

    public boolean isPauseStatus() {
        return pauseStatus;
    }

    public void setPauseStatus(boolean pauseStatus) {
        this.pauseStatus = pauseStatus;
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
            if (clipTime != 0) {
                clip.setMicrosecondPosition(clipTime);
            }
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        if (clipStatus) {
            clip.stop();
            setClipStatus(false);
        } else {
            setClipStatus(true);
            play();
        }
    }

    public void pause() {
        if (!isPauseStatus()) {
            clipTime = clip.getMicrosecondLength();
            setPauseStatus(true);
            clip.stop();
        } else {
            clip.setMicrosecondPosition(clipTime);
            play();
            setPauseStatus(false);
        }
    }

}
