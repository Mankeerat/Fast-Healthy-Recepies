package com.example.yang_.yangzhang_project;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.yang_.yangzhang_project.database.DataSource;
import com.example.yang_.yangzhang_project.database.MyHelper;
import com.example.yang_.yangzhang_project.model.DataItem;
import com.example.yang_.yangzhang_project.sample.SampleDataProvider;

import java.util.List;

public class Under15 extends AppCompatActivity implements AdapterView.OnItemClickListener{

    List<DataItem> dataItemList = SampleDataProvider.dataItemList;

    private RecyclerView myRecycler;
    private DataSource mDataSource;
    private DataItemAdapter myAdapter;
    private MyHelper helper;
    private Cursor cursor;
    private String category = "15mins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under15);

        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems15);
        recyclerView.setAdapter(adapter);

        //instantiate datasource object
        //whenever start the activity, gets a valid reference to the data source object
        mDataSource = new DataSource(this);
        mDataSource.open();
        mDataSource.seedDatabase(dataItemList);
        Toast.makeText(this,"Database acquired",Toast.LENGTH_LONG).show();

        List<DataItem> recipeList = mDataSource.getAllItems(category);
        myAdapter = new DataItemAdapter(this,recipeList);
        helper = new MyHelper(this);

        myRecycler = (RecyclerView) findViewById(R.id.rvItems15);
        recyclerView.setAdapter(myAdapter);

    }


    //whenever the activity is active, it has a valid database connection
    @Override
    protected void onResume() {
        super.onResume();
        mDataSource.open();
    }
    //whenever the user closes the activity, close datasource and database
    //prevent database leak
    @Override
    protected void onPause() {
        super.onPause();
        mDataSource.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
