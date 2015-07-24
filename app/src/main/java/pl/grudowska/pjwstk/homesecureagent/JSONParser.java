package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 24.07.2015
 */
public class JSONParser {

    private Context mContext;
    private String mResponse;

    public JSONParser(Context context, String serverResponse) {
        mContext = context;
        mResponse = serverResponse;
    }

    public void start() {
        // TODO if response "" obsluzyc

        try {
            Toast.makeText(mContext, mResponse, Toast.LENGTH_LONG).show();
            JSONObject json = new JSONObject(mResponse);

            JSONArray sensors = json.getJSONArray("sensorList");
            double temp = sensors.getJSONObject(0).getDouble("temperature");
            double humidity = sensors.getJSONObject(0).getDouble("humidity");

            SensorScreenActivity activity = (SensorScreenActivity) mContext;
            ListSensorFragment fragment = (ListSensorFragment) activity.getSupportFragmentManager().
                    findFragmentByTag("list_fragment");
            ArrayList<Sensor> test = Sensor.sensorDataCreator();
            test.add(0, new Sensor(Sensor.SensorType.TEMPERATURE.toString(), "zupa z trupa",
                    Sensor.SensorState.NONE.toString(), R.drawable.icon_temperature));
            fragment.updateList(test);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
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