package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 20.07.2015
 */
//http://stackoverflow.com/questions/13460462/android-listfragment-how-to-refresh-list
//adapter.notifyDataSetChanged()
public class ListSensorFragment extends ListFragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (ConfigurationStateStoreManager.isStored(getActivity(), "objects_sensor")) {
            //Get indexes of last saved list if exists
            ArrayList<Sensor> selectedSensors = ConfigurationStateStoreManager.loadSensorObjects(getActivity());
            if (selectedSensors != null) {
                setListAdapter(new SensorsAdapter(getActivity(), R.layout.sensor_list_row, selectedSensors));
            }
        } else {
            // Bundle bundle = this.getArguments(); if (bundle == null) {

            // Populate list with static array of all sensor types
            setListAdapter(new SensorsAdapter(getActivity(),
                    R.layout.sensor_list_row, Sensor.initializeSensorDataCreator()));
        }
    }

    public void updateList(ArrayList<Sensor> sensors) {
        ConfigurationStateStoreManager.saveSensorObjectsSensorArray(getActivity(), sensors);
        setListAdapter(new SensorsAdapter(getActivity(), R.layout.sensor_list_row,
                sensors));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        if (index == 0) {
            new HttpAsyncTask(getActivity()).execute(SensorScreenActivity.adress());
        }


        // TODO Implement sensors details
        // Launch a new activity to display the dialog fragment with selected text.
/*      Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsSensorActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);*/
    }
}
