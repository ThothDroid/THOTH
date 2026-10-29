package com.blueapps.thoth.render;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import com.blueapps.maat.BoundCalculation;
import com.blueapps.maat.BoundProperty;
import com.blueapps.maat.ValuePair;
import com.blueapps.signprovider.SignProvider;
import com.blueapps.thoth.R;
import com.blueapps.thoth.cache.DrawableCache;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class RenderClass extends ViewModel {

    // Constants
    private static final String TAG = "RenderClass";

    public static Document renderGlyphXDocument(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new InputSource(new StringReader(xml)));
    }

    public static BoundCalculation renderBoundCalculation(Document glyphXDocument){
        return new BoundCalculation(glyphXDocument);
    }

    public static ArrayList<String> renderIds(BoundCalculation boundCalculation, int writingLayout, int writingDirection){
        return boundCalculation.getIds(writingLayout == BoundProperty.WRITING_LAYOUT_LINES,
                                                    writingDirection == BoundProperty.WRITING_DIRECTION_RTL);
    }

    public static ArrayList<ValuePair<Float, Float>> renderDimensions(Context context, DrawableCache drawableCache, ArrayList<String> ids) {
        ArrayList<ValuePair<Float, Float>> dimensions = new ArrayList<>();
        for (String id : ids){
            Drawable drawable = getDrawable(context, drawableCache, id);
            dimensions.add(new ValuePair<>(getDrawableWidth(context, drawable), getDrawableHeight(context, drawable)));
        }
        return dimensions;
    }

    public static ArrayList<Rect> renderBounds(BoundCalculation boundCalculation, BoundProperty boundProperty, ArrayList<ValuePair<Float, Float>> dimensions){
        return boundCalculation.getBounds(dimensions, boundProperty);
    }

    public static String convertToXmlString(Document xml) throws TransformerException {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            DOMSource source = new DOMSource(xml);
            transformer.transform(source, result);

            return writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Drawable getDrawable(Context context, DrawableCache drawableCache, String id){
        Drawable drawable = drawableCache.getDrawable(id);
        if (drawable == null){
            try {
                SignProvider signProvider = new SignProvider(context);
                drawable = signProvider.getSign(id);
            } catch (IOException | XmlPullParserException e) {
                drawable = ContextCompat.getDrawable(context, R.drawable.not_found_sign);
                e.printStackTrace();
            }
            drawableCache.putDrawable(id, drawable);
        }
        return drawable;
    }

    private static float getDrawableWidth(Context context, Drawable drawable){
        float width = drawable.getIntrinsicWidth();
        float density = context.getResources().getDisplayMetrics().density;

        width = width / density;

        Log.i(TAG, "Density: " + density);

        return width;
    }

    private static float getDrawableHeight(Context context, Drawable drawable){
        float height = drawable.getIntrinsicHeight();
        float density = context.getResources().getDisplayMetrics().density;

        height = height / density;

        Log.i(TAG, "Density: " + density);

        return height;
    }

}
