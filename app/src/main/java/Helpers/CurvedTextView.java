package Helpers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shaibujnr on 8/4/16.
 */
public class CurvedTextView extends View{
    private String myText = "Curved Text View";
    private Path mArc;
    private Paint mPaint;
    public CurvedTextView(Context context) {
        super(context);
        mArc = new Path();
        RectF oval = new RectF(50,100,200,250);
        mArc.addArc(oval,-180,200);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Aerosol.ttf"));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(40f);
    }

    public CurvedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurvedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawTextOnPath(myText,mArc,0,20,mPaint);
        invalidate();
    }

    public String getMyText() {
        return myText;
    }

    public void setMyText(String myText) {
        this.myText = myText;
    }
}
