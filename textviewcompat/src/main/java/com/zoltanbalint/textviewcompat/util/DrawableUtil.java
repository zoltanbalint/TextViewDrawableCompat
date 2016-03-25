package com.zoltanbalint.textviewcompat.util;

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

import com.zoltanbalint.textviewcompat.R;
import com.zoltanbalint.textviewcompat.TintableCompoundDrawableView;

import java.util.Arrays;

public class DrawableUtil {

    public static Drawable adjustColor(Context context, Drawable drawable, int color) {
        if (drawable != null) {
            Bitmap sourceBitmap = null;
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    sourceBitmap = bitmapDrawable.getBitmap();
                }
            }
            if (sourceBitmap != null) {
                Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth() - 1, sourceBitmap.getHeight() - 1);
                Paint p = new Paint();
                ColorFilter filter = new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY);
                p.setColorFilter(filter);
                Canvas canvas = new Canvas(resultBitmap);
                canvas.drawBitmap(resultBitmap, 0, 0, p);
                return new BitmapDrawable(context.getResources(), resultBitmap);
            }
        }
        return null;
    }

    public static int[] parseAttributesForColorTint(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.DTextView,
                0, 0
        );
        int[] tmpColors = new int[]{0, 0, 0, 0};
        try {
            int tmpColor = a.getColor(R.styleable.DTextView_drawableTint, 0xff000000);
            tmpColors[0] = a.getColor(R.styleable.DTextView_drawableTintLeft, tmpColor);
            tmpColors[1] = a.getColor(R.styleable.DTextView_drawableTintTop, tmpColor);
            tmpColors[2] = a.getColor(R.styleable.DTextView_drawableTintRight, tmpColor);
            tmpColors[3] = a.getColor(R.styleable.DTextView_drawableTintBottom, tmpColor);
        } catch (Exception e) {
            // nothing to see here
        } finally {
            // release the TypedArray so that it can be reused.
            a.recycle();
        }
        return tmpColors;
    }

    public static Drawable[] adjustTintForView(Drawable left, Drawable top, Drawable right, Drawable bottom,
                                               Context context, TintableCompoundDrawableView view) {
        final int[] colors;
        final int[] empty = new int[]{0, 0, 0, 0};
        if (view.isBeingEdited()) {
            colors = view.getTmpColors();
        } else {
            colors = view.getColors() == null || Arrays.equals(view.getColors(), empty) ? view.getTmpColors() : view.getColors();
        }
        if (colors != null && !Arrays.equals(colors, empty)) {
            left = DrawableUtil.adjustColor(context, left, colors[0]);
            top = DrawableUtil.adjustColor(context, top, colors[1]);
            right = DrawableUtil.adjustColor(context, right, colors[2]);
            bottom = DrawableUtil.adjustColor(context, bottom, colors[3]);
        }
        return new Drawable[]{left, top, right, bottom};
    }
}
