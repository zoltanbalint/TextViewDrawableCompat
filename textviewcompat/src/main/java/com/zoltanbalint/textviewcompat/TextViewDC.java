package com.zoltanbalint.textviewcompat;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewDC extends TextView {
    private static int color = 0;

    public TextViewDC(Context context) {
        super(context);
    }

    public TextViewDC(Context context, AttributeSet attrs) {
        super(setColorInfoAndReturnContext(context, attrs), attrs);
    }

    private static synchronized Context setColorInfoAndReturnContext(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DTextView,
                0, 0
        );

        try {
            color = a.getColor(R.styleable.DTextView_drawableTint, 0xff000000);
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
        if (color != 0) {
            left = adjustColor(left, color);
            top = adjustColor(top, color);
            right = adjustColor(right, color);
            bottom = adjustColor(bottom, color);
        }
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    private Drawable adjustColor(Drawable drawable, int color) {
        if (drawable != null) {
            Bitmap sourceBitmap = null;
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    sourceBitmap = bitmapDrawable.getBitmap();
                }
            }
            if (sourceBitmap != null) {
                Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth()-1, sourceBitmap.getHeight()-1);
                Paint p = new Paint();
                ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY);
                p.setColorFilter(filter);
                Canvas canvas = new Canvas(resultBitmap);
                canvas.drawBitmap(resultBitmap, 0, 0, p);
                return new BitmapDrawable(getContext().getResources(), resultBitmap);
            }
        }
        return null;
    }

}
