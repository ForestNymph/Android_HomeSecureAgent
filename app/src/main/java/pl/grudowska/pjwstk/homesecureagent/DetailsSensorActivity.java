package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
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

        if (savedInstanceState == null) {
            // During initial setup, plug in the sensor details fragment.
            DetailsSensorFragment details = new DetailsSensorFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}
