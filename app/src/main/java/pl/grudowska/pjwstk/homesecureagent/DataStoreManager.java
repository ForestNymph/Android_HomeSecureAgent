package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 23.07.2015
 */
public class DataStoreManager {

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

    public static void saveIntegerValue(Context context, String key, int value) {
        SharedPreferences mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putInt(key, value);
        prefsEditor.apply();
    }

    public static int loadIntegerValue(Context context, String key) {
        SharedPreferences mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        int value = mPrefs.getInt(key, 0);
        return value;
    }

    // To check if server working. If downloaded data has the same timestamp like last time
    // this means that the server does not reach the latest data from the sensors
    // Not refreshing warning waking applications will be disabled if any of the sensors
    // has "Warning" status.
    public static void saveLastTimestamp(Context context, String key, Long timestamp){
        SharedPreferences mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putLong(key, timestamp);
        prefsEditor.apply();
    }

    public static long loadLastTimestamp(Context context, String key){
        SharedPreferences mPrefs = context.getSharedPreferences(key, Context.MODE_PRIVATE);
        long value = mPrefs.getLong(key, 0);
        return value;
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
