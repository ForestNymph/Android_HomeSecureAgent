package pl.grudowska.pjwstk.homesecureagent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
        Bundle bundle = this.getArguments();
        if (bundle == null) {
            // Populate list with static array of sensor types
            setListAdapter(new ArrayAdapter(getActivity(),
                    android.R.layout.simple_list_item_activated_1, Sensor.SensorType.getSensorList()));
        } else {
            ArrayList<Integer> checkboxPositions = bundle.getIntegerArrayList("checkbox");
            List sensor = parseCheckboxIndexToListString(checkboxPositions);
            // Populated list with sensors choosed by user
            setListAdapter(new ArrayAdapter(getActivity(),
                    android.R.layout.simple_list_item_activated_1, sensor));
            // mSensorDataAdapter.notifyDataSetChanged();
        }
        //Toast.makeText(getActivity(), "onActivityCreated", Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    /**
     * Starting a new activity in which fragment is displayed
     */
    void showDetails(int index) {
        mCurCheckPosition = index;

        // Launch a new activity to display the dialog fragment with selected text.
        Intent intent = new Intent();
        intent.setClass(getActivity(), DetailsSensorActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    private List<String> parseCheckboxIndexToListString(ArrayList index) {

        ArrayList sensor = Sensor.SensorType.getSensorList();
        ArrayList newSensorList = new ArrayList();

        for (int i = 0; i < index.size(); ++i) {
            newSensorList.add(sensor.get((int) index.get(i)));
        }
        return newSensorList;
    }
}
