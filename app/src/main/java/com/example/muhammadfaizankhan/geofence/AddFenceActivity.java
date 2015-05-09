/**
 * Created by Muhammad Muzammil on 25/4/2015.
 */
package com.example.muhammadfaizankhan.geofence;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;


public class AddFenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fence);


    }


    public void addFence(View view) {

        String ofGroup;
        String definedBy;
        String title;
        float radius;

        try {
            ofGroup = ((EditText) findViewById(R.id.ETgroupName)).getText().toString();
            if (ofGroup.startsWith(" ") || ofGroup.length() == 0)
                throw new IllegalArgumentException();

        } catch (Exception ex) {
            Toast.makeText(this, "Wrong Data in Group Field", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            definedBy = ((EditText) findViewById(R.id.ETdefinedBy)).getText().toString();
            if (definedBy.startsWith(" ") || definedBy.length() == 0)
                throw new IllegalArgumentException();

        } catch (Exception ex) {
            Toast.makeText(this, "Wrong Data in Defined Field", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            title = ((EditText) findViewById(R.id.ETtitle)).getText().toString();
            if (title.startsWith(" ") || title.length() == 0)
                throw new IllegalArgumentException();

        } catch (Exception ex) {
            Toast.makeText(this, "Wrong Data in Title Field", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            radius = Float.parseFloat(((EditText) findViewById(R.id.ETradius)).getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "Wrong Data in Radius Field", Toast.LENGTH_SHORT).show();
            return;
        }

//        Location location = MainActivity.mLocationClient.getLastLocation();

//        MainActivity.geofences.add(new Geofence.Builder()

        MainActivity.mGeofence = new Geofence.Builder()
                .setRequestId(ofGroup)
                        // There are three types of Transitions.
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL
                        | Geofence.GEOFENCE_TRANSITION_EXIT
                        | Geofence.GEOFENCE_TRANSITION_ENTER )

                        // Set the geofence location and radius.
                .setCircularRegion(MainActivity.myLastLocation.getLatitude(), MainActivity.myLastLocation.getLongitude(), radius)
                        // How long the geofence will remain in place.
                .setExpirationDuration(1000*60*7)
                        // This is required if you specify GEOFENCE_TRANSITION_DWELL when setting the transition types.
                .setLoiteringDelay(MainActivity.DWELL_PERIOD)
                .build();

        MainActivity.myLastLocationRadius = radius;   //storing radius for drawing marker on the map..
        MainActivity.isFenceAdded=true;
        finish();

    }

    @Override
    public void onBackPressed() {
        MainActivity.mGeofence = null;
        MainActivity.isFenceAdded=false;
        super.onBackPressed();

    }
}
