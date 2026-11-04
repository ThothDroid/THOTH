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
import androidx.core.graphics.ColorKt;

import com.blueapps.thoth.ThothView;

public class EditThoth extends ThothView implements GestureDetector.OnGestureListener {

    private GestureDetector gestureDetector;
    private float xCursorPosition = 0f;

    // Paints
    private final Paint cursorPaint = new Paint();
    private final Paint selectionPaint = new Paint();

    // Attributes
    private @ColorInt int cursorColor = Color.BLUE;
    private float cursorStrength = 7.5f;

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
        selectionPaint.setColor(Color.argb(50, ColorKt.getRed(cursorColor), ColorKt.getGreen(cursorColor), ColorKt.getBlue(cursorColor)));
        gestureDetector = new GestureDetector(getContext(), this);
        pagePaddingRight = pagePaddingRight + 100f;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawRect(xCursorPosition - 100, 0, xCursorPosition + cursorStrength,
                //getTextSize(), selectionPaint);

        canvas.drawRoundRect(xCursorPosition, 0, xCursorPosition + cursorStrength,
                getTextSize(), cursorStrength / 2, cursorStrength / 2, cursorPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public void setPagePaddingRight(float pagePaddingRight) {
        super.setPagePaddingRight(pagePaddingRight + 100f);
    }

    @Override
    public float getPagePaddingRight() {
        return super.getPagePaddingRight() - 100f;
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

    public float getCursorStrength() {
        return cursorStrength;
    }

    public void setCursorStrength(float cursorStrength) {
        this.cursorStrength = cursorStrength;
        invalidate();
    }

    // Gesture Listener
    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        xCursorPosition = motionEvent.getX();
        invalidate();
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
