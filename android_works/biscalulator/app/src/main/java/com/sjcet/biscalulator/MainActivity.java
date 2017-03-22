package com.sjcet.biscalulator;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1=(Button)findViewById(R.id.oneid);
        Button b2=(Button)findViewById(R.id.twoid);
        Button b3=(Button)findViewById(R.id.threeid);
        Button  b4=(Button)findViewById(R.id.fourid);
        Button b5=(Button)findViewById(R.id.fiveid);
        Button b6=(Button)findViewById(R.id.sixid);
        Button b7=(Button)findViewById(R.id.sevenid);
        Button b8=(Button)findViewById(R.id.eightid);
        Button b9=(Button)findViewById(R.id.nineid);
        Button b0=(Button)findViewById(R.id.zeroid);
       b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        b0.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String s;
        int n;
        TextView tv=(TextView)findViewById(R.id.dispid);

        switch(v.getId())
        {
            case R.id.zeroid:
             n=0;
                tv.setText(String.valueOf(n));
                break;
            case R.id.twoid:
                n=2;
                tv.setText(String.valueOf(n));
                break;
            case R.id.threeid:
                n=3;
                tv.setText(String.valueOf(n));
                break;
            case R.id.fourid:
                n=4;
                tv.setText(String.valueOf(n));
                break;
            case R.id.fiveid:
                n=5;
                tv.setText(String.valueOf(n));
                break;
            case R.id.sixid:
                n=6;
                tv.setText(String.valueOf(n));
            case R.id.sevenid:
                n=7;
                tv.setText(String.valueOf(n));
                break;
            case R.id.eightid:
                n=8;
                tv.setText(String.valueOf(n));
                break;
            case R.id.nineid:
                n=9;
                tv.setText(String.valueOf(n));
                break;
            case R.id.oneid:
                n=1;
                tv.setText(String.valueOf(n));
                break;

    }
}
