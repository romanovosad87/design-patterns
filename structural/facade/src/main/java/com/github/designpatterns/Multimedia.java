package com.github.designpatterns;

public class Multimedia {
    private AudioPlayer audioPlayer;
    private VideoPlayer videoPlayer;
    private ImageViewer imageViewer;

    public Multimedia() {
        this.audioPlayer = new AudioPlayer();
        this.videoPlayer = new VideoPlayer();
        this.imageViewer = new ImageViewer();
    }

    public void playAudio() {
        audioPlayer.play();
    }

    public void playVideo() {
        videoPlayer.play();
    }

    public void showImage() {
        imageViewer.show();
    }
}
