package com.zoltanbalint.textviewcompat.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

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
                Bitmap resultBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth()-1, sourceBitmap.getHeight()-1);
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
}
