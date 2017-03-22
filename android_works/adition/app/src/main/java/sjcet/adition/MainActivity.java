package sjcet.adition;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void add(View view) {
        EditText t1=(EditText)findViewById(R.id.etid1);
        EditText t2=(EditText)findViewById(R.id.etid2);
        TextView tv1=(TextView)findViewById(R.id.tvid);
        String a=t1.getText().toString();
        String b=t2.getText().toString();
        int a1,b1,sum;
        a1=Integer.parseInt(a);
        b1=Integer.parseInt(b);
sum=a1+b1;
        tv1.setText(String.valueOf(sum));


    }

    public void clear(View view) {
        EditText t1=(EditText)findViewById(R.id.etid1);
        EditText t2=(EditText)findViewById(R.id.etid2);
        TextView tv1=(TextView)findViewById(R.id.tvid);
        t1.setText("");
        t2.setText("");
        tv1.setText("");
    }
}
