package com.blueapps.thothexampleapp;

import android.os.Handler;
import android.os.Looper;

import com.blueapps.thothexampleapp.databinding.MainActivityBinding;

public class TextChangerThread extends Thread{

    private CharSequence charSequence;
    private final MainActivityBinding binding;
    private boolean altText = false;
    private boolean isGlyphX = true;

    public TextChangerThread(MainActivityBinding binding, boolean altText){
        this.binding = binding;
        this.altText = altText;
    }

    public void setGlyphXText(CharSequence charSequence){
        isGlyphX = true;
        this.charSequence = charSequence;
    }

    public void setMdCText(CharSequence charSequence){
        isGlyphX = false;
        this.charSequence = charSequence;
    }

    @Override
    public void run() {
        //try {
            new Handler(Looper.getMainLooper()).post(() -> {
                if (altText){
                    binding.thothView.setAltText(charSequence.toString());
                } else {
                    if (isGlyphX) {
                        binding.thothView.setGlyphXText(charSequence.toString());
                    } else {
                        binding.thothView.setMdCText(charSequence.toString());
                    }
                }
            });
        /*} catch (NullPointerException e){
            e.printStackTrace();
        }*/
    }

}
