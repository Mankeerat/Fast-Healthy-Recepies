package com.example.yang_.yangzhang_project;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yang_.yangzhang_project.model.DataItem;
import com.example.yang_.yangzhang_project.sample.SampleDataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    private TextView rName, rDescription, rCategory, rInstruction, rCost;
    private ImageView itemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //get the item
        DataItem item = getIntent().getExtras().getParcelable(DataItemAdapter.ITEM_KEY);
        if (item == null) {
            throw new AssertionError("Null data item received!");
        }

        rName = (TextView) findViewById(R.id.name);
        rDescription = (TextView) findViewById(R.id.description);
        rInstruction = (TextView) findViewById(R.id.instruction);
        rCategory = (TextView) findViewById(R.id.categoryTextView);
        rCost = (TextView) findViewById(R.id.cost);
        itemImage = (ImageView) findViewById(R.id.itemImage);

        rName.setText(item.getItemName());
        rDescription.setText(item.getDescription());
        rInstruction.setText(item.getInstruction());
        rCategory.setText(item.getCategory());

        //format the cost
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.getDefault());
        rCost.setText(nf.format(item.getCost()));

        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}