
/**
 * Aik new project banana hai..
 * us main khali map view kr k dekhna hai..
 * phir us main geofence service daal k dekhni hai..
 * phir myservice daalni hai..
 * or phir add fence activity se merge krna hai..
 */



/**
 * Created by Muhammad Muzammil on 25/4/2015.
 */
package com.example.muhammadfaizankhan.geofence;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

//import com.google.android.gms.common.GooglePlayServicesClient;
//import com.google.android.gms.location.LocationClient;

public class MainActivity extends FragmentActivity
        implements
        LocationListener{

    private final static String TAG = "Muzammil";
    final static int DWELL_PERIOD=10000;
    /**
     * Google Map object
     */
    private GoogleMap mMap;

    /**
     * Coordinates for the Geofence.
     */
    private LatLng mGeofenceLatLng = new LatLng(24.92528, 67.085943);

    /**
     * Radius of the Geofence in meters.
     */
    private int mRadius = 80;

    /**
     * The Geofence object.
     */
    public static Geofence mGeofence;
    /**
     * Entry point for Google's location related APIs.
     */

//  TODO  static LocationClient mLocationClient;

    static Location myLastLocation;
    static double myLastLocationRadius;
    static boolean isFenceAdded;
    /**
     * Used to set the priority and intervals of the location requests.
     */
//    static LocationRequest mLocationRequest;
//    static ArrayList<Geofence> geofences = new ArrayList<Geofence>();



    /**
     * Visuals
     */
    private CircleOptions mCircleOptions;
    private Circle mCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("Muzammil", "MA, in onCreate..");
        startService(new Intent(this, GeoFenceService.class));


        /**
         * We create a new LocationClient which is used as an entry point for Google's location
         * related APIs. The first parameter is the context, the second is
         * GooglePlayServicesClient.ConnectionCallbacks, and the third is
         * GooglePlayServicesClient.OnConnectionFailedListener. Since we implemented both listeners
         * on the MainActivity class, we pass 'this' for the second and third parameters.
         */
//        mLocationClient = new LocationClient(this, this, this);   TODO

//      buildGoogleApiClient();

        /**
         * With the LocationRequest, we can set the quality of service. For example, the priority
         * and intervals.
         */



//        mLocationRequest = LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(3600000);
//        mLocationRequest.setFastestInterval(60000);


    }

//    protected synchronized void buildGoogleApiClient(){
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(LocationServices.API)
//                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks(){
//                    @Override
//                    public void onConnected(Bundle bundle) {
//                        functionForOnConnected(bundle);
//                    }
//
//                    @Override
//                    public void onConnectionSuspended(int i) {
//                        Log.v("Muzammil", "Connection to GoogleAPIClient failed!");
//                    }
//                })
//                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(ConnectionResult connectionResult) {
//                        Log.v("Muzammil", "Connection to GoogleAPIClient failed!");
//
//                    }
//                })
//                .build();
//    }


//    public void functionForOnConnected(Bundle bundle) {
//
//        Log.v("Muzammil", "Connected to location services.");
//
//        Log.v("Muzammil", "Calling Service..");
//        PendingIntent pendingIntent = PendingIntent.getService(this, 0,
//                new Intent(this, MyService.class)
//                , PendingIntent.FLAG_UPDATE_CURRENT);
//        Log.v("Muzammil", "Service Called..");
//
//
//        /**
//         * Adding the geofence to the ArrayList, which will be passed as the first parameter
//         * to the LocationClient object's addGeofences function.
//         */
//        if (isFenceAdded) {
//            geofences.add(mGeofence);
//
//            /**
//             * We're creating a PendingIntent that references the ReceiveTransitionsIntentService class
//             * in conjunction with the geofences.
//             */
//
//
//
//            /**
//             * We want this (MainActivity) to handle location updates.(see onLocationChanged function)
//             */
//            /**
//             * Adding the Geofences and PendingIntent to the LocationClient and setting this
//             * (MainActivity) to handle onAddGeofencesResult. The pending intent, which is the
//             * ReceiveTransitionsIntentService, is what gets utilized when one of the transitions
//             * that was specified in the geofence is fired.
//             */
//
//            //DEBUGGING - For checking geofence arraylist and object
//            for (int index = 0; index < geofences.size(); index++) {
//                String x = geofences.get(index).getRequestId();
//                Log.v("MainGeofenceIds", "" + x);
//            }
//
//
////            mLocationClient.addGeofences(geofences, pendingIntent, this); TODO
//
//            //TODO
//            //We can also call just an await() function instead of setResultCallback()..
//
//            isFenceAdded=false;
//
//        }
//
//        LocationServices.FusedLocationApi.requestLocationUpdates
//                (mGoogleApiClient, mLocationRequest, this);
//
//        LocationServices.GeofencingApi.addGeofences
//                (mGoogleApiClient, geofences, pendingIntent)
//                .setResultCallback(new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        Log.v("Muzammil","addGeofences ResultCallBack"+status.getStatusMessage());
//                    }
//                });
//
//        Log.v("GeofenceIds Size: ", "" + geofences.size());
////        Log.v("GEOFENCE", MainActivity.mGeofence.toString());
//
//
//    }

//
//    @Override
//    public void onAddGeofencesResult(int statusCode, String[] geofenceRequestIds) {
//        switch (statusCode) {
//            case LocationStatusCodes.SUCCESS:
//                Log.v(TAG, "Successfully added Geofence.");
//                setUpMap();
//                break;
//            case LocationStatusCodes.ERROR:
//                Log.v(TAG, "Error adding Geofence.");
//                break;
//            case LocationStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
//                Log.v(TAG, "Too many geofences.");
//                break;
//            case LocationStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
//                Log.v(TAG, "Too many pending intents.");
//                break;
//        }
//    }



    @Override
    public void onLocationChanged(Location location) {
        /**
         * Location data is passed back to this function.
         */
        Log.v("Location Changed", "Location Changed: " + location.getLatitude() + ", " + location.getLongitude());
    }

//    @Override
//    public void onRemoveGeofencesByRequestIdsResult(int i, String[] strings) {
//
//    }
//
//    @Override
//    public void onRemoveGeofencesByPendingIntentResult(int i, PendingIntent pendingIntent) {
//
//    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.v("Muzammil", "MA, in onStart()");
//        startService(new Intent(this, MyService.class));

        // Connect to the location APIs.
        mGoogleApiClient.connect();
//        startService(new Intent(this, ReceiveTransitionsIntentService.class));
//        startService(new Intent(getBaseContext(), ReceiveTransitionsIntentService.class));
    }

    protected void onStop() {
        // Disconnect from the location APIs.
        Log.v("Muzammil", "MA, in onStop()");
//        mLocationClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != mGoogleApiClient) {
            mGoogleApiClient.disconnect();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.v("Muzammil", "MA, in onResume()");

        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS) {
            setUpMapIfNeeded();
        } else {
            GooglePlayServicesUtil.getErrorDialog(
                    GooglePlayServicesUtil.isGooglePlayServicesAvailable(this),
                    this, 0);
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the
        // map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the
     * camera. In this case, we just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap}
     * is not null.
     */
    private void setUpMap() {
        // Centers the camera over the building and zooms int far enough to
        // show the floor picker.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(mGeofenceLatLng.latitude, mGeofenceLatLng.longitude), 18));

        //TODO - I have to set lat and long of the current location..

        // Hide labels.
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setIndoorEnabled(false);
        mMap.setMyLocationEnabled(true);

        ArrayList<Double> myLatitudes, myLongitudes;
        myLatitudes= new ArrayList<Double>();
        myLongitudes= new ArrayList<Double>();


        for (Map.Entry<String, LatLng> entry : Constants.HARD_CODED_FENCES.entrySet()) {
            myLatitudes.add(entry.getValue().latitude);
            myLongitudes.add(entry.getValue().longitude);


            mCircleOptions = new CircleOptions()
                    .center(new LatLng(entry.getValue().latitude, entry.getValue().longitude))
                    .radius(90)
                    .fillColor(0x40ff0000)
                    .strokeColor(Color.TRANSPARENT)
                    .strokeWidth(2);

            mCircle = mMap.addCircle(mCircleOptions);
        }


        // Adding visuals.
//        if (myLastLocation == null) {
//            mCircleOptions = new CircleOptions()
//                    .center(mGeofenceLatLng)
//                    .radius(mRadius)
//                    .fillColor(0x40ff0000)
//                    .strokeColor(Color.TRANSPARENT)
//                    .strokeWidth(2);
//            Log.v(TAG, "myLastLcoation == null.");
//
//        } else {
//            mCircleOptions = new CircleOptions()
//                    .center(new LatLng(myLastLocation.getLatitude(), myLastLocation.getLongitude()))
//                    .radius(myLastLocationRadius)
//                    .fillColor(0x40ff0000)
//                    .strokeColor(Color.TRANSPARENT)
//                    .strokeWidth(2);
//            Log.v(TAG, "myLastLcoation != null.");
//
//        }
//        mCircle = mMap.addCircle(mCircleOptions);

    }

//
//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this)
//                .setTitle("Confirm Exit?")
//                .setMessage("Are you sure you want to exit?")
//                .setNegativeButton(android.R.string.no, null)
//                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface arg0, int arg1) {
//                        MainActivity.super.onBackPressed();
//                    }
//                }).create().show();
//    }
//
//    public void addFenceActivity(View view) {
//        myLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        Toast.makeText(this, "adding fence", Toast.LENGTH_LONG).show();
//        startActivity(new Intent(this, AddFenceActivity.class));
//
//    }


}