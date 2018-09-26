package ndh7.edu.calvin.cs262.homework1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

//Custom error exception to handle divide by zero errors
class DivideByZeroException extends Exception
{
    public DivideByZeroException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}

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
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioAddition);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioSubtraction);
        RadioButton rb3 = (RadioButton) findViewById(R.id.radioMultiplication);
        RadioButton rb4 = (RadioButton) findViewById(R.id.radioDivision);
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);
        String input1 = input1EditText.getText().toString();
        String input2 = input2EditText.getText().toString();
        Integer calculation = null;

        //if no radio buttons are clicked, then don't do anything
        if (rGroup.getCheckedRadioButtonId() != -1) {
            resultTextView.setVisibility(View.VISIBLE);
            try {
                if (rb1.isChecked()) {
                    calculation = Integer.parseInt(input1) + Integer.parseInt(input2);
                    resultTextView.setText(calculation.toString());
                } else if (rb2.isChecked()) {
                    calculation = Integer.parseInt(input1) - Integer.parseInt(input2);
                    resultTextView.setText(calculation.toString());
                } else if (rb3.isChecked()) {
                    calculation = Integer.parseInt(input1) * Integer.parseInt(input2);
                    resultTextView.setText(calculation.toString());
                } else if (rb4.isChecked()) {
                    if (Integer.parseInt(input2) == 0) {
                        throw new DivideByZeroException("can't divide by zero");
                    }
                    calculation = Integer.parseInt(input1) / Integer.parseInt(input2);
                    resultTextView.setText(calculation.toString());
                }
            } catch (DivideByZeroException e) {
                String errorMessage = e.getMessage();
                Toast.makeText(this, errorMessage,Toast.LENGTH_SHORT).show();
            } catch (IllegalArgumentException e) {
                Toast.makeText(this,"error: bad input",Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
