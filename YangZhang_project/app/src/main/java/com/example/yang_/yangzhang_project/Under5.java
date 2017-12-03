package com.example.yang_.yangzhang_project;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.example.yang_.yangzhang_project.database.DataSource;
import com.example.yang_.yangzhang_project.database.MyHelper;
import com.example.yang_.yangzhang_project.model.DataItem;
import com.example.yang_.yangzhang_project.sample.SampleDataProvider;
import java.util.ArrayList;
import java.util.List;

public class Under5 extends AppCompatActivity implements AdapterView.OnItemClickListener {


    List<DataItem> dataItemList = SampleDataProvider.dataItemList;

    private RecyclerView myRecycler;
    private DataSource mDataSource;
    private DataItemAdapter myAdapter;
    private MyHelper helper;
    private Cursor cursor;
    private String category = "5mins";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under5);

        DataItemAdapter adapter = new DataItemAdapter(this, dataItemList);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvItems5);
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

        myRecycler = (RecyclerView) findViewById(R.id.rvItems5);
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
