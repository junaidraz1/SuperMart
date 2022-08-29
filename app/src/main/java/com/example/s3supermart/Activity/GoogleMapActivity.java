package com.example.s3supermart.Activity;

import static com.example.s3supermart.Utils.Constants.MY_PERMISSIONS_REQUEST_LOCATION;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;

import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.s3supermart.Helper.ConManager;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Button btn_continueBottomsheet;
    Marker mCurrLocationMarker;
    RelativeLayout RL_titleBottomshet;
    EditText et_currentLocation, et_appartmentInfo;
    double lastLatitude, lastLongitude, clickedLatitude,
            clickedLongitude, searchedLatitude, searchedLongitude;
    PrefsManager prefsManager;
    ConManager conManager;
    DialogHandler dialogHandler;
    View BottomsheetView;
    String currLocation = "", appartmentInfo = "";
    ImageView iv_rotate;
    BottomSheetBehavior<View> bottomSheetBehavior;
    private boolean firstLoad = true;
    SearchView searchView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        //intialising ids to variables
        RL_titleBottomshet = findViewById(R.id.Rl_titleBottomsheetGoogleMap);
        BottomsheetView = findViewById(R.id.ll_bottomSheetGoogleMap);
        et_currentLocation = findViewById(R.id.et_currentLocation);
        et_appartmentInfo = findViewById(R.id.et_appartmentInfo);
        searchView = findViewById(R.id.idSearchView);
        btn_continueBottomsheet = findViewById(R.id.btn_continue_bottomsheet);
        iv_rotate = findViewById(R.id.iv_rotate_bottomSheet);
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        //intialising classes
        prefsManager = new PrefsManager(GoogleMapActivity.this);
        dialogHandler = new DialogHandler();
        conManager = new ConManager();
        mLocationRequest = new LocationRequest();

       /* it is use to take the instance of the
        behavior from the layout params of the View instance*/
        bottomSheetBehavior = BottomSheetBehavior.from(BottomsheetView);

        /* Sets the bottom sheet height with the
        value set on the peekHeight attribute*/
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        //contains implementation of click listeners
        clickListeners();

        // to search a location
        addressbySearch();

        // To check internet connectivity
        if (conManager.checkConnection(GoogleMapActivity.this)) {

            mapFrag.getMapAsync(GoogleMapActivity.this);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            Log.d("checkperm", "onCreate: working permission check");
            checkLocationPermission();
        }
    }

    public void clickListeners() {

        //used to handle behaviour i.e. expand or collapsed of bottom sheet
        RL_titleBottomshet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bottomSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    iv_rotate.animate().rotation(360).setDuration(500);
                } else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    iv_rotate.animate().rotation(180).setDuration(500);
                }
            }
        });

        btn_continueBottomsheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currLocation = et_currentLocation.getText().toString();
                appartmentInfo = et_appartmentInfo.getText().toString();

                if (currLocation.equals("") || appartmentInfo.equals("")) {
                    DialogHandler.customDialog(GoogleMapActivity.this, "Incomplete Address", "Please provide complete address (Appartment info,flat or house number)");

                } else {
                    Intent intent = new Intent(GoogleMapActivity.this, HomeActivity.class);
                    intent.putExtra("CURRENT_ADDRESS", currLocation);
                    intent.putExtra("APPARTMENT_INFO", appartmentInfo);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
            }
        });
    }

    //- - - - - - - - - - - - - - METHODS FOR GOOGLE MAPS, STARTS HERE - - - - - - - - - -  - - - - -
    @Override
    public void onPause() {
        super.onPause();
        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient != null &&
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    /* This method is triggered when the map is ready
    to be used and provides a non-null instance of GoogleMap*/
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);

        //Initialize Google Play Services
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }

    //building google maps api
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    /* Method which is used to check setting
    and permissions when google Api connects */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(0);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setSmallestDisplacement(0.1f);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi
                    .requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    /* This method gets called automatically
    once your location has been changed.*/
    @Override
    public void onLocationChanged(@NonNull Location location) {

        Log.e("Location Changed", "" + location.getLatitude() + location.getLongitude());

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        lastLatitude = location.getLatitude();
        lastLongitude = location.getLongitude();

        //Applying zooming effect on current location only when map is loaded for the first time
        if (firstLoad) {
            //  Place current location marker
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 12));
            firstLoad = false;

            //covert current location latlng to address
            currentlatlngToAddress();
        }

        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        mGoogleMap.getUiSettings().setRotateGesturesEnabled(true);

        // update the marker to the position clicked by
        // the user on GoogleMap and convert latlng to user address
        updateMarkerPosition();

    }

    /* Method to check permission for location*/
    public void checkLocationPermission() {
        ActivityResultLauncher<String[]> locationPermissionRequest =
                registerForActivityResult(new ActivityResultContracts
                        .RequestMultiplePermissions(), result -> {

                    Boolean fineLocationGranted = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        fineLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                    }

                    Boolean coarseLocationGranted = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        coarseLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_COARSE_LOCATION, false);
                    }

                    if (fineLocationGranted != null && fineLocationGranted) {
                        enablephoneLocation();
                        Log.d("checkperm", "checkLocationPermission: fine location granted");

                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        Log.d("checkperm", "checkLocationPermission: coarse location granted");

                    } else {
                        Log.d("checkperm", "checkLocationPermission: non location granted");
                    }
                });

        // Before you perform the actual permission request, check whether your app
        // already has the permissions, and whether your app needs to show a permission
        // rationale dialog. For more details, see Request permissions.
        locationPermissionRequest.launch(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    //to enable user location from mobile
    public void enablephoneLocation() {

        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(0)
                .setFastestInterval(0)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        LocationServices.getSettingsClient(this)
                .checkLocationSettings(builder.build())
                .addOnSuccessListener(this, (LocationSettingsResponse response) -> {
                    Log.d("checkperm", "enablephoneLocation: Location Enabled");
                }).addOnFailureListener(this, ex -> {
                    if (ex instanceof ResolvableApiException) {
                        // Location settings are NOT satisfied,  but this can be fixed  by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),  and check the result in onActivityResult().
                            ResolvableApiException resolvable = (ResolvableApiException) ex;
                            resolvable.startResolutionForResult(GoogleMapActivity.this, MY_PERMISSIONS_REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                    }
                });
    }

    /* Callback for the result from requesting permissions*/
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    //when location permission is denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    //- - - - - - - - - - - - - - METHODS FOR GOOGLE MAPS, ENDS HERE - - - - - - - - - -  - - - - -

    //Method to update the marker to the position clicked by the user on GoogleMap
    public void updateMarkerPosition() {
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng point) {
                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                Log.d("ClickedPosition", "Map clicked [" + point.latitude + " / " + point.longitude + "]");
                //Do your stuff with LatLng here
                //Then pass LatLng to other activity
                clickedLatitude = point.latitude;
                clickedLongitude = point.longitude;
                LatLng ClickedLatLng;
                ClickedLatLng = new LatLng(clickedLatitude, clickedLongitude);
                MarkerOptions markerOptions = new MarkerOptions().position(ClickedLatLng)
                        .title("Clicked Destination");
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ClickedLatLng, 12));
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

                mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);
                mCurrLocationMarker.setPosition(ClickedLatLng);

                // convert latlng to user Address
                latlngToAddress();
            }
        });
    }

    // Method to convert latlng to user Address
    public void latlngToAddress() {
        Geocoder geocoder;
        List<Address> addressList = null;
        geocoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            addressList = geocoder.getFromLocation(clickedLatitude, clickedLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addressList != null) {
            String address = addressList.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addressList.get(0).getLocality();

            et_currentLocation.setText(address);
            Log.d("Address", "Address:" + address);
            Log.d("Address", "City:" + city);
        }
    }

    // Method to covert current location latlng to address
    public void currentlatlngToAddress() {
        Geocoder geocoder;
        List<Address> addressList = null;
        geocoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            addressList = geocoder.getFromLocation(lastLatitude, lastLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (addressList != null) {
            String address = addressList.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addressList.get(0).getLocality();

            et_currentLocation.setText(address);
            Log.d("Address", "Address:" + address);
            Log.d("Address", "City:" + city);
        }
    }

    //Method used to get address by search
    public void addressbySearch() {

        // adding on query listener for our search view.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (mCurrLocationMarker != null) {
                    mCurrLocationMarker.remove();
                }
                // on below line we are getting the
                // location name from search view.
                String location = searchView.getQuery().toString();

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {

                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(GoogleMapActivity.this);
                    try {
                        List<Address> addresses =
                                geocoder.getFromLocationName(location, 1);
                        if (addresses.size() > 0) {
                            searchedLatitude = addresses.get(0).getLatitude();
                            searchedLongitude = addresses.get(0).getLongitude();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    LatLng SearchedLatLng;
                    SearchedLatLng = new LatLng(searchedLatitude, searchedLongitude);
                    MarkerOptions markerOptions = new MarkerOptions().position(SearchedLatLng)
                            .title("Searched Location");
                    // on below line we are adding marker to that position.
                    mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

                    // below line is to animate camera to that position.
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(SearchedLatLng, 15));

                    mCurrLocationMarker.setPosition(SearchedLatLng);

                    searchedlatlngToAddress();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    // Method to covert Searched location latlng to address
    public void searchedlatlngToAddress() {
        Geocoder geocoder;
        List<Address> addressList = null;
        geocoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            addressList = geocoder.getFromLocation(searchedLatitude, searchedLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addressList.size() > 0) {
            String address = addressList.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addressList.get(0).getLocality();

            et_currentLocation.setText(address);
            Log.d("Address", "Address:" + address);
            Log.d("Address", "City:" + city);
        } else {
            et_currentLocation.setText("Wrong Address");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

//- - - - - - - - - - -  - - - - EXTRA METHODS TO BE USED LATER - - - - - - - - - - - - -
//method to draw line as user moves

//    public void handleGetDirectionsResult(ArrayList<LatLng> directionPoints) {
//        PolylineOptions rectLine = new PolylineOptions().width(15).color(Color.RED); //red color line & size=15
//        for (int i = 0; i < directionPoints.size(); i++) {
//            rectLine.add(directionPoints.get(i));
//        }
//        //clear the old line
//        if (polyline != null) {
//            polyline.remove();
//        }
//        // mMap is the Map Object
//        polyline = mGoogleMap.addPolyline(rectLine);
//    }
