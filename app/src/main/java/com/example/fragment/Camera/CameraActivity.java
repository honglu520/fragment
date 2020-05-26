package com.example.fragment.Camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fragment.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CameraActivity extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private static  final int  TAKE_PHOTO=1;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        button=findViewById(R.id.camera);
        imageView=findViewById(R.id.camerashow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File  file=new File(getExternalCacheDir(),"image,jpg");
                if (file.exists()) {//检查是否存在，存在返回true
                    file.delete();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT>=24) {//Build.VERSION.SDK_INT对应手机安卓版本号
                    //imageUri = FileProvider.getUriForFile(CameraActivity.this, " com.example.fragment.Camera.fileProvider", file);
                }else {
                    imageUri=Uri.fromFile(file);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,file);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (requestCode==RESULT_OK) {
                    try {
                        Bitmap bitmap= BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        imageView.setImageBitmap(bitmap);


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
