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
        if (!mResponse.equals("")) {
            try {
                JSONObject json = new JSONObject(mResponse);

                JSONArray sensors = json.getJSONArray("sensorList");

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

              /*          ArrayList<Sensor> test = Sensor.initializeSensorDataCreator();
                        test.remove(0);
                        test.add(0, new Sensor(Sensor.SensorType.TEMPERATURE.toString(), Integer.toString(temp) + " \u00b0C",
                                Sensor.SensorState.NONE.toString(), R.drawable.icon_temperature));
                        test.remove(1);
                        test.add(1, new Sensor(Sensor.SensorType.HUMIDITY.toString(), Integer.toString(humidity) + " %",
                                Sensor.SensorState.NONE.toString(), R.drawable.icon_humidity));*/

                        //Get indexes of last saved list from SharedPreferences if exists
                        ArrayList<Integer> selectedSensors = ConfigurationStateStoreManager.
                                loadConfiguration(mContext);
                        if (selectedSensors != null) {
                            // List of current selected sensor
                            ArrayList<Sensor> updateSensor = Sensor.selectedSensorDataCreator(selectedSensors);

                            for (int i = 0; i < updateSensor.size(); ++i) {
                                String check = updateSensor.get(i).getNameSensor().toLowerCase();

                                switch (check) {
                                    case "temperature": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp) + "\u2103");
                                        break;
                                    }
                                    case "humidity": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(humidity) + "%");
                                        break;
                                    }
                                    case "gas": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp));
                                        break;
                                    }
                                    case "smoke": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp));
                                        break;
                                    }
                                    case "carbonmonoxide": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp));
                                        break;
                                    }
                                    case "motion": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp));
                                        break;
                                    }
                                    case "distance": {
                                        updateSensor.get(i).setValueSensor(Integer.toString(temp));
                                        break;
                                    }
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