package cs301.birthdaycake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.MotionEvent;
import android.view.View;

public class CakeView extends SurfaceView {


    Paint textPaint = new Paint();
    /* These are the paints we'll use to draw the birthday cake below */
    Paint cakePaint = new Paint();
    Paint frostingPaint = new Paint();
    Paint candlePaint = new Paint();
    Paint outerFlamePaint = new Paint();
    Paint innerFlamePaint = new Paint();
    Paint wickPaint = new Paint();
    Paint balloonPaint = new Paint();
    Path balloonPath = new Path();
    Paint redPaint = new Paint();
    Paint greenPaint = new Paint();
    // This is nothin


    /* These constants define the dimensions of the cake.  While defining constants for things
        like this is good practice, we could be calculating these better by detecting
        and adapting to different tablets' screen sizes and resolutions.  I've deliberately
        stuck with hard-coded values here to ease the introduction for CS371 students.
     */
    public static final float cakeTop = 400.0f;
    public static final float cakeLeft = 100.0f;
    public static final float cakeWidth = 1200.0f;
    public static final float layerHeight = 200.0f;
    public static final float frostHeight = 50.0f;
    public static final float candleHeight = 300.0f;
    public static final float candleWidth = 80.0f;
    public static final float wickHeight = 30.0f;
    public static final float wickWidth = 6.0f;
    public static final float outerFlameRadius = 30.0f;
    public static final float innerFlameRadius = 15.0f;

    private CakeModel cake;



    /**
     * constructor must be overridden here as per standard Java inheritance practice.  We need it
     * anyway to initialize the member variables
     */
    public CakeView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //This is essential or your onDraw method won't get called
        setWillNotDraw(false);

        //Setup our palette
        cakePaint.setColor(0xFFF5DD42);  //yellow
        cakePaint.setStyle(Paint.Style.FILL);
        frostingPaint.setColor(0xFFFFFACD);  //pale yellow
        frostingPaint.setStyle(Paint.Style.FILL);
        candlePaint.setColor(0xFF32CD32);  //lime green
        candlePaint.setStyle(Paint.Style.FILL);
        outerFlamePaint.setColor(0xFFFFD700);  //gold yellow
        outerFlamePaint.setStyle(Paint.Style.FILL);
        innerFlamePaint.setColor(0xFFFFA500);  //orange
        innerFlamePaint.setStyle(Paint.Style.FILL);
        wickPaint.setColor(Color.BLACK);
        wickPaint.setStyle(Paint.Style.FILL);
        balloonPaint.setColor(Color.BLUE);
        balloonPaint.setStyle(Paint.Style.FILL);
        redPaint.setColor(Color.RED); //red
        redPaint.setStyle(Paint.Style.FILL);
        greenPaint.setColor(Color.GREEN); //green
        greenPaint.setStyle(Paint.Style.FILL);

        setBackgroundColor(Color.WHITE);  //better than black default

        cake = new CakeModel();

        textPaint.setColor(Color.RED);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setTextSize(50);
        cake.x = -1;
    }

    /**
     * draws a candle at a specified position.  Important:  the left, bottom coordinates specify
     * the position of the bottom left corner of the candle
     */
    public void drawCandle(Canvas canvas, float left, float bottom) {
        if (cake.hasCandles) {
            //draw the wax
            canvas.drawRect(left, bottom - candleHeight, left + candleWidth, bottom, candlePaint);

            //draw the wick
            float wickLeft = left + candleWidth / 2 - wickWidth / 2;
            float wickTop = bottom - wickHeight - candleHeight;
            canvas.drawRect(wickLeft, wickTop, wickLeft + wickWidth, wickTop + wickHeight, wickPaint);

            if (cake.candlesLit) {
                //draw the outer flame
                float flameCenterX = left + candleWidth / 2;
                float flameCenterY = bottom - wickHeight - candleHeight - outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, outerFlameRadius, outerFlamePaint);

                //draw the inner flame
                flameCenterY += outerFlameRadius / 3;
                canvas.drawCircle(flameCenterX, flameCenterY, innerFlameRadius, innerFlamePaint);
            }
        }

    }

    /**
     * onDraw is like "paint" in a regular Java program.  While a Canvas is
     * conceptually similar to a Graphics in javax.swing, the implementation has
     * many subtle differences.  Show care and read the documentation.
     *
     * This method will draw a birthday cake
     */
    @Override
    public void onDraw(Canvas canvas)
    {
        //top and bottom are used to keep a running tally as we progress down the cake layers
        float top = cakeTop;
        float bottom = cakeTop + frostHeight;


        //Frosting on top
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);
        top += layerHeight;
        bottom += frostHeight;

        //Then a second frosting layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, frostingPaint);
        top += frostHeight;
        bottom += layerHeight;

        //Then a second cake layer
        canvas.drawRect(cakeLeft, top, cakeLeft + cakeWidth, bottom, cakePaint);

        //Now draw candles

        if (cake.numCandles == 1){
            drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
        }
        else if (cake.numCandles == 2){
            drawCandle(canvas, cakeLeft + cakeWidth/4 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/4)*3 - candleWidth/2, cakeTop);
        }
        else if (cake.numCandles == 3){
            drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth/4 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/4)*3 - candleWidth/2, cakeTop);
        }
        else if (cake.numCandles == 4){
            drawCandle(canvas, cakeLeft + cakeWidth/5 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/5)*2 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/5)*3 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/5)*4 - candleWidth/2, cakeTop);
        }
        else if (cake.numCandles == 5){
            drawCandle(canvas, cakeLeft + cakeWidth/2 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + cakeWidth/6 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/6)*2 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/6)*4 - candleWidth/2, cakeTop);
            drawCandle(canvas, cakeLeft + (cakeWidth/6)*5 - candleWidth/2, cakeTop);
        }

        if (cake.x >= 0){
            canvas.drawText( "("+cake.x+","+cake.y+ ")", 1982, 40, textPaint);
            canvas.drawText("  x       y   ", 1970,73, textPaint);

        }

        if (cake.hasBalloon){
            balloonPath.reset();
            canvas.drawLine(cake.balloonX,cake.balloonY,cake.balloonX,cake.balloonY+300,wickPaint);
            canvas.drawCircle(cake.balloonX, cake.balloonY, cake.balloonRadius, balloonPaint);
            balloonPath.moveTo(cake.balloonX-60, cake.balloonY+35);
            balloonPath.lineTo(cake.balloonX, cake.balloonY+100);
            balloonPath.lineTo(cake.balloonX+60,cake.balloonY+35);
            canvas.drawPath(balloonPath, balloonPaint);

        }
        if (cake.checkerPlaced) {
            canvas.drawRect(cake.checkerX - 50, cake.checkerY - 50, cake.checkerX, cake.checkerY, greenPaint);
            canvas.drawRect(cake.checkerX + 50, cake.checkerY + 50, cake.checkerX, cake.checkerY, greenPaint);
            canvas.drawRect(cake.checkerX, cake.checkerY - 50, cake.checkerX + 50, cake.checkerY, redPaint);
            canvas.drawRect(cake.checkerX - 50, cake.checkerY, cake.checkerX, cake.checkerY + 50, redPaint);
        }

    }//onDraw

    public CakeModel getCake() {
        return cake;
    }

}//class CakeView

