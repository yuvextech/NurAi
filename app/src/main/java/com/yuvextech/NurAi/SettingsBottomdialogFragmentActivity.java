package com.yuvextech.NurAi;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SettingsBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.settings_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
	}
	
	private void initializeLogic() {
		_Ui();
		Dialog dialog = getDialog();
		// Set the background of the BottomSheetDialog's window to transparent
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		// Also, set the background of the bottom sheet itself to transparent
		dialog.setOnShowListener(dialogInterface -> {
			BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
			FrameLayout bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			if (bottomSheet != null) {
				bottomSheet.setBackground(new ColorDrawable(Color.TRANSPARENT));
			}
		});
		
		if (dialog instanceof BottomSheetDialog) {
			BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
			// Ensure the slide-down animation is active
			bottomSheetDialog.setDismissWithAnimation(true);
			
			// Access the behavior and add your custom logic
			BottomSheetBehavior<?> behavior = bottomSheetDialog.getBehavior();
			behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
				@Override
				public void onStateChanged(@NonNull View bottomSheet, int newState) {
					switch(newState) {
						case BottomSheetBehavior.STATE_EXPANDED: {
							
							break;
						}
						case BottomSheetBehavior.STATE_COLLAPSED: {
							
							break;
						}
						default: {
							
							break;
						}
					}
				}
				
				@Override
				public void onSlide(@NonNull View bottomSheet, float slideOffset) {
					// Change the alpha of a view based on the slide
					// slideOffset goes from 0.0 (collapsed) to 1.0 (expanded)
					// and 0.0 to -1.0 when hiding.
					if (getView() != null) {
						getView().setAlpha(Math.max(0f, 1f + slideOffset));
					}
					
				}
			});
		}
	}
	
	public void _Ui() {
		int[] linear1_qwer = {getResources().getColor(R.color.backgroundColor), getResources().getColor(R.color.backgroundColor) }; 
		android.graphics.drawable.GradientDrawable linear1_qaz = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, linear1_qwer);
		linear1_qaz.setCornerRadii(new float[]{25,25,25,25,0,0,0,0});
		linear1_qaz.setStroke(0, getResources().getColor(R.color.colorPrimary));
		linear1.setElevation((float) 0);
		linear1.setBackground(linear1_qaz);
		int[] linear2_qwer = {0xFF616161, 0xFF616161 }; 
		android.graphics.drawable.GradientDrawable linear2_qaz = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, linear2_qwer);
		linear2_qaz.setCornerRadii(new float[]{5,5,5,5,5,5,5,5});
		linear2_qaz.setStroke(0, Color.TRANSPARENT);
		linear2.setElevation((float) 0);
		linear2.setBackground(linear2_qaz);
	}
	
}