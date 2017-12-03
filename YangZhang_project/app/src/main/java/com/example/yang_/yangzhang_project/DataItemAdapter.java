package com.example.yang_.yangzhang_project;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yang_.yangzhang_project.model.DataItem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.MyViewHolder> {

    public static final String ITEM_ID_KEY = "item_id_key";
    public static final String ITEM_KEY = "item_key";
    public List<DataItem> itemList;
    Context mContext;

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.itemList = items;
    }

    //gets called by adapter automatically each time it needs a new visual representation of a data item
    // when inflate the xml file, gets a view, then wrap in an instance of view holder
    @Override
    public DataItemAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(itemView);
        return viewHolder;
    }

    //gets called each time the adapter encounters a new data item that it needs to display
    //passes in the reference to the viewholder and the position of the data item in the collection
    // display data item's value
    @Override
    public void onBindViewHolder(DataItemAdapter.MyViewHolder holder, int position) {
        final DataItem item = itemList.get(position);
        InputStream inputStream;

        try {
            //getting textview, imageview from viewholder object
            holder.itemNameText.setText(item.getItemName());
            //set the string object to get image data
            String imageFile = item.getImage();
            //inputstream object to get image from the assets
            inputStream = mContext.getAssets().open(imageFile);
            // drawable object from inputstream
            Drawable d = Drawable.createFromStream(inputStream, null);
            //set the view with the drawable object
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //the unique id to pass btw activities
                String itemId = item.getItemId();
                //explicit intent
                Intent intent = new Intent(mContext,DetailActivity.class);
                //pass item itself
                intent.putExtra(ITEM_KEY, item);
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    //responsible for setting up the bindings to the views in the xml file
    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemNameText;
        public ImageView imageView;
        public RelativeLayout myLayout;
        //reference to the view that represent a single data item
        public View mView;

        Context context;

        public MyViewHolder(View itemView) {
            super(itemView);
            myLayout = (RelativeLayout) itemView;

            itemNameText = (TextView) itemView.findViewById(R.id.itemNameText);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;

            itemView.setOnClickListener(this);
            context = itemView.getContext();

        }
        @Override
        public void onClick(View view) {
            Toast.makeText(context,
                    "You have clicked " + ((TextView) view.findViewById(R.id.itemNameText)).getText().toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
