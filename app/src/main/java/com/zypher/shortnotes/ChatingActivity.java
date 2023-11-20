package com.zypher.shortnotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Chat
public class ChatingActivity extends AppCompatActivity {
    private RecyclerView recycler;
    private ArrayList<Message> messageList;
    private MessageAdapter adapter;
    private EditText boxMassage;
    private ImageView sentMessage;
    private DatabaseReference dbRef;

    private String receiveRoom = null;
    private String sentRoom = null;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);

        recycler = findViewById(R.id.chatRecycler);
        messageList = new ArrayList<>();
        adapter = new MessageAdapter(ChatingActivity.this, messageList);
        boxMassage = findViewById(R.id.message);
        sentMessage = findViewById(R.id.send);
        dbRef = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String receiveUid = intent.getStringExtra("uid");
        String sentUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        receiveRoom = sentUid + receiveUid;
        sentRoom = receiveUid + sentUid;

        getSupportActionBar().setTitle(name);

        recycler.setLayoutManager(new LinearLayoutManager(ChatingActivity.this));
        recycler.setAdapter(adapter);

        dbRef.child("chats").child(sentRoom)
                .child("messages").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messageList.clear();

                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Message message = postSnapshot.getValue(Message.class);
                            messageList.add(message);
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle onCancelled
                    }
                });

        sentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boxMassage.getText().toString().isEmpty() || boxMassage.getText().toString().trim().isEmpty()) {
                    sentMessage.setImageResource(R.drawable.mic);
                    Toast.makeText(ChatingActivity.this, "Mic Access", Toast.LENGTH_SHORT).show();
                } else {
                    sentMessage.setImageResource(R.drawable.send);
                    String messageText = boxMassage.getText().toString();
                    Message messageObject = new Message(messageText, sentUid);

                    dbRef.child("chats").child(sentRoom).child("messages").push().setValue(messageObject)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dbRef.child("chats").child(receiveRoom).child("messages").push().setValue(messageObject);
                                }
                            });

                    boxMassage.setText("");
                }
            }
        });


    }
}