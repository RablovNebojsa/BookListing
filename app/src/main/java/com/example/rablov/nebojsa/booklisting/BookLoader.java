package com.example.rablov.nebojsa.booklisting;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

/**
 * Created by Nebojsa on 10/16/2017.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String keyWord;

    public BookLoader(Context context, String word){
        super(context);
        keyWord = word;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        List<Book> books = Utils.FetchBooks(keyWord);
        return books;
    }
}
