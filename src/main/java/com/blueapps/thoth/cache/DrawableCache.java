package com.blueapps.thoth.cache;

import android.graphics.drawable.Drawable;
import android.util.LruCache;

public class DrawableCache {

    private LruCache<String, Drawable> drawableLruCache;

    public DrawableCache(int maxSize){

        drawableLruCache = new LruCache<>(maxSize){
            @Override
            protected int sizeOf(String key, Drawable value){
                return 1;
            }
        };

    }

    public Drawable getDrawable(String id){
        return drawableLruCache.get(id);
    }

    public void putDrawable(String id, Drawable drawable){
        drawableLruCache.put(id, drawable);
    }

}
