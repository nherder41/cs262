package ndh7.cs262.calvin.edu.lab05;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader<String> {

    private String bookQuery;

    public BookLoader(@NonNull Context context, String query) {
        super(context);
        bookQuery = query;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getBookInfo(bookQuery);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
