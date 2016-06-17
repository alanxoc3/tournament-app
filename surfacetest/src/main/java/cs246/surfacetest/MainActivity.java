package cs246.surfacetest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private OurView _v;
    Bitmap _spamBot;
    float x, y;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _v = new OurView(this);
        _v.setOnTouchListener(this);

        _spamBot = BitmapFactory.decodeResource(getResources(), R.drawable.spam_bot);
        x = y = 0;
        score = 0;
        setContentView(_v);
    }

    @Override
    protected void onResume() {
        super.onResume();

        _v.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        _v.pause();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        float halfW = _spamBot.getWidth() / 2;
        float halfH = _spamBot.getHeight() / 2;

        if (touchX < x + halfW && touchX > x - halfW
                && touchY < y + halfH && touchY > y - halfH) {
            score++;
            x = (float) (Math.random() * _v.getWidth());
            y = (float) (Math.random() * _v.getHeight());
        }

        return false;
    }

    public class OurView extends SurfaceView implements Runnable {

        Thread t = null;
        SurfaceHolder holder;
        boolean isItOK = false;
        Paint scorePaint;

        public OurView(Context context) {
            super(context);

            holder = getHolder();
            scorePaint = new Paint();
            scorePaint.setColor(Color.RED);
            scorePaint.setStyle(Paint.Style.FILL);
            scorePaint.setTextSize(100);
        }

        @Override
        public void run() {
            while (isItOK) {
                if(!holder.getSurface().isValid()) {
                    continue;
                }
                Canvas c = holder.lockCanvas();
                c.drawARGB(255, 150, 150, 10);
                c.drawBitmap(_spamBot, x - _spamBot.getWidth() / 2, y - _spamBot.getHeight() / 2, null);

                c.drawText(String.valueOf(score), c.getWidth() / 2, 100 + 10, scorePaint);
                holder.unlockCanvasAndPost(c);
            }
        }

        public void pause() {
            isItOK = false;
            while(true) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }

            t = null;
        }

        public void resume() {
            isItOK = true;
            t = new Thread(this);
            t.start();
        }
    }
}
