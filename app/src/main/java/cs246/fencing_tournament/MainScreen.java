package cs246.fencing_tournament;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {

    public Button viewContestant;
    public Button bracketView;
    public Button poolView;
    public Button enterContestant;

    public void init() {
        viewContestant = (Button) findViewById(R.id.viewContestant);
        bracketView =  (Button) findViewById(R.id.bracketView);
        poolView =  (Button) findViewById(R.id.poolView);
        enterContestant =  (Button) findViewById(R.id.enterContestant);

        viewContestant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        bracketView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        poolView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        enterContestant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent action = new Intent(MainScreen.this,EnterContestant.class);

                startActivity (action);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }
}
