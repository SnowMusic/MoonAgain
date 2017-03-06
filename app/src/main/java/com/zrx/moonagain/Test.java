package com.zrx.moonagain;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Schnee on 2017/3/2.
 */

public class Test extends Activity implements View.OnTouchListener{
    int d = 10;

    public int getD() {
        return d;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;

    }



    static class B extends Test {
        @Override
        public int getD() {
            return 11;
        }
    }

    public static void main(String[] args) {
        Test t = new B();
        System.out.println(t.getD());
    }

}
