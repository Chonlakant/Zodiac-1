package com.koncius.zodiac;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

public class EditPreferences extends PreferenceActivity {
	SharedPreferences prefs = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("TAG", "EDIT PREFS ON CREATE");
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setIcon(
				new ColorDrawable(getResources().getColor(
						android.R.color.transparent)));
		addPreferencesFromResource(R.xml.preferences);
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d("TAG", "CHANGE ");
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(onChange);
	}

	@Override
	public void onPause() {
		prefs.unregisterOnSharedPreferenceChangeListener(onChange);

		super.onPause();
	}

	SharedPreferences.OnSharedPreferenceChangeListener onChange = new SharedPreferences.OnSharedPreferenceChangeListener() {
		public void onSharedPreferenceChanged(SharedPreferences prefs,
				String key) {

			if ("use_notification".equals(key)) {
				Log.d("TAG", "CHANGE ");
				boolean enabled = prefs.getBoolean(key, false);
				int flag = (enabled ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
						: PackageManager.COMPONENT_ENABLED_STATE_DISABLED);
				ComponentName component = new ComponentName(
						EditPreferences.this, OnBootReceiver.class);

				getPackageManager().setComponentEnabledSetting(component, flag,
						PackageManager.DONT_KILL_APP);

				if (enabled) {
					OnBootReceiver.setAlarm(EditPreferences.this);
				} else {
					OnBootReceiver.cancelAlarm(EditPreferences.this);
				}
			} else if ("alarm_time".equals(key)) {
				OnBootReceiver.cancelAlarm(EditPreferences.this);
				OnBootReceiver.setAlarm(EditPreferences.this);
			}
		}
	};

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
