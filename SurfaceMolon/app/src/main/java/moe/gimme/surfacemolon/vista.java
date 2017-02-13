package moe.gimme.surfacemolon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ik_2dm3 on 03/02/2017.
 */

public class vista extends SurfaceView implements SurfaceHolder.Callback{

    Paint pintureta = new Paint(Color.GREEN);


    public vista(Context context) {
        super(context);
    }

    public void onDraw(Canvas c){

        c.drawColor(0xFF0000FF);
        c.drawRect(10,10,100,100, pintureta);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
