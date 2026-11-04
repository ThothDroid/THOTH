package com.blueapps.thoth.edit;

import android.graphics.Rect;

import java.util.ArrayList;

public class SnappingCursor {

    public static float snapToSigns(float cursor, ArrayList<Rect> signs){

        // Get index of signs next to cursor
        float before = 0f;
        float after = 0f;
        for (Rect sign : signs) {
            if (sign.right < cursor){
                before = sign.right;
            }
            if (sign.left > cursor){
                after = sign.left;
                break;
            }
        }

        // Calculate distance
        if (cursor - before > after - cursor){
            return after;
        } else {
            return before;
        }
    }

}
