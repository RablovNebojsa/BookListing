package com.example.rablov.nebojsa.booklisting;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Nebojsa on 10/10/2017.
 * Contains utilty methods for networking, parsing and displaying books
 */

public  final class Utils {
    private static final String LOG_TAG = "UTILS";
    private static final String BASE_API_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String MAX_RESOLTS = "&maxResults=1";

    public static ArrayList<Book> FetchBooks(String word){
        URL apiURL = createUrl(BASE_API_URL + word + MAX_RESOLTS);
        String jsonResponse = makeHttpRequest(apiURL);
        return ParseJSONResponse(jsonResponse);
    }

    private static String makeHttpRequest(URL apiURL){
        String jsonResponse = "";
        if(apiURL == null){
            return jsonResponse;
        }
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try{
            connection = (HttpURLConnection) apiURL.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();

            if(connection.getResponseCode() == 200){
                inputStream = connection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }else{
                Log.i(LOG_TAG, "Response code: " + connection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG,"Error: ", e);
        }finally {
            if(connection != null)
                connection.disconnect();
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) {
        StringBuilder output = new StringBuilder();
        if(inputStream != null){
            InputStreamReader inputStrreamBuilder = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStrreamBuilder);
            try {
                String line = reader.readLine();
                while (line != null){
                    output.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output.toString();
    }


    private static ArrayList<Book> ParseJSONResponse(String json){
        ArrayList<Book> listOFBooks = new ArrayList<Book>();
        // If json string is empty, return earlier
        if(json.isEmpty()) {
            return listOFBooks;
        }
        try {
            Book book;
            String title;
            String authors;
            JSONObject root = new JSONObject(json);
            JSONArray items = root.getJSONArray("items");
            JSONObject volumeInfo;
            JSONArray authorsArray;
            for(int i = 0; i < items.length(); i++){
                volumeInfo = items.getJSONObject(i);
                title = volumeInfo.getString("title");
                authorsArray = volumeInfo.getJSONArray("authors");
                authors = authorsArray.getString(0);
                book = new Book(title, authors);
                listOFBooks.add(book);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSON error: ",e);
        }
        return listOFBooks;
    }

    /**
     * Converts string url to URL object
     * @param google_url
     * @return url - URL object
     */
    private static URL createUrl(String google_url) {
        URL url = null;
        try {
            url = new URL(google_url);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }
}
