package cs246.fencing_tournament.screens;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



import android.os.CountDownTimer;

import android.view.View.OnClickListener;

import cs246.fencing_tournament.R;
import cs246.fencing_tournament.data.MatchData;

public class MatchScreen extends AppCompatActivity {

    private boolean p1Yellow;
    private boolean p2Yellow;
    private MatchData thisMatch;

    public void yellow1(View v){
        if (p1Yellow){
            red1(v);
        }
        else {
            p1Yellow = true;
            int yellow = Color.YELLOW;
            Button box = (Button) findViewById(R.id.cards);
            if (box != null)
                box.setBackgroundColor(yellow);
        }
    }
    public void yellow2(View v){
        if (p2Yellow){
            red2(v);
        }
        else {
            p2Yellow = true;
            int yellow = Color.YELLOW;
            Button box = (Button) findViewById(R.id.cards2);
            if (box != null)
                box.setBackgroundColor(yellow);
        }
    }
    public void red1(View v){
        p1Yellow = true;
        int red = Color.RED;
        Button box = (Button) findViewById(R.id.cards);
        if (box != null)
            box.setBackgroundColor(red);
        thisMatch.pointP2();
        update();
    }
    public void red2(View v){
        p2Yellow = true;
        int red = Color.RED;
        Button box = (Button) findViewById(R.id.cards2);
        if (box != null)
            box.setBackgroundColor(red);
        thisMatch.pointP1();
        update();
    }

    public void pointP1(View v){
        thisMatch.pointP1();
        update();
    }
    public void pointP2(View v){
        thisMatch.pointP2();
        update();
    }
    public void decPointP1(View v){
        thisMatch.setP1Score(thisMatch.getP1Score() - 1);
        update();
    }
    public void decPointP2(View v){
        thisMatch.setP2Score(thisMatch.getP2Score() - 1);
        update();
    }


    public void update() {
        TextView score = (TextView) findViewById(R.id.p1Score);
        if (score != null)
            score.setText(Integer.toString(thisMatch.getP1Score()));
        score = (TextView) findViewById(R.id.p2Score);
        if (score != null)
            score.setText(Integer.toString(thisMatch.getP2Score()));
    }

    //Declare a variable to hold count down timer's paused status
    private boolean isPaused = false;
    //Declare a variable to hold count down timer's paused status
    private boolean isCanceled = false;

    //Declare a variable to hold CountDownTimer remaining time
    private long timeRemaining = 0;

    public void setTimeText(long millisUntilFinished, TextView tView){
        long minutes = millisUntilFinished / 60000;
        String seconds = "";
        if (millisUntilFinished / 1000 - minutes * 60 < 10) {
            seconds += "0" + (millisUntilFinished / 1000 - minutes * 60);
        }
        else {
            seconds += (millisUntilFinished / 1000 - minutes * 60);
        }
        String time = "" + minutes + ":"
                + seconds;
        tView.setText(time);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_screen);

        thisMatch = new MatchData(1,2); //THIS WILL HAVE TO BE CHANGED!!

        int defalt = Color.WHITE;
        Button box = (Button) findViewById(R.id.cards);
        if (box != null)
            box.setBackgroundColor(defalt);
        box = (Button) findViewById(R.id.cards2);
        if (box != null)
            box.setBackgroundColor(defalt);
        p1Yellow = false;
        p2Yellow = false;


        //Get reference of the XML layout's widgets
        final TextView tView = (TextView) findViewById(R.id.tv);
        final Button btnStart = (Button) findViewById(R.id.start);
        final Button btnPause = (Button) findViewById(R.id.stop);
        final Button btnResume = (Button) findViewById(R.id.btn_resume);
        final Button btnCancel = (Button) findViewById(R.id.btn_cancel);

        //Initially disabled the pause, resume and cancel button
        btnPause.setEnabled(false);
        btnResume.setEnabled(false);
        btnCancel.setEnabled(false);


        //Set a Click Listener for start button
        if (btnStart != null){
            btnStart.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View v){

                    isPaused = false;
                    isCanceled = false;

                    //Disable the start and pause button
                    btnStart.setEnabled(false);
                    btnResume.setEnabled(false);
                    //Enabled the pause and cancel button
                    btnPause.setEnabled(true);
                    btnCancel.setEnabled(true);

                    CountDownTimer timer;
                    long millisInFuture = 900000; //30 seconds
                    long countDownInterval = 1000; //1 second

                    //Initialize a new CountDownTimer instance
                    timer = new CountDownTimer(millisInFuture,countDownInterval){
                        public void onTick(long millisUntilFinished){
                            //do something in every tick
                            if(isPaused || isCanceled)
                            {
                                //If the user request to cancel or paused the
                                //CountDownTimer we will cancel the current instance
                                cancel();
                            }
                            else {
                                //Display the remaining seconds to app interface
                                //1 second = 1000 milliseconds
                                setTimeText(millisUntilFinished, tView);

                                //Put count down timer remaining time in a variable
                                timeRemaining = millisUntilFinished;
                            }
                        }
                        public void onFinish(){
                            //Do something when count down finished
                            tView.setText("Done");

                            //Enable the start button
                            btnStart.setEnabled(true);
                            //Disable the pause, resume and cancel button
                            btnPause.setEnabled(false);
                            btnResume.setEnabled(false);
                            btnCancel.setEnabled(false);
                        }
                    }.start();
                }
            });
        }

        //Set a Click Listener for pause button
        btnPause.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //When user request to pause the CountDownTimer
                isPaused = true;

                //Enable the resume and cancel button
                btnResume.setEnabled(true);
                btnCancel.setEnabled(true);
                //Disable the start and pause button
                btnStart.setEnabled(false);
                btnPause.setEnabled(false);
            }
        });

        //Set a Click Listener for resume button
        btnResume.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //Disable the start and resume button
                btnStart.setEnabled(false);
                btnResume.setEnabled(false);
                //Enable the pause and cancel button
                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);

                //Specify the current state is not paused and canceled.
                isPaused = false;
                isCanceled = false;

                //Initialize a new CountDownTimer instance
                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval){
                    public void onTick(long millisUntilFinished){
                        //Do something in every tick
                        if(isPaused || isCanceled)
                        {
                            //If user requested to pause or cancel the count down timer
                            cancel();
                        }
                        else {
                            setTimeText(millisUntilFinished, tView);
                            //Put count down timer remaining time in a variable
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        //Do something when count down finished
                        tView.setText("Done");
                        //Disable the pause, resume and cancel button
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                        //Enable the start button
                        btnStart.setEnabled(true);
                    }
                }.start();

                //Set a Click Listener for cancel/stop button
                btnCancel.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //When user request to cancel the CountDownTimer
                        isCanceled = true;

                        //Disable the cancel, pause and resume button
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                        //Enable the start button
                        btnStart.setEnabled(true);

                        //Notify the user that CountDownTimer is canceled/stopped
                        tView.setText("CountDownTimer Canceled/stopped.");
                    }
                });
            }
        });

        //Set a Click Listener for cancel/stop button
        btnCancel.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                //When user request to cancel the CountDownTimer
                isCanceled = true;

                //Disable the cancel, pause and resume button
                btnPause.setEnabled(false);
                btnResume.setEnabled(false);
                btnCancel.setEnabled(false);
                //Enable the start button
                btnStart.setEnabled(true);

                //Notify the user that CountDownTimer is canceled/stopped
                tView.setText("Done");
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }
}
