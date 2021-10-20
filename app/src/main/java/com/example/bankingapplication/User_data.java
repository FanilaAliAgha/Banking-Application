package com.example.bankingapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingapplication.TransactionContract;
import com.example.bankingapplication.TransactionHelper;
import com.example.bankingapplication.R;

public class User_data extends AppCompatActivity {

    TextView name, email_id, accountNo, balance, mobile;
    Button money_transfer;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        name = findViewById(R.id.name);
        email_id = findViewById(R.id.email_id);
        accountNo = findViewById(R.id.accountNo);
        balance = findViewById(R.id.balance);
        mobile = findViewById(R.id.mobile);
        money_transfer = findViewById(R.id.money_transfer);

        // Getting the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        // Extracting the data
        if (extras != null){
            name.setText(extras.getString("NAME"));
            accountNo.setText(String.valueOf(extras.getInt("ACCOUNT_NO")));
            email_id.setText(extras.getString("EMAIL"));
            mobile.setText(extras.getString("PHONE_NO"));
            balance.setText(extras.getString("BALANCE"));
        }
        else {
            Log.d("TAG", "Empty Intent");
        }

        money_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterAmount();
            }
        });
    }

    private void enterAmount() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(User_data.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_box, null);
        mBuilder.setTitle("Enter Amount").setView(mView).setCancelable(false);

        final EditText mAmount = (EditText) mView.findViewById(R.id.enter_money);
        mBuilder.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) { }
        }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                transactionCancel();
            }
        });

        dialog = mBuilder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking whether amount entered is correct or not
                int currentBalance = Integer.parseInt(String.valueOf(balance.getText()));

                if (mAmount.getText().toString().isEmpty()) {
                    mAmount.setError("Amount can't be empty");
                } else if (Integer.parseInt(mAmount.getText().toString()) > currentBalance){
                    mAmount.setError("Your account don't have enough balance");
                } else {
                    Intent send = new Intent(User_data.this, SendToUserList.class);
                    send.putExtra("FROM_USER_ACCOUNT_NO", Integer.parseInt(accountNo.getText().toString()));    // PRIMARY_KEY
                    send.putExtra("FROM_USER_NAME", name.getText());
                    send.putExtra("FROM_USER_ACCOUNT_BALANCE", balance.getText());
                    send.putExtra("TRANSFER_AMOUNT", mAmount.getText().toString());
                    startActivity(send);
                    finish();
                }
            }
        });
    }

    private void transactionCancel() {
        AlertDialog.Builder builder_exitbutton = new AlertDialog.Builder(User_data.this);
        builder_exitbutton.setTitle("Do you want to cancel the transaction?").setCancelable(false)
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(User_data.this, "Transaction Cancelled!", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                enterAmount();
            }
        });
        AlertDialog alertexit = builder_exitbutton.create();
        alertexit.show();
    }
}