package com.noelchew.ncutils.demo;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.noelchew.ncutils.crypto.AESUtil;

import java.security.GeneralSecurityException;

/**
 * Created by noelchew on 04/11/2016.
 */

public class EncryptionActivity extends AppCompatActivity {

    private static final String TAG = "EncryptionActivity";

    Context context;

    TextInputEditText etPassword, etEncryption, etDecryption;
    Button btnEncrypt, btnDecrypt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_encryption);
        context = this;
        setView();
    }

    private void setView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Encryption");
        setSupportActionBar(toolbar);

        etPassword = (TextInputEditText) findViewById(R.id.et_password);
        etEncryption = (TextInputEditText) findViewById(R.id.et_encrypt);
        etDecryption = (TextInputEditText) findViewById(R.id.et_decrypt);
        btnEncrypt = (Button) findViewById(R.id.btn_encrypt);
        btnEncrypt.setOnClickListener(btnEncryptOnClickListener);
        btnDecrypt = (Button) findViewById(R.id.btn_decrypt);
        btnDecrypt.setOnClickListener(btnDecryptOnClickListener);
    }

    private View.OnClickListener btnEncryptOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String encryptionText = etEncryption.getText().toString().trim();

            try {
                etDecryption.setText(AESUtil.encryptWithAES(etPassword.getText().toString().trim(), encryptionText));
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            }
        }
    };

    private View.OnClickListener btnDecryptOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String decryptionText = etDecryption.getText().toString().trim();
            try {
                etEncryption.setText(AESUtil.decryptWithAES(etPassword.getText().toString().trim(), decryptionText));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
