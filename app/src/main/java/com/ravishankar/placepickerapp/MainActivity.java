package com.ravishankar.placepickerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends Activity {

    TextView name,phone,latlong,getpalce;
    Button pickplace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getpalce=(TextView)findViewById(R.id.address);
        name=(TextView)findViewById(R.id.name);
        phone=(TextView)findViewById(R.id.phone);
        latlong=(TextView)findViewById(R.id.latlong);
        pickplace=(Button)findViewById(R.id.pickplace);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1001)
        {
            if(resultCode==RESULT_OK)
            {
                Place place= PlacePicker.getPlace(data, this);
                String addresspicked=place.getAddress().toString();
                String namepicked=place.getName().toString();
                String phonepicked=place.getPhoneNumber().toString();
                getpalce.setText(addresspicked);
                name.setText(namepicked);
                phone.setText(phonepicked);
                double latitude= (double) place.getLatLng().latitude;
                double longitude= (double) place.getLatLng().longitude;
                String latlongpicked="Latt:"+latitude+" Long:"+longitude;
                latlong.setText(latlongpicked);
            }
        }

    }
    public void OnPlacePick(View v)
    {

       // Intent intent=new Intent(this,MapsActivity.class);
        //tartActivity(intent);
       try
        {
            PlacePicker.IntentBuilder builder=new PlacePicker.IntentBuilder();
            Intent intent;
            intent=builder.build(this);
            startActivityForResult(intent,1001);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }
}
