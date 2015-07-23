package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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
        return DataDownloader.getData(urls[0]);
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mContext, result, Toast.LENGTH_LONG).show();
    /*    try {
            JSONObject json = new JSONObject(result);

            String str = "";

            JSONArray articles = json.getJSONArray("articleList");
            str += "articles length = " + json.getJSONArray("articleList").length();
            str += "\n--------\n";
            str += "names: " + articles.getJSONObject(0).names();
            str += "\n--------\n";
            str += "url: " + articles.getJSONObject(0).getString("url");

            // etResponse.setText(str);
            //etResponse.setText(json.toString(1));

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        */
    }
}
