package com.example.baumarkt.dialog;

import com.example.test.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class ArticelDetailDialog extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedSate) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.articel_detail_view, null));
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				System.out.println("OK Klick");
			}
		});
		return builder.create();
	}
}
