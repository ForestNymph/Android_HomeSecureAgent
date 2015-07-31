package pl.grudowska.pjwstk.homesecureagent;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by s.grudowska on 23.07.2015
 */
public class SensorDataDownloader {

    public static String getData(String url) {

        InputStream inputStream = null;
        String result = "";

        try {
            // Create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // Make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            // Receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
            } else {
                result = "Did not work!";
            }
        } catch (IOException e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        inputStream.close();
        return result;
    }
}
