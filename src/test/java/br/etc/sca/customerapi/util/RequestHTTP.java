package br.etc.sca.customerapi.util;

import spark.utils.IOUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHTTP {

    private static Logger LOG = LoggerFactory.getLogger(RequestHTTP.class);

    private String endPoint;

    private RequestHTTP() {

    }

    private RequestHTTP(String endPoint) {
        this.endPoint = endPoint;
    }


    public static RequestHTTP prepare(String address, int port) {
        return prepare(address, port, false);
    }

    public static RequestHTTP prepare(String address, int port, boolean useHTTPS) {
        StringBuffer buffer = new StringBuffer();
        buffer.append( useHTTPS ? "https" : "http" );
        buffer.append("://");
        buffer.append(address);
        buffer.append(":");
        buffer.append(port);

        return new RequestHTTP( buffer.toString() );
    }

    public ResponseHTTP doRequest(String method, String path) {
        return doRequest( method, path, null);
    }

    public ResponseHTTP doRequest(String method, String path, String objectToJson) {
        String address = String.format("%s/%s", this.endPoint, path);
        try {
            URL url = new URL( address );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");

            if (objectToJson != null) {
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write( objectToJson );
                osw.flush();
                osw.close();
            }

            connection.connect();

            String body = IOUtils.toString(connection.getInputStream());

            return new ResponseHTTP(connection.getResponseCode(), body);

        } catch (IOException e) {
            LOG.error("Error trying to access ".concat(address));
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
