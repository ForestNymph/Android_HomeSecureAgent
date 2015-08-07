package pl.grudowska.pjwstk.homesecureagent;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

//http://simpledeveloper.com/how-to-communicate-between-fragments-and-activities/
public class SensorScreenActivity extends AppCompatActivity
        implements SensorListCheckboxDialog.OnCheckboxSelectedListener, TimeNotificationDialog.OnRadiobuttonSelectedListener {

    private Timer timer = null;
    private PendingIntent mPendingIntent;
    private Context context = this;
    private int mSecondsInterval = 0;
    private final int SERVICE_CODE = 17111981;
    private static final String mAdress = "http://grudowska.pl:8080/current_sensor";
    private IntentFilter mIntent = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    // Broadcast receiver checking if internet connection exist and upadting MenuItem internet icon
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Activity method updating action bar
            invalidateOptionsMenu();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_screen);

        // Set fragment with sensor list
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem internetIcon = menu.findItem(R.id.action_internet_state);
        if (ConnectionController.isConnect(this)) {
            internetIcon.setIcon(getResources().getDrawable(R.drawable.connected_state));
        } else {
            internetIcon.setIcon(getResources().getDrawable(R.drawable.unknown_state));
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        switch (item.getItemId()) {
            case R.id.action_about_dialog:
                AboutAppDialog about = new AboutAppDialog();
                about.show(getSupportFragmentManager(), "");
                return true;
            case R.id.action_update_notification_dialog:
                TimeNotificationDialog update = new TimeNotificationDialog();
                update.show(getSupportFragmentManager(), "");
                return true;
            case R.id.action_sensors_dialog:
                SensorListCheckboxDialog sensor = new SensorListCheckboxDialog();
                sensor.show(getSupportFragmentManager(), "");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Callback method to communicate between fragments
    // Call when user change app service configuration
    @Override
    public void onRadiobuttonSelected(int seconds) {
        mSecondsInterval = seconds;
    }

    // Callback method to communicate between fragments (SensorListCheckboxDialog and ListSensorFragment)
    // User change set of sensor in SensorListCheckboxDialog, then view in ListSensorFragment should refresh
    @Override
    public void onCheckboxSelected(ArrayList<Integer> checkedbox) {

        // http://stackoverflow.com/questions/22474584/remove-old-fragment-from-fragment-manager
        // Remove old fragment
        // Fragment fragmentToRemove = getSupportFragmentManager().findFragmentByTag("list_fragment");
        // if (fragmentToRemove != null) {
        //     getSupportFragmentManager().beginTransaction().remove(fragmentToRemove).commit();
        // }

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
        transaction.replace(R.id.list_content_fragment, fragment, "list_fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon_orange);
    }

    @Override
    protected void onStart() {
        // Stops alarm service when app is running
        stopAlarmService();
        registerReceiver(mReceiver, mIntent);
        super.onStart();
    }

    @Override
    protected void onStop() {
        // When app is closing service is start to check values
        int interval = DataStoreManager.loadIntegerValue(this, "notification_seconds");
        if (interval == 0) {
            stopAlarmService();
        } else {
            startAlarmService(interval);
        }
        unregisterReceiver(mReceiver);
        super.onStop();
    }

    private void stopAlarmService() {
        Intent intent = new Intent(SensorScreenActivity.this, AlarmService.class);
        PendingIntent stopService = PendingIntent.getService(this, SERVICE_CODE, intent, 0);
        if (stopService != null) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.cancel(stopService);

            // Toast.makeText(SensorScreenActivity.this, "Stop Alarm Service", Toast.LENGTH_SHORT).show();
        }
    }

    private void startAlarmService(int interval) {
        Intent intent = new Intent(SensorScreenActivity.this, AlarmService.class);
        mPendingIntent = PendingIntent.getService(SensorScreenActivity.this, SERVICE_CODE, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, mPendingIntent);

        // Toast.makeText(SensorScreenActivity.this, "Start Alarm Service", Toast.LENGTH_SHORT).show();
    }

    public static String adress() {
        return mAdress;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // Method to check if service still running
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void callAsyncTask() {
        final Handler handler = new Handler();
        timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        new HttpAsyncTask(context).execute(adress());
                    }
                });
            }
        };
        timer.schedule(task, 0, 3000);
    }
    /*
    Callback method from SensorListCheckboxDialog (fragment), returning the value of user
    input. Replaced by the recommended Interface to talk back to the activity.
    @param selectedValues value returned from SensorListCheckboxDialog. array of selected checkboxes.

    public void onUserSelectValues(ArrayList<Integer> selectedValues) {
        Toast.makeText(this, "index " + selectedValues.get(0).toString(), Toast.LENGTH_LONG).show();
    }
*/
}
