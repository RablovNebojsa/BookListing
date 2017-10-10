package com.example.rablov.nebojsa.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Main activity. Handles click on find button.
 */
public class BookListingActivity extends AppCompatActivity {
    private static final String LOG_TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String searchText;
                EditText searchEditText = (EditText)findViewById(R.id.search_field);
                searchText = searchEditText.getText().toString();
                    Log.i(LOG_TAG, searchText);
                ArrayList<Book> books = Utils.FetchBooks(searchText);
            }
        });
    }
}
