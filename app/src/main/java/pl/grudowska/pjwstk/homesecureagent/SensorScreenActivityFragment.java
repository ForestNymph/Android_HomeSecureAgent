package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class SensorScreenActivityFragment extends Fragment {

    public SensorScreenActivityFragment() {
    }

    // To provide a layout for a fragment, you must implement the onCreateView() callback method,
    // which the Android system calls when it's time for the fragment to draw its layout.
    // Your implementation of this method
    // must return a View that is the root of your fragment's layout.
    // The container parameter passed to onCreateView() is the parent ViewGroup
    // (from the activity's layout) in which your fragment layout will be inserted.
    // The savedInstanceState parameter is a Bundle that provides data about
    // the previous instance of the fragment, if the fragment is being resumed

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sensor_screen, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
/*
        if (item.getItemId() == R.id.action_sensors_dialog) {
            Toast.makeText(getActivity(), "Dialog sensor box", Toast.LENGTH_SHORT).show();
            return true;
        }*/

        //Todo nie wiem gdzie dac obsluge actionbaru
        return super.onOptionsItemSelected(item);
    }
}
