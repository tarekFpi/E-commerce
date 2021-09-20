package com.example.my_json_review;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.media.audiofx.BassBoost;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;

public class user_location extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
   private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Geocoder geocoder;
    private List<Address>addressList;
    private Boolean isPermission;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationManager locationManager;
    private LatLng latLng;
    private Location mlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_location);

        if(request_permissions_single()){
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
            check_location();
        }


 googleApiClient=new GoogleApiClient.Builder(this)
 .addApi(LocationServices.API)
 .addConnectionCallbacks(this)
 .addOnConnectionFailedListener(this).build();

 geocoder=new Geocoder(this);
    }

    private boolean check_location() {
        if(!isCheckLocationEnable()){
         showAlit();
        }
        return isCheckLocationEnable();

    }

    private void showAlit() {
        AlertDialog alertDialog=new AlertDialog.Builder(this)
         .setTitle("Location Enabale")
                .setCancelable(false)
         .setPositiveButton("Location Setting", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                 startActivity(intent);
             }
         }).setNegativeButton("Cencle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      finish();
                    }
                }).create();
           alertDialog.show();
    }

    private boolean isCheckLocationEnable() {
        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private boolean request_permissions_single() {

        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                       isPermission=true;
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                    if(permissionDeniedResponse.isPermanentlyDenied()){
                    isPermission=false;
                       // Toast.makeText(user_location.this, "PermanentlyDenied", Toast.LENGTH_SHORT).show();
                    }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();

        return  true;
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onPause() {
        googleApiClient.disconnect();
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        latLng=new LatLng(mlocation.getLatitude(),mlocation.getLongitude());
        if(latLng!=null){
            mMap.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18F));
        }else{
            Toast.makeText(this, "MarkerOptions Null", Toast.LENGTH_SHORT).show();
        }
/*
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(42.484421, 9.503180);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                        !=PackageManager.PERMISSION_GRANTED){
            return;
        }
        LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

   if(mlocation ==null){
   startLocation_Update();
   }else{
  Toast.makeText(this, "Location Null..", Toast.LENGTH_SHORT).show();
   }

       // LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,user_location.this);

    }

    private void startLocation_Update() {

        locationRequest= LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(2000)
                .setFastestInterval(5000);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)
                        !=PackageManager.PERMISSION_GRANTED){
            return;
        }
     LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,locationRequest,user_location.this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
      /*  try {
            location.getLatitude();
            location.getLongitude();

            addressList=geocoder.getFromLocation( location.getLatitude(),location.getLongitude(),1);
            if(addressList.size()>0){
                String area=addressList.get(0).getAddressLine(0);
                String city=addressList.get(0).getLocality();
                String Cuntry=addressList.get(0).getCountryName();
                String postCord=addressList.get(0).getPostalCode();
           Toast.makeText(this, "area:"+area, Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
   /*     String area=addressList.get(0).getAddressLine(0);
        String city=addressList.get(0).getLocality();
        String Cuntry=addressList.get(0).getCountryName();
        String postCord=addressList.get(0).getPostalCode();*/
        latLng=new LatLng(location.getLatitude(),location.getLongitude());

     String udate_location="Update Location:"+Double.toString(location.getLatitude())+","+
                Double.toString(location.getLongitude());

       // Toast.makeText(this, "hello:"+udate_location, Toast.LENGTH_LONG).show();


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    protected void onStop() {
        if(googleApiClient.isConnected()){
            googleApiClient.disconnect();
        }
        super.onStop();
    }
}