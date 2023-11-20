package com.zypher.shortnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SignUpActivity extends AppCompatActivity {
    private EditText email, password;
    EditText nameEditText;
    private Button signup;
    private TextView signin;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        auth = FirebaseAuth.getInstance();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String nameText = nameEditText.getText().toString();
                if (emailText.isEmpty() || emailText.trim().isEmpty() || passwordText.isEmpty() || passwordText.trim().isEmpty() || nameEditText.getText().toString().isEmpty() || nameEditText.getText().toString().trim().isEmpty()){
                    Toast.makeText(SignUpActivity.this, "Please enter name, email, and password", Toast.LENGTH_SHORT).show();
                } else if (nameText.length() <= 3) {
                    Toast.makeText(SignUpActivity.this, "Enter a name of at least 3 characters", Toast.LENGTH_SHORT).show();
                } else {
                    auth.createUserWithEmailAndPassword(emailText, passwordText)
                            .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // User creation successful, proceed with additional steps
                                        addUserToDatabase(nameText, emailText, auth.getCurrentUser().getUid());
                                        Log.d("SignUpActivity", "createUserWithEmail: Success");
                                        Toast.makeText(SignUpActivity.this, "Successfully created user", Toast.LENGTH_SHORT).show();

                                        // Navigate to the MainActivity
                                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                        finish();
                                        startActivity(intent);
                                    } else {
                                        // If user creation fails, display a message to the user.
                                        Log.w("SignUpActivity", "createUserWithEmail: Failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
    private void addUserToDatabase(String name, String email, String uid) {
        // Use the class-level dbRef variable
        dbRef.child("user").child(uid).setValue(new User(name, email, uid));
    }

}