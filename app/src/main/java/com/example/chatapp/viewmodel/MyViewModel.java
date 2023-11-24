package com.example.chatapp.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.chatapp.model.ChatGroup;
import com.example.chatapp.model.ChatMessage;
import com.example.chatapp.repository.Repository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    Repository repository;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    //Auth
    public  void signUpAnonymousUser(){
        Context c = this.getApplication();
        repository.firebaseAnonymousAuth(c);
    }
    public  String getCurrentUserId(){
        return repository.getCurrentUserId();
    }

    public  void signOut(){
        repository.signOut();
    }

    //Getting Chat Groups
    public MutableLiveData<List<ChatGroup>> getGroupList(){
        return repository.getChatGroupmutableLiveData();
    }

    public  void createNewGroup(String groupName){
        repository.createNewChatGroup(groupName);
    }

    //Messages
    public MutableLiveData<List<ChatMessage>> getMessagesLiveData(String groupName){
        return repository.getMessageLiveData(groupName);
    }

    public  void sendMessage(String msg, String chatGroup){
        repository.sendMessage(msg, chatGroup);
    }
}
