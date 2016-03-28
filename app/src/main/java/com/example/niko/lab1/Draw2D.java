package com.example.niko.lab1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by niko on 26/03/16.
 */
public class Draw2D extends View {
    public Draw2D(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){

        super.onDraw(canvas);
        float width = 400f;
        float height = 240f;
        float radius = 100f;

        Path path = new Path();
        path.addCircle(width, height, radius, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        float center_x, center_y;
        center_x = 360;
        center_y = 500;

        final RectF oval = new RectF();
        oval.set(center_x - radius, center_y - radius, center_x + radius,
                center_y + radius);
        canvas.drawArc(oval, 45, 270, true, paint);

        paint.setStyle(Paint.Style.STROKE);
        oval.set(center_x - 200f, center_y - 200f, center_x + 200f,
                center_y + 200f);
        canvas.drawArc(oval, 45, 270, true, paint);

    }
}