package akc8.cs262.calvin.edu.lab02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, getString(R.string.toast_message), Toast.LENGTH_LONG);
        toast.show();
    }

    private int mCount = 0;
    private TextView mShowCount;

    public void countUp(View view) {
        mShowCount = (TextView) findViewById(R.id.show_count);
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }
}
