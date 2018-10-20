package com.example.aggel.mypatternlock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.aggel.mypatternlock.pattern.PatternView;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText textBox, textBox2, textBox3;
    private static User user;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox =  findViewById(R.id.textBox);
        textBox2 = findViewById(R.id.textBox2);
        textBox3 =  findViewById(R.id.textBox3);

    }

    //onClick του κουμπιου
    public void startApp(View view) throws IOException {
        Intent c = new Intent(this, PatternView.class);

        if (this.textBox.getText().toString().equals("username") || this.textBox3.getText().toString().equals("finger") || this.textBox2.getText().toString().equals("hand")) {

            Toast.makeText(this, "Please fill the textboxes", Toast.LENGTH_SHORT).show();

        } else {

            Log.e("username", this.textBox.getText().toString());

            user = new User(this.textBox.getText().toString(), Integer.parseInt(this.textBox2.getText().toString()), Integer.parseInt(this.textBox3.getText().toString()));

            startActivity(c);
        }

    }

    public static User getUser() {
        return user;
    }

    public void box(View view) {
        this.textBox.setText("");
    }

    public void box3(View view) {
        this.textBox3.setText("");
    }

    public void box2(View view) {
        this.textBox2.setText("");
    }


}
