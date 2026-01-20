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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.material.appbar.AppBarLayout;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class DiscoverFragmentActivity extends Fragment {
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private AppBarLayout app_bar_layout7;
	private RecyclerView rvDiscoverGrid;
	private LinearLayout linear8;
	private TextView textview4;
	private EditText etDiscoverSearch;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.discover_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		app_bar_layout7 = _view.findViewById(R.id.app_bar_layout7);
		rvDiscoverGrid = _view.findViewById(R.id.rvDiscoverGrid);
		linear8 = _view.findViewById(R.id.linear8);
		textview4 = _view.findViewById(R.id.textview4);
		etDiscoverSearch = _view.findViewById(R.id.etDiscoverSearch);
	}
	
	private void initializeLogic() {
		_Ui();
	}
	
	public void _Ui() {
		for(int _repeat10 = 0; _repeat10 < (int)(20); _repeat10++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("q", "");
				list.add(_item);
			}
		}
		rvDiscoverGrid.setAdapter(new RvDiscoverGridAdapter(list));
		rvDiscoverGrid.setLayoutManager(new GridLayoutManager(getContext().getApplicationContext(), 2));
	}
	
	public class RvDiscoverGridAdapter extends RecyclerView.Adapter<RvDiscoverGridAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public RvDiscoverGridAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.item_discover_category, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView cardview3 = _view.findViewById(R.id.cardview3);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final ImageView ivCategoryIcon = _view.findViewById(R.id.ivCategoryIcon);
			final TextView tvCategoryTitle = _view.findViewById(R.id.tvCategoryTitle);
			final TextView tvCategoryCount = _view.findViewById(R.id.tvCategoryCount);
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