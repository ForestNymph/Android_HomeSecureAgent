package pl.grudowska.pjwstk.homesecureagent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s.grudowska on 20.07.2015
 */
//http://stackoverflow.com/questions/13460462/android-listfragment-how-to-refresh-list
//adapter.notifyDataSetChanged()
public class ListSensorFragment extends ListFragment {

    private int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Get indexes last saved list from SharedPreferences if exists
        ArrayList<Integer> selectedSensors = ConfigurationStateManager.loadConfiguration(getActivity());
        if (selectedSensors != null) {
            ArrayList<Sensor> sensors = (ArrayList<Sensor>) parseSelectedSensors(selectedSensors);
            setListAdapter(new SensorsAdapter(getActivity(), R.layout.sensor_list_row, sensors));
        } else {/*
            Bundle bundle = this.getArguments();
            if (bundle == null) {*/
            // Populate list with static array of all sensor types
            setListAdapter(new SensorsAdapter(getActivity(), R.layout.sensor_list_row, Sensor.sensorDataCreator()));
//          }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
/*        ListView lv = getListView();
        if(lv == null) {
            Toast.makeText(getActivity(), "...", Toast.LENGTH_LONG).show();
        }*/

        // mSensorDataAdapter.notifyDataSetChanged();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    /**
     * Starting a new activity in which fragment is displayed
     */
    void showDetails(int index) {
        // Launch a new activity to display the dialog fragment with selected text.
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsSensorActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    // To get only sensor selected by user
    private List<Sensor> parseSelectedSensors(ArrayList index) {

        ArrayList<Sensor> sensor = Sensor.sensorDataCreator();
        ArrayList<Sensor> newSensorList = new ArrayList();

        for (int i = 0; i < index.size(); ++i) {
            int position = (int) index.get(i);
            newSensorList.add(sensor.get(position));
        }
        return newSensorList;
    }
}
