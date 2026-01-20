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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class SettingsFragmentActivity extends Fragment {
	
	private MaterialToolbar settingsToolbar;
	private ScrollView vscroll2;
	private LinearLayout linear7;
	private TextView textview3;
	private MaterialCardView cardview3;
	private TextView textview4;
	private MaterialCardView cardview4;
	private LinearLayout linear8;
	private RelativeLayout relativelayout5;
	private RelativeLayout relativelayout6;
	private ImageView ivSettingIcon;
	private TextView tvSettingTitle;
	private ImageView imageview4;
	private ImageView imageview8;
	private TextView textview6;
	private ImageView imageview9;
	private LinearLayout linear9;
	private RelativeLayout relativelayout7;
	private View view11;
	private RelativeLayout relativelayout8;
	private ImageView imageview10;
	private TextView textview7;
	private ImageView imageview11;
	private ImageView imageview12;
	private TextView textview8;
	private ImageView imageview13;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.settings_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		settingsToolbar = _view.findViewById(R.id.settingsToolbar);
		vscroll2 = _view.findViewById(R.id.vscroll2);
		linear7 = _view.findViewById(R.id.linear7);
		textview3 = _view.findViewById(R.id.textview3);
		cardview3 = _view.findViewById(R.id.cardview3);
		textview4 = _view.findViewById(R.id.textview4);
		cardview4 = _view.findViewById(R.id.cardview4);
		linear8 = _view.findViewById(R.id.linear8);
		relativelayout5 = _view.findViewById(R.id.relativelayout5);
		relativelayout6 = _view.findViewById(R.id.relativelayout6);
		ivSettingIcon = _view.findViewById(R.id.ivSettingIcon);
		tvSettingTitle = _view.findViewById(R.id.tvSettingTitle);
		imageview4 = _view.findViewById(R.id.imageview4);
		imageview8 = _view.findViewById(R.id.imageview8);
		textview6 = _view.findViewById(R.id.textview6);
		imageview9 = _view.findViewById(R.id.imageview9);
		linear9 = _view.findViewById(R.id.linear9);
		relativelayout7 = _view.findViewById(R.id.relativelayout7);
		view11 = _view.findViewById(R.id.view11);
		relativelayout8 = _view.findViewById(R.id.relativelayout8);
		imageview10 = _view.findViewById(R.id.imageview10);
		textview7 = _view.findViewById(R.id.textview7);
		imageview11 = _view.findViewById(R.id.imageview11);
		imageview12 = _view.findViewById(R.id.imageview12);
		textview8 = _view.findViewById(R.id.textview8);
		imageview13 = _view.findViewById(R.id.imageview13);
	}
	
	private void initializeLogic() {
	}
	
}