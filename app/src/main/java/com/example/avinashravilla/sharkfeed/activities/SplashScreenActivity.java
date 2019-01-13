package com.example.avinashravilla.sharkfeed.activities;

import android.content.Intent;
import android.view.MotionEvent;

import com.example.avinashravilla.sharkfeed.R;

public class SplashScreenActivity extends BaseActivity {

    private float x1, x2;

    private final int MIN_DISTANCE = 150;

    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                float deltaX = x2 - x1;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    Intent intent = new Intent(SplashScreenActivity.this, FeedActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                    SplashScreenActivity.this.finish();
                } else {
                    // not a swipe
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
