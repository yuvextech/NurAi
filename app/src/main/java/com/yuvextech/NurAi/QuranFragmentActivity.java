package com.yuvextech.NurAi;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.*;
import com.google.android.material.button.MaterialButtonToggleGroup;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class QuranFragmentActivity extends Fragment {
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linearTop;
	private TextView textview10;
	private TextView textview9;
	private ImageView imageview3;
	private LinearLayout linear4;
	private ImageView imageview1;
	private ImageView imageview2;
	private MaterialButtonToggleGroup toggleButton;
	private ViewPager2 viewPager21;
	private MaterialButton button1;
	private MaterialButton button2;
	private MaterialButton button3;
	
	private Intent kk = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.quran_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear3 = _view.findViewById(R.id.linear3);
		linearTop = _view.findViewById(R.id.linearTop);
		textview10 = _view.findViewById(R.id.textview10);
		textview9 = _view.findViewById(R.id.textview9);
		imageview3 = _view.findViewById(R.id.imageview3);
		linear4 = _view.findViewById(R.id.linear4);
		imageview1 = _view.findViewById(R.id.imageview1);
		imageview2 = _view.findViewById(R.id.imageview2);
		toggleButton = _view.findViewById(R.id.toggleButton);
		viewPager21 = _view.findViewById(R.id.viewPager21);
		button1 = _view.findViewById(R.id.button1);
		button2 = _view.findViewById(R.id.button2);
		button3 = _view.findViewById(R.id.button3);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SettingsBottomdialogFragmentActivity SettingsBottomdialogFragmentActivity = new SettingsBottomdialogFragmentActivity();
				SettingsBottomdialogFragmentActivity.show(getActivity().getSupportFragmentManager(), SettingsBottomdialogFragmentActivity.getTag());
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewPager21.setCurrentItem((int)0);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewPager21.setCurrentItem((int)1);
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewPager21.setCurrentItem((int)2);
			}
		});
	}
	
	private void initializeLogic() {
		_Ui();
	}
	
	
	private class MyrrAdapter extends FragmentStateAdapter {
		
		public MyrrAdapter(@NonNull FragmentActivity fragmentActivity) {
			super(fragmentActivity);
		}
		
		@NonNull
		@Override
		public Fragment createFragment(int _position) {
			// Return a fragment instance based on position
			switch (_position) {
				case 0:
				return new QuranIndexFragmentActivity();
				case 1:
				return new JuzIndexFragmentActivity();
				case 2:
				return new BookmarksIndexFragmentActivity();
				default:
				return new QuranIndexFragmentActivity();
			}
		}
		
		@Override
		public int getItemCount() {
			return 3; // Number of pages
		}
	}
	
	public void _Ui() {
		MyrrAdapter adapter = new MyrrAdapter(requireActivity()); // 'this' is FragmentActivity or Fragment
		viewPager21.setAdapter(new MyrrAdapter(requireActivity()));
		viewPager21.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
		viewPager21.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
			@Override
			public void onPageSelected(int _position) {
				super.onPageSelected(_position);
				// Handle page selected event
				if (_position == 0) {
					button1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
					button1.setTextColor(getResources().getColor(R.color.surface));
					button2.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
					button2.setTextColor(getResources().getColor(R.color.text_primary));
					button3.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
					button3.setTextColor(getResources().getColor(R.color.text_primary));
				}
				if (_position == 1) {
					button2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
					button2.setTextColor(getResources().getColor(R.color.surface));
					button3.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
					button3.setTextColor(getResources().getColor(R.color.text_primary));
					button1.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
					button1.setTextColor(getResources().getColor(R.color.text_primary));
				}
				if (_position == 2) {
					button3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
					button3.setTextColor(getResources().getColor(R.color.surface));
					button1.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
					button1.setTextColor(getResources().getColor(R.color.text_primary));
					button2.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
					button2.setTextColor(getResources().getColor(R.color.text_primary));
				}
			}
		});
		int[] linear3_qwer = {getResources().getColor(R.color.surface), getResources().getColor(R.color.surface) }; 
		android.graphics.drawable.GradientDrawable linear3_qaz = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, linear3_qwer);
		linear3_qaz.setCornerRadii(new float[]{25,25,25,25,0,0,0,0});
		linear3_qaz.setStroke(0, 0xFF000000);
		linear3.setElevation((float) 0);
		linear3.setBackground(linear3_qaz);
	}
	
}