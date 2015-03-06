package com.koncius.zodiac;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TodayActivity extends Activity {
	String url = " ";
	ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.today_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));    

		SharedPreferences sharedPreferences = getSharedPreferences("MY", 0);

		setTitle(sharedPreferences.getString("ZODIAC_NAME", "")
				+ ", "
				+ android.text.format.DateFormat.format("EEEE, MMMM d",
						new java.util.Date()));

		switch (sharedPreferences.getInt("ZODIAC", 0)) {
		case 1:
			url = "http://www.elle.com/horoscopes/daily/a60/aries-daily-horoscope/";
			break;
		case 2:
			url = "http://www.elle.com/horoscopes/daily/a98/taurus-daily-horoscope/";
			break;
		case 3:
			url = "http://www.elle.com/horoscopes/daily/a99/gemini-daily-horoscope/";
			break;
		case 4:
			url = "http://www.elle.com/horoscopes/daily/a100/cancer-daily-horoscope/";
			break;
		case 5:
			url = "http://www.elle.com/horoscopes/daily/a101/leo-daily-horoscope/";
			break;
		case 6:
			url = "http://www.elle.com/horoscopes/daily/a102/virgo-daily-horoscope/";
			break;
		case 7:
			url = "http://www.elle.com/horoscopes/daily/a103/libra-daily-horoscope/";
			break;
		case 8:
			url = "http://www.elle.com/horoscopes/daily/a104/scorpio-daily-horoscope/";
			break;
		case 9:
			url = "http://www.elle.com/horoscopes/daily/a105/sagittarius-daily-horoscope/";
			break;
		case 10:
			url = "http://www.elle.com/horoscopes/daily/a106/capricorn-daily-horoscope/";
			break;
		case 11:
			url = "http://www.elle.com/horoscopes/daily/a107/aquarius-daily-horoscope/";
			break;
		case 12:
			url = "http://www.elle.com/horoscopes/daily/a108/pisces-daily-horoscope/";
			break;
		}

		new TextPullAsync().execute();
	}

	private class TextPullAsync extends AsyncTask<Void, Void, Void> {
		String desc;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(TodayActivity.this);
			mProgressDialog.setMessage("Preparing...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				// Connect to the web site
				Document document = Jsoup.connect(url).get();
				// Using Elements to get the data
				Elements description = document
						.select("div.article-body--text p");
				desc = description.text();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// Set description into TextView
			TextView txtdesc = (TextView) findViewById(R.id.textView1);
			txtdesc.setTypeface(Typeface.createFromAsset(getAssets(),
					"fonts/DroidSerif-Italic.ttf"));
			txtdesc.setText(desc);
			mProgressDialog.dismiss();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        finish();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
}
