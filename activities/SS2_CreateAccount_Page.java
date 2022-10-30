package com.ass2.f190260_i190468.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ass2.f190260_i190468.R;
import com.ass2.f190260_i190468.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ass2.f190260_i190468.MemoryData;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class SS2_CreateAccount_Page extends AppCompatActivity {
    EditText name, email, ph_no, pass;
    Button signup, signin;
    CheckBox cb;
    ImageView male, female, custom;
    CircleImageView dp;
    TextView tv, show;
    String Name, Email, Phone_Number, Password, Gender, ImageUrl;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Uri Actual_Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ss2_create_account_page);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        ph_no = findViewById(R.id.ph_no);
        pass = findViewById(R.id.pass);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        custom = findViewById(R.id.custom);
        cb = findViewById(R.id.cb);
        signin = findViewById(R.id.signin);
        signup = findViewById(R.id.signup);
        dp = findViewById(R.id.dp);
        tv = findViewById(R.id.tv);
//        show = findViewById(R.id.show);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading ...");


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

//        if (!MemoryData.getName(SS2_CreateAccount_Page.this).isEmpty()) {
//            startActivity(new Intent(SS2_CreateAccount_Page.this, UsersActivity.class));
//           finish();
//        }
//        name.setText("name");
//        email.setText("fake_name@gmail.com");
//        pass.setText("123456789");
//        ph_no.setText("00000000000");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                Name = name.getText().toString();
                Email = email.getText().toString();
                Password = pass.getText().toString();
                Phone_Number = ph_no.getText().toString();
                if (!Name.isEmpty() && !Email.isEmpty() && !Phone_Number.isEmpty() && !Password.isEmpty() && cb.isChecked()) {
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(Phone_Number)) {
                                Toast.makeText(SS2_CreateAccount_Page.this, "Account Already Exits", Toast.LENGTH_SHORT).show();
                            } else {
                                mAuth.createUserWithEmailAndPassword(
                                                email.getText().toString(),
                                                pass.getText().toString()
                                        )
                                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                            @Override
                                            public void onSuccess(AuthResult authResult) {
                                                Toast.makeText(SS2_CreateAccount_Page.this, "[Auth] : Successfully Created Account", Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(
                                                        SS2_CreateAccount_Page.this,
                                                        "[Auth] : Failed",
                                                        Toast.LENGTH_LONG
                                                ).show();
                                            }
                                        });

                                Calendar c = Calendar.getInstance();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                StorageReference ref = storage.getReference().child("Profile Pictures/" + Phone_Number + "/" + c.getTimeInMillis() + ".jpg");
                                ref.putFile(Actual_Image)
                                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                            @Override
                                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                Toast.makeText(SS2_CreateAccount_Page.this, "PFP Uploaded", Toast.LENGTH_LONG).show();
                                                Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                                                task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        Picasso.get().load(uri.toString()).into(dp);
                                                        ImageUrl = uri.toString();
                                                        int i=0;
                                                        while(ImageUrl==null){
                                                            i++;
                                                        }
                                                        Users user = new Users(Name, Email, Phone_Number, Password, Gender, ImageUrl);
                                                        Toast.makeText(SS2_CreateAccount_Page.this, "ImageUrl isssss:"+ImageUrl, Toast.LENGTH_SHORT).show();
                                                        myRef.child(Phone_Number).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                name.setText("");
                                                                email.setText("");
                                                                pass.setText("");
                                                                ph_no.setText("");
                                                                cb.setChecked(false);
                                                                Toast.makeText(SS2_CreateAccount_Page.this, "[Real Db] : Successfully Created Account", Toast.LENGTH_LONG).show();
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(SS2_CreateAccount_Page.this, "Failed To Upload PFP", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                MemoryData.saveName(Name, SS2_CreateAccount_Page.this);
                                MemoryData.saveEmail(Email, SS2_CreateAccount_Page.this);
                                MemoryData.savePhoneNumber(Phone_Number, SS2_CreateAccount_Page.this);
                                MemoryData.savePassword(Password, SS2_CreateAccount_Page.this);
                                MemoryData.saveGender(Gender, SS2_CreateAccount_Page.this);
                                MemoryData.savePicUrl("https://firebasestorage.googleapis.com/v0/b/f190260-i190468-5d10c.appspot.com/o/Profile%20Pictures%2F03039717109%2F1666556472061.jpg?alt=media&token=dbb9148c-a6bc-48e9-bc4d-ff77e249185b", SS2_CreateAccount_Page.this);
                                progressDialog.dismiss();
                                startActivity(new Intent(SS2_CreateAccount_Page.this, UsersActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                } else {
                    Toast.makeText(SS2_CreateAccount_Page.this, "Please Enter all Details", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }
            }

        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SS2_CreateAccount_Page.this, SS3_SignIn_Page.class));
            }
        });
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gender = "Male";
            }
        });
        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gender = "Female";
            }
        });
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gender = "Custom";
            }
        });
//        show.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (pass.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
//                    //Show Password
//                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//                } else {
//                    //Hide Password
//                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//                }
//            }
//        });
        dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, 20);
//                Toast.makeText(SS2_CreateAccount_Page.this, "DP CLICKED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 & resultCode == RESULT_OK) {
            Actual_Image = data.getData();
            dp.setImageURI(Actual_Image);
        }
    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mAuth.getCurrentUser() != null) {
//            //mAuth.getCurrentUser().getEmail()
//            startActivity(new Intent(SS2_CreateAccount_Page.this, SS21_Liked_Page.class));
//        }
//    }
}