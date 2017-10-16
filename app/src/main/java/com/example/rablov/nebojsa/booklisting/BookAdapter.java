package com.example.rablov.nebojsa.booklisting;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nebojsa on 10/16/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> books){
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Book curentBook = getItem(position);
        TextView bookTitle = (TextView) listItemView.findViewById(R.id.bookTitle);
        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.bookAuthor);
        bookTitle.setText(curentBook.getTitle());
        bookAuthor.setText(curentBook.getAuthors());
        return listItemView;
    }
}
