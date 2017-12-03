package com.example.yang_.yangzhang_project.model;


import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.yang_.yangzhang_project.database.ItemsTable;

import java.util.UUID;

public class DataItem implements Parcelable {
    private String itemId;
    private String itemName;
    private String category;
    private String description;
    private String instruction;
    private int sortPosition;
    private double cost;
    private String image;

    public DataItem() {
    }

    public DataItem(String itemId, String itemName, String category, String description, String instruction, int sortPosition, double cost, String image) {

        //assign each object a unique primary key
        if(itemId == null){
            itemId = UUID.randomUUID().toString();
        }

        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.instruction = instruction;
        this.sortPosition = sortPosition;
        this.cost = cost;
        this.image = image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(int sortPosition) {
        this.sortPosition = sortPosition;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//for converting data object into a contentvalues object, and use with databse code
    public ContentValues toValues(){
        //8 columns
        ContentValues values = new ContentValues(8);
        values.put(ItemsTable.COLUMN_ID, itemId);
        values.put(ItemsTable.COLUMN_NAME, itemName);
        values.put(ItemsTable.COLUMN_DESCRIPTION, description);
        values.put(ItemsTable.COLUMN_CATEGORY, category);
        values.put(ItemsTable.COLUMN_INSTRUCTION, instruction);
        values.put(ItemsTable.COLUMN_POSITION, sortPosition);
        values.put(ItemsTable.COLUMN_COST, cost);
        values.put(ItemsTable.COLUMN_IMAGE, image);
        return values;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", instruction='" + instruction + '\'' +
                ", sortPosition=" + sortPosition +
                ", cost=" + cost +
                ", image='" + image + '\'' +
                '}';
    }


    // parcelable object allow us to pass objects that are instance of this class
    //data item btw activities as intent extras
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.itemId);
        dest.writeString(this.itemName);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeString(this.instruction);
        dest.writeInt(this.sortPosition);
        dest.writeDouble(this.cost);
        dest.writeString(this.image);
    }

    protected DataItem(Parcel in) {
        this.itemId = in.readString();
        this.itemName = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.instruction = in.readString();
        this.sortPosition = in.readInt();
        this.cost = in.readDouble();
        this.image = in.readString();
    }

    public static final Parcelable.Creator<DataItem> CREATOR = new Parcelable.Creator<DataItem>() {
        @Override
        public DataItem createFromParcel(Parcel source) {
            return new DataItem(source);
        }

        @Override
        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };
}
