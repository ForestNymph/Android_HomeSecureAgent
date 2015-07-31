package pl.grudowska.pjwstk.homesecureagent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by s.grudowska on 27.07.2015
 */
public class TimeNotificationDialog extends android.support.v4.app.DialogFragment {

    private int mPosition = 0;

    // Interface object
    public OnRadiobuttonSelectedListener mListener;

    // Container Activity must implement this interface
    public interface OnRadiobuttonSelectedListener {
        void onRadiobuttonSelected(int seconds);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        if (DataStoreManager.isStored(getActivity(), "notification_radiobutton")) {
            mPosition = DataStoreManager.loadIntegerValue(getActivity(),
                    "notification_radiobutton");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogTheme);
        builder.setTitle(R.string.action_update);

        builder.setSingleChoiceItems(R.array.time_list, mPosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mPosition = which;
            }
        });

        builder.setPositiveButton(R.string.apply_about_dialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                DataStoreManager.saveIntegerValue(getActivity(),
                        "notification_radiobutton", mPosition);
                int secondsInterval = transformFromPositionToSeconds(mPosition);
                DataStoreManager.saveIntegerValue(getActivity(),
                        "notification_seconds", secondsInterval);

                // Listener to inform activity about change time interval
                mListener.onRadiobuttonSelected(secondsInterval);
            }
        });

        builder.setNegativeButton(R.string.cancel_dialog, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                // Do nothing
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnRadiobuttonSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnCheckboxSelectedListener");
        }
    }

    private int transformFromPositionToSeconds(int position) {
        int seconds;
        switch (position) {
            case 0: {
                seconds = 0;
                break;
            }
            case 1: {
                seconds = 5 * 1000;
                break;
            }
            case 2: {
                seconds = 10 * 1000;
                break;
            }
            case 3: {
                seconds = 30 * 1000;
                break;
            }
            case 4: {
                seconds = 60 * 1000;
                break;
            }
            case 5: {
                seconds = 300 * 1000;
                break;
            }
            case 6: {
                seconds = 600 * 1000;
                break;
            }
            case 7: {
                seconds = 1800 * 1000;
                break;
            }
            case 8: {
                seconds = 3600 * 1000;
                break;
            }
            default: {
                seconds = 0;
                break;
            }
        }
        return seconds;
    }
}
