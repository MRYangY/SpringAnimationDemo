package com.example.yangyu.springanimationdemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;
    private Button normalAnimation;
    private Button springAnimationOne;
    private Button springAnimationTwo;
    private Button springAnimationThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.animation_image);
        normalAnimation = (Button) findViewById(R.id.normal_animation);
        springAnimationOne = (Button) findViewById(R.id.spring_animation_one);
        springAnimationTwo = (Button) findViewById(R.id.spring_animation_two);
        springAnimationThree = (Button) findViewById(R.id.spring_animation_three);

        normalAnimation.setOnClickListener(this);
        springAnimationOne.setOnClickListener(this);
        springAnimationTwo.setOnClickListener(this);
        springAnimationThree.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_animation:
                onScaleAnimation();
                break;
            case R.id.spring_animation_one:
                onScaleAnimationBySpringWayOne();
                break;
            case R.id.spring_animation_two:
                onScaleAnimationBySpringWayTwo();
                break;
            case R.id.spring_animation_three:
                onScaleAnimationBySpringWayThree();
                break;
        }
    }

    private void onScaleAnimation() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.8f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.8f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animatorX, animatorY);
        set.start();
    }

    private void onScaleAnimationBySpringWayOne() {
//        ScaleAnimation animation =new ScaleAnimation(1.0f,1.8f,1.0f,1.8f);
//        animation.setDuration(1000);
//        animation.setInterpolator(new SpringScaleInterpolator(0.4f));
//        imageView.startAnimation(animation);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(imageView, "scaleX", 1.0f, 1.8f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "scaleY", 1.0f, 1.8f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(new SpringScaleInterpolator(0.4f));
        set.playTogether(animatorX, animatorY);
        set.start();

    }

    private void onScaleAnimationBySpringWayTwo() {
        SpringSystem springSystem = SpringSystem.create();
        Spring spring = springSystem.createSpring();
        spring.setCurrentValue(1.0f);
        spring.setSpringConfig(new SpringConfig(50, 5));
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                super.onSpringUpdate(spring);
                float currentValue = (float) spring.getCurrentValue();
                imageView.setScaleX(currentValue);
                imageView.setScaleY(currentValue);
            }
        });
        spring.setEndValue(1.8f);
    }

    private void onScaleAnimationBySpringWayThree() {
        SpringAnimation animationX = new SpringAnimation(imageView, SpringAnimation.SCALE_X, 1.8f);
        SpringAnimation animationY = new SpringAnimation(imageView, SpringAnimation.SCALE_Y, 1.8f);
        animationX.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
        animationX.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        animationX.setStartValue(1.0f);

        animationY.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
        animationY.getSpring().setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY);
        animationY.setStartValue(1.0f);

        animationX.start();
        animationY.start();
    }


}
