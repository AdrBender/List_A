package com.adriano.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import android.widget.ProgressBar;
import android.util.Log;

/**
 * @author AdrBender
 */
public class SplashActivity extends AppCompatActivity {

	private static final String TAG = "com.adriano.lista.SplashActivity";
	
	ProgressBar splashProgress; //
	int SPLASH_TIME = 3000;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

		splashProgress = (ProgressBar)findViewById(R.id.splash_progress); //
        progress(); //
		
		Thread splash=new Thread() {
            public void run() {
                try{
                    sleep(SPLASH_TIME);
                    Intent i =new Intent (getBaseContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){
					Log.i(TAG, "Splash Exception " +e);
                }
            }
        };
        splash.start();
	}
		/*
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
		}, SPLASH_TIME);
    }*/
	
	//Method to run progress bar for 5 seconds
    private void progress() {
        ObjectAnimator.ofInt(splashProgress, "progress", 100)
			.setDuration(3000)
			.start();
    }
}

/*
	<resources>

    <style name="MyMaterialTheme" parent="MyMaterialTheme.Base">

    </style>

    <style name="MyMaterialTheme.Base" parent="Theme.AppCompat.Light.DarkActionBar">
        <item name="windowNoTitle">true</item>
        <item name="windowActionBar">false</item>
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
        <item name="colorAccent">@color/colorAccent</item>
    </style>

</resources>
*/

/*
Thread splash=new Thread() {
            public void run() {

                try{

// set sleep time
                    sleep(3*1000);
                    Intent i =new Intent (getBaseContext(),HomePage.class);
                    startActivity(i);
                    finish();
                }catch (Exception e){

                }
            }

        };
        splash.start();
*/
