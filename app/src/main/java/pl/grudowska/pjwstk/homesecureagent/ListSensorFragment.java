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

        if (DataStoreManager.isStored(getActivity(), "objects_sensor")) {
            //Get indexes of last saved list if exists
            ArrayList<Sensor> selectedSensors = DataStoreManager.loadSensorObjects(getActivity());
            if (selectedSensors != null) {
                setListAdapter(new SensorsListAdapter(getActivity(), R.layout.sensor_list_row, selectedSensors));
            }
        } else {
            // Bundle bundle = this.getArguments(); if (bundle == null) {

            // Populate list with static array of all sensor types
            setListAdapter(new SensorsListAdapter(getActivity(),
                    R.layout.sensor_list_row, Sensor.initializeSensorDataCreator()));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        updateListTask();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    public void updateListView(ArrayList<Sensor> sensors) {
        DataStoreManager.saveSensorObjectsSensorArray(getActivity(), sensors);
        setListAdapter(new SensorsListAdapter(getActivity(), R.layout.sensor_list_row,
                sensors));
    }

    public void updateListTask() {
        // Call AsyncTask to perform network operation in separate thread
        new HttpAsyncTask(getActivity()).execute(SensorScreenActivity.adress());
    }

    /**
     * Starting a new activity in which fragment is displayed
     */
    void showDetails(int index) {
        if (index == 0) {
            updateListTask();
        }

        // TODO Implement sensors details
        // Launch a new activity to display the dialog fragment with selected text.
/*      Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsSensorActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);*/
    }
}
