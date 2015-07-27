package pl.grudowska.pjwstk.homesecureagent;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by s.grudowska on 27.07.2015
 */
public class TimeNotificationDialog extends android.support.v4.app.DialogFragment {

    private int mPosition = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        if (ConfigurationStateStoreManager.isStored(getActivity(), "notification_radiobutton")) {
            SharedPreferences mPrefs = getActivity().getSharedPreferences("notification_radiobutton", Context.MODE_PRIVATE);
            mPosition = mPrefs.getInt("notification_radiobutton", 0);
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
                SharedPreferences mPrefs = getActivity().getSharedPreferences("notification_radiobutton", Context.MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putInt("notification_radiobutton", mPosition);
                prefsEditor.apply();
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
}
