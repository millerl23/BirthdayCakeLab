package cs301.birthdaycake;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        CakeView cakeView = findViewById(R.id.cakeview);
        CakeController cakeController = new CakeController(cakeView);
        System.out.println("4");
        cakeView.setOnTouchListener(cakeView);

        cakeView.setOnTouchListener(cakeController);

        Button blowout = findViewById(R.id.blowOut);
        blowout.setOnClickListener(cakeController);


        Switch candles = findViewById(R.id.switch2);
        candles.setOnCheckedChangeListener(cakeController);

        SeekBar numCandles = findViewById(R.id.seekBar);
        numCandles.setOnSeekBarChangeListener(cakeController);

    }

    public void goodbye(View button) {
        Log.i("goodbye", "Goodbye");
    }

}
