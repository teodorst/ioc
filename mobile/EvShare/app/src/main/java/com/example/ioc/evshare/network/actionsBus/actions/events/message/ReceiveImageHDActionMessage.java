package com.example.ioc.evshare.network.actionsBus.actions.events.message;

public class ReceiveImageHDActionMessage {

    private byte[] image;

    private Long imageId;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

}
