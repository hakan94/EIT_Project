package com.example.hozturk.app_no_db;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by H.Ozturk on 25.11.17.
 */

public class ClassManagement extends AppCompatActivity{
    private static int NBR_ITMS;
    private GridLayout grid;
    private ScrollView scrollView;
    private ValueAnimator animator;
    private AtomicBoolean isScrolling = new AtomicBoolean(false);
    private Button studentsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_management);

        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        scrollView.setSmoothScrollingEnabled(true);

        grid = (GridLayout) findViewById(R.id.grid_layout);
        grid.setOnDragListener(new MyDragListener());

        final LayoutInflater inflater = LayoutInflater.from(this);

        NBR_ITMS = student.length;
        for (int i = 0; i < NBR_ITMS; i++) {
            final View itemView = inflater.inflate(R.layout.class_management_list, grid, false);
            final ImageView imageView = (ImageView) itemView.findViewById(R.id.studentsPicture);
            final TextView firstName = (TextView) itemView.findViewById(R.id.studentsFirstName);

            imageView.setLayoutParams(new FrameLayout.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(student[i]);
            firstName.setText(String.valueOf(i + 1));
            firstName.setBackgroundColor(Color.WHITE);
            firstName.setGravity(Gravity.END);

            itemView.setOnLongClickListener(new LongPressListener());
            itemView.setOnClickListener(new View.OnClickListener(){
                int counter = 0;

                public void onClick(View view) {
                    if (counter == 0) {
                        itemView.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                    } else if (counter == 1) {
                        itemView.getBackground().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                    } else if (counter == 2) {
                        itemView.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    }
                    counter++;
                    if (counter >2){
                        counter = 0;
                    }
                }
            });
            grid.addView(itemView);

            studentsList = (Button) findViewById(R.id.showStudents);
            studentsList.setText("Show Students");
            studentsList.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Intent i = new Intent(ClassManagement.this, StudentsList.class);
                    startActivity(i);
                }
            });
        }
    }


    class LongPressListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View view) {
            final ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
            view.startDrag(data, shadowBuilder, view, 0);
            view.setVisibility(View.INVISIBLE);
            return true;
        }
    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View view, DragEvent event) {
            final View view2 = (View) event.getLocalState();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_LOCATION:
                    if (view2 == view) {
                        return true;
                    }
                    final int index = calculateNewIndex(event.getX(), event.getY());

                    final int scrollY = scrollView.getScrollY();
                    final Rect rect = new Rect();
                    scrollView.getHitRect(rect);

                    if (event.getY() - scrollY > scrollView.getBottom() - 250) {
                        startScrolling(scrollY, grid.getHeight());
                    } else if (event.getY() - scrollY < scrollView.getTop() + 250) {
                        startScrolling(scrollY, 0);
                    } else {
                        stopScrolling();
                    }

                    grid.removeView(view2);
                    grid.addView(view2, index);
                    view2.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DROP:
                    view.setVisibility(View.VISIBLE);
                    break;

                case DragEvent.ACTION_DRAG_ENDED:
                    if (!event.getResult()) {
                        view.setVisibility(View.VISIBLE);
                    }
                    break;
            }
            return true;
        }
    }

    private void startScrolling(int from, int to) {
        if (from != to && animator == null) {
            isScrolling.set(true);
            animator = new ValueAnimator();
            animator.setInterpolator(new OvershootInterpolator());
            animator.setDuration(Math.abs(to - from));
            animator.setIntValues(from, to);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    scrollView.smoothScrollTo(0, (int) valueAnimator.getAnimatedValue());
                }
            });
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animator) {
                    isScrolling.set(false);
                    animator = null;
                }
            });
            animator.start();
        }
    }

    private void stopScrolling() {
        if (animator != null) {
            animator.cancel();
        }
    }

    private int calculateNewIndex(float x, float y) {
        final float cellWidth = grid.getWidth() / grid.getColumnCount();
        final int column = (int) (x / cellWidth);

        final int cellHeight = grid.getHeight() / grid.getRowCount();
        final int row = (int) (y / cellHeight);

        int index = row * grid.getColumnCount() + column;
        if (index >= grid.getChildCount()) {
            index = grid.getChildCount() - 1;
        }

        return index;
    }

    private Integer [] student = {
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile,
            R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile, R.drawable.ic_profile
    };

}
