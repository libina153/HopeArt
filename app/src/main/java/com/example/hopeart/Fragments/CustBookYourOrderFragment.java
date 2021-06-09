package com.example.hopeart.Fragments;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.LocaleData;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.hopeart.DataModel.ArtistCustomizeOrderModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.UtilityMethods;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import static android.app.Activity.RESULT_OK;
import static android.content.Context.CAMERA_SERVICE;

public class CustBookYourOrderFragment extends Fragment {
    Context ctx;

    String strFrameSize, strPaperType, strArtworkType;

    String[] CustArtworkType = {"Sketching", "Painting"};
    String[] CustFrameSize = {"4*6", "5*7", "6*8", "5*8", "8*8"};
    String[] CustPaperType = {"Matt Coated", "Silk Coated", "Watermarked", "Kraft Paper"};

    TextView txtGallery, txtCamera, txtFrameSize, txtPaperType, txtBookArtworkType;
    ImageView img;
    Button btnBookOrder;

    StorageReference rootReference;
    FirebaseFirestore firebaseDB;

    int PICK_IMAGE_REQUEST = 10;
    private static final int CAMERA_REQUEST = 1888;

    private Uri filePath;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean attachToRoot;
        return inflater.inflate(R.layout.cust_fragment_bookyourorder, container, attachToRoot = false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        firebaseDB = FirebaseFirestore.getInstance();
        rootReference= FirebaseStorage.getInstance().getReference();


        txtGallery = view.findViewById(R.id.txtCustGallery);
        txtCamera = view.findViewById(R.id.txtCustCamera);
        txtBookArtworkType = view.findViewById(R.id.txtCustBookArtworkType);
        txtPaperType = view.findViewById(R.id.txtCustBookPaperType);
        txtFrameSize = view.findViewById(R.id.txtCustBookFrameSize);

        img = view.findViewById(R.id.imgcustBookOrder);
        btnBookOrder = view.findViewById(R.id.btnCustBookSubmit);

        txtBookArtworkType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select ArtworkType")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(CustArtworkType, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtBookArtworkType.setText(CustArtworkType[position]);
                                 strArtworkType= (CustArtworkType[position]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });


        txtFrameSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select FrameSize")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(CustFrameSize, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtFrameSize.setText(CustFrameSize[position]);
                                strFrameSize= (CustFrameSize[position]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });


        txtPaperType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select PaperType")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(CustPaperType, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtPaperType.setText(CustPaperType[position]);
                                 strPaperType= (CustPaperType[position]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });
        btnBookOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArtistCustomizeOrderModel om = new ArtistCustomizeOrderModel
                        ("1","2",strArtworkType,strFrameSize,strPaperType,"Pending",
                                UtilityMethods.getDateAndTime());

                Map<String, Object> data= om.toMap();

                firebaseDB.collection("CustomOrderData")
                        .add(data)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ctx, "Data Added", Toast.LENGTH_SHORT).show();
                                String insertedId = documentReference.getId();
                                uploadImage(insertedId);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ctx, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                cv.put(MediaStore.Images.Media.TITLE, "Title");
                cv.put(MediaStore.Images.Media.DESCRIPTION, "Desc");

                filePath = ctx.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cv);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePath);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });


        txtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    selectImage();
                }
            }

            private void selectImage() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent, "Select Image from here..."), PICK_IMAGE_REQUEST);
            }
        });

    }
    private void uploadImage (String insId) {
        final ProgressDialog progressDialog = new ProgressDialog(ctx);
        progressDialog.setTitle("Uploading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StorageReference fileRef = rootReference.child(UUID.randomUUID().toString() + ".jpg");

        fileRef.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(ctx, "Upload Success..", Toast.LENGTH_LONG).show();
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();

                                Map<String, Object> upData = new HashMap<>();
                                upData.put("customPhoto", url);

                                firebaseDB.collection("CustomOrderData")
                                        .document(insId)
                                        .update(upData)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(ctx, "CustomOrderData", Toast.LENGTH_SHORT).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(ctx, "Data Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText(ctx, "Upload Fail..", Toast.LENGTH_LONG).show();
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
                                   @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null)
        {
            //Get the uri of data
            filePath = data.getData();
            try {
                //Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(ctx.getContentResolver(),
                                filePath);
                img.setImageBitmap(bitmap);
            } catch (IOException e) {
                //Log the exception
                e.printStackTrace();
            }
        }

        if(resultCode == RESULT_OK) {
            img.setImageURI(filePath);
        }

    }

}