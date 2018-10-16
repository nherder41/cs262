package edu.calvin.cs262.quest.homework2;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String MONOPOLY_BASE_URL =  "https://calvincs262-monopoly.appspot.com/monopoly/v1/players"; //Monopoly API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string

    static String getPlayerInfo(String queryString){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJSONString = null;

        try {

            //Build up your query URI, limiting results to 10 items and printed books
            Uri builtURI = Uri.parse(MONOPOLY_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString).build();

            //turn the base url and parameters into the final URL string
            URL requestURL = new URL(builtURI.toString());

            //create the connection and request the information from the API
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            resultJSONString = buffer.toString();

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        } finally {

            //close the reader and url connections
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d(LOG_TAG, resultJSONString);

            /*
            return the full list if no query was specified.  Otherwise only return the JSON object
               with the id that the user is looking for information on.
            */
            if (queryString == "") {
                return resultJSONString;
            }
            try {
                JSONObject jsonObject = new JSONObject(resultJSONString);
                //get array of the JSON items
                JSONArray itemsArraay = jsonObject.getJSONArray("items");

                for (int i = 0; i < itemsArraay.length(); i++) {
                    JSONObject playerInfo = itemsArraay.getJSONObject(i);
                    if (playerInfo.getInt("id") == Integer.parseInt(queryString)) {
                        return "{ " + "\"items\": " + "[ " + playerInfo.toString() + " ] " + " }";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
