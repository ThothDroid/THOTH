package com.blueapps.thoth.edit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.blueapps.thoth.ThothView;

public class EditThoth extends ThothView {

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
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, 5, getTextSize(), cursorPaint);
    }

    public int getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
        cursorPaint.setColor(cursorColor);
        invalidate();
    }
}
