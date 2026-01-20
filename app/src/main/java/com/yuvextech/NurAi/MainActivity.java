package com.yuvextech.NurAi;

import com.yuvextech.NurAi.SplashActivity;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
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
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class MainActivity extends AppCompatActivity {
	
	private ViewPager viewpager1;
	private BottomNavigationView bottomnavigation1;
	
	private FaFragmentAdapter fa;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		viewpager1 = findViewById(R.id.viewpager1);
		bottomnavigation1 = findViewById(R.id.bottomnavigation1);
		fa = new FaFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		
		viewpager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				Menu menu = bottomnavigation1.getMenu();
				int index = _position; 
				MenuItem item = menu.getItem(index);
				item.setChecked(true);
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		bottomnavigation1.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override
			public boolean onNavigationItemSelected(MenuItem item) {
				final int _itemId = item.getItemId();
				viewpager1.setCurrentItem((int)_itemId);
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		_currently();
	}
	
	public class FaFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public FaFragmentAdapter(Context context, FragmentManager manager) {
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
				return new HomeFragmentActivity();
			}
			if (_position == 1) {
				return new QuranFragmentActivity();
			}
			if (_position == 2) {
				return new ChatFragmentActivity();
			}
			if (_position == 3) {
				return new DiscoverFragmentActivity();
			}
			if (_position == 4) {
				return new SettingsFragmentActivity();
			}
			return new Fragment();
		}
		
	}
	
	public void _currently() {
		bottomnavigation1.getMenu().add(0, 0, 0, getString(R.string.nav_home)).setIcon(R.drawable.home);
		bottomnavigation1.getMenu().add(0, 1, 0, getString(R.string.nav_quran)).setIcon(R.drawable.quran);
		bottomnavigation1.getMenu().add(0, 2, 0, getString(R.string.nav_ai_chat)).setIcon(R.drawable.ai_chat);
		bottomnavigation1.getMenu().add(0, 3, 0, getString(R.string.nav_discover)).setIcon(R.drawable.book_quran);
		bottomnavigation1.getMenu().add(0, 4, 0, getString(R.string.nav_settings)).setIcon(R.drawable.settings);
		fa.setTabCount(5);
		viewpager1.setAdapter(fa);
	}
	
}