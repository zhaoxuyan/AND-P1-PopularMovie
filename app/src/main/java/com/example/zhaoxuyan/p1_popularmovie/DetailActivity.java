package com.example.zhaoxuyan.p1_popularmovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView mPoster;
    private TextView mTitle;
    private TextView mReleaseDate;
    private TextView mScore;
    private TextView mOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mPoster = (ImageView)findViewById(R.id.detail_imageView);
        mTitle = (TextView)findViewById(R.id.detail_title);
        mReleaseDate = (TextView)findViewById(R.id.detail_releasedate);
        mScore = (TextView)findViewById(R.id.detail_score);
        mOverview = (TextView)findViewById(R.id.detail_overview);
        /*
         * part[0]:movieTitle
         * part[1]:moviePoster
         * part[2]:movieOverview
         * part[3]:movieScore
         * part[4]:movieRelease
         */
        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                String detailString = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                String[] parts = detailString.split("-");
                Picasso.with(this)
                        .load("https://image.tmdb.org/t/p/w342" + parts[1])
                        .into(mPoster);
                mTitle.setText(parts[0]);
                mReleaseDate.setText(parts[4]);
                mScore.setText(parts[3] + " / 10");
                mOverview.setText(parts[2]);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
