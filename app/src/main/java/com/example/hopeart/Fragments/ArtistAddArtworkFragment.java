package com.example.hopeart.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.hopeart.DataModel.ArtistArtWorkModel;
import com.example.hopeart.DataModel.UserProfileModel;
import com.example.hopeart.R;
import com.example.hopeart.Utility.SharedPreferenceManger;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

public class ArtistAddArtworkFragment extends Fragment {

    Context ctx;
    String strArtworkType, strArtWorkFrameSize, strArtWorkPaperType,strArtWorkImage;

    String[] ArtWorkType = {"Sketching", "Painting"};
    String[] ArtworkFrameSize = {"4*6", "5*7", "6*8", "5*8", "8*8"};
    String[] ArtworkPaperType = {"Matt Coated", "Silk Coated", "Watermarked", "Kraft Paper"};

    Boolean artistApproval;
    private Uri filePath;

    int PICK_IMAGE_REQUEST = 10;


    ImageView imgShow;
    TextView txtArtworkType, txtArtworkFramesize, txtArtworkPaperType;
    EditText edtArtworkPrice;
    Button btnSave;

    StorageReference rootReference;
    FirebaseFirestore firebaseDB;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        boolean attachToRoot;
        return inflater.inflate(R.layout.artist_fragment_addartwork, container, attachToRoot = false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseDB = FirebaseFirestore.getInstance();
        rootReference= FirebaseStorage.getInstance().getReference();

        txtArtworkType = view.findViewById(R.id.txtArtWorkType);
        txtArtworkFramesize = view.findViewById(R.id.txtFrameSize);
        txtArtworkPaperType = view.findViewById(R.id.txtPaperType);
        edtArtworkPrice = view.findViewById(R.id.edtPrice);

        imgShow = view.findViewById(R.id.artwork_img);
        btnSave = view.findViewById(R.id.btnSave);

        String id= SharedPreferenceManger.getUserId(ctx);
        firebaseDB.collection("UsersProfile")
                .document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        UserProfileModel artist=documentSnapshot.toObject(UserProfileModel.class);
                        artistApproval=artist.getApproved();
                    }
                });


        txtArtworkType.setOnClickListener(new View.OnClickListener() {
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
                        .setSingleChoiceItems(ArtWorkType, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtArtworkType.setText(ArtWorkType[position]);
                                strArtworkType = ArtWorkType[position];
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });

        txtArtworkFramesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select ArtworkFrameSize")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(ArtworkFrameSize, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtArtworkFramesize.setText(ArtworkFrameSize[position]);
                                strArtWorkFrameSize = (ArtworkFrameSize[position]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });

        txtArtworkPaperType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Select ArtworkPaperType")
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setSingleChoiceItems(ArtworkPaperType, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int position) {
                                txtArtworkPaperType.setText(ArtworkPaperType[position]);
                                strArtWorkPaperType = (ArtworkPaperType[position]);
                                dialogInterface.dismiss();
                            }
                        })
                        .setIcon(R.drawable.ic_launcher_background)
                        .setCancelable(false);
                builder.create().show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (artistApproval) {
                Float artWorkPrice = Float.parseFloat(edtArtworkPrice.getText().toString());

                ArtistArtWorkModel st = new ArtistArtWorkModel(
                        strArtworkType, strArtWorkFrameSize,
                        strArtWorkPaperType, artWorkPrice, strArtWorkImage);

                Map<String, Object> data = st.toMap();

                firebaseDB.collection("ArtWorkData")
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
                }else {
                    Toast.makeText(ctx,"You Are Not Approved By Admin",Toast.LENGTH_LONG).show();
                }
            }
        });

        imgShow.setOnClickListener(new View.OnClickListener() {
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
                                            String url=uri.toString();

                                            Map<String,Object> upData=new HashMap<>();
                                            upData.put("strArtWorkImage",url);

                                            firebaseDB.collection("ArtWorkData")
                                                    .document(insId)
                                                    .update(upData)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(ctx, "Data Updated ", Toast.LENGTH_SHORT).show();
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
                            && data.getData() != null) {
                        //Get the uri of data
                        filePath = data.getData();
                        try {
                            //Setting image on image view using Bitmap
                            Bitmap bitmap = MediaStore
                                    .Images
                                    .Media
                                    .getBitmap(ctx.getContentResolver(),
                                            filePath);
                            imgShow.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            //Log the exception
                            e.printStackTrace();
                        }

                    }

                }
            }