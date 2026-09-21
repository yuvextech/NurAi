package com.yuvextech.NurAi;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class QuranIndexFragmentActivity extends Fragment {
	
	private String json3 = "";
	
	private ArrayList<HashMap<String, Object>> quran_list = new ArrayList<>();
	
	private ListView listview1;
	
	private Intent go = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.quran_index_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		listview1 = _view.findViewById(R.id.listview1);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				go.setClass(getContext().getApplicationContext(), QuranActivity.class);
				go.putExtra("sorah", String.valueOf((long)(_position)));
				startActivity(go);
			}
		});
	}
	
	private void initializeLogic() {
		try {
			java.io.InputStream is = getActivity().getAssets().open("quran_en.json");
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			json3 = new String(buffer, "UTF-8");
			
			quran_list = new Gson().fromJson(json3, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			if (quran_list.size() > 0) {
				Listview1Adapter adapter = new Listview1Adapter(quran_list, requireContext());
				listview1.setAdapter(adapter);
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
		} catch(Exception e) {
			
		}
		listview1.setHorizontalScrollBarEnabled(false);
		listview1.setVerticalScrollBarEnabled(false);
		listview1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
private Context context;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr, Context context) {
			_data = _arr;
this.context = context;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.item_surah_list, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView type = _view.findViewById(R.id.type);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView textviewTransliteration = _view.findViewById(R.id.textviewTransliteration);
			final TextView textviewName = _view.findViewById(R.id.textviewName);
			final TextView textviewTranslation = _view.findViewById(R.id.textviewTranslation);
			final TextView textviewTotal = _view.findViewById(R.id.textviewTotal);
			
			linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)15, (int)1, getResources().getColor(R.color.card_stroke), getResources().getColor(R.color.pop)));
			textviewTranslation.setText(_data.get((int)_position).get("translation").toString().concat(" | ".concat(_data.get((int)_position).get("type").toString())));
			textviewTransliteration.setText(_data.get((int)_position).get("transliteration").toString());
			textviewTotal.setText(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("total_verses").toString()))).concat(" Ayah"));
			textviewName.setText(String.format("surah%03d", _position + 1));
			textviewName.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/surah_name_v4.ttf"), 0);
			if (_data.get((int)_position).get("type").toString().equals("meccan")) {
				type.setImageResource(R.drawable.kaaba);
			} else {
				type.setImageResource(R.drawable.mosque);
			}
			
			return _view;
		}
	}
}