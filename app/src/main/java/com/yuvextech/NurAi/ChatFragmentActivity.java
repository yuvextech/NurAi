package com.yuvextech.NurAi;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.internal.LinkedTreeMap;

public class ChatFragmentActivity extends Fragment {
	
	private HashMap<String, Object> map = new HashMap<>();
	private String sako = "";
	private String data = "";
	
	private ArrayList<HashMap<String, Object>> chats = new ArrayList<>();
	
	private LinearLayout linear1;
	private RecyclerView messages;
	private LinearLayout linear3;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear2;
	private EditText ask;
	private ImageView micBtn;
	private ImageView sendBtn;
	
	private SpeechRecognizer speechRecognition;
	private RequestNetwork askAi;
	private RequestNetwork.RequestListener _askAi_request_listener;
	private SharedPreferences aiChat;
	private SharedPreferences setting;
	
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
		textview1 = _view.findViewById(R.id.textview1);
		imageview2 = _view.findViewById(R.id.imageview2);
		linear2 = _view.findViewById(R.id.linear2);
		ask = _view.findViewById(R.id.ask);
		micBtn = _view.findViewById(R.id.micBtn);
		sendBtn = _view.findViewById(R.id.sendBtn);
		speechRecognition = SpeechRecognizer.createSpeechRecognizer(getContext());
		askAi = new RequestNetwork((Activity) getContext());
		aiChat = getContext().getSharedPreferences("Chatting", Activity.MODE_PRIVATE);
		setting = getContext().getSharedPreferences("settings", Activity.MODE_PRIVATE);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		micBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Listening...");
			}
		});
		
		sendBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (ask.getText().toString().equals("")) {
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Enter your question about Islam!");
				} else {
					askAi.startRequestNetwork(RequestNetworkController.GET, "https://bf31jhdm60.execute-api.eu-west-2.amazonaws.com/dev/ask/".concat(ask.getText().toString()), "", _askAi_request_listener);
					ask.setText("");
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Please wait!");
				}
			}
		});
		
		speechRecognition.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				ask.setText(_result);
				speechRecognition.cancel();
				speechRecognition.destroy();
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Something when wrong!");
				speechRecognition.cancel();
				speechRecognition.destroy();
			}
		});
		
		_askAi_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				ArrayList<LinkedTreeMap<String, Object>> choices = (ArrayList<LinkedTreeMap<String, Object>>) map.get("choices");
				
				if (choices != null && !choices.isEmpty()) {
					LinkedTreeMap<String, Object> message = (LinkedTreeMap<String, Object>) choices.get(0).get("message");
					
					if (message != null) {
						String content = (String) message.get("content");
						sako = content;
						
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		_Ui();
		data = aiChat.getString("chat", "");
		chats = new Gson().fromJson(data, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
		messages.setAdapter(new MessagesAdapter(chats));
		messages.setLayoutManager(new LinearLayoutManager(getContext()));
	}
	
	public void _Ui() {
		ask.setFilters(new InputFilter[]{new InputFilter.LengthFilter((int) 100)});
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)30, (int)2, getResources().getColor(R.color.card_stroke), getResources().getColor(R.color.my_secondary_container)));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)15, getResources().getColor(R.color.surface)));
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
				tvAiMessage.setText(_data.get((int)_position).get("response").toString());
			} else if (_data.get((int)_position).get("id").toString() == "u") {
				relativelayout6.setVisibility(View.VISIBLE);
				tvUserMessage.setText(_data.get((int)_position).get("response").toString());
			} else {
				
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