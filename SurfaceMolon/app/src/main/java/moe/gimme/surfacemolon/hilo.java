package moe.gimme.surfacemolon;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by ik_2dm3 on 03/02/2017.
 */

public class hilo extends Thread{

    private boolean run = true;
    private vista vista;

    public hilo(vista vista) {
        this.vista=vista;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    @Override
    public void run() {

        while (run) {
            Canvas c = null;
            try {
                c = vista.getHolder().lockCanvas(null);
                synchronized (vista.getHolder()) {
                    vista.onDraw(c);

                }
            } finally {
                //hacer esto en un bloque finally facilita que,
                //si ocurre un error, no se deja el SurfaceView inconsistente,
                //se deja de forma "elegante"
                if (c != null) {
                    vista.getHolder().unlockCanvasAndPost(c);
                }
            }
        }

    }

}