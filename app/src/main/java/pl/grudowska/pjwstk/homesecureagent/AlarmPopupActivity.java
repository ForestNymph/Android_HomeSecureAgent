package pl.grudowska.pjwstk.homesecureagent;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by s.grudowska on 28.07.2015
 */
public class AlarmPopupActivity extends Activity {

    private Vibrator vibrator = null;
    private Ringtone alarm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_dialog);

        Intent intent = getIntent();
        String sensor = intent.getStringExtra("warning");

        TextView v = (TextView) this.findViewById(R.id.warning_text);
        v.setText("Watch out!\n" + sensor + " warning");
        ImageView image = (ImageView) this.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.icon_orange);

        vibrateAlarm();
        ringtoneAlarm();

        Button okButton = (Button) findViewById(R.id.yes);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alarm.stop();
                finish();
            }
        });
    }

    private void vibrateAlarm() {
        //final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        // Start immediately
        // Vibrate for 200 milliseconds
        // Sleep for 200 milliseconds
        long[] pattern = {0, 400, 100, 400, 100, 400, 100, 400, 100, 400, 100, 400, 100, 400, 100};

        // The "0" means to repeat the pattern starting at the beginning
        // The "-1" means to repeat only once
        vibrator.vibrate(pattern, -1);
    }

    private void ringtoneAlarm() {
        Uri system_sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        alarm = RingtoneManager.getRingtone(getApplicationContext(), system_sound);
        alarm.play();
    }

    @Override
    public void finish() {
        vibrator.cancel();
        super.finish();
    }
}
