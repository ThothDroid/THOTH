package com.blueapps.thoth.edit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blueapps.thoth.ThothView;

public class EditThoth extends ThothView implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;

    // Paints
    private final Paint cursorPaint = new Paint();

    // Attributes
    private @ColorInt int cursorColor = Color.BLUE;

    public EditThoth(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor();
    }

    public EditThoth(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor();
    }

    public EditThoth(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructor();
    }

    public EditThoth(Context context) {
        super(context);
        constructor();
    }

    private void constructor(){
        cursorPaint.setColor(cursorColor);
        gestureDetector = new GestureDetector(getContext(), this);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 5, getTextSize(), cursorPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    // Getter and Setter
    public int getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
        cursorPaint.setColor(cursorColor);
        invalidate();
    }

    // Gesture Listener
    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return true;
    }

    @Override
    public boolean onFling(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onScroll(@Nullable MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return true;
    }
}
