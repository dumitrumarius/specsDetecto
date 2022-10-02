package com.example.specsdetecto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText pwdField;
    EditText pwdCheckField;
    Button btnSetPwd;
    TextView textNoMatchPwd;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("com.example.specsdetecto", MODE_PRIVATE);
    }

    @Override
    protected void onStart(){
        super.onStart();
        /*
            Get SharedPreferences to check if this is the first time the app was opened
         */
        if (sharedPreferences.getBoolean("isFirstUse", false)) {
            Intent intent = new Intent(MainActivity.this, SpecsActivity.class);
            startActivity(intent);
            finish();
        } else {
            pwdField = findViewById(R.id.pwdField);
            pwdCheckField = findViewById(R.id.pwdCheckField);
            btnSetPwd = findViewById(R.id.savePwdButton);
            textNoMatchPwd = findViewById(R.id.textNoPatchPwd);

            btnSetPwd.setOnClickListener(view -> {
                if (String.valueOf(pwdField.getText()).isEmpty()){
                    textNoMatchPwd.setVisibility(View.VISIBLE);
                    textNoMatchPwd.setText(R.string.textPwdEmpty);
                } else {
                    if (pwdCheckField.getText().toString().equals(pwdField.getText().toString())){
                        Intent intent = new Intent(MainActivity.this, SpecsActivity.class);
                        sharedPreferences.edit().putString("password", String.valueOf(pwdField.getText())).apply();
                        startActivity(intent);
                    } else {
                        textNoMatchPwd.setText(R.string.textNoMatchPwd);
                        textNoMatchPwd.setVisibility(View.VISIBLE);
                        pwdField.setText("");
                        pwdCheckField.setText("");
                    }
                }
            });
            sharedPreferences.edit().putBoolean("isFirstUse", true).apply();
        }
    }
}