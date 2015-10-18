package com.example.olga.vkhometaskkire.models;

/**
 * Created by Olga on 18.10.2015.
 */
public class AudioItem {
    private AudioTreck treck;
    private int state;

    public static final int stateNone=0;
    public static final int statePlay=1;
    public static final int statePause=2;

    public AudioItem(AudioTreck treck, int state) {
        this.treck = treck;
        this.state = state;
    }

    public AudioTreck getTreck() {
        return treck;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
