package Helpers.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by AbdulGafar on 6/16/2016.
 */
public class MenuTextView extends TextView {
    public MenuTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/GreatPoints.ttf"));
    }
}
