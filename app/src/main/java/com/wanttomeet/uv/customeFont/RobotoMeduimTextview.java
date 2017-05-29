package com.wanttomeet.uv.customeFont;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Web on 4/22/2016.
 */
public class RobotoMeduimTextview extends TextView {

    public RobotoMeduimTextview(Context context) {
        super(context);
        setFontType(context);
    }

    public RobotoMeduimTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFontType(context);
    }

    public RobotoMeduimTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontType(context);
    }

    public void setFontType(Context context) {


            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "roboto.medium.ttf");
            setTypeface(typeface);


    }
}
