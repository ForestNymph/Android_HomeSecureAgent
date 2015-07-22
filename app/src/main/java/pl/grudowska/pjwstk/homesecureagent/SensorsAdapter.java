package pl.grudowska.pjwstk.homesecureagent;

import android.content.Context;
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

    private ArrayList<Sensor> mSensor;

    // row layout - textViewResourceId
    public SensorsAdapter(Context context, int textViewResourceId, ArrayList<Sensor> sensors) {
        super(context, textViewResourceId, sensors);
        mSensor = sensors;
    }

    // getView method defines how each list item will be look
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        ViewHolder view = null;

        if (rowView == null) {
            // Get a new instance of the row layout view
            rowView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_list_row, parent, false);

            // Hold the view objects in an ViewHolder object
            view = new ViewHolder();
            view.sensor_name = (TextView) rowView.findViewById(R.id.textview_sensorname);
            view.sensor_value = (TextView) rowView.findViewById(R.id.textview_sensorvalue);
            view.sensor_string_status = (TextView) rowView.findViewById(R.id.textview_sensorstatus);
            view.sensor_image = (ImageView) rowView.findViewById(R.id.imageview_sensorimage);

            rowView.setTag(view);
        } else {
            view = (ViewHolder) rowView.getTag();
        }
        // Set data to views
        Sensor sensor = mSensor.get(position);
        view.sensor_name.setText(sensor.getNameSensor());
        view.sensor_value.setText(sensor.getValueSensor());
        view.sensor_string_status.setText(sensor.getStatusSensor());
        view.sensor_image.setImageResource(sensor.getImageSensor());

        return rowView;
    }

    protected static class ViewHolder {
        protected TextView sensor_name;
        protected TextView sensor_value;
        protected TextView sensor_string_status;
        protected ImageView sensor_image;
    }
}
