package com.example.ioc.evshare.model;

import android.graphics.Bitmap;

public class Photo {
    private Long id;
    private Bitmap image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
