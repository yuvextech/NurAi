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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class ChatFragmentActivity extends Fragment {
	
	private ArrayList<HashMap<String, Object>> chats = new ArrayList<>();
	
	private LinearLayout linear1;
	private RecyclerView messages;
	private LinearLayout linear3;
	private ImageView imageview1;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear2;
	private EditText ask;
	private ImageView micBtn;
	private ImageView sendBtn;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.chat_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		messages = _view.findViewById(R.id.messages);
		linear3 = _view.findViewById(R.id.linear3);
		imageview1 = _view.findViewById(R.id.imageview1);
		textview1 = _view.findViewById(R.id.textview1);
		imageview2 = _view.findViewById(R.id.imageview2);
		linear2 = _view.findViewById(R.id.linear2);
		ask = _view.findViewById(R.id.ask);
		micBtn = _view.findViewById(R.id.micBtn);
		sendBtn = _view.findViewById(R.id.sendBtn);
		
		micBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		sendBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
	}
	
	private void initializeLogic() {
		_Ui();
		for(int _repeat11 = 0; _repeat11 < (int)(15); _repeat11++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("id", "a");
				chats.add(_item);
			}
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("id", "u");
				chats.add(_item);
			}
		}
		messages.setAdapter(new MessagesAdapter(chats));
		messages.setLayoutManager(new LinearLayoutManager(getContext()));
	}
	
	public void _Ui() {
		ask.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) 100)});
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)2, getResources().getColor(R.color.card_stroke), getResources().getColor(R.color.my_secondary_container)));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, getResources().getColor(R.color.surface_white)));
	}
	
	public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public MessagesAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.item_chat_ai, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final RelativeLayout relativelayout6 = _view.findViewById(R.id.relativelayout6);
			final ImageView imageview4 = _view.findViewById(R.id.imageview4);
			final com.google.android.material.card.MaterialCardView cardview4 = _view.findViewById(R.id.cardview4);
			final TextView tvAiMessage = _view.findViewById(R.id.tvAiMessage);
			final com.google.android.material.card.MaterialCardView cardview6 = _view.findViewById(R.id.cardview6);
			final TextView tvUserMessage = _view.findViewById(R.id.tvUserMessage);
			
			linear8.setVisibility(View.GONE);
			relativelayout6.setVisibility(View.GONE);
			if (_data.get((int)_position).get("id").toString() == "a") {
				linear8.setVisibility(View.VISIBLE);
				tvAiMessage.setText(getString(R.string.ai_welcome_message));
			} else if (_data.get((int)_position).get("id").toString() == "u") {
				relativelayout6.setVisibility(View.VISIBLE);
			} else {
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Not register yet!");
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