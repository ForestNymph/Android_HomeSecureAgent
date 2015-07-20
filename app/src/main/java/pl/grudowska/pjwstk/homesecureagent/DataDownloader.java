package pl.grudowska.pjwstk.homesecureagent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by s.grudowska on 11.07.2015
 */
public class DataDownloader {

    public DataDownloader() {
    }

    public String getData(URL url) {

        InputStream inputStream = new ConnectionHandler().OpenConnection(url);
        String tagResult = null;

        if (inputStream != null) {
            try {
                tagResult = convertInputStreamToString(inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tagResult = "Open connection did not work";
        }
        return tagResult;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        return result;
    }
}
