package com.example.ioc.evshare.network;


public class NetworkManager {
    private String token;
    private static NetworkManager instance;

    private NetworkManager () {
    }

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
