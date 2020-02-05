package com.noelchew.ncutils.demo;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.noelchew.ncutils.alert.ToastUtil;
import com.noelchew.ncutils.demo.model.DummyObject;

/**
 * Created by noelchew on 04/11/2016.
 */

public class AddDataActivity extends AppCompatActivity {

    private static final String TAG = "AddDataActivity";

    Context context;

    TextInputEditText etTitle, etDescription;
    Button btnSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_data);
        context = this;
        setView();
    }

    private void setView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Add Data");
        setSupportActionBar(toolbar);

        etTitle = (TextInputEditText) findViewById(R.id.et_title);
        etDescription = (TextInputEditText) findViewById(R.id.et_description);
        btnSave = (Button) findViewById(R.id.btn_ok);
        btnSave.setOnClickListener(btnSaveOnClickListener);
    }

    private View.OnClickListener btnSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = etTitle.getText().toString().trim();
            String description = etDescription.getText().toString().trim();
            if (TextUtils.isEmpty(title)) {
                ToastUtil.toastShortMessage(context, "Invalid title.");
                return;
            }
            if (TextUtils.isEmpty(description)) {
                ToastUtil.toastShortMessage(context, "Invalid description.");
                return;
            }

            DummyObject dummyObject = new DummyObject(title, description);
            if (!dummyObject.save()) {
                ToastUtil.toastShortMessage(context, "Error saving data!");
            } else {
                finish();
            }
        }
    };

}
