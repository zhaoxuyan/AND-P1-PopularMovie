package com.example.zhaoxuyan.p1_popularmovie.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by zhaoxuyan on 2017/12/25.
 * NetworkUtils
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    public static final String MOVIE_BASIC_URL = "http://api.themoviedb.org/3/movie";

    /**
     * PATH

     */
    final static String POPULAR_PATH = "popular";

    final static String TOP_RATED_PATH = "top_rated";

    public static String getPopularPath() {
        return POPULAR_PATH;
    }

    public static String getTopRatedPath() {
        return TOP_RATED_PATH;
    }
    /**
     * PARAM
     */
    final static String API_KEY_PARM = "api_key";
    /**
     * 提供你的API_KEY
     * final static String API_KEY = ""
     */
    

    public static URL buildUrl(String path) {
        Uri builtUri = Uri.parse(MOVIE_BASIC_URL).buildUpon()
                .appendEncodedPath(path)
                .appendQueryParameter(API_KEY_PARM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
