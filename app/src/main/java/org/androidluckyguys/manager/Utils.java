package org.androidluckyguys.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.widget.Button;

import org.androidluckyguys.R;


/**
 * Created by XKL0275 on 11/10/2015.
 */
public class Utils {
    private static final String TAG = "Utils";

    public static boolean isNetworkConnected(Context context, boolean isShowMessage,int teamId) {
        return isNetworkConnected(context, isShowMessage, false,teamId);
    }

    public static boolean isNetworkConnected(Context context,boolean isShowMessage, boolean isClose,int teamId) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            String message = "";
            boolean airPlaneMode = Settings.System.getInt(context.getContentResolver(),
                    Settings.System.AIRPLANE_MODE_ON, 0) != 0;
            if (airPlaneMode) {
                message = "Airplane Mode Turn On, Use Wi-Fi or Cellular data to Access Data";
            } else {
                message = context.getResources().getString(R.string.no_internet_connection);
            }

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                    && activeNetwork.isConnected();
            if (!isConnected && isShowMessage) {
                showNoNetworkMessage(context, isClose, message,teamId);
            }
            return isConnected;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    private static AlertDialog showNoNetworkMessage(final Context context, final boolean isClose, String message, int teamId) {
        AlertDialog alert = new AlertDialog.Builder(context,teamId)
                .setMessage(message)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // bug fixed MORE-572
						/*Intent intent = new Intent(
								Settings.ACTION_WIFI_SETTINGS);
						*/
                        Intent intent = new Intent(
                                Settings.ACTION_SETTINGS);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        dialog.dismiss();
                    }
                }).setNegativeButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (isClose && context instanceof Activity) {
                            ((Activity) context).finish();
                        }
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(true);
        customAlertDialog(context, alert);
        return alert;
    }
    public static void customAlertDialog(Context context,
                                         AlertDialog alertDialog) {
        if (alertDialog != null) {
            Button b = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            if (b != null) {
                // b.setTextColor(getColorGroup4(context));
                // b.setTypeface(getBoldFont(context));
            }
            b = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            if (b != null) {
                //  b.setTextColor(getColorGroup4(context));
                //  b.setTypeface(getBoldFont(context));
            }
        }
    }


}
