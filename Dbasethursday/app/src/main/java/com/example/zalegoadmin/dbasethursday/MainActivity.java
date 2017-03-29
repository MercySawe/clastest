package com.example.zalegoadmin.dbasethursday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nameET;
    Button submitBtn, getDataBtn;
    TextView tv;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.
                layout.activity_main);
        nameET=(EditText)findViewById(R.id.nameET);
        tv=(TextView)findViewById(R.id.Text);
        lv = (ListView)findViewById(R.id.list);
        submitBtn=(Button)findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mercy","--start--");
                String name=nameET.getText().toString();
                String url="http://192.168.43.153/androidremotedb/senddata.php";
                new SendData(name,url).execute();

            }
        });
        getDataBtn =(Button) findViewById(R.id.getdataBtn);
        getDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mercy","--start--");
                new GetData("http://192.168.43.153/androidremotedb/getdata2.php", new UrlCallback() {
                    @Override
                    public void done(String result) {
                        if(result==null){
                            tv.setText("no data found");
                        }else {
                           String[] dataFromDb = result.split(",", -1);
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                                    android.R.layout.simple_list_item_1,
                                    dataFromDb);
                            lv.setAdapter(adapter);

                        }
                    }
                }).execute();
            }
        });
    }
}
/*ipconfig*/