package com.example.zalegoadmin.lizsharedpreference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText nameET;
    TextView textview;
    Button submitBtn;
    Button clearBtn;
    LocalStorage storeRep;
    String nameSaved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storeRep=new LocalStorage(this);
        nameET=(EditText)findViewById(R.id.nameET);
        textview=(TextView)findViewById(R.id.textview);
        submitBtn=(Button)findViewById(R.id.submitBtn);
        clearBtn=(Button)findViewById(R.id.clearBtn);

         nameSaved =storeRep.getData();
        textview.setText(nameSaved);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameET.getText().toString();
                storeRep.storeData(name);

                 nameSaved =storeRep.getData();
                textview.setText(nameSaved);
            }
        });
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeRep.clearData();
                textview.setText("");
            }
        });
    }
}
