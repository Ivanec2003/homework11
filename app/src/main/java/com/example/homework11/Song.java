package com.example.homework11;

import java.io.Serializable;

public class Song implements Serializable {
    private final String name;
    private final String performer;
    private final int image;

    public Song(String name, String performer, int image) {
        this.name = name;
        this.performer = performer;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPerformer() {
        return performer;
    }

    public int getImage() {
        return image;
    }
}
