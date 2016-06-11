package cs246.canvastest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by alanxoc3 on 6/11/16.
 */
public class DoodleView extends View {
    private Paint _paintDoodle;
    private Paint _paintRect;
    private Path _path;

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

        _path = new Path();
        _paintDoodle.setColor(Color.RED);
        _paintDoodle.setAntiAlias(true);
        _paintDoodle.setStyle(Paint.Style.STROKE);

        _paintRect.setColor(Color.BLUE);
        _paintRect.setAntiAlias(true);
        _paintRect.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float pad = 30;
        canvas.drawRect(0 + pad, 0 + pad, getWidth() - pad, getHeight() - pad, _paintRect);

        canvas.drawLine(0, 0, getWidth(), getHeight(), _paintDoodle);
        canvas.drawPath(_path, _paintDoodle);
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
