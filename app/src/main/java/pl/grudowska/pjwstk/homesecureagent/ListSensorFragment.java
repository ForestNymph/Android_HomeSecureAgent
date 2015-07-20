package pl.grudowska.pjwstk.homesecureagent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by s.grudowska on 20.07.2015
 */
public class ListSensorFragment extends ListFragment {

    private int mCurCheckPosition = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Populate list with our static array of titles.
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, SensorEnum.getSensorList()));

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }
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
}
