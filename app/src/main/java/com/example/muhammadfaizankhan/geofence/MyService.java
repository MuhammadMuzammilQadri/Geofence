package com.example.muhammadfaizankhan.geofence;

/**
 * Created by Muhammad Faizan Khan on 24/04/2015.
 */

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

public class MyService extends Service {

    // Unique Identification Number for the Notification.
    // We use it on Notification start, and to cancel it.


    /**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */

    @Override
    public void onCreate() {

        Log.v("Muzammil", "MS,Service Created..");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.v("Muzammil", "MS, onStartCommand..");

        GeofencingEvent mGeoFencingEvent = GeofencingEvent.fromIntent(intent);

        if (!mGeoFencingEvent.hasError()) {
            Log.v("Muzammil", "MS, Received start id " + startId + ": " + intent);
            // We want this service to continue running until it is explicitly

//        Log.v("Muzammil", "MS, "+ intent.getStringExtra("intentTest"));

            Toast.makeText(this, "MS, Transition occur..", Toast.LENGTH_LONG).show();

            int transition = mGeoFencingEvent.getGeofenceTransition();
            Log.v("Muzammil", "MS, Transition Occur: "+ transition);


            // Post a notification
            List<Geofence> geofences = mGeoFencingEvent.getTriggeringGeofences();
            String[] geofenceIds = new String[geofences.size()];

            Log.v("Muzammil", "MS, GeofenceIds Size: " + geofences.size());
            for (int index = 0; index < geofences.size(); index++) {
                geofenceIds[index] = geofences.get(index).getRequestId();
                Log.v("Muzammil", "MS, Ids: " + geofenceIds[index]);

            }
            String ids = TextUtils.join(", ", geofenceIds);
            String transitionType = getTransitionString(transition);

            sendNotification(transitionType, ids);
        } else {
            Log.e("Muzammil", "Error: "+String.valueOf(mGeoFencingEvent.getErrorCode()));
        }
        // stopped, so return sticky.


        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        // Cancel the persistent notification.
        // mNM.cancel(NOTIFICATION);

        // Tell the user we stopped.
//        Toast.makeText(this, R.string.local_service_stopped, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
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