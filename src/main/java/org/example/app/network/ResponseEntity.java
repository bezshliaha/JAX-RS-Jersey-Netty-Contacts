package org.example.app.network;


public class ResponseEntity<T> extends ResponseInfo {

    private final T data;

    public ResponseEntity(int status, String message, boolean success, T data) {
        super(status, message, success);
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ " +
                super.toString() +
                " \"data\" : " + data.toString() +
                "} ";
    }
}
