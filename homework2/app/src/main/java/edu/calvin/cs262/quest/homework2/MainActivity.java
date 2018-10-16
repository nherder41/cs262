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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        resultText.setText(R.string.loading);
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


        if ( networkInfo.isConnected() ) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryInput", input);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);
        } else {
            resultText.setText("check your network connection...");
        }
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle args) {
//        if(getSupportLoaderManager().getLoader(0)!=null){
//            getSupportLoaderManager().initLoader(0,null,this);
//        }
        return new playerLoader(this, args.getString("queryInput"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // Convert the response into a JSON object.
        try {
            JSONObject jsonObject = new JSONObject(data);
            //get array of the JSON items
            JSONArray itemsArraay = jsonObject.getJSONArray("items");

            String player = null;
            String email = null;

            resultText.setText("we got this far!");

        } catch (JSONException e) {
            resultText.setText(R.string.no_results);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {}

}