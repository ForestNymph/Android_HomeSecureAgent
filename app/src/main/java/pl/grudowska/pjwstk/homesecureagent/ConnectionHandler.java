package pl.grudowska.pjwstk.homesecureagent;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by s.grudowska on 11.07.2015
 */
public class ConnectionHandler {

    public ConnectionHandler() {
    }

    public InputStream OpenConnection(URL url) {
        HttpURLConnection connect = null;
        InputStream inputStream = null;
        try {
            connect = (HttpURLConnection) url.openConnection();
            inputStream = connect.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
