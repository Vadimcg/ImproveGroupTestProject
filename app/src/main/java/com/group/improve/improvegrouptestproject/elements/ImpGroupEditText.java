package com.group.improve.improvegrouptestproject.elements;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.group.improve.improvegrouptestproject.R;

public class ImpGroupEditText extends EditText
{
    /**
     * Paint Object used to paint limit text.
     */
    private Paint limit_text_paint;

    private Paint linePaint;

    /**
     * Current size of text displayed in the EditView.
     */
    private int current_text_size;
    /**
     * Maximum text size allowed.
     */
    private int maximum_text_size;
    /**
     * Default limit indicator X margin used.
     */
    private final int DEFAULT_X_MARGIN= 10;
    /**
     * Default limit indicator Y margin used.
     */
    private final int DEFAULT_Y_MARGIN= 100;
    /**
     * Flag indicating text size limit is unlimited.
     */
    public static final int UNLIMITED= -100;


    private static final int mHeigthFont=17;

    /**
     * LimitedEditText constructor.
     * @param context Context that will display this view.
     * @param attrs Set of attributes defined for this view.
     * @param defStyle Style defined for this view.
     */
    public ImpGroupEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initComponents(attrs);
    }
    /**
     * LimitedEditText constructor.
     * @param context Context that will display this view.
     * @param attrs Set of attributes defined for this view.
     */
    public ImpGroupEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponents(attrs);
    }
    /**
     * LimitedEditText constructor.
     * @param context Context that will display this view.
     */
    public ImpGroupEditText(Context context) {
        super(context);
        initComponents(null);
    }

    /**
     * Initialize the UI components.
     * @param attrs Set of attributes defined for this view.
     */
    private void initComponents(AttributeSet attrs) {



        this.setPadding(
                 this.getPaddingLeft()
                ,this.getPaddingTop()
                ,this.getRight()
                ,this.getPaddingBottom()+mHeigthFont);

        linePaint=new Paint();
        linePaint.setColor(getResources().getColor(R.color.greyBack));
        linePaint.setStrokeWidth(4f);

        limit_text_paint= new Paint();
        limit_text_paint.setColor(getResources().getColor(R.color.greyBack));
        limit_text_paint.setTextSize(this.mHeigthFont);
        maximum_text_size= UNLIMITED;
        current_text_size= 0;
        if(attrs!=null) //android:maxLength
            maximum_text_size= attrs.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLength", UNLIMITED);

        super.addTextChangedListener(new  TextWatcher(){

            @Override
            public void afterTextChanged(Editable s)
            {
                if(maximum_text_size != UNLIMITED)
                    current_text_size= getText().toString().length();
                invalidate();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before,int count) {}
        });




        this.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    linePaint.setColor(getResources().getColor(R.color.yellow));
                }else {
                    linePaint.setColor(getResources().getColor(R.color.greyBack));
                }
            }
        });

    }

    /**
     * Set maximum text size for given EditText.
     * @param max Maximum number of characters allowed.
     */
    public void setMaxTextSize(int max)
    {
        maximum_text_size= max;
        if(max== UNLIMITED)
            setFilters(null);
        else
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
    }
    /**
     * Remove text size limit.
     * This will stop drawing the amount of characters left.
     */
    public void removeTextSizeLimit()
    {
        setMaxTextSize(UNLIMITED);
    }
    /**
     * Get the X position of limit indicator.
     * @param text Text to be drawn on the limit indicator.
     * @return X position of limit indicator.
     */
    protected float getLimitIndicatorX(String text)
    {
        float widths[]= new float[text.length()];
        limit_text_paint.getTextWidths(text, widths);

        float sum= 0;
        for(float w: widths)
            sum+= w;
        return getWidth() + getScrollX() - sum - DEFAULT_X_MARGIN;
    }
    /**
     * Get the Y position of limit indicator.
     * @param text Text to be drawn on the limit indicator.
     * @return Y position of limit indicator.
     */
    protected float getLimitIndicatorY(String text)
    {
        return DEFAULT_Y_MARGIN - getScrollY();
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(maximum_text_size== UNLIMITED)
            return;

        String text= current_text_size+"/"+maximum_text_size;
        canvas.drawText(text, getLimitIndicatorX(text), getHeight(), limit_text_paint);

        float heigth=getHeight()-this.mHeigthFont;

        canvas.drawLine(0,heigth,getWidth(),heigth,linePaint);
    }

}