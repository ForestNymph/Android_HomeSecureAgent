package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 23.07.2015
 */
public class ConfigurationStateManager {

    public static void saveConfiguration(Context context, ArrayList<Integer> selectedCheckboxes) {
        SharedPreferences mPrefs = context.getSharedPreferences("selected_sensors", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(selectedCheckboxes);
        prefsEditor.putString("selected_sensors", json);
        prefsEditor.apply();
    }

    public static ArrayList<Integer> loadConfiguration(Context context) {
        SharedPreferences mPrefs = context.getSharedPreferences("selected_sensors", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("selected_sensors", "");

        // new TypeToken (...) return Integer Array
        ArrayList<Integer> checkbox = gson.fromJson(json, new TypeToken<ArrayList<Integer>>() {
        }.getType());

        return checkbox;
    }
}
