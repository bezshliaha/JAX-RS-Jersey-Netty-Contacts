package org.example.app.network;

import java.util.List;

public class ResponseEntityList<T> extends ResponseInfo {
    private final List<T> data;

    public ResponseEntityList(int status, String message, boolean success, List<T> data) {
        super(status, message, success);
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ " +
                super.toString() +
                " \"data\" : " + data.stream().toList() +
                "} ";
    }
}
