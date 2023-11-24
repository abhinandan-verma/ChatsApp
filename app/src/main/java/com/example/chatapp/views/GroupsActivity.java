package com.example.chatapp.views;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.R;
import com.example.chatapp.databinding.ActivityGroupBinding;
import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.viewmodel.MyViewModel;
import com.example.chatapp.views.adapter.GroupAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    private ArrayList<ChatGroup> chatGroupArrayList;

    private RecyclerView recyclerView;
    private GroupAdapter groupAdapter;
    private ActivityGroupBinding binding;
    private MyViewModel myViewModel;
    private FloatingActionButton fab;

    // Dialog
    private Dialog chatGroupDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_group);

        //Define the viewModel
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //RecyclerView with the DataBinding
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Setup an observer to listen for changes in a "Live data" object
        myViewModel.getGroupList().observe(this, new Observer<List<ChatGroup>>() {
            @Override
            public void onChanged(List<ChatGroup> chatGroups) {
                // The updated data is received as "chatGroups" parameter in the onChanged() method

                chatGroupArrayList = new ArrayList<>();
                chatGroupArrayList.addAll(chatGroups);

                groupAdapter = new GroupAdapter(chatGroupArrayList);

                recyclerView.setAdapter(groupAdapter);
                groupAdapter.notifyDataSetChanged();
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    public  void showDialog(){
       chatGroupDialog = new Dialog(this);
       chatGroupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

       View view = LayoutInflater.from(this)
               .inflate(R.layout.dialog_layout,null);

       chatGroupDialog.setContentView(view);
       chatGroupDialog.show();

        Button submit = view.findViewById(R.id.dialog_btn);
        EditText edit = view.findViewById(R.id.dialog_edit);

       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String groupName = edit.getText().toString();

               Toast.makeText(GroupsActivity.this, "ChatGroup Name: "+groupName, Toast.LENGTH_SHORT).show();
                recyclerView.setAdapter(groupAdapter);
               chatGroupDialog.dismiss();

               myViewModel.createNewGroup(groupName);
           }
       });

    }
}
