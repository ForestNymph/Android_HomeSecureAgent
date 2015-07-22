package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by s.grudowska on 20.07.2015
 */

/**
 * Activity that manage DetailsSensorFragment to show sensor details that the user has selected
 */
public class DetailsSensorActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sensor_screen);

        if (savedInstanceState == null) {
            // During initial setup, plug in the sensor details fragment.
            DetailsSensorFragment details = new DetailsSensorFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.detail_content_fragment, details).commit();
        }
        restoreActionBar();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.icon_orange);
    }
}
