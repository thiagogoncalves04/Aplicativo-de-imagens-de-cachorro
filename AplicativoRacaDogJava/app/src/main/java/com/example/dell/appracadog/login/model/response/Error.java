package com.example.dell.appracadog.login.model.response;

import com.google.gson.annotations.Expose;

public class Error extends Exception{

    @Expose
    private Error error;
    @Expose
    private String message;

    public Error getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {

        private Error error;
        private String message;

        public Error.Builder withError(Error error) {
            this.error = error;
            return this;
        }

        public Error.Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public Error build() {
            Error error = new Error();
            error.error = error;
            error.message = message;
            return error;
        }

    }
}

