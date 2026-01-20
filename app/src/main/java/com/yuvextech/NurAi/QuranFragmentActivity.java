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
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.google.android.material.button.MaterialButtonToggleGroup;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class QuranFragmentActivity extends Fragment {
	
	private LinearLayout linear1;
	private MaterialButtonToggleGroup toggleButton;
	private ViewPager viewpager1;
	private Button button1;
	private Button button2;
	private Button button3;
	
	private FfFragmentAdapter ff;
	
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
		toggleButton = _view.findViewById(R.id.toggleButton);
		viewpager1 = _view.findViewById(R.id.viewpager1);
		button1 = _view.findViewById(R.id.button1);
		button2 = _view.findViewById(R.id.button2);
		button3 = _view.findViewById(R.id.button3);
		ff = new FfFragmentAdapter(getContext().getApplicationContext(), getActivity().getSupportFragmentManager());
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)0);
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)1);
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				viewpager1.setCurrentItem((int)2);
			}
		});
	}
	
	private void initializeLogic() {
		_Ui();
	}
	
	public class FfFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public FfFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			return "";
		}
		
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new QuranIndexFragmentActivity();
			}
			if (_position == 1) {
				return new JuzIndexFragmentActivity();
			}
			if (_position == 2) {
				return new BookmarksIndexFragmentActivity();
			}
			return new Fragment();
		}
		
	}
	
	public void _Ui() {
		int[] colorsCRNAF = { Color.parseColor("#ffffff"), Color.parseColor("#ffffff") }; android.graphics.drawable.GradientDrawable CRNAF = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNAF);
		CRNAF.setCornerRadii(new float[]{(int)25,(int)25,(int)25,(int)25,(int)0,(int)0,(int)0,(int)0});
		linear1.setBackground(CRNAF);
		ff.setTabCount(3);
		viewpager1.setAdapter(ff);
		viewpager1.setCurrentItem((int)0);
	}
	
}