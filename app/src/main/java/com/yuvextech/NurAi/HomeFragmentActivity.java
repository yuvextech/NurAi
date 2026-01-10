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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.bumptech.glide.Glide;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class HomeFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	
	private double position = 0;
	private double num = 0;
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private RelativeLayout linear4;
	private RecyclerView grids;
	private LinearLayout linear6;
	private ViewPager viewpager_slider;
	private LinearLayout linear5;
	private RecyclerView recyclerview1;
	
	private TimerTask seven;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.home_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		linear4 = _view.findViewById(R.id.linear4);
		grids = _view.findViewById(R.id.grids);
		linear6 = _view.findViewById(R.id.linear6);
		viewpager_slider = _view.findViewById(R.id.viewpager_slider);
		linear5 = _view.findViewById(R.id.linear5);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
	}
	
	private void initializeLogic() {
		_Ui();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		seven.cancel();
	}
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _Ui() {
		int[] colorsCRNAF = { Color.parseColor("#ffffff"), Color.parseColor("#ffffff") }; android.graphics.drawable.GradientDrawable CRNAF = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNAF);
		CRNAF.setCornerRadii(new float[]{(int)16,(int)16,(int)16,(int)16,(int)0,(int)0,(int)0,(int)0});
		CRNAF.setStroke((int) 0, Color.parseColor("#000000"));
		linear3.setElevation((float) 0);
		linear3.setBackground(CRNAF);
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS376GTjTmKi6D30_Z50SMg4aNj5RayrIS8dw&usqp=CAU");
			listmap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDEt5q0ycD-mr_kKnRyPULbT1yOwcue5HdNQ&usqp=CAU");
			listmap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRghf-coFyo1NsiLp4n6JyYb2VN7XP6vgcahw&usqp=CAU");
			listmap.add(_item);
		}
		{
			HashMap<String, Object> _item = new HashMap<>();
			_item.put("img", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRITxaEBapgd9m4HZX7mDyyMXdxvewgrOIOOw&usqp=CAU");
			listmap.add(_item);
		}
		final float scaleFactor = 0.90f; viewpager_slider.setPageMargin(-30); viewpager_slider.setOffscreenPageLimit(2); viewpager_slider.setPageTransformer(false, new ViewPager.PageTransformer() { @Override public void transformPage(@NonNull View page1, float position) { page1.setScaleY((1 - Math.abs(position) * (1 - scaleFactor))); page1.setScaleX(scaleFactor + Math.abs(position) * (1 - scaleFactor)); } });
		viewpager_slider.setAdapter(new Viewpager_sliderAdapter(listmap));
		recyclerview1.setAdapter(new Recyclerview1Adapter(listmap));
		recyclerview1.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false));
		recyclerview1.setHasFixedSize(true);
		position = 0;
		num = 0;
		seven = new TimerTask() {
			@Override
			public void run() {
				getActivity().runOnUiThread(new Runnable() {
					@Override
					public void run() {
						viewpager_slider.setCurrentItem((int)num);
						num++;
						if (num == listmap.size()) {
							num = 0;
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(seven, (int)(0), (int)(1500));
	}
	
	public class Viewpager_sliderAdapter extends PagerAdapter {
		
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		
		public Viewpager_sliderAdapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}
		
		public Viewpager_sliderAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getContext().getApplicationContext();
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}
		
		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}
		
		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}
		
		@Override
		public CharSequence getPageTitle(int pos) {
			// Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}
		
		@Override
		public Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.slider, _container, false);
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("img").toString())).into(imageview1);
			cardview1.setRadius((float)10);
			cardview1.setCardElevation((float)12);
			
			_container.addView(_view);
			return _view;
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.dot, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
			_view.setLayoutParams(_lp);
			_rippleRoundStroke(linear1, "#FFFFFF", "#FFFFFF", 555, 0, "#FFFFFF");
			if (position == _position) {
				_rippleRoundStroke(linear1, "#FFFFFF", "#FFFFFF", 555, 0, "#FFFFFF");
				linear1.setAlpha((float)(1.0d));
			} else {
				_rippleRoundStroke(linear1, "#FFFFFF", "#FFFFFF", 555, 0, "#FFFFFF");
				linear1.setAlpha((float)(0.5d));
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}