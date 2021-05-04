package com.streamliners.inputconstraints;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.streamliners.inputconstraints.databinding.ActivityInputConstraintsBinding;

public class InputConstraints extends AppCompatActivity {

    // request code of data transfer
    private static final int REQUEST_INPUT = 0;
    ActivityInputConstraintsBinding bind;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityInputConstraintsBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        // set title of the activity
        setTitle("InputConstraints Activity");

        sendConstraints();
    }

    private void sendConstraints() {
        bind.btnInput.setOnClickListener(v -> {
            inputConstraints();
            if(bundle.isEmpty()){
                Toast.makeText(InputConstraints.this, "Select constraints", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(InputConstraints.this, MainActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, REQUEST_INPUT);
        });
    }
    private void inputConstraints(){
        bundle = new Bundle();
        if(bind.uppercaseCheckBox.isChecked()){
            bundle.putString(Constants.UPPERCASE_ALPHABETS, "true");
        }
        if(bind.lowercaseCheckBox.isChecked()){
            bundle.putString(Constants.LOWERCASE_ALPHABETS, "true");
        }
        if(bind.digitsCheckBox.isChecked()){
            bundle.putString(Constants.DIGITS, "true");
        }
        if(bind.operationsCheckBox.isChecked()){
            bundle.putString(Constants.MATHEMATICAL_OPERATORS, "true");
        }
        if(bind.symbolsCheckBox.isChecked()){
            bundle.putString(Constants.OTHER_SYMBOLS, "true");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Check result
        if(requestCode == REQUEST_INPUT && resultCode == RESULT_OK){
            bind.textView.setText("Result is : " + data.getStringExtra(Constants.INPUT_DATA));
            bind.textView.setVisibility(View.VISIBLE);

        }

    }
}