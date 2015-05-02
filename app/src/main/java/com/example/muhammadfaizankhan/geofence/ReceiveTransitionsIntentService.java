package com.example.muhammadfaizankhan.geofence;

/**
 * Created by Muhammad Faizan Khan on 24/04/2015.
 */

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationClient;

import java.util.List;

public class ReceiveTransitionsIntentService extends IntentService {

    private final static String TAG = ReceiveTransitionsIntentService.class.getPackage() + "." + ReceiveTransitionsIntentService.class.getSimpleName();

    public ReceiveTransitionsIntentService() {
        super("ReceiveTransitionsIntentService");
        Log.v("GEOFENCE", "RTIS, Service Constructor");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (!LocationClient.hasError(intent)) {
            int transition = LocationClient.getGeofenceTransition(intent);
            Log.v("GEOFENCE", "RTIS, Transition Occured: " + transition);

            // Post a notification
            List<Geofence> geofences = LocationClient.getTriggeringGeofences(intent);
            String[] geofenceIds = new String[geofences.size()];

            Log.v("RTIS, FenceIds Size: ", "" + geofences.size());
            for (int index = 0; index < geofences.size(); index++) {
                geofenceIds[index] = geofences.get(index).getRequestId();
                Log.v("RTIS, GeofenceIds", "" + geofenceIds[index]);

            }
            String ids = TextUtils.join(", ", geofenceIds);
            String transitionType = getTransitionString(transition);

            sendNotification(transitionType, ids);
        }
        else {
            Log.e("GEOFENCE", "Error: "+ String.valueOf(LocationClient.getErrorCode(intent)));
        }
    }

    /**
     * Posts a notification in the notification bar when a transition is detected.
     * If the user clicks the notification, control goes to the main Activity.
     *
     * @param transitionType The type of transition that occurred.
     */
    private void sendNotification(String transitionType, String ids) {

        // Create an explicit content Intent that starts the main Activity
        Intent notificationIntent =
                new Intent(getApplicationContext(), MainActivity.class);

        // Construct a task stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the main Activity to the task stack as the parent
        stackBuilder.addParentStack(MainActivity.class);

        // Push the content Intent onto the stack
        stackBuilder.addNextIntent(notificationIntent);

        // Get a PendingIntent containing the entire back stack
        PendingIntent notificationPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Get a notification builder that's compatible with platform versions >= 4
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        // Set the notification contents
        builder.setSmallIcon(R.drawable.e_notifications)
                .setContentTitle(
                        getString(R.string.geofence_transition_notification_title,
                                transitionType, ids))
                .setContentText(getString(R.string.geofence_transition_notification_text))
                .setContentIntent(notificationPendingIntent);

        // Get an instance of the Notification manager
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Issue the notification
        mNotificationManager.notify(0, builder.build());
    }

    private String getTransitionString(int transitionType) {
        switch (transitionType) {

            case Geofence.GEOFENCE_TRANSITION_ENTER:
                return getString(R.string.geofence_transition_entered);

            case Geofence.GEOFENCE_TRANSITION_EXIT:
                return getString(R.string.geofence_transition_exited);

            case Geofence.GEOFENCE_TRANSITION_DWELL:
                return getString(R.string.geofence_transition_dwell);

            default:
                return getString(R.string.geofence_transition_unknown);
        }
    }
}