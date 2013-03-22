package com.luminous.activity;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gmail.yuyang226.flickrj.sample.android.FlickrjActivity;
import com.luminous.utils.Debug;

public class MainActivity extends Activity {

	File fileUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button btnFlickr = (Button) findViewById(R.id.btnFlickr);
		btnFlickr.setOnClickListener(mFlickrClickListener);

		Button btnPick = (Button) findViewById(R.id.btnPick);
		btnPick.setOnClickListener(mPickClickListener);

	}

	View.OnClickListener mPickClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setType("image/*");
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
			startActivityForResult(intent, 102);
		}
	};

	View.OnClickListener mFlickrClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			if (fileUri == null) {
				Toast.makeText(getApplicationContext(), "Please pick photo",
						Toast.LENGTH_SHORT).show();

				return;
			}

			Intent intent = new Intent(getApplicationContext(),
					FlickrjActivity.class);
			intent.putExtra("flickImagePath", fileUri.getAbsolutePath());
			startActivity(intent);
		}
	};

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == 102) {

			if (resultCode == Activity.RESULT_OK) {
				Uri tmp_fileUri = data.getData();

				((ImageView) findViewById(R.id.imageView1))
						.setImageURI(tmp_fileUri);

				String selectedImagePath = getPath(tmp_fileUri);
				fileUri = new File(selectedImagePath);
				Debug.e("", "fileUri : " + fileUri.getAbsolutePath());
			}

		}
	};

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
