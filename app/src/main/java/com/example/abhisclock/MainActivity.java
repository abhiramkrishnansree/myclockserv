package com.example.abhisclock;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.*;
import android.os.Handler;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends Activity {
    private final TimeZone KOLKATA = TimeZone.getTimeZone("Asia/Kolkata");
    private TextView clock, date;
    private final Handler handler = new Handler();
    private final Runnable tick = new Runnable() {
        public void run() { update(); handler.postDelayed(this, 1000); }
    };
    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER);
        box.setPadding(24,24,24,24);
        box.setBackgroundColor(Color.rgb(10,10,12));

        TextView title = new TextView(this);
        title.setText("ABHIS CLOCK");
        title.setTextColor(Color.WHITE); title.setTextSize(18); title.setGravity(Gravity.CENTER);
        clock = new TextView(this); clock.setTextColor(Color.WHITE); clock.setTextSize(52); clock.setGravity(Gravity.CENTER);
        date = new TextView(this); date.setTextColor(Color.LTGRAY); date.setTextSize(20); date.setGravity(Gravity.CENTER);

        TextView place = new TextView(this);
        place.setText("Kolkata • India  |  Asia/Kolkata");
        place.setTextColor(Color.GRAY); place.setTextSize(14); place.setGravity(Gravity.CENTER);

        box.addView(title, new LinearLayout.LayoutParams(-1, -2));
        box.addView(clock, new LinearLayout.LayoutParams(-1, -2));
        box.addView(date, new LinearLayout.LayoutParams(-1, -2));
        box.addView(place, new LinearLayout.LayoutParams(-1, -2));
        setContentView(box);
        handler.post(tick);
    }
    private void update() {
        Date now = new Date();
        SimpleDateFormat t = new SimpleDateFormat("hh:mm:ss a", Locale.ENGLISH); t.setTimeZone(KOLKATA);
        SimpleDateFormat d = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.ENGLISH); d.setTimeZone(KOLKATA);
        clock.setText(t.format(now)); date.setText(d.format(now));
    }
    @Override protected void onDestroy(){ handler.removeCallbacksAndMessages(null); super.onDestroy(); }
}
