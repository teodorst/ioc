package com.example.ioc.evshare.network.eventsBus.events;


public class BaseNetworkEvent {
    public static final String UNHANDLED_MSG = "UNHANDLED_MSG";
    public static final int UNHANDLED_CODE = -1;

    protected static class OnStart<Request> {
        private Request mRequest;

        public OnStart(Request request) {
            mRequest = request;
        }

        public Request getRequest() {
            return mRequest;
        }
    }


    protected static class OnDone<Response> {

        private Response mResponse;

        public OnDone(Response response) {
            mResponse = response;
        }

        public Response getResponse() {
            return mResponse;
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
