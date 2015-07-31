package pl.grudowska.pjwstk.homesecureagent;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmService extends Service {

    private NetworkAsyncTask mTask = null;

    public AlarmService() {
    }

    @Override
    public void onCreate() {
        // Toast.makeText(this, "AlarmService.onCreate()", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mTask = new NetworkAsyncTask();
        mTask.execute(SensorScreenActivity.adress());
        // Toast.makeText(this, "AlarmService.onStartCommand()", Toast.LENGTH_SHORT).show();
        return Service.START_NOT_STICKY;
    }

    public void callbackValues(String warningSensor) {
        Intent dialogIntent = new Intent(this, AlarmPopupActivity.class);
        dialogIntent.putExtra("warning", warningSensor);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        if (mTask != null) {
            mTask.cancel(true);
        }
        super.onDestroy();
    }

    private class NetworkAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return SensorDataDownloader.getData(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            parseValues(result);
        }

        public void parseValues(String result) {
            JSONObject json = null;
            try {
                json = new JSONObject(result);
                JSONArray sensors = json.getJSONArray("sensorList");
                Integer temp = sensors.getJSONObject(0).getInt("temperature");
                if (SensorStatusParser.parseValue("temperature", temp).equals("Warning")) {
                    callbackValues("Temperature");
                }
                Integer humidity = sensors.getJSONObject(0).getInt("humidity");
                if (SensorStatusParser.parseValue("humidity", humidity).equals("Warning")) {
                    callbackValues("Humidity");
                }
                // TODO parse values by scope
                Integer gas = sensors.getJSONObject(0).getInt("gas");
                Integer smoke = sensors.getJSONObject(0).getInt("smoke");
                Integer carbonMonoxide = sensors.getJSONObject(0).getInt("carbon monoxide");
                Integer distance = sensors.getJSONObject(0).getInt("distance");
                Integer motion = sensors.getJSONObject(0).getInt("motion");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
