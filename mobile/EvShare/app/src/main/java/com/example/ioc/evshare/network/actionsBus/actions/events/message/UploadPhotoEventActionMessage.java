package com.example.ioc.evshare.network.actionsBus.actions.events.message;

import android.graphics.Bitmap;

import java.io.File;

public class UploadPhotoEventActionMessage {


    private long eventId;
    private File imageFile;

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }
}
