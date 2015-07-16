package pl.grudowska.pjwstk.homesecureagent;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class SensorScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_screen);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        if (item.getItemId() == R.id.action_sensors_dialog) {
            //Toast.makeText(this, "Dialog sensor box", Toast.LENGTH_SHORT).show();

            DialogFragment dialog = new SensorDialog();
            dialog.show(getSupportFragmentManager(), "");

            return true;
        }
        // noinspection SimplifiableIfStatement
        else if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        // actionBar.setTitle(mTitle);
        // TODO zmienilam zeby zawsze bylo sensors zamiast wybranego pojedynczego czujnika
        // actionBar.setTitle("Sensors");
        // To nie dziala
        // actionBar.setIcon(R.drawable.wifigreen);
    }
}
