package sjcet.displayintextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void display(View view) {
        EditText ed=(EditText)findViewById(R.id.editid);
        TextView txt=(TextView)findViewById(R.id.dispid);
        String s=ed.getText().toString();
        txt.setText(s);
        Toast.makeText(this, "dispaly", Toast.LENGTH_SHORT).show();
    }

    public void delete(View view) {
        TextView t=(TextView)findViewById(R.id.dispid);
        t.setText("");
        Toast.makeText(this, "clears", Toast.LENGTH_SHORT).show();

    }
}
