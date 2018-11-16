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


/**
 * This is a custom exception to handle the divide by zero case within the calculator
 * This is a subclass of the Java Exception class.
 */
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

    /**
     * @author Nate Herder
     * @version 1.0
     * @since 10/2018 Calvin College CS262
     *
     * When the main activity is created, get variables with values of
     * main views in the applicatio so that their values can be received
     * and data can be passed to them programmatically.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input1EditText = (EditText) findViewById(R.id.input1);
        input2EditText = (EditText) findViewById(R.id.input2);
        resultTextView = (TextView) findViewById(R.id.result);
    }

    /**
     * @author Nate Herder
     *
     * This is the method called when the calculate button of the application of pressed.
     * This method checks the operands of the textViews for valid input, makes sure that one
     * of the radio buttons is selected.  If everything is as expected the operator (+, -, /, *)
     * is applied to the two input operands.
     *
     * @param view the view of the
     * @return nothing, void method.
     * @throws DivideByZeroException on attempt to divide by zero
     */
    public void calculate(View view) {
        RadioButton rb1 = (RadioButton) findViewById(R.id.radioAddition);
        RadioButton rb2 = (RadioButton) findViewById(R.id.radioSubtraction);
        RadioButton rb3 = (RadioButton) findViewById(R.id.radioMultiplication);
        RadioButton rb4 = (RadioButton) findViewById(R.id.radioDivision);
        RadioGroup rGroup = (RadioGroup) findViewById(R.id.radioGroup);
        String input1 = input1EditText.getText().toString();
        String input2 = input2EditText.getText().toString();
        Integer calculation = null;

        // this if statement ensures that one of the radio buttons is selected
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
