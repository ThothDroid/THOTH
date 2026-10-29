package com.blueapps.thoth.render;

import android.content.Context;

import com.blueapps.maat.BoundProperty;
import com.blueapps.thoth.cache.DrawableCache;
import com.blueapps.thoth.cache.Storage;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class StorageUpdater implements Callable<Storage> {

    private final Storage storage;
    private final ArrayList<Integer> changes;
    private final Context context;
    private final DrawableCache drawableCache;
    private final BoundProperty boundProperty;

    // Constants
    public static final int REFRESH_ALL = 0;
    public static final int REFRESH_LAYOUT = 1;
    public static final int REFRESH_DIRECTION = 2;

    public StorageUpdater(Context context, DrawableCache drawableCache, Document glyphX, BoundProperty boundProperty){
        this.storage = new Storage();
        this.changes = new ArrayList<>();
        changes.add(REFRESH_ALL);
        this.context = context;
        this.drawableCache = drawableCache;
        this.boundProperty = boundProperty;
        // GlyphX Document
        storage.setGlyphXDocument(glyphX);
    }

    public StorageUpdater(Storage oldStorage, ArrayList<Integer> changes, Context context, DrawableCache drawableCache, Document glyphX, BoundProperty boundProperty){
        this.storage = oldStorage;
        this.changes = changes;
        this.context = context;
        this.drawableCache = drawableCache;
        this.boundProperty = boundProperty;
        // GlyphX Document
        storage.setGlyphXDocument(glyphX);
    }

    @Override
    public Storage call() {
        // BoundCalculation
        if (changes.contains(REFRESH_ALL) || changes.contains(REFRESH_LAYOUT) ||
                changes.contains(REFRESH_DIRECTION)) {

            storage.setBoundCalculation(
                    RenderClass.renderBoundCalculation(storage.getGlyphXDocument())
            );
        }

        // Ids
        if (changes.contains(REFRESH_ALL) || changes.contains(REFRESH_DIRECTION)) {

            storage.setIds(
                    RenderClass.renderIds(storage.getBoundCalculation(),
                            boundProperty.getWritingLayout(), boundProperty.getWritingDirection())
            );
        }

        // Dimensions
        if (changes.contains(REFRESH_ALL) || changes.contains(REFRESH_LAYOUT) ||
                changes.contains(REFRESH_DIRECTION)) {

            storage.setDimensions(
                    RenderClass.renderDimensions(context, drawableCache, storage.getIds())
            );
        }

        // Bounds
        if (changes.contains(REFRESH_ALL) || changes.contains(REFRESH_LAYOUT) ||
                changes.contains(REFRESH_DIRECTION)) {

            storage.setBounds(
                    RenderClass.renderBounds(storage.getBoundCalculation(), boundProperty, storage.getDimensions())
            );
        }

        return storage;
    }

}
