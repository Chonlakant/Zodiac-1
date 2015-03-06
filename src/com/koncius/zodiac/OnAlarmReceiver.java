package com.koncius.zodiac;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class OnAlarmReceiver extends BroadcastReceiver {

	private static final int NOTIFY_ME_ID = 1337;

	@Override
	public void onReceive(Context ctxt, Intent intent) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(ctxt);
		boolean useNotification = prefs.getBoolean("use_notification", true);
		
		Log.d("Tag", "______________________RECEIVED__________________");

		if (useNotification) {			
			NotificationManager mgr = (NotificationManager) ctxt
					.getSystemService(Context.NOTIFICATION_SERVICE);
			Notification note = new Notification(R.drawable.dot,
					"Horoscope Update", System.currentTimeMillis());

			PendingIntent i = PendingIntent.getActivity(ctxt, 0, new Intent(
					ctxt, ChooseZodiacActivity.class), 0);

			note.setLatestEventInfo(ctxt, "Horoscope",
					"Check out your horoscope for today! Select to see it.", i);

			note.flags |= Notification.FLAG_AUTO_CANCEL;
			note.defaults |= Notification.DEFAULT_VIBRATE;
			note.defaults |= Notification.DEFAULT_SOUND;
			note.defaults |= Notification.DEFAULT_LIGHTS;

			mgr.notify(NOTIFY_ME_ID, note);
		} else {
			Intent i = new Intent(ctxt, ChooseZodiacActivity.class);

			i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

			ctxt.startActivity(i);
		}
	}

	/*public void Notification(Context context, String message) {
		// Set Notification Title
		String strtitle = "Title";
		// Open NotificationView Class on Notification Click
		Intent intent = new Intent(context, NotificationView.class);
		// Send data to NotificationView Class
		intent.putExtra("title", strtitle);
		intent.putExtra("text", "Message");
		// Open NotificationView.java Activity
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		// Create Notification using NotificationCompat.Builder
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context)
		// Set Icon
				.setSmallIcon(R.drawable.dot)
				// Set Ticker Message
				.setTicker(message)
				// Set Title
				.setContentTitle("Title")
				// Set Text
				.setContentText(message)
				// Add an Action Button below Notification
				.addAction(R.drawable.ic_launcher, "Action Button", pIntent)
				// Set PendingIntent into Notification
				.setContentIntent(pIntent)
				// Dismiss Notification
				.setAutoCancel(true);

		// Create Notification Manager
		NotificationManager notificationmanager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		// Build Notification with Notification Manager
		notificationmanager.notify(0, builder.build());

	}*/
}