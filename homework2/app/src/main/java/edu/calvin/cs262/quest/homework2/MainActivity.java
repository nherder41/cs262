package edu.calvin.cs262.quest.homework2;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText userInput;
    private TextView resultText;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.userInput);
        resultText = findViewById(R.id.resultsTextView);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_order:
                displayToast("test1");
                return true;
            case R.id.action_status:
                displayToast("test2");
                return true;
            case R.id.action_favorites:
                displayToast("test3");
                return true;
            case R.id.action_contact:
                displayToast("test4");
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }



    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
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

        try {
            if ( networkInfo.isConnected() ) {
                Bundle queryBundle = new Bundle();
                queryBundle.putString("queryInput", input);
                getSupportLoaderManager().restartLoader(0, queryBundle,this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            toast.show();
        }
    }



    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle args) {
        return new playerLoader(this, args.getString("queryInput"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        // Convert the response into a JSON object.
        try {
            JSONObject jsonObject = new JSONObject(data);
            //get array of the JSON items
            JSONArray itemsArraay = jsonObject.getJSONArray("items");
            String concatenatedResults = "";

            for (int i = 0; i < itemsArraay.length(); i++) {
                String player = null;
                String email = null;
                String id = null;
                JSONObject playerInfo = itemsArraay.getJSONObject(i);

                try {
                    player = playerInfo.getString("name");
                } catch (Exception e) {
                    e.printStackTrace();
                    player = "no name";
                } try {
                    email = playerInfo.getString("emailAddress");
                } catch (Exception e) {
                    e.printStackTrace();
                    email = "no contact email...";
                } try {
                    id = playerInfo.getString("id");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                concatenatedResults += id + ", " + player + ", " + email + "\n";
            }
            resultText.setText(concatenatedResults);

        } catch (JSONException e) {
            resultText.setText(R.string.no_results);
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {}

}
