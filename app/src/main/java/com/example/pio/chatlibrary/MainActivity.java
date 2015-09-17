package com.example.pio.chatlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.pio.chatlibrary.login.LoginActivity;
import com.example.pio.chatlibrary.login.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonSignIn;
    private Button buttonRegister;

    public void signin(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void register(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);

        Animation animationLeft = AnimationUtils.loadAnimation(this, R.anim.animation_left);
        Animation animationRight = AnimationUtils.loadAnimation(this, R.anim.animation_right);
        buttonSignIn.setAnimation(animationLeft);
        buttonRegister.setAnimation(animationRight);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

    }
//    private void moveViewToScreenCenter( View view )
//    {
//        DisplayMetrics dm = new DisplayMetrics();
//        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
//
//        int originalPos[] = new int[2];
//        originalPos[0] = view.getLeft();
//        originalPos[1]= view.getTop();
//
//        int xDest = dm.widthPixels/2;
//        int o = view.getMeasuredWidth();
//
//        TranslateAnimation anim = new TranslateAnimation( -2*o, xDest/2 , 0, 0 );
//        anim.setDuration(1000);
//        anim.setFillAfter( true );
//        view.startAnimation(anim);
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
