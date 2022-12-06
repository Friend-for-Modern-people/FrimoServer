package com.gachon.frimo.service;

import org.springframework.stereotype.Service;

import com.gachon.frimo.domain.chatting.Chatting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChattingService {
    FirebaseAuth defaultAuth = FirebaseAuth.getInstance();
    FirebaseDatabase defaultDatabase = FirebaseDatabase.getInstance();
    // private DatabaseReference dbRef= database.getReference("namseunghyeon");

    public void getChattingByDate() {

    }
}