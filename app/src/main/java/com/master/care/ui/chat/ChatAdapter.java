package com.master.care.ui.chat;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.master.care.R;
import com.master.care.model.ChatMessage;

import java.util.List;

class ChatAdapter extends BaseAdapter {

    private List<ChatMessage> chatMessageArrayList;
    private Context context;


    public ChatAdapter(List<ChatMessage> chatMessageArrayList, Context context) {
        this.chatMessageArrayList = chatMessageArrayList;
        this.context = context;

    }


    @Override
    public int getCount() {
        if (chatMessageArrayList != null) {
            return chatMessageArrayList.size();
        } else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        if (chatMessageArrayList != null) {
            return chatMessageArrayList.get(position);
        } else
            return null;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        ChatMessage chatMessage = chatMessageArrayList.get(position);
        ViewHolder holder;

        if (convertView == null) {
            v = LayoutInflater.from(context).inflate(R.layout.item_own_chat_row, null, false);
            holder = new ViewHolder();


            holder.messageTextView =  v.findViewById(R.id.myChatRowTV);
            holder.messageTextView.setLongClickable(false);
            v.setTag(holder);
        } else {
            v = convertView;
            holder = (ViewHolder) v.getTag();

        }

        holder.messageTextView.setText(String.valueOf("  "+chatMessage.getMsg()+"  "));

        return v;
    }

    private static class ViewHolder {
        public TextView messageTextView;

    }
}