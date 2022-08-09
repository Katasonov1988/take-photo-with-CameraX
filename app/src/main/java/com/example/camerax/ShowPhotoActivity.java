package com.example.camerax;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class ShowPhotoActivity extends AppCompatActivity {
    private ImageView previewPhoto;
    private Button buttonDeletePhotoAndFinish;
    private Button buttonGetUriPhotoAndGoToEditNoteActivity;
    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_photo);
        hideStatusBar();

        buttonDeletePhotoAndFinish = findViewById(R.id.buttonDeletePhotoAndFinish);
        buttonGetUriPhotoAndGoToEditNoteActivity = findViewById(R.id.buttonGetUriPhotoAndGoToEditNoteActivity);
        previewPhoto = findViewById(R.id.previewPhoto);

        Intent intent = getIntent();
        photoUri = Uri.parse(intent.getStringExtra("photoUri"));
        previewPhoto.setImageURI(photoUri);

        buttonDeletePhotoAndFinish.setOnClickListener(deletePhotoAndGoToTakePhotoActivity);
        buttonGetUriPhotoAndGoToEditNoteActivity.setOnClickListener(savePhotoAndGoToTakePhotoActivity);

    }

    View.OnClickListener deletePhotoAndGoToTakePhotoActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            deletePhotoFile();
        }
    };

    View.OnClickListener savePhotoAndGoToTakePhotoActivity = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
            Toast.makeText(ShowPhotoActivity.this, "photo is saved:  " + photoUri.getPath(), Toast.LENGTH_SHORT).show();
        }
    };

    private void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        deletePhotoFile();
    }

    private void deletePhotoFile() {
        File fDelete = new File(photoUri.getPath());
        if (fDelete.delete()) {
            Toast.makeText(ShowPhotoActivity.this, "photo is deleted :" + photoUri.getPath(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ShowPhotoActivity.this, "file not Deleted :" + photoUri.getPath(), Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}