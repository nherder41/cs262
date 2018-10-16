package edu.calvin.cs262.quest.homework2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class playerLoader extends AsyncTaskLoader<String> {

    private String playerQuery;

    public playerLoader(@NonNull Context context, String query) {
        super(context);
        playerQuery = query;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPlayerInfo(playerQuery);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
