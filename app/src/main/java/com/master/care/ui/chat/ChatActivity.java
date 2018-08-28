package com.master.care.ui.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.master.care.R;
import com.master.care.model.ChatMessage;
import com.master.care.ui.base.BaseActivity;
import com.master.care.utils.FloatingView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatActivity extends BaseActivity {

    public static void launchActivity(@NonNull Activity startingActivity, @NonNull String name) {
        Intent intent = new Intent(startingActivity, ChatActivity.class);
        intent.putExtra("name", name);
        startingActivity.startActivity(intent);
    }

    @BindView(R.id.attachment)
    ImageView imageViewAttachment;

    @BindView(R.id.listViewChat)
    ListView listViewChat;

    @BindView(R.id.chatEditText)
    EditText editTextChat;

    @BindView(R.id.buttonSend)
    ImageButton imageButtonSendMsg;

    @BindView(R.id.doctorName)
    TextView doctorNameTv;


    private List<ChatMessage> chatMessageList = new ArrayList<>();

    private ChatAdapter chatAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);
        initViews();
    }

    private void initViews() {

        doctorNameTv.setText(getIntent().getStringExtra("name"));
        imageButtonSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextChat.getText().toString().length() > 0) {
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setMsg(editTextChat.getText().toString());
                    editTextChat.setText("");
                    chatMessageList.add(chatMessage);
                    chatAdapter.notifyDataSetChanged();
                }
            }
        });

        imageViewAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        chatAdapter = new ChatAdapter(chatMessageList, this);
        listViewChat.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listViewChat.setAdapter(chatAdapter);

        chatAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                listViewChat.setSelection(chatAdapter.getCount() - 1);
            }
        });

    }

    private void openDialog() {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // inflate the custom popup layout
        final View inflatedView;

        inflatedView = layoutInflater.inflate(R.layout.custom_dialog_options_menu, null, false);

        LinearLayout layoutGallery;
        layoutGallery = inflatedView.findViewById(R.id.layoutGallery);
        layoutGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FloatingView.dismissWindow();
            }
        });

        FloatingView.onShowPopup(this, inflatedView);
    }
}
