package edu.calvin.cs262.quest.homework2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText userInput;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.userInput);
        resultText = findViewById(R.id.resultsTextView);
    }

    public void fetchPlayer(View view) {

        userInput.setText(R.string.loading);
        String input = userInput.getText().toString();

        //Hide the keyboard so the user knows the search is taking place
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        //check for a network connection
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryInput", input);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);       //MAY BE CAUSE OF ERROR LATER
        } else {
            resultText.setText("check your network connection...");
        }
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle args) {
        if(getSupportLoaderManager().getLoader(0)!=null){
            getSupportLoaderManager().initLoader(0,null,this);
        }

        return new playerLoader(this, args.getString("queryInput"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        //This is where I will parse the JSON for results https://google-developer-training.github.io/android-developer-fundamentals-course-practicals/en/Unit%203/72_p_asynctask_asynctaskloader.html#task1intro
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
