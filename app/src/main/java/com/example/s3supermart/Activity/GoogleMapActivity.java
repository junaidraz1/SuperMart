package com.example.s3supermart.Activity;

import static java.security.AccessController.getContext;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.s3supermart.Fragment.HomeFragment;
import com.example.s3supermart.Helper.DialogHandler;
import com.example.s3supermart.Helper.PrefsManager;
import com.example.s3supermart.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest = new LocationRequest();
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Button btn_continueBottomsheet;
    Marker mCurrLocationMarker;
    RelativeLayout RL_titleBottomshet;
    String address, name, mobileNo, email, visting, total, btn;
    EditText et_currentLocation, et_appartmentInfo;
    double lastLatitude, lastLongitude, clickedLatitude, clickedLongitude, searchedLatitude, searchedLongitude;
    double destinationLatitude = 0, destinationLongitude = 0;
    boolean isRight;
    boolean connected = false;
    PrefsManager prefsManager;
    DialogHandler dialogHandler;
    String currLocation ="",appartmentInfo="";
    ImageView iv_rotate;
    BottomSheetBehavior<View> bottomSheetBehavior;
    ArrayList<LatLng> routepoints;
    private boolean firstLoad = true;
    SearchView searchView;
    TextView tv_address;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);


        //intialising ids to variables

        RL_titleBottomshet = findViewById(R.id.Rl_titleBottomsheetGoogleMap);
        View BottomsheetView = findViewById(R.id.ll_bottomSheetGoogleMap);
        et_currentLocation = findViewById(R.id.et_currentLocation);
        et_appartmentInfo = findViewById(R.id.et_appartmentInfo);
        searchView = findViewById(R.id.idSearchView);
        btn_continueBottomsheet = findViewById(R.id.btn_continue_bottomsheet);
        iv_rotate = findViewById(R.id.iv_rotate_bottomSheet);
       /* tv_topBar = findViewById(R.id.tv_topBar);
        ll_backTopBar = findViewById(R.id.ll_backTopBar);
        tv_patientName = findViewById(R.id.tv_patientNameBottomsheet);
        tv_patientNumber = findViewById(R.id.tv_mobileNoBottomsheet);
        tv_patientlocation = findViewById(R.id.tv_patientLocationbottomsheet);
        tv_patientLocationCV = findViewById(R.id.tv_patientLocationCardView);
        tv_visitingCharges = findViewById(R.id.tv_visitingCharges);
        tv_totalCharges = findViewById(R.id.tv_totalCharges);
        ll_infoBottomsheet = findViewById(R.id.ll_infobottomsheet);
        ll_menuTopBar = findViewById(R.id.ll_menuTopBar);
        ll_phoneCall = findViewById(R.id.ll_phoneCall);
        RL_titleBottomshet = findViewById(R.id.Rl_titleBottomsheet);
        iv_directions = findViewById(R.id.iv_directions);
        ll_locations = findViewById(R.id.ll_locations);
        btn_Ride = findViewById(R.id.btn_Ride);
        tv_currentLocation = findViewById(R.id.tv_currentlocation);
        View BottomsheetView = findViewById(R.id.ll_bottomSheetView);


        //setting title of activity
        tv_topBar.setText(R.string.map_activity_title);

        //boolean variable to save state of sliding card view that contains current and drop off location info
        isRight = false;

        *//*  getting the data in intent from appointment adapter*//*
        address = getIntent().getStringExtra("Location");
        name = getIntent().getStringExtra("name");
        mobileNo = getIntent().getStringExtra("mobileNo");
        email = getIntent().getStringExtra("email");
        visting = getIntent().getStringExtra("visiting");
        total = getIntent().getStringExtra("total");
        btn = getIntent().getStringExtra("button");

        //setting data into respective fields
        tv_patientName.setText(name);
        tv_patientNumber.setText(mobileNo);
        tv_patientlocation.setText(address);
        tv_patientLocationCV.setText(address);
        tv_visitingCharges.setText(visting);
        tv_totalCharges.setText(total);
        btn_Ride.setText(btn);

        btn_Ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = btn_Ride.getText().toString();
                if (text.equals("Start Ride")) {
                    DialogHandler.errorDialog(GoogleMapActivity.this, "Ride Status", "Ride started");

                } else {
                    DialogHandler.errorDialog(GoogleMapActivity.this, "Ride Status", "Ride ended");
                }
            }
        });

        *//* SupportFragment is a Map component in an app  It's a wrapper around
            a view of a map to automatically handle the necessary life cycle needs*//*
        mapFrag.getMapAsync(GoogleMapActivity.this);



        //intiailising array to save routepoints
        routepoints = new ArrayList<>();


        //method that contains implementation of click listeners
        clickListeners();*/
        //intialising prefsmanager and dialoghandler classes

        prefsManager = new PrefsManager(GoogleMapActivity.this);
        dialogHandler = new DialogHandler();

       /* it is use to take the instance of the
        behavior from the layout params of the View instance*/
        bottomSheetBehavior = BottomSheetBehavior.from(BottomsheetView);

        /* Sets the bottom sheet height with the
        value set on the peekHeight attribute*/
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        clickListeners();

        mapFrag = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // to search a location
        addresbySearch();

        // To check internet connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

          /* SupportFragment is a Map component in an app  It's a wrapper around
          a view of a map to automatically handle the necessary life cycle needs*/
            mapFrag.getMapAsync(GoogleMapActivity.this);

            connected = true;
        } else {
            // Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            DialogHandler.customDialog(this, "No Internet Connection", "Pllease check your internet connection and try again");
            connected = false;
        }

        /* It means the device running the app has Android SDK 23 or up -
                and the Block of code inside "if" statement will be executed
                otherwise- the SDK version is lower than 23.*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

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

                currLocation= et_currentLocation.getText().toString();
                appartmentInfo = et_appartmentInfo.getText().toString();
                if (currLocation.equals("") || appartmentInfo.equals("")) {
                    DialogHandler.customDialog(GoogleMapActivity.this, "Provide Complete Address", "Please provide complete address (Appartment info,flat or house number)");
                } else {

             /*       prefsManager.setCURRENTLOCATION_KEY(currLocation);
                    prefsManager.setAPARTMENTINFO_KEY(appartmentInfo);*/
                    Intent intent = new Intent(GoogleMapActivity.this, SignupActivity.class);
                    intent.putExtra("CURRENT_ADDRESS", currLocation);
                    intent.putExtra("APPARTMENT_INFO", appartmentInfo);
                    startActivity(intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
            }
        });

        //to open info from bottom sheet
       /*  ll_infoBottomsheet.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 *//* Alert dialog that contains infromation about the user*//*
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View view = LayoutInflater.from(GoogleMapActivity.this).inflate(R.layout.dialog_userinfo_bottomsheet, null);
                builder.setView(view);
                AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                LinearLayout layout_close;
                Button btn_ok;
                TextView tv_patientName, tv_mobileNo, tv_email, tv_location;

                layout_close = view.findViewById(R.id.ll_closeUserinfoBottomsheet);
                tv_patientName = view.findViewById(R.id.tv_patientNameBottomsheetDialog);
                tv_mobileNo = view.findViewById(R.id.tv_mobileNoBottomsheetDialog);
                tv_email = view.findViewById(R.id.tv_emailBottomsheetDialog);
                tv_location = view.findViewById(R.id.tv_patientLocationBottomsheetDialog);
                btn_ok = view.findViewById(R.id.Btn_OkUserInfoBottomsheet);

                tv_patientName.setText(name);
                tv_mobileNo.setText(mobileNo);
                tv_email.setText(email);
                tv_location.setText(address);

                layout_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btn_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        iv_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSlideViewButtonClick(v);
            }
        });

        ll_backTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        iv_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + address))
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        ll_menuTopBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogHandler.showPopupWindow(GoogleMapActivity.this, "GoogleMap");
            }
        });

        ll_phoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + mobileNo));
                startActivity(callIntent);
            }
        });*/
    }
/*
    //- - - - - - - - - - - - - - - - - - ANIMATIONS, STARTS HERE - - - - - - - - - - - - - - - - - -
    // To animate view slide out from left to right
    public void slideToRight(View view) {
        TranslateAnimation animate = new TranslateAnimation(view.getWidth(), 0, 0, 0);
        animate.setDuration(1000);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    // To animate view slide out from right to left
    public void slideToLeft(View view) {
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(0, view.getWidth(), 0, 0);
        animate.setDuration(1000);
        animate.setFillAfter(true);
        view.startAnimation(animate);

    }

    *//*Method to rotate imageview as the animated view
    slide out from right to left or left to right*//*
    public void onSlideViewButtonClick(View view) {
        if (isRight) {
            slideToRight(ll_locations);
            iv_rotate.animate().rotation(360).setDuration(1000);

        } else {
            slideToLeft(ll_locations);
            // btn.setRotation(180);
            iv_rotate.animate().rotation(180).setDuration(1000);

        }
        isRight = !isRight;
    }*/
    //- - - - - - - - - - - - - - - - - - ANIMATIONS, ENDS HERE - - - - - - - - - - - - - - - - - -

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
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    /* This method is triggered when the map is ready
    to be used and provides a non-null instance of GoogleMap*/
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
       /* LatLng latLngdest;

        // To convert address into latlong
        addressToLatlng();

        latLngdest = new LatLng(destinationLatitude, destinationLongitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLngdest)
                .title("Your Destination");
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngdest, 12));
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));

        //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mGoogleMap.addMarker(markerOptions);
*/
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
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
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

       /* LatLng latLng,latLng1;
        double latitude = 0,longtitude = 0;
        prevLatitude = latitude;
        prevLongitude = longtitude;
        latitude = location.getLatitude();
        longtitude = location.getLongitude();
        latLng = new LatLng(latitude, longtitude);
        */
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        lastLatitude = location.getLatitude();
        lastLongitude = location.getLongitude();
        mLastLocation = location;
   /*     if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
            //mCurrLocationMarker.setPosition(latLng);
        }*/

//        PolylineOptions pOptions = new PolylineOptions()
//                .width(5)
//                .color(Color.BLUE)
//                .geodesic(true);

        //routePoints is an arraylist of LatLng's of current position
        //this loop below draws line when user moves

//        for (int z = 0; z < routepoints.size(); z++) {
//            LatLng point = routepoints.get(z);
//            pOptions.add(point);
//        }
//        Polyline line1 = mGoogleMap.addPolyline(pOptions);
//        routepoints.add(latLng);

        // draw a line from current position to destination
        /* if (line != null){
            line.remove();
        }
        line = mGoogleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(lastLatitude,lastLongitude), new LatLng(distinationLatitude, distinationLongitude))
                .width(5)
                .color(Color.BLUE)); */
       /*
       LatLng LastPos = new LatLng(lastLatitude,lastLongitude);
        // Place current location marker
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(LastPos);
        markerOptions1.title("Current Position");

        //move map camera
        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LastPos,12));
        markerOptions1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));

        mGoogleMap.addMarker(markerOptions1);
        */


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
        //move map camera
        // mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));

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
                        // Precise location access granted.
                    } else if (coarseLocationGranted != null && coarseLocationGranted) {
                        // Only approximate location access granted.
                    } else {
                        // No location access granted.
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
    // Its the callback method for GoogleMap.OnMapClickListener interface

    //Method to update the marker to the position clicked by the user on GoogleMap
    public void updateMarkerPosition() {
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
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

                //map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
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
        List<Address> addresses = null;
        geocoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(clickedLatitude, clickedLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();

        et_currentLocation.setText(address);
        Log.d("Address", "Address:" + address);
        Log.d("Address", "City:" + city);

    }

    // Method to covert current location latlng to address
    public void currentlatlngToAddress() {
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lastLatitude, lastLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();

        et_currentLocation.setText(address);
        Log.d("Address", "Address:" + address);
        Log.d("Address", "City:" + city);

    }

    //Method used to convert string into latlong
    public void addressToLatlng() {

        Geocoder geoCoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            List<Address> addresses =
                    geoCoder.getFromLocationName(address, 1);
            if (addresses.size() > 0) {
                destinationLatitude = addresses.get(0).getLatitude();
                destinationLongitude = addresses.get(0).getLongitude();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Method used to get address by search
    public void addresbySearch() {

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

                // below line is to create a list of address
                // where we will store the list of all address.
                /* List<Address> addressList = null;*/

                // checking if the entered location is null or not.
                if (location != null || location.equals("")) {
                    // on below line we are creating and initializing a geo coder.
                    Geocoder geocoder = new Geocoder(GoogleMapActivity.this);
               /*     try {
                        // on below line we are getting location from the
                        // location name and adding that location to address list.
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/
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

                    // on below line we are getting the location
                    // from our list a first position.
                  /*  Address address = addressList.get(0);
                    Log.d("GoogleAddress", "Address: "+address);*/

                    // on below line we are creating a variable for our location
                    // where we will add our locations latitude and longitude.
                    //   LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                   /* if (addressList.size() > 0) {
                        searchedLatitude = addressList.get(0).getLatitude();
                        searchedLongitude = addressList.get(0).getLongitude();
                    }*/
                    LatLng SearchedLatLng;
                    SearchedLatLng = new LatLng(searchedLatitude, searchedLongitude);
                    MarkerOptions markerOptions = new MarkerOptions().position(SearchedLatLng)
                            .title("Searched Location");
                    // on below line we are adding marker to that position.
                    // mGoogleMap.addMarker(new MarkerOptions().position(SearchedLatLng).title(location));
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
        List<Address> addresses = null;
        geocoder = new Geocoder(GoogleMapActivity.this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(searchedLatitude, searchedLongitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0) {
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            String postalCode = addresses.get(0).getPostalCode();

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
