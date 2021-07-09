package com.example.hopeart.Activity;

import android.Manifest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.UserProfileModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.SharedPreferenceManger;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArtistAddProfile extends AppCompatActivity {

    String userId;
    String userName;
    String userMobileNo;
    String userAddress;
    String userEmail;
    String userType;
    String userAadhar;
    String userHandicapCerti;
    String userAadharImg;
    String userProfileImg;

    EditText edtArtistName,edtArtistMobno,edtArtistAddress,edtArtistAadhaar,edtArtistEmail;
    ImageView imgUploadAadhar,imgUploadHandicapCerti;

    FloatingActionButton btn_moveto_artisthome;
    com.mikhaellopez.circularimageview.CircularImageView imgArtistProfile;

    int PICK_IMAGE_REQUEST=10;

    int PICK_IMAGE_CERTIFICATE=60;
    int PICK_IMAGE_AADHAR=85;

    private Uri filePath;
    private Uri filePathAadhar;
    private Uri filePathCerfificate;

    StorageReference rootReference;
    FirebaseFirestore firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_addartist_profile_activity);

        edtArtistName=findViewById(R.id.edtArtistName);
        edtArtistMobno=findViewById(R.id.edtArtistMobileNo);
        edtArtistEmail=findViewById(R.id.edtArtistEmail);
        edtArtistAddress=findViewById(R.id.edtArtistAddress);
        edtArtistAadhaar=findViewById(R.id.edtArtistAadharNo);

        imgUploadAadhar=findViewById(R.id.imgUploadAadhar);
        imgUploadHandicapCerti=findViewById(R.id.imgUploadHandicapCerti);

        imgArtistProfile=findViewById(R.id.imgArtistProfile);

        firebaseDB = FirebaseFirestore.getInstance();
        rootReference= FirebaseStorage.getInstance().getReference();

        ActivityCompat.requestPermissions(ArtistAddProfile.this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        },1);

        btn_moveto_artisthome = findViewById(R.id.btn_moveto_artisthome);
        btn_moveto_artisthome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName=edtArtistName.getText().toString();
                userMobileNo=edtArtistMobno.getText().toString();
                userEmail=edtArtistEmail.getText().toString();
                userAadhar=edtArtistAadhaar.getText().toString();
                userAddress=edtArtistAddress.getText().toString();

                UserProfileModel st=new UserProfileModel(userId,userName,userMobileNo,
                        userAddress,userEmail,"Artist",userAadhar,
                        userHandicapCerti,userAadharImg,userProfileImg
                       );

                Map<String,Object> data=st.toMap();

                firebaseDB.collection("UsersProfile")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ArtistAddProfile.this, "Data Added", Toast.LENGTH_SHORT).show();
                                String insertedId=documentReference.getId();
                                SharedPreferenceManger.setUserId(ArtistAddProfile.this,insertedId);
                                SharedPreferenceManger.setUserName(ArtistAddProfile.this,userName);
                                SharedPreferenceManger.setMobileNo(ArtistAddProfile.this,userMobileNo);
                                SharedPreferenceManger.setEmail(ArtistAddProfile.this,userEmail);
                                SharedPreferenceManger.setAadharNo(ArtistAddProfile.this,userAadhar);
                                SharedPreferenceManger.setAddress(ArtistAddProfile.this,userAddress);
                                SharedPreferenceManger.setAadharPic(ArtistAddProfile.this,userAadharImg);
                                SharedPreferenceManger.setHandiCertiPic(ArtistAddProfile.this,userHandicapCerti);
                                uploadImage(insertedId);
                                uploadAadhaarImage(insertedId);
                                uploadHandiCertiImage(insertedId);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ArtistAddProfile.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent homeIntent=new Intent(ArtistAddProfile.this, ArtistHomeBottomNav.class);
                startActivity(homeIntent);
                finish();
            }
        });

        imgArtistProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        imgUploadAadhar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectAadharImage();
            }
        });
        imgUploadHandicapCerti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectCertificateImage ();
            }
        });
    }

    private void selectImage (){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(
                Intent.createChooser(
                        intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }

    private void selectAadharImage (){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(
                Intent.createChooser(
                        intent, "Select Image from here..."), PICK_IMAGE_AADHAR);
    }

    private void selectCertificateImage (){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(
                Intent.createChooser(
                        intent, "Select Image from here..."), PICK_IMAGE_CERTIFICATE);
    }

    private void uploadImage (String insId)
    {
/*
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
*/

        StorageReference fileRef = rootReference.child(UUID.randomUUID().toString() + ".jpg");

        fileRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       // progressDialog.dismiss();
                        Toast.makeText(ArtistAddProfile.this, "Upload Success..", Toast.LENGTH_LONG).show();
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url=uri.toString();
                                SharedPreferenceManger.setProfilePic(ArtistAddProfile.this,url);
                                Map<String,Object> upData=new HashMap<>();
                                upData.put("userProfileImg",url);

                                firebaseDB.collection("UsersProfile")
                                        .document(insId)
                                        .update(upData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ArtistAddProfile.this, "Data Updated ", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ArtistAddProfile.this, "Data Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //progressDialog.dismiss();
                Toast.makeText(ArtistAddProfile.this, "Upload Fail..", Toast.LENGTH_LONG).show();
                Toast.makeText(ArtistAddProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                //progressDialog.setMessage("Uploaded" + (int) progress + "%");
            }
        });
    }


    private void uploadAadhaarImage (String insId)
    {
        StorageReference fileRef = rootReference.child(UUID.randomUUID().toString() + ".jpg");

        fileRef.putFile(filePathAadhar)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // progressDialog.dismiss();
                        Toast.makeText(ArtistAddProfile.this, "Aadhaar image uploaded", Toast.LENGTH_LONG).show();
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url=uri.toString();
                                SharedPreferenceManger.setAadharPic(ArtistAddProfile.this,url);
                                Map<String,Object> upData=new HashMap<>();
                                upData.put("userAadharImg",url);

                                firebaseDB.collection("UsersProfile")
                                        .document(insId)
                                        .update(upData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ArtistAddProfile.this, "Data Updated ", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ArtistAddProfile.this, "Data Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //progressDialog.dismiss();
                Toast.makeText(ArtistAddProfile.this, "Upload Fail..", Toast.LENGTH_LONG).show();
                Toast.makeText(ArtistAddProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                //progressDialog.setMessage("Uploaded" + (int) progress + "%");
            }
        });
    }

    private void uploadHandiCertiImage (String insId)
    {
        StorageReference fileRef = rootReference.child(UUID.randomUUID().toString() + ".jpg");

        fileRef.putFile(filePathCerfificate)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // progressDialog.dismiss();
                        Toast.makeText(ArtistAddProfile.this, "Handicap Certificate uploaded", Toast.LENGTH_LONG).show();
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url=uri.toString();
                                SharedPreferenceManger.setHandiCertiPic(ArtistAddProfile.this,url);
                                Map<String,Object> upData=new HashMap<>();
                                upData.put("userHandicapCerti",url);

                                firebaseDB.collection("UsersProfile")
                                        .document(insId)
                                        .update(upData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ArtistAddProfile.this, "Data Updated ", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ArtistAddProfile.this, "Data Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                //progressDialog.dismiss();
                Toast.makeText(ArtistAddProfile.this, "Upload Fail..", Toast.LENGTH_LONG).show();
                Toast.makeText(ArtistAddProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                //progressDialog.setMessage("Uploaded" + (int) progress + "%");
            }
        });
    }


    @Override
    public void onActivityResult ( int requestCode, int resultCode,
                                   @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            //Get the uri of data
            filePath = data.getData();
            try {
                //Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(ArtistAddProfile.this.getContentResolver(),
                                filePath);
                imgArtistProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                //Log the exception
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_IMAGE_AADHAR
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            //Get the uri of data
            filePathAadhar = data.getData();
            try {
                //Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(ArtistAddProfile.this.getContentResolver(),
                                filePathAadhar);
                imgUploadAadhar.setImageBitmap(bitmap);
            } catch (IOException e) {
                //Log the exception
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_IMAGE_CERTIFICATE
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            //Get the uri of data
            filePathCerfificate = data.getData();
            try {
                //Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(ArtistAddProfile.this.getContentResolver(),
                                filePathCerfificate);
                imgUploadHandicapCerti.setImageBitmap(bitmap);
            } catch (IOException e) {
                //Log the exception
                e.printStackTrace();
            }
        }
    }
    }


