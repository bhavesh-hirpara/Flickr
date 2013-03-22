# Android Flickr Integration

## Features
 * pick image from gallery and upload on Flickr

![Screenshot](https://raw.github.com/luminousman/Flickr/master/1.png)-

 
## Usage

``` java
//change your key and secreat key
private static final String API_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"; //$NON-NLS-1$
public static final String API_SEC = "xxxxxxxxxxxxx"; //$NON-NLS-1$

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
```

In AndroidManifest.xml

``` xml

<activity
            android:name="com.gmail.yuyang226.flickrj.sample.android.FlickrjActivity"
            android:label="@string/app_name"
            android:launchMode="singleTask" >
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data android:scheme="flickrj-android-sample-oauth" />
            </intent-filter>
</activity>
```

