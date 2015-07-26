package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 23.07.2015
 */
public class ConfigurationStateStoreManager {

    public static void saveSensorObjectsIndexArray(Context context, ArrayList<Integer> sensor) {
        SharedPreferences mPrefs = context.getSharedPreferences("objects_sensor", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();

        // Create object from list indexes
        ArrayList<Sensor> sensorObject = Sensor.selectedSensorDataCreator(sensor);

        String json = gson.toJson(sensorObject);
        prefsEditor.putString("objects_sensor", json);
        prefsEditor.apply();
    }

    public static void saveSensorObjectsSensorArray(Context context, ArrayList<Sensor> sensor) {
        SharedPreferences mPrefs = context.getSharedPreferences("objects_sensor", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(sensor);
        prefsEditor.putString("objects_sensor", json);
        prefsEditor.apply();
    }

    public static ArrayList<Sensor> loadSensorObjects(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("objects_sensor", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("objects_sensor", "");

        // new TypeToken (...) return Sensor Array
        ArrayList<Sensor> sensors = gson.fromJson(json, new TypeToken<ArrayList<Sensor>>() {
        }.getType());

        return sensors;
    }

    public static boolean isStored(Context context, String key) {
        SharedPreferences mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        if (mPrefs.contains(key)) {
            return true;
        } else {
            return false;
        }
    }
}
