package com.example.yang_.yangzhang_project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.yang_.yangzhang_project.Constants;
import com.example.yang_.yangzhang_project.database.MyHelper;
import com.example.yang_.yangzhang_project.model.DataItem;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    private Context mContext;
    private SQLiteDatabase mDatabase;
    private final SQLiteOpenHelper mDbHelper;

    public DataSource(Context context) {
        this.mContext = context;
        mDbHelper = new MyHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    //open the database
    //each time call getWritbale will get a reference
    public void open() {
        mDatabase = mDbHelper.getWritableDatabase();
    }


    //methods of the super class
    //close the database
    public void close() {
        mDbHelper.close();
    }

    //insert data items into database table
    public DataItem createDataItem(DataItem item) {
        //like a bundle, a set of key value pairs
        ContentValues values = item.toValues();
        // (name of the table, no column hat, contentvalues object);
        mDatabase.insert(ItemsTable.TABLE_ITEMS, null, values);
        return item;
    }


    //count the rows in the databse table
    //find out how many items in the table
    public long getDataItemsCount() {
        return DatabaseUtils.queryNumEntries(mDatabase, ItemsTable.TABLE_ITEMS);
    }

    public void seedDatabase(List<DataItem> dataItemList) {
        long numItems = getDataItemsCount();
        //only insert item if the database is already exist
        if (numItems == 0) {
            for (DataItem item : dataItemList) {
                try {
                    createDataItem(item);
                } catch (SQLiteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //list of data item objects
    public List<DataItem> getAllItems(String category){
        List<DataItem> dataItems = new ArrayList<>();

        //use cursor to traverse the dataset, a reference of the data that returned from the query
        Cursor cursor = null;
        if(category == null) {
            cursor = mDatabase.query(ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS,
                    null, null, null, null, null);
        }else{
            //sort items by category
            String[] categories = {category};
            cursor = mDatabase.query(ItemsTable.TABLE_ITEMS, ItemsTable.ALL_COLUMNS,
                    ItemsTable.COLUMN_CATEGORY + "=?", categories, null, null, ItemsTable.COLUMN_NAME);
        }
        while (cursor.moveToNext()){
            DataItem item = new DataItem();
            //put the name of the colum
            item.setItemId(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_ID)));
            item.setItemName(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_NAME)));
            item.setDescription(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_DESCRIPTION)));
            item.setCategory(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_CATEGORY)));
            item.setInstruction(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_INSTRUCTION)));
            item.setSortPosition(cursor.getInt(
                    cursor.getColumnIndex(ItemsTable.COLUMN_ID)));
            item.setCost(cursor.getDouble(
                    cursor.getColumnIndex(ItemsTable.COLUMN_COST)));
            item.setImage(cursor.getString(
                    cursor.getColumnIndex(ItemsTable.COLUMN_IMAGE)));
            dataItems.add(item);
        }

        return dataItems;
    }

}