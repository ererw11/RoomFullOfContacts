package com.android.emery.roomfullofcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class NewContactActivity extends AppCompatActivity {

    public static final String EXTRA_SUBMIT_FIRST =
            "com.android.emery.roomfullofcontacts.First";
    public static final String EXTRA_SUBMIT_LAST =
            "com.android.emery.roomfullofcontacts.Last";
    public static final String EXTRA_SUBMIT_PHONE =
            "com.android.emery.roomfullofcontacts.Phone";
    public static final String EXTRA_SUBMIT_EMAIL =
            "com.android.emery.roomfullofcontacts.Email";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        firstNameEditText = findViewById(R.id.add_first_name);
        lastNameEditText = findViewById(R.id.add_last_name);
        phoneEditText = findViewById(R.id.add_phone_number);
        emailEditText = findViewById(R.id.add_email_address);

        final Button saveButton = findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitIntent = new Intent();
                // Only first and last name is required
                if (TextUtils.isEmpty(firstNameEditText.getText()) ||
                        TextUtils.isEmpty(lastNameEditText.getText())) {
                    setResult(RESULT_CANCELED, submitIntent);
                } else {
                    String contactFirstName = firstNameEditText.getText().toString();
                    String contactLastName = lastNameEditText.getText().toString();
                    String contactPhone = PhoneNumberUtils.formatNumber(phoneEditText.getText().toString());
                    String contactEmail = emailEditText.getText().toString();

                    submitIntent.putExtra(EXTRA_SUBMIT_FIRST, contactFirstName);
                    submitIntent.putExtra(EXTRA_SUBMIT_LAST, contactLastName);
                    submitIntent.putExtra(EXTRA_SUBMIT_PHONE, contactPhone);
                    submitIntent.putExtra(EXTRA_SUBMIT_EMAIL, contactEmail);

                    Log.i("Contact First", contactFirstName);
                    Log.i("Contact Laat", contactLastName);
                    Log.i("Contact Phone", contactPhone);
                    Log.i("Contact Email", contactEmail);

                    setResult(RESULT_OK, submitIntent);
                }
                finish();
            }
        });
    }
}
