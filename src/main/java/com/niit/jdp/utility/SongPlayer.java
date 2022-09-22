package com.niit.jdp.utility;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SongPlayer {
    AudioInputStream audioInputStream;
    Clip clip;

    private long clipTime;

    private String filePath;
    private boolean clipStatus;

    private boolean pauseStatus;

    public long getClipTime() {
        return clipTime;
    }

    public void setClipTime(long clipTime) {
        this.clipTime = clipTime;
    }

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
            if (getClipTime() != 0) {
                clip.setMicrosecondPosition(getClipTime());
            }
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        if (!clipStatus) {
            setClipStatus(true);
            play();
        } else {
            setClipTime(0);
            setClipStatus(false);
            clip.stop();
        }
    }

    public void pause() {
        if (!isPauseStatus()) {
            setPauseStatus(true);
            setClipTime(clip.getMicrosecondPosition());
            clip.stop();
        } else {
            play();
            setPauseStatus(false);
        }
    }

}
