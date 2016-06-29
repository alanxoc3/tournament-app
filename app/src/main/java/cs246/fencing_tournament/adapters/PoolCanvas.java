package cs246.fencing_tournament.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.util.Pools;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.List;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.PoolData;

/**
 * Created by alanxoc3 on 6/11/16.
 */
public class PoolCanvas extends View {
    // NOTES
    // So, the idea is that I am going to have an array of rectangles. If the user touches any of
    // them, then an activity is opened to save that pool data.
    private Paint _paintDoodle;
    private Bitmap _spamBot;
    private PoolData _pool;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private float focusX = 0.0f;
    private float focusY = 0.0f;
    private float touchX = 0.0f;
    private float touchY = 0.0f;
    private float viewX = 0.0f;
    private float viewY = 0.0f;
    private static final String TAG = "PoolCanvas";

    int x, y;
    int incX, incY;

    public PoolCanvas(Context context) {
        super(context);
        init(context, null, 0);
    }

    public PoolCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public PoolCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        _paintDoodle = new Paint();
        _spamBot = new BitmapFactory().decodeResource(getResources(), R.drawable.spam_bot);
        x = 0;
        y = 0;
        incX = 10;
        incY = 10;
        _pool = null;

        _paintDoodle.setColor(Color.RED);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);
    }

    private Path createPath(int scrW, int scrH) {
        // This is going to create the bounds.
        Path path = new Path();
        int pad = 10;
        int altW = scrW - 2*pad; // Got to account for padding.
        int altH = scrH - 2*pad;
        int wl = _pool.getWL();

        for (int col = 0; col < altW; col += altW / wl) {
            path.moveTo(col + pad, pad);
            path.lineTo(col + pad, scrH - pad);
        }

        for (int row = 0; row < altH; row += altH / wl) {
            path.moveTo(pad, row + pad);
            path.lineTo(scrW - pad, row + pad);
        }

        return path;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, focusX, focusY);
        viewX = canvas.getClipBounds().exactCenterX() - canvas.getClipBounds().width() / 2;
        viewY = canvas.getClipBounds().exactCenterY() - canvas.getClipBounds().height() / 2;
        //Log.e(TAG, "WL: " + _pool.getWL());

        float pad = 30;

        canvas.drawLine(0, 0, getWidth(), getHeight(), _paintDoodle);

        canvas.drawPath(createPath(getWidth(), getHeight()), _paintDoodle);

        int bx = 0 - _spamBot.getWidth() / 2;
        int by = 0 - _spamBot.getHeight() / 2;
        int w = canvas.getWidth() - _spamBot.getWidth() / 2;
        int h = canvas.getHeight() - _spamBot.getHeight() / 2;

        if (x < bx) {
            x = bx;
            incX = -incX;
        } else if (x > w) {
            x = w;
            incX = -incX;
        }

        if (y < by) {
            y = by;
            incY = -incY;
        } else if (y > h) {
            y = h;
            incY = -incY;
        }

        x += incX;
        y += incY;

        canvas.drawBitmap(_spamBot, x, y, new Paint());

        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        mScaleDetector.getFocusX();
        mScaleDetector.onTouchEvent(motionEvent);

        touchX = motionEvent.getX() / mScaleFactor + viewX;
        touchY = motionEvent.getY() / mScaleFactor + viewY;



        // Forces a view draw.
        invalidate();

        // Because we handled the touch event.
        return true;
    }

    public void setPool(PoolData pool) {
        _pool = pool;
        Log.i(TAG, "SETTING THE POOL");
    }

    public PoolData getPool() {
        return _pool;
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            if (detector.getScaleFactor() > 1.0)
                Log.i(TAG, "Zoomed in");
            else if (detector.getScaleFactor() < 1.0)
                Log.i(TAG, "Zoomed out");

            if (mScaleFactor > 5.0f)
                Log.e(TAG, "Zoomed in too much");

            // Don't let the object get too small or too large.
            mScaleFactor = Math.max(1.0f, Math.min(mScaleFactor, 5.0f));
            focusX = mScaleDetector.getFocusX();
            focusY = mScaleDetector.getFocusY();
            invalidate();
            return true;
        }
    }
}