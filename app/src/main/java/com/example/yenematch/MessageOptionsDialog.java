package com.example.yenematch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class MessageOptionsDialog extends BottomSheetDialogFragment {

    private String userName;

    // We pass the name so the dialog knows who we are deleting
    public MessageOptionsDialog(String userName) {
        this.userName = userName;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Create a simple layout for the pop-up
        View v = inflater.inflate(R.layout.fragment_message_options_dialog, container, false);

        TextView tvTitle = v.findViewById(R.id.tvOptionTitle);
        TextView btnUnmatch = v.findViewById(R.id.btnOptionUnmatch);
        TextView btnDelete = v.findViewById(R.id.btnOptionDelete);

        tvTitle.setText("Options for " + userName);

        btnUnmatch.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Unmatched with " + userName, Toast.LENGTH_SHORT).show();
            dismiss();
        });

        btnDelete.setOnClickListener(view -> {
            Toast.makeText(getContext(), "Chat deleted", Toast.LENGTH_SHORT).show();
            dismiss();
        });

        return v;
    }
}