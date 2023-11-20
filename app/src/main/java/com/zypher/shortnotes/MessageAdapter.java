package com.zypher.shortnotes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int RECEIVE = 1;
    private static final int SENT = 2;

    private final Context context;
    private final ArrayList<Message> messageList;

    public MessageAdapter(Context context, ArrayList<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == RECEIVE) {
            View view = LayoutInflater.from(context).inflate(R.layout.receive, parent, false);
            return new ReceiveViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.sent, parent, false);
            return new SentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message currentMessage = messageList.get(position);

        if (holder instanceof SentViewHolder) {
            SentViewHolder sentHolder = (SentViewHolder) holder;
            sentHolder.sentMessage.setText(currentMessage.getMessage());
        } else if (holder instanceof ReceiveViewHolder) {
            ReceiveViewHolder receiveHolder = (ReceiveViewHolder) holder;
            receiveHolder.receiveMessage.setText(currentMessage.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message currentMessage = messageList.get(position);

        if (FirebaseAuth.getInstance().getCurrentUser() != null &&
                FirebaseAuth.getInstance().getCurrentUser().getUid().equals(currentMessage.getSenderId())) {
            return SENT;
        } else {
            return RECEIVE;
        }
    }

    static class ReceiveViewHolder extends RecyclerView.ViewHolder {
        TextView receiveMessage;

        ReceiveViewHolder(View view) {
            super(view);
            receiveMessage = view.findViewById(R.id.receive);
        }
    }

    static class SentViewHolder extends RecyclerView.ViewHolder {
        TextView sentMessage;

        SentViewHolder(View view) {
            super(view);
            sentMessage = view.findViewById(R.id.sent);
        }
    }
}