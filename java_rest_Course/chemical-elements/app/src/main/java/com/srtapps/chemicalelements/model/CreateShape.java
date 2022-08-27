package com.srtapps.chemicalelements.model;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;

public class CreateShape {

    public static GradientDrawable shapeCreator (int bgColor, int radius, int strokeWidth, int strokeColor) {
        GradientDrawable gdDefault = new GradientDrawable();
        gdDefault.setColor(bgColor);
        gdDefault.setCornerRadius(radius);
        gdDefault.setStroke(strokeWidth, strokeColor);

        return gdDefault;
    }

    public static LayerDrawable layerListCreator (GradientDrawable layer1, GradientDrawable layer2) {
        InsetDrawable insetLayer2 = new InsetDrawable(layer2, 0, 0, 0, 0);
        return new LayerDrawable(new Drawable[]{layer1, insetLayer2});
    }

    public static StateListDrawable stateListCreator (LayerDrawable statePressed, GradientDrawable stateNormal) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int []{android.R.attr.state_pressed}, statePressed);
        res.addState(new int []{android.R.attr.state_focused}, statePressed);
        res.addState(new int []{}, stateNormal);
        return res;
    }

    public static LayerDrawable layerListCreator (GradientDrawable layer1, Drawable layer2) {
        InsetDrawable insetLayer2 = new InsetDrawable(layer2, 0, 0, 0, 0);
        return new LayerDrawable(new Drawable[]{layer1, insetLayer2});
    }

    public static StateListDrawable stateListCreator (LayerDrawable statePressed, LayerDrawable stateNormal) {
        StateListDrawable res = new StateListDrawable();
        res.addState(new int []{android.R.attr.state_pressed}, statePressed);
        res.addState(new int []{android.R.attr.state_focused}, statePressed);
        res.addState(new int []{}, stateNormal);
        return res;
    }
}
