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
        ViewHolder view;

        if (rowView == null) {
            if (position == 0) {
                // Get a new instance of the layout view for timestamp
                rowView = LayoutInflater.from(getContext()).inflate(R.layout.timestamp_row, parent, false);
            } else {
                // Get a new instance of the other rows in layout view
                rowView = LayoutInflater.from(getContext()).inflate(R.layout.sensor_list_row, parent, false);
            }
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
        Sensor sensor = mSensor.get(position);
        if (position == 0) {
            view.sensor_name.setText("Last update: ");
        } else {
            view.sensor_name.setText(sensor.getNameSensor());
        }
        view.sensor_value.setText(sensor.getValueSensor());
        view.sensor_string_status.setText(sensor.getStatusSensor());
        view.sensor_image.setImageResource(sensor.getImageSensor());

        return rowView;
    }

    // Methods to turn off recycle view
    // http://stackoverflow.com/questions/6921462/listview-reusing-views-when-i-dont-want-it-to#
    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    protected static class ViewHolder {
        protected TextView sensor_name;
        protected TextView sensor_value;
        protected TextView sensor_string_status;
        protected ImageView sensor_image;
    }
}
