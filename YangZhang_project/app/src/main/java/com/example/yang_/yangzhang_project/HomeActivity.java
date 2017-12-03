package com.example.yang_.yangzhang_project;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener,SensorEventListener {

    ImageView under5, under10, under15, more, viewAll;
    ImageView addButton;

    //url to check more recipes
    private String url = "http://allrecipes.com/";

    //sensor
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView tempView;
    private final static String unavailable = "Sorry, sensor not available for this device.";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        under5 = (ImageView) findViewById(R.id.under5);
        under5.setOnClickListener(this);

        under10 = (ImageView) findViewById(R.id.under10);
        under10.setOnClickListener(this);

        under15 = (ImageView) findViewById(R.id.under15);
        under15.setOnClickListener(this);

        more = (ImageView) findViewById(R.id.explore);
        more.setOnClickListener(this);

        viewAll = (ImageView) findViewById(R.id.viewAll);
        viewAll.setOnClickListener(this);

        addButton = (ImageView) findViewById(R.id.add);
        addButton.setOnClickListener(this);

        tempView = (TextView) findViewById(R.id.tempView);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            mSensor= mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        if (mSensor == null) {
            tempView.setText(unavailable);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.under5:
                Intent intent5 = new Intent(this, Under5.class);
                startActivity(intent5);
                break;

            case R.id.under10:
                Intent intent10 = new Intent(this, Under10.class);
                startActivity(intent10);
                break;

            case R.id.under15:
                Intent intent15 = new Intent(this, Under15.class);
                startActivity(intent15);
                break;

            case R.id.viewAll:
                Intent viewALl = new Intent(this, ViewAll.class);
                startActivity(viewALl);
                break;

            case R.id.explore:
                Intent intentMore = new Intent(Intent.ACTION_VIEW);
                intentMore.setData(Uri.parse(url));
                startActivity(intentMore);
                break;

            case R.id.add:
                Intent addRecipe = new Intent(this, AddRecipe.class);
                startActivity(addRecipe);
                break;
     }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onPause() {

        mSensorManager.unregisterListener(this);
        super.onPause();

    }

    @Override
    protected void onStop() {

        mSensorManager.unregisterListener(this);
        super.onStop();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float ambient_temperature = event.values[0];
        tempView.setText("Ambient Temperature:\n " + String.valueOf(ambient_temperature) + getResources().getString(R.string.celsius));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}