package com.koncius.zodiac;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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



public class WeekActivity extends Activity {
	String url = " ";
	ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.week_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));    
		
		SharedPreferences sharedPreferences = getSharedPreferences(
				"MY", 0);
		

		switch (sharedPreferences.getInt("ZODIAC", 0)) {
		case 1:
			url = "http://www.elle.com/horoscopes/weekly/a97/aries-weekly-horoscope/";
			break;
		case 2:
			url = "http://www.elle.com/horoscopes/weekly/a63/taurus-weekly-horoscope/";
			break;
		case 3:
			url = "http://www.elle.com/horoscopes/weekly/a64/gemini-weekly-horoscope/";
			break;
		case 4:
			url = "http://www.elle.com/horoscopes/weekly/a65/cancer-weekly-horoscope/";
			break;
		case 5:
			url = "http://www.elle.com/horoscopes/weekly/a66/leo-weekly-horoscope/";
			break;
		case 6:
			url = "http://www.elle.com/horoscopes/weekly/a67/virgo-weekly-horoscope//";
			break;
		case 7:
			url = "http://www.elle.com/horoscopes/weekly/a68/libra-weekly-horoscope/";
			break;
		case 8:
			url = "http://www.elle.com/horoscopes/weekly/a69/scorpio-weekly-horoscope/";
			break;
		case 9:
			url = "http://www.elle.com/horoscopes/weekly/a70/sagittarius-weekly-horoscope/";
			break;
		case 10:
			url = "http://www.elle.com/horoscopes/weekly/a71/capricorn-weekly-horoscope/";
			break;
		case 11:
			url = "http://www.elle.com/horoscopes/weekly/a72/aquarius-weekly-horoscope/";
			break;
		case 12:
			url = "http://www.elle.com/horoscopes/weekly/a73/pisces-weekly-horoscope/";
			break;
		}

		new TextPullAsync().execute();
		String str = sharedPreferences.getString("ZODIAC_NAME", "")
				+ ", "
				+ sharedPreferences.getString("WEEK", "").substring(1, 
						sharedPreferences.getString("WEEK", "").length()-1);
		
		setTitle(str);
	}

	private class TextPullAsync extends AsyncTask<Void, Void, Void> {
		String desc;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(WeekActivity.this);
			mProgressDialog.setMessage("Preparing...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {
				SharedPreferences sharedPreferences = getSharedPreferences(
						"MY", 0);
				final SharedPreferences.Editor editor = sharedPreferences
						.edit();
				// Connect to the web site
				Document document = Jsoup.connect(url).get();
				// Using Elements to get the data
				Elements description = document
						.select("div.article-body--text p");
				desc = description.text();
				editor.putString("WEEK", document.select("meta[property=og:description]").attr("content"));
				editor.commit();

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
