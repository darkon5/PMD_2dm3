package moe.gimme.pizarra;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by ik_2dm3 on 27/10/2016.
 */

public class Board extends View{

    private Bitmap mBitmap=null;
    public static Canvas mCanvas=null;
    private Paint mPaint=null;

    private float mX, mY;
    private Path mPath=null;

    private static final float TOLERANCE = 4;

    public Board (Context context, AttributeSet attrs){
        super(context);
        init(context);
    }

    public Board (Context context){
        super(context);
        init(context);
    }

    private void init(Context context){
        // obtener pantalla
        Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        Point point = new Point();
        display.getSize(point);

        mBitmap = Bitmap.createBitmap(point.x, point.y, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas (mBitmap);
        mCanvas.drawColor(0xFF000000);

        mPath=  new Path();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFF0F0F0);
    }
    @Override
    public boolean onTouchEvent (MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x,y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                invalidate();
                break;
        }
        return true;
    }
    private void touchStart(float x,float y){
        mPath.reset();
        mPath.moveTo(x,y);
        mX=x;
        mY=y;
    }
    private void touchMove(float x,float y){

        if (Math.abs(x-mX)>=TOLERANCE || Math.abs(y-mY)>=TOLERANCE) {
            mPath.quadTo(mX,mY,(x+mX)/2,(y+mY)/2);
            mX=x;
            mY=y;
        }
    }
    private void touchUp() {
        mPath.lineTo(mX, mY);
        mCanvas.drawPath(mPath,mPaint);
        mPath.reset();   // opcional
    }

    @Override
    protected void onDraw (Canvas canvas){
        //fondo
        canvas.drawColor(0xFFBBBBBB);
        //
        canvas.drawBitmap(mBitmap,0,0,null);
        // le añadimos el trazo actual
        canvas.drawPath(mPath,mPaint);
    }
}
