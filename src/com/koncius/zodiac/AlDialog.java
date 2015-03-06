package com.koncius.zodiac;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlDialog extends DialogFragment{
	
	@Override
	public Dialog onCreateDialog(final Bundle savedInstanceState) {
		
		AlertDialog.Builder theDialog = new AlertDialog.Builder(getActivity());
		
		theDialog.setTitle("Do you want to save your selection?");
		
		theDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		
		theDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//savedInstanceState.putChar("Y", 'c');
			}

		});
		
		
		return theDialog.create();
	
	}
	
}
