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
public class MonthActivity extends Activity {

	String url = " ";
	ProgressDialog mProgressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.month_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));    

		SharedPreferences sharedPreferences = getSharedPreferences("MY", 0);

		setTitle(sharedPreferences.getString("ZODIAC_NAME", "")
				+ ", "
				+ android.text.format.DateFormat.format("MMMM, yyyy",
						new java.util.Date()));

		switch (sharedPreferences.getInt("ZODIAC", 0)) {
		case 1:
			url = "http://www.elle.com/horoscopes/monthly/a61/aries-monthly-horoscope/";
			break;
		case 2:
			url = "http://www.elle.com/horoscopes/monthly/a74/taurus-monthly-horoscope/";
			break;
		case 3:
			url = "http://www.elle.com/horoscopes/monthly/a75/gemini-monthly-horoscope/";
			break;
		case 4:
			url = "http://www.elle.com/horoscopes/monthly/a76/cancer-monthly-horoscope/";
			break;
		case 5:
			url = "http://www.elle.com/horoscopes/monthly/a77/leo-monthly-horoscope/";
			break;
		case 6:
			url = "http://www.elle.com/horoscopes/monthly/a78/virgo-monthly-horoscope/";
			break;
		case 7:
			url = "http://www.elle.com/horoscopes/monthly/a79/libra-monthly-horoscope/";
			break;
		case 8:
			url = "http://www.elle.com/horoscopes/monthly/a80/scorpio-monthly-horoscope/";
			break;
		case 9:
			url = "http://www.elle.com/horoscopes/monthly/a81/sagittarius-monthly-horoscope/";
			break;
		case 10:
			url = "http://www.elle.com/horoscopes/monthly/a82/capricorn-monthly-horoscope/";
			break;
		case 11:
			url = "http://www.elle.com/horoscopes/monthly/a83/aquarius-monthly-horoscope/";
			break;
		case 12:
			url = "http://www.elle.com/horoscopes/monthly/a84/pisces-monthly-horoscope/";
			break;
		}

		new TextPullAsync().execute();
	}

	private class TextPullAsync extends AsyncTask<Void, Void, Void> {
		String desc;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MonthActivity.this);
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
