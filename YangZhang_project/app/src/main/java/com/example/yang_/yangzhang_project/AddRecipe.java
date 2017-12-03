package com.example.yang_.yangzhang_project;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.yang_.yangzhang_project.database.DataSource;
import com.example.yang_.yangzhang_project.model.DataItem;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class AddRecipe extends AppCompatActivity implements View.OnClickListener {

    //text
    EditText nameEditText,categoryEditText,
            costEditText,descriptionEditText,
            instructionEditText;
    Button addBtn;

    private DataSource dataSource;
    private DataItem recipe;

    //camera
    public static final int TAKE_PHOTO = 0;
    ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);


        //camera
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(this);

        //text input
        nameEditText = (EditText)findViewById(R.id.nameEditText);
        categoryEditText = (EditText)findViewById(R.id.categoryEditText);
        costEditText = (EditText)findViewById(R.id.costEditText);
        descriptionEditText = (EditText)findViewById(R.id.descriptionEditText);
        instructionEditText = (EditText)findViewById(R.id.instructionEditText);
        addBtn = (Button) findViewById(R.id.addRecipe);
        addBtn.setOnClickListener(this);

        dataSource = new DataSource(this);
        dataSource.open();


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, TAKE_PHOTO);
                break;

            case R.id.addRecipe:
                addRecipe();
                Intent intent2 = new Intent(this, HomeActivity.class);
                startActivity(intent2);
                break;
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //check if the user clicks yes or cancel
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                //get image thumbnail
                bitmap = (Bitmap) data.getExtras().get("data");
                //show image
                imageView.setImageBitmap(bitmap);

            }
        }
    }

    //convert bitmap to string
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }


    public void addRecipe () {
        String name = nameEditText.getText().toString();
        String category = categoryEditText.getText().toString();
        Double cost = Double.parseDouble(costEditText.getText().toString());
        String description = descriptionEditText.getText().toString();
        String instruction = instructionEditText.getText().toString();
        String image = BitMapToString(bitmap);

        recipe = new DataItem(UUID.randomUUID().toString(), name, category, description, instruction, 0, cost, image);

//        Toast.makeText(this, name + " added to the recipes database", Toast.LENGTH_SHORT).show();
        //add this new recipe to database
        dataSource.createDataItem(recipe);
        long numItems = dataSource.getDataItemsCount();

        if (numItems == 0) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        nameEditText.setText("");
        categoryEditText.setText("");
        costEditText.setText("");
        descriptionEditText.setText("");
        instructionEditText.setText("");
    }
}
