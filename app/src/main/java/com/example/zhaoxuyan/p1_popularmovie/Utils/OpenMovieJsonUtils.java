package com.example.zhaoxuyan.p1_popularmovie.Utils;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaoxuyan on 2017/12/25.
 */

public final class OpenMovieJsonUtils {
    public static String[] getSimpleMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {
        // 结果字段
        final String MOVIE_RESULT = "results";
        // 电影名称
        final String MOVIE_TITLE = "title";
        // 电影海报
        final String MOVIE_POSTER = "poster_path";
        // 电影简介
        final String MOVIE_OVERVIEW = "overview";
        // 电影热度
        final String MOVIE_POPULARITY = "popularity";
        // 电影评分
        final String MOVIE_SCORE = "vote_average";
        // 发布日期
        final String MOVIE_RELEASE = "release_date";

        String[] parsedMovieData = null;

        JSONObject movieJson = new JSONObject(movieJsonStr);

        JSONArray resultsArray = movieJson.getJSONArray(MOVIE_RESULT);

        parsedMovieData = new String[resultsArray.length()];

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject movieObject = resultsArray.getJSONObject(i);

            String movieTitle = movieObject.getString(MOVIE_TITLE);
            String moviePoster = movieObject.getString(MOVIE_POSTER);
            String movieOverview = movieObject.getString(MOVIE_OVERVIEW);
            double movieScore = movieObject.getDouble(MOVIE_SCORE);
            double moviePopularity = movieObject.getDouble(MOVIE_POPULARITY);
            String movieRelease = movieObject.getString(MOVIE_RELEASE);

            parsedMovieData[i] =
                    movieTitle + "#"
                            + moviePoster + "#"
                            + movieOverview + "#"
                            + movieScore + "#"
                            + moviePopularity + "#"
                            + movieRelease;
        }
        return parsedMovieData;
    }
}
