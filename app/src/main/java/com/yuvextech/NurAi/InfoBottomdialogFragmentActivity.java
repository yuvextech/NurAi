package com.yuvextech.NurAi;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.card.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.google.gson.internal.LinkedTreeMap;

public class InfoBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	// Stores generated drawables to save memory and CPU
	private final Map<String, Drawable> drawableCache = new HashMap<>();
	private String currentTap = "";
	private String selectedVerse = "";
	private String sako = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String id = "";
	private String AyaId = "";
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private ScrollView vscroll1;
	private ImageView imageview2;
	private LinearLayout linear4;
	private ImageView imageview3;
	private TextView textview1;
	private TextView textview2;
	private LinearLayout linear10;
	private LinearLayout linear5;
	private LinearLayout linear7;
	private HorizontalScrollView hscroll1;
	private LinearLayout linearThinking;
	private LinearLayout linearAiResponse;
	private TextView textviewAya;
	private LinearLayout linear6;
	private TextView textviewTranslation;
	private LinearLayout linear9;
	private ImageView copyBtn;
	private ImageView shareBtn;
	private ImageView bookmarkBtn;
	private TextView textview3;
	private ImageView imageview5;
	private LinearLayout linear8;
	private TextView textview4;
	private TextView textview5;
	private TextView textview6;
	private ImageView imageview6;
	private TextView textview7;
	private ImageView imageview4;
	private MaterialCardView cardview4;
	private TextView tvAiMessage;
	
	private Intent yg = new Intent();
	private RequestNetwork askAi;
	private RequestNetwork.RequestListener _askAi_request_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.info_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		linear1 = _view.findViewById(R.id.linear1);
		linear2 = _view.findViewById(R.id.linear2);
		linear3 = _view.findViewById(R.id.linear3);
		vscroll1 = _view.findViewById(R.id.vscroll1);
		imageview2 = _view.findViewById(R.id.imageview2);
		linear4 = _view.findViewById(R.id.linear4);
		imageview3 = _view.findViewById(R.id.imageview3);
		textview1 = _view.findViewById(R.id.textview1);
		textview2 = _view.findViewById(R.id.textview2);
		linear10 = _view.findViewById(R.id.linear10);
		linear5 = _view.findViewById(R.id.linear5);
		linear7 = _view.findViewById(R.id.linear7);
		hscroll1 = _view.findViewById(R.id.hscroll1);
		linearThinking = _view.findViewById(R.id.linearThinking);
		linearAiResponse = _view.findViewById(R.id.linearAiResponse);
		textviewAya = _view.findViewById(R.id.textviewAya);
		linear6 = _view.findViewById(R.id.linear6);
		textviewTranslation = _view.findViewById(R.id.textviewTranslation);
		linear9 = _view.findViewById(R.id.linear9);
		copyBtn = _view.findViewById(R.id.copyBtn);
		shareBtn = _view.findViewById(R.id.shareBtn);
		bookmarkBtn = _view.findViewById(R.id.bookmarkBtn);
		textview3 = _view.findViewById(R.id.textview3);
		imageview5 = _view.findViewById(R.id.imageview5);
		linear8 = _view.findViewById(R.id.linear8);
		textview4 = _view.findViewById(R.id.textview4);
		textview5 = _view.findViewById(R.id.textview5);
		textview6 = _view.findViewById(R.id.textview6);
		imageview6 = _view.findViewById(R.id.imageview6);
		textview7 = _view.findViewById(R.id.textview7);
		imageview4 = _view.findViewById(R.id.imageview4);
		cardview4 = _view.findViewById(R.id.cardview4);
		tvAiMessage = _view.findViewById(R.id.tvAiMessage);
		askAi = new RequestNetwork((Activity) getContext());
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (((BottomSheetDialog) getDialog()).getBehavior().getState() == BottomSheetBehavior.STATE_EXPANDED) {
					((BottomSheetDialog) getDialog()).getBehavior().setState(BottomSheetBehavior.STATE_COLLAPSED);
				} else {
					((BottomSheetDialog) getDialog()).getBehavior().setState(BottomSheetBehavior.STATE_EXPANDED);
				}
			}
		});
		
		copyBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getContext().getSystemService(getContext().getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", getArguments().getString("Aya").concat("\n\n------------------------------------------------------\n\n".concat(getArguments().getString("Translation")))));
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Ayah copied");
			}
		});
		
		shareBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		bookmarkBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		textview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linearThinking.setVisibility(View.VISIBLE);
				linearThinking.animate().translationY(0); 
				askAi.startRequestNetwork(RequestNetworkController.GET, "https://bf31jhdm60.execute-api.eu-west-2.amazonaws.com/dev/ask/".concat("In quran chapter ".concat(id).concat("Explain verse ".concat(AyaId))), "", _askAi_request_listener);
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linearThinking.setVisibility(View.VISIBLE);
				linearThinking.animate().translationY(0); 
				askAi.startRequestNetwork(RequestNetworkController.GET, "https://bf31jhdm60.execute-api.eu-west-2.amazonaws.com/dev/ask/".concat("Historical context of verse ".concat(AyaId.concat(" In quran chapter ".concat(id)))), "", _askAi_request_listener);
			}
		});
		
		textview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				linearThinking.setVisibility(View.VISIBLE);
				linearThinking.animate().translationY(0); 
				askAi.startRequestNetwork(RequestNetworkController.GET, "https://bf31jhdm60.execute-api.eu-west-2.amazonaws.com/dev/ask/".concat("Recitation guide for verse ".concat(AyaId.concat(" In quran chapter ".concat(id)))), "", _askAi_request_listener);
			}
		});
		
		tvAiMessage.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				((ClipboardManager) getContext().getSystemService(getContext().getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", tvAiMessage.getText().toString()));
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Text copied to clipboard");
				return true;
			}
		});
		
		_askAi_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (getView() != null) {
					map = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
					ArrayList<LinkedTreeMap<String, Object>> choices = (ArrayList<LinkedTreeMap<String, Object>>) map.get("choices");
					
					if (choices != null && !choices.isEmpty()) {
						LinkedTreeMap<String, Object> message = (LinkedTreeMap<String, Object>) choices.get(0).get("message");
						
						if (message != null) {
							String content = (String) message.get("content");
							sako = content;
							
						}
					}
					tvAiMessage.setText(sako);
					linearThinking.setVisibility(View.GONE);
					linearThinking.animate().translationY(500); 
					linearAiResponse.setVisibility(View.VISIBLE);
					linearAiResponse.animate().translationY(0); 
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				if (getView() != null) {
					tvAiMessage.setText(getString(R.string.error_ai_connection));
					linearThinking.setVisibility(View.GONE);
					linearThinking.animate().translationY(500); 
					linearAiResponse.setVisibility(View.VISIBLE);
					linearAiResponse.animate().translationY(0); 
				}
			}
		};
	}
	
	private void initializeLogic() {
		id = getArguments().getString("surah");
		AyaId = getArguments().getString("AyaId");
		int idSurahInt = Integer.parseInt(id);
		textview1.setText(String.format("surah%03d", idSurahInt));
		textview2.setText(getArguments().getString("Transliterate"));
		textviewAya.setText(getArguments().getString("Aya"));
		textviewTranslation.setText("\"".concat(getArguments().getString("Translation").concat("\"")));
		_Ui();
		// setPeekHeight dynamically!
		((BottomSheetDialog) getDialog()).getBehavior().setPeekHeight(130);
		Dialog dialog = getDialog();
		// Set the background of the BottomSheetDialog's window to transparent
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		// Also, set the background of the bottom sheet itself to transparent
		dialog.setOnShowListener(dialogInterface -> {
			BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialogInterface;
			FrameLayout bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			if (bottomSheet != null) {
				bottomSheet.setBackground(new ColorDrawable(Color.TRANSPARENT));
			}
		});
		
		if (dialog instanceof BottomSheetDialog) {
			BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
			// Ensure the slide-down animation is active
			bottomSheetDialog.setDismissWithAnimation(true);
			
			// Access the behavior and add your custom logic
			BottomSheetBehavior<?> behavior = bottomSheetDialog.getBehavior();
			behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
				@Override
				public void onStateChanged(@NonNull View bottomSheet, int newState) {
					switch(newState) {
						case BottomSheetBehavior.STATE_EXPANDED: {
							imageview3.setImageResource(R.drawable.collapse);
							break;
						}
						case BottomSheetBehavior.STATE_COLLAPSED: {
							imageview3.setImageResource(R.drawable.expand);
							break;
						}
						case BottomSheetBehavior.STATE_HIDDEN: {
							
							break;
						}
						default: {
							
							break;
						}
					}
				}
				
				@Override
				public void onSlide(@NonNull View bottomSheet, float slideOffset) {
					// Change the alpha of a view based on the slide
					// slideOffset goes from 0.0 (collapsed) to 1.0 (expanded)
					// and 0.0 to -1.0 when hiding.
					if (getView() != null) {
						getView().setAlpha(Math.max(0f, 1f + slideOffset));
					}
					
				}
			});
		}
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
		paint.setColor(getResources().getColor(R.color.text_primary)); // Set number color
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
	
	public void _Ui() {
		int[] linear1_qwer = {getResources().getColor(R.color.backgroundColor), getResources().getColor(R.color.backgroundColor) }; 
		android.graphics.drawable.GradientDrawable linear1_qaz = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, linear1_qwer);
		linear1_qaz.setCornerRadii(new float[]{25,25,25,25,0,0,0,0});
		linear1_qaz.setStroke(0, getResources().getColor(R.color.colorPrimary));
		linear1.setElevation((float) 0);
		linear1.setBackground(linear1_qaz);
		textview1.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/surah_name_v4.ttf"), 0);
		applyVerseImages(textviewAya);
		textviewAya.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/isep_misbah.ttf"), 0);
		linear5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)25, (int)1, getResources().getColor(R.color.card_stroke), getResources().getColor(R.color.pop)));
		linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)5, 0xFF616161));
		textview4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, getResources().getColor(R.color.pop)));
		textview5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, getResources().getColor(R.color.pop)));
		textview6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)25, getResources().getColor(R.color.pop)));
		linearThinking.setVisibility(View.GONE);
		linearAiResponse.setVisibility(View.GONE);
		textview4.setElevation((int)2);
		textview5.setElevation((int)2);
		textview6.setElevation((int)2);
	}
	
}