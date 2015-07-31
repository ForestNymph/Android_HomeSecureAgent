package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by s.grudowska on 23.07.2015
 */
public class HttpAsyncTask extends AsyncTask<String, Void, String> {

    private Context mContext;


    public HttpAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(String... urls) {
        return SensorDataDownloader.getData(urls[0]);
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        new JSONParser(mContext, result).start();
    }
}
