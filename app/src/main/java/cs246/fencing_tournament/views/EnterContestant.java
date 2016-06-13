package cs246.fencing_tournament.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

<<<<<<< HEAD:app/src/main/java/cs246/fencing_tournament/views/EnterContestant.java
import cs246.fencing_tournament.R;
=======
import java.util.ArrayList;
import java.util.List;
>>>>>>> 54fe353fd6c9c1e6229d30b652e51526027e83fc:app/src/main/java/cs246/fencing_tournament/EnterContestant.java

public class EnterContestant extends AppCompatActivity {

    public Button finished;


    public void done(View v){
        List<ContestantData> contestants = new ArrayList<ContestantData> ();
        Intent action = new Intent(EnterContestant.this, MainScreen.class);
        startActivity(action);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_contestant);

    }
}
