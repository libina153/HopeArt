package com.example.hopeart.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.hopeart.DataModel.UserProfileModel;
import com.example.hopeart.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustAddProfile extends AppCompatActivity {

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

    EditText edtCustName,edtCustMobno,edtCustAddress,edtCustEmailId;
    FloatingActionButton btn_moveto_custhomee;
    com.mikhaellopez.circularimageview.CircularImageView imgCustProfile;

    int PICK_IMAGE_REQUEST=10;
    private Uri filePath;

    StorageReference rootReference;
    FirebaseFirestore firebaseDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cust_addcust_profile_activity);

        edtCustName=findViewById(R.id.edtCustName);
        edtCustMobno=findViewById(R.id.edtCustMobno);
        edtCustAddress=findViewById(R.id.edtCustAddress);
        edtCustEmailId=findViewById(R.id.edtCustEmail);

        imgCustProfile=findViewById(R.id.imgCustProfile);

        firebaseDB = FirebaseFirestore.getInstance();
        rootReference= FirebaseStorage.getInstance().getReference();

        btn_moveto_custhomee=findViewById(R.id.btn_moveto_custhome);

        ActivityCompat.requestPermissions(CustAddProfile.this,new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        },1);

       btn_moveto_custhomee.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               userName=edtCustName.getText().toString();
               userMobileNo=edtCustMobno.getText().toString();
               userEmail=edtCustEmailId.getText().toString();
               userAddress=edtCustAddress.getText().toString();

               UserProfileModel st=new UserProfileModel(userId,userName,userMobileNo,
                       userAddress,userEmail,"Customer",userAadhar,userHandicapCerti,userAadharImg,userProfileImg
               );

               Map<String,Object> data=st.toMap();

               firebaseDB.collection("UsersProfile")
                       .add(data)
                       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                           @Override
                           public void onSuccess(DocumentReference documentReference) {
                               Toast.makeText(CustAddProfile.this, "Data Added", Toast.LENGTH_SHORT).show();
                               String insertedId=documentReference.getId();
                               uploadImage(insertedId);
                           }
                       }).addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Toast.makeText(CustAddProfile.this, "Fail", Toast.LENGTH_SHORT).show();
                   }
               });

           }
       });

        imgCustProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }


    private void selectImage (){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
    }
    private void uploadImage (String insId)
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StorageReference fileRef = rootReference.child(UUID.randomUUID().toString() + ".jpg");

        fileRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(CustAddProfile.this, "Upload Success..", Toast.LENGTH_LONG).show();
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url=uri.toString();

                                Map<String,Object> upData=new HashMap<>();
                                upData.put("userProfileImg",url);

                                firebaseDB.collection("UsersProfile")
                                        .document(insId)
                                        .update(upData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(CustAddProfile.this, "Data Updated ", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CustAddProfile.this, "Data Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText(CustAddProfile.this, "Upload Fail..", Toast.LENGTH_LONG).show();
                Toast.makeText(CustAddProfile.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                progressDialog.setMessage("Uploaded" + (int) progress + "%");
            }
        });
    }
    @Override
    public void onActivityResult ( int requestCode, int resultCode,
                                   @Nullable Intent data) {
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
                        .getBitmap(CustAddProfile.this.getContentResolver(),
                                filePath);
                imgCustProfile.setImageBitmap(bitmap);
            } catch (IOException e) {
                //Log the exception
                e.printStackTrace();
            }
        }
    }
}


