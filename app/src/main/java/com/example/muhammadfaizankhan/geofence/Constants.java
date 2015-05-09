/**
 * Created by Muhammad Muzammil on 25/4/2015.
 */

package com.example.muhammadfaizankhan.geofence;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Constants
 */
public final class Constants {

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.example.muhammadfaizankhan.geofence";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MINUTES = 5;

    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_MINUTES * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 90;

    /**
     * Map for storing information
     */

    public static final HashMap<String, LatLng> HARD_CODED_FENCES = new HashMap<String, LatLng>();
    static {
        // Current Location.
        HARD_CODED_FENCES.put("Current",
                new LatLng(LocationServices.FusedLocationApi.getLastLocation(GeoFenceService.mGoogleApiClient).getLatitude(),
                        LocationServices.FusedLocationApi.getLastLocation(GeoFenceService.mGoogleApiClient).getLongitude())
        );

        // XYZ Location.
        HARD_CODED_FENCES.put("XYZ Location", new LatLng(37.422611, -122.0840577));
    }
}
