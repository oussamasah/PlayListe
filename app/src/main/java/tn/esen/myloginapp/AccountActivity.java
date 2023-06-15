package tn.esen.myloginapp;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {

    Button mCaptureBtn ,mSaveInfo,mLoadInfo;
    ImageView mImageView;
    Uri image_uri;
    SharedPreferences sharedPref ;

    EditText Quaot;
    private FirebaseAuth mAuth;
    public static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        String email = user.getEmail();

        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText("Welcome : " + email);

        Button logoutButton = findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(AccountActivity.this, LoginActivity.class));
                finish();
            }
        });
        Button Home_btn = findViewById(R.id.Home_btn);
        Home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountActivity.this, SongListActivity.class));
                finish();
            }
        });
        mImageView = findViewById(R.id.image_view);
        mCaptureBtn =findViewById(R.id.capture_image_btn);

        //Button Click
        mCaptureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                            ||
                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        //Request Permession
                        String[] permession = {Manifest.permission.CAMERA , Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        //PopUp TO Request Permessions
                        requestPermissions(permession ,PERMISSION_CODE );
                    }
                    else {
                        //Permession Already Granted
                        openCamera();
                    }

                }
            }
        });

        //SharedPreferences
        sharedPref = getSharedPreferences("My_Prefs", Context.MODE_PRIVATE);

    }

        private void openCamera() {
            ContentValues values =new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, "New Picture");
            values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
            image_uri =getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , values);
            //Intenet to  camera
            Intent cameraIntent = new  Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT , image_uri);
            startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            //If user  decline/accept the  permessions
            switch (requestCode) {
                case PERMISSION_CODE: {
                    if (grantResults.length > 0 && grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED) {
                        openCamera();
                    } else {
                        Toast.makeText(this, "Permession Denied..", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data){

            //capture is  Done
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                //edit My Image
                mImageView.setImageURI(image_uri);
            }
        }

        public void buttonSave(View v){
           EditText Quaot = (EditText) findViewById(R.id.statue1);
           SharedPreferences.Editor myeditor = sharedPref.edit();
           myeditor.putString("Data" ,Quaot.getText().toString());
           myeditor.apply();
        }

        public void buttonLoad(View v){
            TextView Quaot = (TextView) findViewById(R.id.Statue2);
            Quaot.setText(sharedPref.getString("Data", "Empty"));
        }


}


