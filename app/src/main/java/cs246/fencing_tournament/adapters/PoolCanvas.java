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
import cs246.fencing_tournament.data.ContestantData;
import cs246.fencing_tournament.data.PoolData;

/**
 * Created by alanxoc3 on 6/11/16.
 */
public class PoolCanvas extends View {
    // NOTES
    // So, the idea is that I am going to have an array of rectangles. If the user touches any of
    // them, then an activity is opened to save that pool data.
    private Bitmap _spamBot;
    private PoolData _pool;
    private List<ContestantData> _contestants;

    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor = 1.f;
    private float focusX = 0.0f;
    private float focusY = 0.0f;
    private float touchX = 0.0f;
    private float touchY = 0.0f;
    private float viewX = 0.0f;
    private float viewY = 0.0f;
    private int pad;

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
        _spamBot = new BitmapFactory().decodeResource(getResources(), R.drawable.spam_bot);
        x = 0;
        y = 0;
        incX = 10;
        incY = 10;
        _pool = null;

        // Info for the padding and all.
        pad = 10;
    }

    private Path createPath(int scrW, int scrH) {
        // This is going to create the bounds.
        Path path = new Path();
        int wl = _pool.getWL();
        int altW = scrW - 2*pad; // Got to account for padding.
        int altH = scrH - 2*pad;

        for (int col = 0; col <= altW; col += altW / wl) {
            path.moveTo(col + pad, pad);
            path.lineTo(col + pad, scrH - pad);
        }

        for (int row = 0; row <= altH; row += altH / wl) {
            path.moveTo(pad, row + pad);
            path.lineTo(scrW - pad, row + pad);
        }

        return path;
    }

    private void drawText(Canvas canvas) {
        int wl = _pool.getWL();
        int scrW = canvas.getWidth();
        int scrH = canvas.getHeight();
        int altW = scrW - 2*pad;
        int altH = scrH - 2*pad;
        int cellW = altW / wl;
        int cellH = altH / wl;
        int leftPad = cellW / 15;

        int p1_text_size = cellH / 6;
        int p2_text_size = cellH / 6;
        int vs_text_size = cellH / 10;

        int p1Pad = cellH / 4;
        int p2Pad = 3 * cellH / 4;
        int vsPad = cellH / 2;
        String vs = "VERSUS";

        Paint p1_paint = new Paint();
        Paint p2_paint = new Paint();
        Paint vs_paint = new Paint();

        p1_paint.setColor(Color.BLUE);
        p2_paint.setColor(Color.RED);
        vs_paint.setColor(Color.BLACK);

        p1_paint.setTextSize(p1_text_size);
        p2_paint.setTextSize(p2_text_size);
        vs_paint.setTextSize(vs_text_size);

        for (int row = 0; row < wl; ++row) {
            for (int col = 0; col < wl; ++col) {
                // A complicated way to get the names :)
                String p1 = ContestantData.findById(_contestants, _pool.getMatch(row * wl + col).getId1()).getName();
                String p2 = ContestantData.findById(_contestants, _pool.getMatch(row * wl + col).getId2()).getName();

                canvas.drawText(p1, col * cellW + pad + leftPad, row * cellH + pad + p1Pad, p1_paint);
                canvas.drawText(vs, col * cellW + pad + leftPad, row * cellH + pad + vsPad, vs_paint);
                canvas.drawText(p2, col * cellW + pad + leftPad, row * cellH + pad + p2Pad, p2_paint);
            }
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, focusX, focusY);
        viewX = canvas.getClipBounds().exactCenterX() - canvas.getClipBounds().width() / 2;
        viewY = canvas.getClipBounds().exactCenterY() - canvas.getClipBounds().height() / 2;

        // Drawing Here.

        Paint canvasPaint = new Paint();
        canvasPaint.setColor(Color.BLACK);
        canvasPaint.setAntiAlias(true);
        canvasPaint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(createPath(getWidth(), getHeight()), canvasPaint);

        drawText(canvas);





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

    public void setContestantList(List<ContestantData> contestants) {
        _contestants = contestants;
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