package br.etc.sca.customerapi.util;

public class ResponseHTTP {

    private final int status;

    private final String body;

    public ResponseHTTP(int status, String body) {
        this.status = status;
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public int getStatus() {
        return status;
    }
}
