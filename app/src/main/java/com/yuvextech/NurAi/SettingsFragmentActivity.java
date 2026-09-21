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
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.card.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SettingsFragmentActivity extends Fragment {
	
	private ScrollView vscroll2;
	private LinearLayout linear7;
	private TextView textview9;
	private TextView textview3;
	private MaterialCardView cardview3;
	private TextView textview4;
	private MaterialCardView cardview4;
	private LinearLayout linear8;
	private RelativeLayout relativelayout5;
	private RelativeLayout relativelayout6;
	private LinearLayout linear9;
	private RelativeLayout relativelayout7;
	private View view11;
	private RelativeLayout relativelayout8;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.settings_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		vscroll2 = _view.findViewById(R.id.vscroll2);
		linear7 = _view.findViewById(R.id.linear7);
		textview9 = _view.findViewById(R.id.textview9);
		textview3 = _view.findViewById(R.id.textview3);
		cardview3 = _view.findViewById(R.id.cardview3);
		textview4 = _view.findViewById(R.id.textview4);
		cardview4 = _view.findViewById(R.id.cardview4);
		linear8 = _view.findViewById(R.id.linear8);
		relativelayout5 = _view.findViewById(R.id.relativelayout5);
		relativelayout6 = _view.findViewById(R.id.relativelayout6);
		linear9 = _view.findViewById(R.id.linear9);
		relativelayout7 = _view.findViewById(R.id.relativelayout7);
		view11 = _view.findViewById(R.id.view11);
		relativelayout8 = _view.findViewById(R.id.relativelayout8);
	}
	
	private void initializeLogic() {
	}
	
}