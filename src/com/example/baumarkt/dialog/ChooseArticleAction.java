package com.example.baumarkt.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;


/**
 * Dialog, welcher angezeigt wird, wenn auf einen Artikel in der Artikelliste geklickt wird.
 */
public class ChooseArticleAction extends DialogFragment {
	
	/**
	 * Interface als Callback für den Aufrufer
	 */
	public interface ArticelActionChoiceresult {
		public void itemSelected(DialogFragment dialog);	
	}
	
	private int selectedItem = -1;
	ArticelActionChoiceresult mListener;
	
	@Override
	public Dialog onCreateDialog(Bundle savedSate) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Bitte Aktion auswählen");
		builder.setItems(new String[]{"Artikel Details","Zu Einkaufsliste hinzufügen", "Standort anzeigen", "Abbruch"}, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				System.out.println(which + " pressed!");
				selectedItem = which;
				
				//Infomiere die unterliegende Aktivität über das Inteface:
				mListener.itemSelected(ChooseArticleAction.this);
			}
		});
		
		return builder.create();
	}
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		mListener = (ArticelActionChoiceresult) activity;
	}
	
	public int getSelectedItem() {
		return selectedItem;
	}
}
