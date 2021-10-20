package com.example.bankingapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class home_screen extends AppCompatActivity {

    public androidx.cardview.widget.CardView card1, transcation_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
//castig
        card1 =(androidx.cardview.widget.CardView) findViewById(R.id.card1);
        transcation_card = (androidx.cardview.widget.CardView) findViewById(R.id.transcation_card);

    }

    //Intent
    public void showAllUsers(View view) {

        Intent intent = new Intent(home_screen.this, UsersList.class);
        startActivity(intent);
    }

    public void showAllTransactions(View view) {

        Intent abc = new Intent(home_screen.this,TransactionHistory.class);
        startActivity(abc);
    }

}