package cs246.fencing_tournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class EnterContestant extends AppCompatActivity {

    public Button finished;

    public void init(){
        finished = (Button) findViewById(R.id.finished);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contestant);
        init();
    }
}
