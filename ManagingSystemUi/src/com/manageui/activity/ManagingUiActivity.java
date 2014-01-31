package com.manageui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.immersivedemo.activity.R;

public class ManagingUiActivity extends SherlockActivity {
	private CheckBox mCb_hide_navigation, mCb_full_screen, mCb_low_profile,
			mCb_Stable_layout, mCb_full_screen_layout,
			mCb_hide_navigation_layout, mCb_Immersive, mCb_Immersive_Sticky;
	private boolean isLowerVersion = false;
	private int newUiOptions;
	private View decorView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_managing_ui);
		mCb_full_screen = (CheckBox) findViewById(R.id.cb_full_screen);

		mCb_full_screen_layout = (CheckBox) findViewById(R.id.cb_full_screen_layout);

		mCb_hide_navigation = (CheckBox) findViewById(R.id.cb_hide_navigation);

		mCb_hide_navigation_layout = (CheckBox) findViewById(R.id.cb_hide_navigation_layout);

		mCb_low_profile = (CheckBox) findViewById(R.id.cb_low_profile);
		mCb_Stable_layout = (CheckBox) findViewById(R.id.cb_stable_layout);
		mCb_Immersive = (CheckBox) findViewById(R.id.cb_immersive);
		mCb_Immersive_Sticky = (CheckBox) findViewById(R.id.cb_immersive_sticky);

		mCb_full_screen.setOnCheckedChangeListener(new CheckChange());
		mCb_full_screen_layout.setOnCheckedChangeListener(new CheckChange());
		mCb_hide_navigation.setOnCheckedChangeListener(new CheckChange());
		mCb_hide_navigation_layout
				.setOnCheckedChangeListener(new CheckChange());
		mCb_low_profile.setOnCheckedChangeListener(new CheckChange());
		mCb_Stable_layout.setOnCheckedChangeListener(new CheckChange());
		mCb_Immersive.setOnCheckedChangeListener(new CheckChange());
		mCb_Immersive_Sticky.setOnCheckedChangeListener(new CheckChange());
	
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Build.VERSION.SDK_INT < 19) {
			mCb_Immersive.setEnabled(false);
			mCb_Immersive_Sticky.setEnabled(false);
		} else if (Build.VERSION.SDK_INT < 16) {
			isLowerVersion = true;
			mCb_full_screen_layout.setEnabled(false);
			mCb_hide_navigation_layout.setEnabled(false);
			mCb_Stable_layout.setEnabled(false);
		} else if (Build.VERSION.SDK_INT < 14) {
			mCb_low_profile.setEnabled(false);
			mCb_hide_navigation.setEnabled(false);
			isLowerVersion = true;
		}

	}

	public class CheckChange implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			switch (buttonView.getId()) {
			case R.id.cb_full_screen:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_FULLSCREEN;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
				}
				break;
			case R.id.cb_full_screen_layout:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
				}
				break;
			case R.id.cb_hide_navigation:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
				}
				break;
			case R.id.cb_hide_navigation_layout:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
				}
				break;
			case R.id.cb_low_profile:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_LOW_PROFILE;
				}
				break;
			case R.id.cb_stable_layout:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
				}
				break;
			case R.id.cb_immersive:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE;
				}
				break;
			case R.id.cb_immersive_sticky:
				if (isChecked) {
					newUiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
				} else {
					newUiOptions &= ~View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
				}
				break;
			}
		}

	}
@Override
public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
	getSupportMenuInflater().inflate(R.menu.main, menu);
	return super.onCreateOptionsMenu(menu);
}
	
@Override
public boolean onOptionsItemSelected(
		com.actionbarsherlock.view.MenuItem item) {
	if (item.getItemId() == R.id.sample_action) {
		if(!isLowerVersion)
		{
			decorView = this.getWindow().getDecorView();
			int uiOptions = decorView.getSystemUiVisibility();
			newUiOptions |= uiOptions;
			
		decorView.setSystemUiVisibility(newUiOptions);
		}else
		{
			if(mCb_full_screen.isChecked())
			{
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
						WindowManager.LayoutParams.FLAG_FULLSCREEN);
			}
		}
	}

	return true;
}
}
