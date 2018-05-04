package com.example.aggel.mypatternlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.aggel.mypatternlock.pattern.PatternView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText textBox;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = (EditText) findViewById(R.id.textBox);
        Log.e("username:",this.textBox.getText().toString());
    }


    public void startApp(View view) throws IOException {
        Intent c = new Intent(this, PatternView.class);

        if (!this.textBox.getText().toString().equals("username")) {
            //toast pleas enter username
        } else {

        }
        Log.e("username",this.textBox.getText().toString());
        user = new User(this.textBox.getText().toString());
        startActivity(c);
    }

    public void box(View view) {
        this.textBox.setText(" ");
    }
}
