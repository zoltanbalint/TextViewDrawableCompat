package com.zoltanbalint.textviewcompat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

import com.zoltanbalint.textviewcompat.util.DrawableUtil;

public class EditTextDC extends EditText implements TintableCompoundDrawableView {
    private static int[] tmpColors = new int[]{0, 0, 0, 0};
    private int[] colors = new int[]{0, 0, 0, 0};

    public EditTextDC(Context context) {
        super(context);
    }

    public EditTextDC(Context context, AttributeSet attrs) {
        super(setColorInfoAndReturnContext(context, attrs), attrs);
        this.colors = tmpColors;
    }

    private static synchronized Context setColorInfoAndReturnContext(Context context, AttributeSet attrs) {
        tmpColors = DrawableUtil.parseAttributesForColorTint(context, attrs);
        return context;
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        final Drawable[] tintedDrawables = DrawableUtil.adjustTintForView(left, top, right, bottom, getContext(), this);
        super.setCompoundDrawablesWithIntrinsicBounds(tintedDrawables[0], tintedDrawables[1], tintedDrawables[2], tintedDrawables[3]);
    }

    @Override
    public int[] getColors() {
        return colors;
    }

    @Override
    public int[] getTmpColors() {
        return tmpColors;
    }

    @Override
    public boolean isBeingEdited() {
        return isInEditMode();
    }
}
