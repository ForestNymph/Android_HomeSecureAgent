package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

//http://simpledeveloper.com/how-to-communicate-between-fragments-and-activities/

public class SensorScreenActivity extends AppCompatActivity implements SensorDialog.OnCheckboxSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_screen);

        ListSensorFragment listfragment = new ListSensorFragment();
        getSupportFragmentManager().beginTransaction().
                add(R.id.list_content_fragment, listfragment, "list_fragment").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sensor_screen, menu);
        restoreActionBar();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_about:
                DialogFragment about = new AboutDialog();
                about.show(getSupportFragmentManager(), "");
                return true;
            case R.id.action_update:
                // Update option clicked.
                return true;
            case R.id.action_notification:
                // Notification option clicked.
                return true;
            case R.id.action_sensors_dialog:
                DialogFragment dialog = new SensorDialog();
                dialog.show(getSupportFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Callback method to communicate between fragments (SensorDialog and ListSensorFragment)
    // User change set of sensor in SensorDialog, then view in ListSensorFragment should refresh
    @Override
    public void onCheckboxSelected(ArrayList<Integer> checkedbox) {

        // Passing data between fragments
        Bundle args = new Bundle();
        args.putIntegerArrayList("checkbox", checkedbox);

        ListSensorFragment fragment = new ListSensorFragment();

        /**
         * Supply the construction arguments for this fragment.  This can only
         * be called before the fragment has been attached to its activity; that
         * is, you should call it immediately after constructing the fragment.  The
         * arguments supplied here will be retained across fragment destroy and
         * creation.
         */
        fragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.list_content_fragment, fragment);

        // Commit the transaction
        transaction.commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon_orange);
    }
/*
    Callback method from SensorDialog (fragment), returning the value of user
    input. Replaced by the recommended Interface to talk back to the activity.
    @param selectedValues value returned from SensorDialog. array of selected checkboxes.

    public void onUserSelectValues(ArrayList<Integer> selectedValues) {
        Toast.makeText(this, "index " + selectedValues.get(0).toString(), Toast.LENGTH_LONG).show();
    }
*/
}
