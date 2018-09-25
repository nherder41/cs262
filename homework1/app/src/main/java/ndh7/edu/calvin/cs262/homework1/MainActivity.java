package ndh7.edu.calvin.cs262.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText input1EditText;
    private EditText input2EditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1EditText = (EditText) findViewById(R.id.input1);
        input2EditText = (EditText) findViewById(R.id.input2);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    public void calculate(View view) {
        Editable input1 = input1EditText.getText();
        Editable input2 = input2EditText.getText();
    }
}
