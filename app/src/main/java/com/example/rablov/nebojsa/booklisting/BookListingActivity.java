package com.example.rablov.nebojsa.booklisting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookListingActivity extends AppCompatActivity {
   // private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_listing);

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String searchText;
                EditText searchTopic = (EditText)findViewById(R.id.search_field);
                searchText = searchTopic.getText().toString();
                    Log.i("TEST", searchText);
            }
        });
    }
}
