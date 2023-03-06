package com.example.mycamera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView vid;
    private final int req = 0;
    private final int reqv = 0;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        vid = findViewById(R.id.videoView);
        vid.setVisibility(View.GONE);
    }

    public void ChooseIMG(View view) {
        Intent intentChooser = new Intent();
        intentChooser.setType("image/*");
        intentChooser.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intentChooser, 1);
    }

    public void take(View view) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
        startActivityForResult(intent, req);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(imageBitmap);
    }

    public void Video(View view) {

        vid.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        Intent intente = new Intent();
        intente.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intente.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intente, reqv);
        }
    }
}