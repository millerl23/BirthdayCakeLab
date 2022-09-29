package cs301.birthdaycake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class CakeController
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener, View.OnTouchListener {
    private CakeView cv;
    private CakeModel cm;

    public CakeController(CakeView _cv) {
        cv = _cv;
        cm = _cv.getCake();
    }


    @Override
    public void onClick(View view) {

        if (cm.candlesLit) {
            cm.candlesLit = false;
        } else {
            cm.candlesLit = true;
        }

        cv.invalidate();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (cm.hasCandles) {

            cm.hasCandles = false;
        } else {
            cm.hasCandles = true;
        }
        cv.invalidate();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        cm.numCandles = i;

        cv.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
            cm.hasBalloon = true;

            cm.balloonX = motionEvent.getX();
            cm.balloonY = motionEvent.getY();
            cm.checkerPlaced = true;
            cm.checkerX = motionEvent.getX();
            cm.checkerY = motionEvent.getY();
            cm.x = (int) motionEvent.getX();
            cm.y = (int) motionEvent.getY();

            cv.invalidate();
        }

        return true;
    }
}
