package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 18.07.2015
 */
public class SensorsAdapter extends ArrayAdapter<Sensor> {

    public SensorsAdapter(Context context, ArrayList<Sensor> sensors) {
        super(context, 0, sensors);
    }

    @Override
    public View getView(int position, View rowView, ViewGroup parent) {

        // Get the data item for this position
        Sensor sensor = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_list_row, parent, false);
        }

        // Lookup view for data population
        ImageView sensorImage = (ImageView) rowView.findViewById(R.id.image_sensor);
        TextView sensorName = (TextView) rowView.findViewById(R.id.textview_sensorname);
        TextView sensorValue = (TextView) rowView.findViewById(R.id.textview_sensorvalue);

        // Populate the data into the template view using the data object
        sensorName.setText(sensor.getNameSensor());
        sensorValue.setText(sensor.getValueSensor());
        sensorImage.setImageDrawable(Drawable.createFromPath("/home/sylwia/Desktop/HSA_App/HomeSecureAgent/app/src/main/res/drawable/icon_orange.png"));

        // Return the completed view to render on screen
        return rowView;
    }
}
