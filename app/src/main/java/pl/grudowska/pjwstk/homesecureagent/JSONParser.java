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

    public void start() {
        if (!mResponse.equals("")) {
            try {
                JSONObject json = new JSONObject(mResponse);

                JSONArray sensors = json.getJSONArray("sensorList");
                Long timestamp = sensors.getJSONObject(0).getLong("timestamp");
                Integer temp = sensors.getJSONObject(0).getInt("temperature");
                Integer humidity = sensors.getJSONObject(0).getInt("humidity");

                /*
                TODO support sensor below
                Integer gas = sensors.getJSONObject(0).getInt("gas");
                Integer smoke = sensors.getJSONObject(0).getInt("smoke");
                Integer carbonMonoxide = sensors.getJSONObject(0).getInt("carbon_monoxide");
                Integer distance = sensors.getJSONObject(0).getInt("distance");
                Integer motion = sensors.getJSONObject(0).getInt("motion");
                */

                // If app still working - update list from thread
                if (mContext != null) {

                    SensorScreenActivity activity = (SensorScreenActivity) mContext;
                    ListSensorFragment fragment = (ListSensorFragment) activity.getSupportFragmentManager().
                            findFragmentByTag("list_fragment");

                    if (fragment != null) {
                        //Get indexes of last saved list from SharedPreferences if exists
                        ArrayList<Integer> selectedSensors = ConfigurationStateStoreManager.
                                loadConfiguration(mContext);
                        if (selectedSensors != null) {
                            // List of current selected sensor
                            ArrayList<Sensor> updateSensor = Sensor.selectedSensorDataCreator(selectedSensors);

                            for (int i = 0; i < updateSensor.size(); ++i) {
                                String check = updateSensor.get(i).getNameSensor().toLowerCase();

                                switch (check) {
                                    case "timestamp": {
                                        Date date = new Date(timestamp);
                                        DateFormat formatter = new SimpleDateFormat("d MMM, HH:mm:ss", Locale.getDefault());
                                        updateSensor.get(i).setValueSensor(formatter.format(date));
                                        break;
                                    }
                                    case "temperature": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp) + "\u2103");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, temp));
                                        break;
                                    }
                                    case "humidity": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(humidity) + "%");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, humidity));
                                        break;
                                    }
                                    case "gas": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(0) + " unit");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "smoke": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(0) + " unit");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "carbonmonoxide": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(0) + " unit");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "motion": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(0) + " unit");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    case "distance": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(0) + "unit");
                                        updateSensor.get(i).setStatusSensor(StatusParser.parseValue(check, 0));
                                        break;
                                    }
                                    default:
                                        break;
                                }
                            }
                            fragment.updateList(updateSensor);
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