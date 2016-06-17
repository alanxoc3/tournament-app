package cs246.fencing_tournament.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import cs246.fencing_tournament.R;

/**
 * Created by alanxoc3 on 6/11/16.
 */
public class DoodleView extends View {
    private Paint _paintDoodle;
    private Paint _paintRect;
    private Path _path;
    private Bitmap _spamBot;

    int x, y;
    int incX, incY;

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        _paintDoodle = new Paint();
        _paintRect = new Paint();
        _spamBot = new BitmapFactory().decodeResource(getResources(), R.drawable.spam_bot);
        x = 0;
        y = 0;
        incX = 10;
        incY = 10;

        _path = new Path();
        _paintDoodle.setColor(Color.RED);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);

        _paintRect.setColor(Color.BLUE);
        _paintRect.setAntiAlias(true);
        _paintRect.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float pad = 30;
        canvas.drawRect(0 + pad, 0 + pad, getWidth() - pad, getHeight() - pad, _paintRect);

        canvas.drawLine(0, 0, getWidth(), getHeight(), _paintDoodle);
        canvas.drawPath(_path, _paintDoodle);

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
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();

        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                _path.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                _path.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        // Forces a view draw.
        invalidate();

        // Because we handled the touch event.
        return true;
    }
}
