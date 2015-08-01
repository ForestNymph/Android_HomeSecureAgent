package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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

    public JSONParser(String serverResponse) {
        mResponse = serverResponse;
    }

    public void start() {
        if (!mResponse.equals("")) {
            try {
                JSONObject json = new JSONObject(mResponse);

                JSONArray sensors = json.getJSONArray("sensorList");
                Long timestamp = sensors.getJSONObject(0).getLong("timestamp");
                Integer temp = sensors.getJSONObject(0).getInt("temperature");
                Integer humidity = sensors.getJSONObject(0).getInt("humidity");
                Integer gas = sensors.getJSONObject(0).getInt("gas");
                Integer smoke = sensors.getJSONObject(0).getInt("smoke");
                Integer carbonMonoxide = sensors.getJSONObject(0).getInt("carbon monoxide");
                // Integer distance = sensors.getJSONObject(0).getInt("distance");
                Integer motion = sensors.getJSONObject(0).getInt("motion");

                // If app still working - update UI list from thread
                if (mContext != null) {

                    SensorScreenActivity activity = (SensorScreenActivity) mContext;
                    ListSensorFragment fragment = (ListSensorFragment) activity.getSupportFragmentManager().
                            findFragmentByTag("list_fragment");

                    if (fragment != null) {
                        ArrayList<Sensor> selectedSensors = null;
                        if (DataStoreManager.isStored(mContext, "objects_sensor")) {
                            // Get latest saved sensors list objects and update
                            selectedSensors = DataStoreManager.
                                    loadSensorObjects(mContext);
                        } else {
                            selectedSensors = Sensor.initializeSensorDataCreator();
                        }
                        if (selectedSensors != null) {
                            for (int i = 0; i < selectedSensors.size(); ++i) {
                                String check = selectedSensors.get(i).getNameSensor().toLowerCase();

                                switch (check) {
                                    case "timestamp": {
                                        Date date = new Date(timestamp);
                                        DateFormat formatter = new SimpleDateFormat("d MMM, HH:mm:ss", Locale.getDefault());
                                        selectedSensors.get(i).setValueSensor(formatter.format(date));
                                        break;
                                    }
                                    case "temperature": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(temp) + "\u2103");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, temp));
                                        break;
                                    }
                                    case "humidity": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(humidity) + "%");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, humidity));
                                        break;
                                    }
                                    case "gas": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(gas) + " unit");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "smoke": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(smoke) + " unit");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "carbon monoxide": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(carbonMonoxide) + " unit");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "motion": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(motion) + " unit");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    /*  case "distance": {
                                        selectedSensors.get(i).setValueSensor(Integer.toString(distance) + " unit");
                                        selectedSensors.get(i).setStatusSensor(SensorStatusParser.parseValue(check, 0));
                                        break;
                                    }*/
                                    default:
                                        break;
                                }
                            }
                            fragment.updateListView(selectedSensors);
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(mContext, "Server connection error", Toast.LENGTH_SHORT).show();
        }
    }
}