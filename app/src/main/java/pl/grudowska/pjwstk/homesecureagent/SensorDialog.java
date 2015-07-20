package pl.grudowska.pjwstk.homesecureagent;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by s.grudowska on 15.07.2015.
 */
//https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView
public class SensorDialog extends android.support.v4.app.DialogFragment {

    private ArrayList mSelectedItems = null;
    private String[] mSensorsList = null;
    private ArrayAdapter<String> mItemsAdapter = null;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Array with selected items
        mSelectedItems = new ArrayList();

        // Use the Builder class for convenient dialog construction
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);

        // Get the layout inflater
        // LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        // builder.setView(inflater.inflate(R.layout.sensor_list_dialog, null));

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.sensors);
        // Specify the list array, the items to be selected by default (null for none),
        // and the listener through which to receive callbacks when items are selected
        builder.setMultiChoiceItems(R.array.sensors_list, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    // If the user checked the item, add it to the selected items
                    mSelectedItems.add(which);
                } else if (mSelectedItems.contains(which)) {
                    // Else, if the item is already in the array, remove it
                    mSelectedItems.remove(Integer.valueOf(which));
                }
            }
        });

        builder.setPositiveButton(R.string.apply_dialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.setNegativeButton(R.string.cancel_dialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                // Do nothing
            }
        });
        // builder.setIcon(R.drawable.icon_inside_app);
        // builder.setView(configureDialogView());

        // Create the AlertDialog object and return it
        return builder.create();
    }

    private View configureDialogView() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.sensor_list_dialog, null);

        mSensorsList = getResources().getStringArray(R.array.sensors_list);
        mItemsAdapter = new ArrayAdapter<String>(this.getActivity(), R.layout.sensor_list_row, mSensorsList);

        ListView listView = (ListView) view.findViewById(R.id.list_sensors);
        listView.setAdapter(mItemsAdapter);

        return view;
    }
}
