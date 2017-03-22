package sjcet.layoutorientation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toast(View view) {
        Button b=(Button)findViewById(R.id.button);

        Toast t =Toast.makeText(getApplicationContext(),"haii",Toast.LENGTH_LONG).show();
    }
}
