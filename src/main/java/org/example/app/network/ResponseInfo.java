package org.example.app.network;

public class ResponseInfo {

    private final int status;
    private final String message;
    private final boolean success;

    public ResponseInfo(int status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    @Override
    public String toString() {
        return "\"status\" : " + status + ", " +
                "\"message\" : \"" + message + "\", " +
                "\"success\" : " + success + ", ";
    }
}
