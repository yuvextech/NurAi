package com.yuvextech.NurAi;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
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

public class QuranActivity extends AppCompatActivity {
	
	// Stores generated drawables to save memory and CPU
	private final Map<String, Drawable> drawableCache = new HashMap<>();
	private boolean withTranslate = false;
	private String json3 = "";
	private String currentTap = "";
	private String selectedVerse = "";
	private String names = "";
	private String totally = "";
	private String translations = "";
	private String quran = "";
	
	private ArrayList<HashMap<String, Object>> surah = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> quranListMap = new ArrayList<>();
	
	private LinearLayout linearToolBar;
	private ScrollView vscroll1;
	private ListView listview1;
	private LinearLayout linear3;
	private ImageView options;
	private TextView toolBarText;
	private TextView toolBarSubText;
	private LinearLayout linear2;
	private ImageView imageview2;
	private TextView textView;
	
	private SharedPreferences sh;
	private Intent mm = new Intent();
	private Intent mm2 = new Intent();
	private Intent mm3 = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.quran);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linearToolBar = findViewById(R.id.linearToolBar);
		vscroll1 = findViewById(R.id.vscroll1);
		listview1 = findViewById(R.id.listview1);
		linear3 = findViewById(R.id.linear3);
		options = findViewById(R.id.options);
		toolBarText = findViewById(R.id.toolBarText);
		toolBarSubText = findViewById(R.id.toolBarSubText);
		linear2 = findViewById(R.id.linear2);
		imageview2 = findViewById(R.id.imageview2);
		textView = findViewById(R.id.textView);
		sh = getSharedPreferences("settings", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		withTranslate = false;
		_Ui();
		textView.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event){
				int ev = event.getAction();
				switch (ev) {
					case MotionEvent.ACTION_DOWN:
					
					
					
					break;
					case MotionEvent.ACTION_UP:
					
					TextView tv = (TextView) v;
					int offset = getPreciseOffset(tv, event);
					if (offset != -1) {
						selectVerse(tv, offset);
					}
					
					break;
				} return true;
			}
		});
	}
	
	
	private void applyVerseImages(TextView tv) {
		String text = tv.getText().toString();
		if (TextUtils.isEmpty(text)) return;
		
		SpannableStringBuilder ssb = new SpannableStringBuilder(text);
		// Added parentheses () to capture the number and \\s* to handle potential spaces
		Pattern pattern = Pattern.compile("﴿\\s*(\\d+)\\s*﴾");
		Matcher matcher = pattern.matcher(text);
		
		int size = (int) (tv.getTextSize() * 1.3);
		
		while (matcher.find()) {
			try {
				// Now group(1) will correctly pull only the digits
				String verseNumber = matcher.group(1); 
				
				Drawable combinedDrawable = drawableCache.get(verseNumber);
				
				if (combinedDrawable == null) {
					combinedDrawable = getVerseDrawable(verseNumber, size);
					drawableCache.put(verseNumber, combinedDrawable);
				}
				
				if (combinedDrawable != null) {
					combinedDrawable.setBounds(0, 0, size, size);
					ImageSpan imageSpan = new ImageSpan(combinedDrawable, ImageSpan.ALIGN_BASELINE);
					ssb.setSpan(imageSpan, matcher.start(), matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			} catch (Exception e) {
				// This prevents a single bad marker from crashing the whole app
				Log.e("VerseError", "Error processing marker at " + matcher.start());
			}
		}
		tv.setText(ssb);
	}
	private Drawable getVerseDrawable(String number, int size) {
		// 1. Load the frame image
		Bitmap frame = BitmapFactory.decodeResource(getResources(), R.drawable.ayat);
		Bitmap scaledFrame = Bitmap.createScaledBitmap(frame, size, size, true);
		
		// 2. Create a blank canvas to draw on
		Bitmap canvasBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(canvasBitmap);
		
		// 3. Draw the frame
		canvas.drawBitmap(scaledFrame, 0, 0, null);
		
		// 4. Draw the number in the center
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(Color.BLACK); // Set number color
		paint.setTextSize(size * 0.4f); // Adjust size to fit inside frame
		paint.setTextAlign(Paint.Align.CENTER);
		
		// Calculate center position
		float xPos = canvas.getWidth() / 2;
		float yPos = (canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2);
		
		canvas.drawText(number, xPos, yPos, paint);
		
		return new BitmapDrawable(getResources(), canvasBitmap);
	}
	private void selectVerse(TextView tv, int offset) {
		CharSequence text = tv.getText();
		if (!(text instanceof Spannable)) return;
		
		Spannable spannable = (Spannable) text;
		int length = spannable.length();
		if (offset < 0 || offset >= length) return;
		
		// We use the same pattern as applyVerseImages
		Pattern pattern = Pattern.compile("﴿\\s*(\\d+)\\s*﴾");
		Matcher matcher = pattern.matcher(text.toString());
		
		int verseStart = 0;
		int verseEnd = length;
		int lastMarkerEnd = 0;
		boolean found = false;
		
		while (matcher.find()) {
			int markerEnd = matcher.end();
			// If the tap is before the end of this marker, this is our verse!
			if (offset < markerEnd) {
				verseStart = lastMarkerEnd;
				verseEnd = markerEnd;
				found = true;
				break;
			}
			lastMarkerEnd = markerEnd;
		}
		
		// If tap was after the last marker
		if (!found) {
			verseStart = lastMarkerEnd;
			verseEnd = length;
		}
		
		// Safety check for empty ranges
		if (verseStart >= verseEnd) return;
		
		// Remove old highlights
		BackgroundColorSpan[] spans = spannable.getSpans(0, length, BackgroundColorSpan.class);
		for (BackgroundColorSpan span : spans) {
			spannable.removeSpan(span);
		}
		
		// Update the global selectedVerse variable for the copy function
		selectedVerse = text.toString().substring(verseStart, verseEnd).trim();
		currentTap = selectedVerse;
		
		// Apply the highlight (0x5590EE90 is semi-transparent light green)
		spannable.setSpan(
		new BackgroundColorSpan(0x5590EE90),
		verseStart,
		verseEnd,
		Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
		);
		
		// IMPORTANT: DO NOT call tv.setText(spannable) here.
		// The view will update automatically because we modified the existing spannable object.
	}
	
	
	private void copySelectedVerse() {
		if (selectedVerse.isEmpty()) {
			Toast.makeText(this, "Please select a verse first", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Regex to remove the marker: ﴿ followed by digits followed by ﴾
		// We also trim extra spaces that might be left behind
		String cleanText = selectedVerse.replaceAll("﴿\\s*\\d+\\s*﴾", "").trim();
		
		android.content.ClipboardManager clipboard = (android.content.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		android.content.ClipData clip = android.content.ClipData.newPlainText("Selected Verse", cleanText);
		if (clipboard != null) {
			clipboard.setPrimaryClip(clip);
			Toast.makeText(this, "Verse copied without markers!", Toast.LENGTH_SHORT).show();
		}
	}
	private void jumpToVerse(int verseNumber) {
		String text = textView.getText().toString();
		// Search for the specific verse marker ﴿verseNumber﴾
		Pattern pattern = Pattern.compile("﴿\\s*" + verseNumber + "\\s*﴾");
		Matcher matcher = pattern.matcher(text);
		
		if (matcher.find()) {
			Layout layout = textView.getLayout();
			if (layout != null) {
				// Get the line number where the verse marker starts
				int line = layout.getLineForOffset(matcher.start());
				// Get the vertical top coordinate of that line
				int y = layout.getLineTop(line);
				
				// Scroll the ScrollView to that position
				vscroll1.smoothScrollTo(0, y);
				
				// Highlight the verse automatically
				selectVerse(textView, matcher.start());
			}
		} else {
			Toast.makeText(this, "Verse not found", Toast.LENGTH_SHORT).show();
		}
	}
	private void showJumpDialog() {
		final EditText input = new EditText(this);
		input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		input.setHint("Enter verse number");
		
		new AlertDialog.Builder(this)
		.setTitle("Jump to Verse")
		.setView(input)
		.setPositiveButton("Go", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String val = input.getText().toString();
				if (!val.isEmpty()) {
					jumpToVerse(Integer.parseInt(val));
				}
			}
		})
		.setNegativeButton("Cancel", null)
		.show();
	}
	
	
	private int getPreciseOffset(TextView tv, MotionEvent event) {
		Layout layout = tv.getLayout();
		if (layout == null) return -1;
		
		int x = (int) event.getX() - tv.getTotalPaddingLeft() + tv.getScrollX();
		int y = (int) event.getY() - tv.getTotalPaddingTop() + tv.getScrollY();
		
		int line = layout.getLineForVertical(y);
		int start = layout.getLineStart(line);
		int end = layout.getLineEnd(line);
		
		float touchX = x;
		int bestOffset = start;
		float minDist = Float.MAX_VALUE;
		
		for (int i = start; i < end; i++) {
			float glyphX = layout.getPrimaryHorizontal(i);
			float dist = Math.abs(glyphX - touchX);
			if (dist < minDist) {
				minDist = dist;
				bestOffset = i;
			}
		}
		return bestOffset;
	}
	
	private void clearSelection() {
		CharSequence text = textView.getText();
		if (text instanceof Spannable) {
			Spannable spannable = (Spannable) text;
			
			// 1. Find all background color spans (the green highlights)
			BackgroundColorSpan[] spans = spannable.getSpans(0, spannable.length(), BackgroundColorSpan.class);
			
			// 2. Remove them
			for (BackgroundColorSpan span : spans) {
				spannable.removeSpan(span);
			}
			
			// 3. Reset the UI elements
			selectedVerse = "";
			
			
			Toast.makeText(this, "Selection cleared", Toast.LENGTH_SHORT).show();
		}
	}
	// Helper to remove Arabic diacritics for easier searching
	private String normalizeArabic(String text) {
		return text.replaceAll("[\\u064B-\\u0652\\u0670]", "");
	}
	
	private void searchInText(String query) {
		if (query.isEmpty()) return;
		
		Spannable spannable = (Spannable) textView.getText();
		String fullText = spannable.toString();
		
		// Normalize both for a "fuzzy" match
		String normalizedFull = normalizeArabic(fullText);
		String normalizedQuery = normalizeArabic(query);
		
		// Clear previous search highlights (using a different color, like Cyan)
		BackgroundColorSpan[] oldSpans = spannable.getSpans(0, spannable.length(), BackgroundColorSpan.class);
		for (BackgroundColorSpan span : oldSpans) {
			spannable.removeSpan(span);
		}
		
		int index = normalizedFull.indexOf(normalizedQuery);
		if (index == -1) {
			Toast.makeText(this, "Word not found", Toast.LENGTH_SHORT).show();
			return;
		}
		
		// Loop to find all occurrences
		while (index >= 0) {
			// We map the normalized index back to the original text length
			// (Note: In simple apps, usually the index is very close)
			spannable.setSpan(
			new BackgroundColorSpan(Color.CYAN),
			index,
			index + query.length(), // This is an approximation if diacritics exist
			Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
			);
			
			// Scroll to the first match found
			if (index == normalizedFull.indexOf(normalizedQuery)) {
				int line = textView.getLayout().getLineForOffset(index);
				vscroll1.smoothScrollTo(0, textView.getLayout().getLineTop(line) - 100);
			}
			
			index = normalizedFull.indexOf(normalizedQuery, index + 1);
		}
	}
	
	public void _Ui() {
		if (withTranslate) {
			vscroll1.setVisibility(View.GONE);
			try {
				java.io.InputStream is = this.getAssets().open("quran_en.json");
				int size = is.available();
				byte[] buffer = new byte[size];
				is.read(buffer);
				is.close();
				json3 = new String(buffer, "UTF-8");
				
				_createJSONArray(json3);
				_get(Double.parseDouble(getIntent().getStringExtra("sorah")));
				surah = new Gson().fromJson(jsonarray.toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				names = surah.get((int)Double.parseDouble(getIntent().getStringExtra("sorah"))).get("name").toString();
				translations = surah.get((int)Double.parseDouble(getIntent().getStringExtra("sorah"))).get("translation").toString();
				totally = surah.get((int)Double.parseDouble(getIntent().getStringExtra("sorah"))).get("total_verses").toString();
				toolBarText.setText("سورة ".concat(names).concat(" / ".concat(translations)));
				toolBarSubText.setText("verses  ".concat(String.valueOf((long)(Double.parseDouble(totally))).concat("  الآيات")));
				_setUp("verses");
				quranListMap = new Gson().fromJson(jsonarray.toString(), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				listview1.setAdapter(new Listview1Adapter(quranListMap));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			} catch(Exception e) {
				
			}
		} else {
			toolBarText.setText("سورة ".concat(names).concat(" / ".concat(translations)));
			toolBarSubText.setText("verses  ".concat(String.valueOf((long)(Double.parseDouble(totally))).concat("  الآيات")));
			try {
				String sorahIndex = getIntent().getStringExtra("sorah"); // e.g., "0"
				int fileNumber = Integer.parseInt(sorahIndex) + 1;
				String fileName = "quran/surah"+fileNumber+".txt";
				
				InputStream is = this.getAssets().open(fileName);
				int size = is.available();
				byte[] buffer = new byte[size];
				is.read(buffer);
				is.close();
				quran = new String(buffer, "UTF-8");
				
			} catch(Exception e) {
			}
			textView.setText(quran);
			textView.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/isep_misbah.ttf"), 0);
			applyVerseImages(textView);
		}
		translations = String.valueOf((long)(Double.parseDouble(getIntent().getStringExtra("sorah")) + 1));
	}
	
	
	public void _library() {
	}
	private org.json.JSONArray jsonarray;
	private org.json.JSONObject json;
	private org.json.JSONObject json2;
	public void ArabWare () {
	}
	
	
	public void _set(final TextView _textview, final String _key) {
		try{
			_textview.setText(json2.getString(_key));
		}catch(Exception e){
			
		}
	}
	
	
	public void _setUp(final String _key) {
		try{
			jsonarray = json.getJSONArray(_key);
		}catch(Exception e){
			
		}
	}
	
	
	public void _get(final double _num) {
		try {
			// We save the result to json2 (the verse) 
			// AND json (the surah) so they can be used interchangeably
			json2 = jsonarray.getJSONObject((int) _num);
			json = jsonarray.getJSONObject((int) _num); 
		} catch(Exception e) {
		}
	}
	
	
	public void _createJSONObject(final String _string) {
		try{
			json = new org.json.JSONObject(_string);
		}catch(Exception e){
			
		}
	}
	
	
	public void _createJSONArray(final String _string) {
		try{
			jsonarray = new org.json.JSONArray(_string);
		}catch(Exception e){
			
		}
	}
	
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
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
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.surah_reader_item, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout top = _view.findViewById(R.id.top);
			final TextView verseText = _view.findViewById(R.id.verseText);
			final LinearLayout bottom = _view.findViewById(R.id.bottom);
			final TextView surahName = _view.findViewById(R.id.surahName);
			final ImageView bismillah = _view.findViewById(R.id.bismillah);
			final TextView translationText = _view.findViewById(R.id.translationText);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final ImageView copyBtn = _view.findViewById(R.id.copyBtn);
			final ImageView shareBtn = _view.findViewById(R.id.shareBtn);
			final ImageView bookmarkBtn = _view.findViewById(R.id.bookmarkBtn);
			
			top.setVisibility(View.GONE);
			verseText.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/isep_misbah.ttf"), 0);
			if (_data.get((int)_position).get("text").toString().equals("بِسۡمِ ٱللَّهِ ٱلرَّحۡمَٰنِ ٱلرَّحِيمِ")) {
				_data.remove((int)(_position));
			}
			if (_position == 0) {
				top.setVisibility(View.VISIBLE);
			}
			surahName.setText(names.concat(""));
			verseText.setText(_data.get((int)_position).get("text").toString().concat("﴿".concat(String.valueOf((long)(Double.parseDouble(_data.get((int)_position).get("id").toString()))).concat("﴾"))));
			translationText.setText(_data.get((int)_position).get("translation").toString());
			applyVerseImages(verseText);
			
			return _view;
		}
	}
}