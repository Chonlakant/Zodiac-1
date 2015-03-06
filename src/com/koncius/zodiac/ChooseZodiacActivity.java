package com.koncius.zodiac;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


public class ChooseZodiacActivity extends Activity{
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_layout);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(
				   new ColorDrawable(getResources().getColor(android.R.color.transparent)));    
		setTitle("");
		
		onSelect(R.id.imageButton1, 1, "Aries");
		onSelect(R.id.imageButton2, 2, "Taurus");
		onSelect(R.id.imageButton3, 3, "Gemini");
		onSelect(R.id.imageButton4, 4, "Cancer");
		onSelect(R.id.imageButton5, 5, "Leo");
		onSelect(R.id.imageButton6, 6, "Virgo");
		onSelect(R.id.imageButton7, 7, "Libra");
		onSelect(R.id.imageButton8, 8, "Scorpio");
		onSelect(R.id.imageButton9, 9, "Sagittarius");
		onSelect(R.id.imageButton10, 10, "Capricorn");
		onSelect(R.id.imageButton11, 11, "Aquarius");
		onSelect(R.id.imageButton12, 12, "Pisces");
		
	}
	
	private void onSelect(int ID, final int i, final String s) {
		ImageButton iB = (ImageButton) findViewById(ID);
		iB.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				SharedPreferences sharedPreferences = getSharedPreferences(
						"MY", 0);
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putInt("ZODIAC", i);
				editor.putString("ZODIAC_NAME", s);
				editor.commit();
				//DialogFragment myFragment = new AlDialog();
				//Bundle b = myFragment.getArguments();
				//myFragment.show(getFragmentManager(), "theDialog");
				//if(b.get("Y").equals('c'))
				launchFeed();
			}
		});
		
	}
	
	private void launchFeed(){
		SharedPreferences sharedPreferences = getSharedPreferences(
				"MY", 0);
		if(sharedPreferences.getString("STATE", "").equals("MONTH")){
			startActivity(new Intent(this, MonthActivity.class));
		} else if(sharedPreferences.getString("STATE", "").equals("DAY")){
			startActivity(new Intent(this, TodayActivity.class));
		} else if(sharedPreferences.getString("STATE", "").equals("WEEK")){
			startActivity(new Intent(this, WeekActivity.class));
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
