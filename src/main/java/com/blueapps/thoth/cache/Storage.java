package com.blueapps.thoth.cache;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.LruCache;

import com.blueapps.maat.BoundCalculation;
import com.blueapps.maat.ValuePair;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class Storage {

    // Cached Values
    private Document glyphXDocument;
    private BoundCalculation boundCalculation;
    private ArrayList<String> ids;
    private ArrayList<ValuePair<Float, Float>> dimensions;
    private ArrayList<Rect> bounds;

    // Getters
    public ArrayList<Rect> getBounds() {
        return bounds;
    }

    public ArrayList<ValuePair<Float, Float>> getDimensions() {
        return dimensions;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public BoundCalculation getBoundCalculation() {
        return boundCalculation;
    }

    public Document getGlyphXDocument() {
        return glyphXDocument;
    }

    // Setters
    public void setGlyphXDocument(Document glyphXDocument) {
        this.glyphXDocument = glyphXDocument;
    }

    public void setBoundCalculation(BoundCalculation boundCalculation) {
        this.boundCalculation = boundCalculation;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    public void setDimensions(ArrayList<ValuePair<Float, Float>> dimensions) {
        this.dimensions = dimensions;
    }

    public void setBounds(ArrayList<Rect> bounds) {
        this.bounds = bounds;
    }
}
