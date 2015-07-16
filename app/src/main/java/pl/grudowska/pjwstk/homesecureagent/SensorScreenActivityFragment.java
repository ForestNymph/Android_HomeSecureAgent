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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
