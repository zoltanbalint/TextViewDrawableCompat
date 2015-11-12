package com.zoltanbalint.textviewcompat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

import com.zoltanbalint.textviewcompat.util.DrawableUtil;

public class EditTextDC extends EditText implements TintableCompoundDrawable {
    private static int tmpColor = 0;
    private int color = 0;

    public EditTextDC(Context context) {
        super(context);
    }

    public EditTextDC(Context context, AttributeSet attrs) {
        super(setColorInfoAndReturnContext(context, attrs), attrs);
        color = tmpColor;
    }

    private static synchronized Context setColorInfoAndReturnContext(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DTextView,
                0, 0
        );
        try {
            tmpColor = a.getColor(R.styleable.DTextView_drawableTint, 0xff000000);
        } catch (Exception e) {
            // TODO
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }
        return context;
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        final int color = this.color == 0 ? tmpColor : this.color;
        if (color != 0) {
            left = DrawableUtil.adjustColor(getContext(), left, color);
            top = DrawableUtil.adjustColor(getContext(), top, color);
            right = DrawableUtil.adjustColor(getContext(), right, color);
            bottom = DrawableUtil.adjustColor(getContext(), bottom, color);
        }
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void setColor(int color) {

    }
}
