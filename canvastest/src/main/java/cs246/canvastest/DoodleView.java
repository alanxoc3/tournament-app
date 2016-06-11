package cs246.canvastest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by alanxoc3 on 6/11/16.
 */
public class DoodleView extends View {
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

    }
}
