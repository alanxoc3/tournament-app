package cs246.fencing_tournament.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import cs246.fencing_tournament.data.TournamentData;

/**
 * Created by alanxoc3 on 6/11/16.
 */
public class BracketCanvas extends View {
    // NOTES
    // So, the idea is that I am going to have an array of rectangles. If the user touches any of
    // them, then an activity is opened to save that pool data.
    private TournamentData _tournament;

    private ScaleGestureDetector mScaleDetector;
    private int numOfPeeps = 16;
    private float mScaleFactor = 1.f;
    private float focusX = 0.0f;
    private float focusY = 0.0f;
    private float touchX = 0.0f;
    private float touchY = 0.0f;
    private float viewX = 0.0f;
    private float viewY = 0.0f;
    private int pad;
    boolean scaleLock;
    int didPress = 0;

    private static final String TAG = "BracketCanvas";

    int x, y;
    int incX, incY;

    public BracketCanvas(Context context) {
        super(context);
        init(context, null, 0);
    }

    public BracketCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BracketCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        x = 0;
        y = 0;
        incX = 10;
        incY = 10;
        _tournament = null;

        // Info for the padding and all.
        pad = 10;
    }

    private Path createPath(int scrW, int scrH) {
        // This is going to create the bounds.
       return null;
    }

    private void drawText(Canvas canvas) {

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

        canvas.drawLine(0, 0, 200, 300, new Paint());

        drawText(canvas);

        canvas.restore();
    }

    public void callDriver(float x, float y) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        // didPress = 0 -- Nope
        // didPress = 1 -- Pressed
        // didPress = 2 -- Released

        mScaleDetector.onTouchEvent(motionEvent);

        final int action = motionEvent.getAction();

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE: {
                if (mScaleDetector.isInProgress()) didPress = 0;
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                didPress = 1;
                break;
            }

            case MotionEvent.ACTION_UP: {
                if(didPress == 1) didPress = 2;
                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_POINTER_UP:
                didPress = 0;
                break;

            default: break;
        }

        if (didPress == 2) {
            touchX = motionEvent.getX() / mScaleFactor + viewX;
            touchY = motionEvent.getY() / mScaleFactor + viewY;
            callDriver(touchX, touchY);
            didPress = 0;
        }

        return true;
    }

    public void setTournament(TournamentData tournament) {
        _tournament = tournament;
        Log.i(TAG, "SETTING THE TOURNAMENT");
    }

    public TournamentData getTournament() {
        return _tournament;
    }

    private class ScaleListener
            extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            scaleLock = true;
            return super.onScaleBegin(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
            scaleLock = false;
        }

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