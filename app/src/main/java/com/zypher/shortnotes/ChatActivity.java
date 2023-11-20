package com.zypher.shortnotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// main
public class ChatActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ArrayList<User> userList;
    private UserAdapter adapter;
    private FirebaseAuth auth;
    private DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recycler = findViewById(R.id.recycler);
        userList = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference();
        adapter = new UserAdapter(ChatActivity.this, userList);

        recycler.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
        recycler.setAdapter(adapter);

        dbRef.child("user").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    User currentUser = postSnapshot.getValue(User.class);

                    if (auth.getCurrentUser() != null && !auth.getCurrentUser().getUid().equals(currentUser.getUid())) {
                        userList.add(currentUser);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.chat) {
            Toast.makeText(this, "Chat", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.status) {
            Toast.makeText(this, "Status", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.call) {
            Toast.makeText(this, "Call", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.logout) {
            auth.signOut();
            Intent intent = new Intent(ChatActivity.this, SigninActivity.class);
            finish();
            startActivity(intent);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}