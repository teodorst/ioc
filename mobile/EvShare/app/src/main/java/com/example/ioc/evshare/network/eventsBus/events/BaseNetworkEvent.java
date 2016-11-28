package com.example.ioc.evshare.network.eventsBus.events;


public class BaseNetworkEvent {
    public static final String UNHANDLED_MSG = "UNHANDLED_MSG";
    public static final int UNHANDLED_CODE = -1;

    protected static class OnStart<Message> {
        private Message message;

        public OnStart() {
            this.message = null;
        }

        public OnStart(Message message) {
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }
    }


    protected static class OnDone<Response> {

        private Response response;

        public OnDone(Response response) {
            this.response = response;
        }

        public Response getResponse() {
            return response;
        }

    }


    protected static class OnFailed {

        private String mErrorMessage;
        private int mCode;

        public OnFailed(String errorMessage, int code) {
            mErrorMessage = errorMessage;
            mCode = code;
        }

        public String getErrorMessage() {
            return mErrorMessage;
        }

        public int getCode() {
            return mCode;
        }
    }
}
