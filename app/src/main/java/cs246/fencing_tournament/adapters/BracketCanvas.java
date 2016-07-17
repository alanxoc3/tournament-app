package cs246.fencing_tournament.adapters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

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

    int numOfPeeps = 64;

    private float mScaleFactor = 1.f;
    private float focusX = 0.0f;
    private float focusY = 0.0f;
    private float touchX = 0.0f;
    private float touchY = 0.0f;
    private float viewX = 0.0f;
    private float viewY = 0.0f;
    boolean scaleLock;
    int didPress = 0;
    int pad = 30;

    List<Rect> _collisionBracketRects;

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
        _collisionBracketRects = null;
    }

    private List<Rect> createRects(int scrW, int scrH) {
        // This algorithm is similar to the create paths one.
        List<Rect> rects = new ArrayList<Rect>();

        int numOfRects = numOfPeeps - 1;

        // Test num of contestants (people).
        int numOfLevels = (int) (Math.log(numOfPeeps) / Math.log(2));

        // How wide the screen is, accounting for padding.
        int altW = (scrW - 2 * pad);
        int altH = (scrH - 2 * pad);

        // How wide the tree levels will be.
        int cellW = (altW / (numOfLevels + 1));

        // Used for the loop below
        int cellH, curNumOfMatches, curHPad, midCellH;

        // Go for the number of match levels. And the number of matches.
        for (int wl = 0; wl < numOfLevels; ++wl) {
            curNumOfMatches = numOfPeeps / (int) Math.pow(2, wl + 1);
            cellH = altH / curNumOfMatches;
            midCellH = cellH / 2;
            curHPad = midCellH / 2;

            for (int hl = 0; hl < curNumOfMatches; ++hl) {
                Rect tmpRect = new Rect(
                    pad + wl*cellW,
                    curHPad + hl * cellH,
                    pad + (wl+1)*cellW,
                    curHPad + hl * cellH + midCellH
                );

                rects.add(tmpRect);
            }
        }

        return rects;
    }

    private Path createPath(int scrW, int scrH) {
        Path path = new Path();

        // Test num of contestants (people).
        int numOfLevels = (int) (Math.log(numOfPeeps) / Math.log(2));

        // How wide the screen is, accounting for padding.
        int altW = (scrW - 2 * pad);
        int altH = (scrH - 2 * pad);

        // How wide the tree levels will be.
        int cellW = (altW / (numOfLevels + 1));

        // Used for the loop below
        int cellH;
        int curNumOfPeeps;
        int curHPad;

        // WidthLoop and HeightLoop
        for (int wl = 0; wl < numOfLevels + 1; ++wl) {
            curNumOfPeeps = numOfPeeps / (int) Math.pow(2, wl);
            cellH = (altH / curNumOfPeeps);
            curHPad = cellH / 2;
            for (int hl = 0; hl < curNumOfPeeps; ++hl) {
                if (hl % 2 == 0) {
                    path.moveTo(pad + wl*cellW,     curHPad + hl * cellH);
                    path.lineTo(pad + (wl+1)*cellW, curHPad + hl * cellH);
                } else {
                    path.lineTo(pad + (wl+1)*cellW, curHPad + hl * cellH);
                    path.lineTo(pad + wl*cellW,     curHPad + hl * cellH);
                }
            }
        }

        return path;
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

        // Draw the Rectangles.
        _collisionBracketRects = createRects(getWidth(), getHeight());

        Paint blob = new Paint();
        blob.setStyle(Paint.Style.FILL_AND_STROKE);



        for (int i = 0; i < _collisionBracketRects.size(); ++i) {
            if (didPress == 1 && _collisionBracketRects.get(i).contains((int) touchX, (int) touchY)) {
                blob.setARGB(255, 190, 190, 190);
            } else {
                blob.setARGB(255, 230, 230, 230);
            }

            canvas.drawRect(_collisionBracketRects.get(i), blob);
        }

        // Drawing Here.
        Paint canvasPaint = new Paint();
        canvasPaint.setColor(Color.BLACK);
        canvasPaint.setAntiAlias(true);
        canvasPaint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(createPath(getWidth(), getHeight()), canvasPaint);

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
        Log.e(TAG, "TOUCH");

        final int action = motionEvent.getAction();
        touchX = motionEvent.getX() / mScaleFactor + viewX;
        touchY = motionEvent.getY() / mScaleFactor + viewY;

        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE: {
                if (mScaleDetector.isInProgress()) didPress = 0;
                break;
            }

            case MotionEvent.ACTION_DOWN: {
                didPress = 1;
                Log.e(TAG, "PRESSED");
                invalidate();

                break;
            }

            case MotionEvent.ACTION_UP: {
                if(didPress == 1) didPress = 2;
                Log.e(TAG, "RELEASED");
                invalidate();

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