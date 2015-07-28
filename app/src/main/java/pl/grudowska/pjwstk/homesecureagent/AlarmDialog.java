package pl.grudowska.pjwstk.homesecureagent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by s.grudowska on 28.07.2015
 */
public class AlarmDialog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_dialog);

        Intent intent = getIntent();
        String sensor = intent.getStringExtra("warning");

        TextView v = (TextView) this.findViewById(R.id.warning_text);
        v.setText("Watch out!\n" + sensor + " warning");
        ImageView image = (ImageView) this.findViewById(R.id.imageView);
        image.setImageResource(R.drawable.icon_red);

        Button okButton = (Button) findViewById(R.id.yes);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
