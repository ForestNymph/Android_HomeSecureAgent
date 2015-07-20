package pl.grudowska.pjwstk.homesecureagent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by s.grudowska on 20.07.2015
 */
public class SplashScreenActivity extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 1200;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, SensorScreenActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
