package com.acme.metalball;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.graphics.Rect;

import android.graphics.Color;
import android.graphics.Paint;


import java.util.Random;
import java.util.prefs.Preferences;

import static android.R.attr.x;
import static android.R.attr.y;


//SensorEvenListener se usa para recibir/gestionar las notificaciones
//de un SensorManager. Cuando un SensorManager nos diga qua a un Sensor
//"le pasa" algo, lo gestionamos con el listener
public class MetalBall extends Activity implements SensorEventListener {

    public double accDividir = 2;

    //la clase SensorManager permite acceder a los sensores (en general)
    //del dispositivo. Esto significa acceder al hardware
    private SensorManager mSensorManager;

    //la clase Sensor es principalmente, un cúmulo de variables estáticas finales
    //para representar los diferentes tipos de sensores, ademas de algunos
    //setters y getters
    private Sensor mAccelerometer;

    public GroundView ground=null;//GroundView es una clase propia anidada.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //le quitamos la barra de titulo, para hacer una pantalla completa
        //request hay que hacerlo antes de setContentView!
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_metal_ball);

        //obtenemos referencias al servicio, y lo "ligamos"
        //al sensor del tipo acelerometro
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Configuramos la pantalla:
        //pantalla panoramica
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //fijamos las banderas de pantalla completa para gestionar esta
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //comprobamos que tengamos una version superior al 3.0 (HoneyComb)
        //para fijar mas parametros de la pantalla, que antes de esta version
        //no habia
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_IMMERSIVE
            );
        }

        //Se indica la vista
        ground = new GroundView(this);
        setContentView(ground);

    }

    //implementar SensorEventListener obliga a implementar
    //on AccuracyChanged y onSensorChanged
    public void onAccuracyChanged(Sensor sensor, int accuracy){}

    //Es aqui donde recogemos los cambios que "percibe" al hardware
    //(en este caso el acelerometro), y nos da la opcion a gestionarlo
    //pasandonos la informacion mediante un parametro tipo SensorEvent
    //SensorEvent guarda bastante informacion:
    //tipo de sensor, fecha-hora del evento
    public void onSensorChanged (SensorEvent event){
        //event.values es un array con los cambios en los ejes:
        //event.values[0]: ACELERACION en el eje X
        //event.values[1]: aceleracion en el eje Y
        //event.values[2]: aceleracion en el eje Z
        //el "problema" a tener en cuenta es que, al poner el dispositivo
        //en modo landscape, hemos invertido los ejes X e Y,
        //PORQUE GIRAR LA PANTALLA NO CAMBIA LOS EJES!! los ejes estan determinados
        //en relacion al dispositivo, no al usuario
        ground.updateMe(event.values[1], event.values[0]);
    }


    //en estas dos funciones (onResume y onPause), registramos un listener al
    //empezar/continuar un juego, y lo liberamos al pausarlo para ahorrar recursos
    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    //SurfaceHolder nos sirve para implementar esta clase como gestora de una superficie
    //Asi, podremos
    public class GroundView extends SurfaceView implements SurfaceHolder.Callback{

        //coordenadas de la bola
        private float cx = 10;
        private float cy=10;
        //cordenadas del cuadro
        private float cdx=200;
        private float cdy=200;
        //ultimo incremento de la posicion
        private float lastGX = 0;
        private float lastGY = 0;
        //tamaño del grafico de la bola
        private int pictureHeight=0;
        private int pictureWidth=0;
        private int cuadroHeight=0;
        private int cuadroWidth=0;
        private Bitmap icon=null;
        private Bitmap cuadro=null;
        private Bitmap fondo=null;
        //tamaño de la pantalla
        private int width=0;
        private int height=0;
        //booleans para comprobar si esta tocando el borde
        private boolean noBorderX=false;
        private boolean noBorderY=false;
        private Vibrator vibratorService=null;
        private DrawThread thread;
        private int dentro=0;


        public RectF debajoBola = new RectF(100,100,100,100);
        public RectF debajoTuenti = new RectF(100,100,48,48);

        public GroundView(Context context){
            super(context);

            //asignamos el callback a la superficie subyacente del SurfaceView.
            //addCallback pertenece a SurfaceHolder, y lo que hace es asignar un
            //callback a un contexto. Este contexto suele ser tipicamente un SurfaceView.
            //Por lo tanto, al hacer esto, estamos fijando este objeto (el que estamos
            // creando con onCreate) como gestor del SurfaceView.
            getHolder().addCallback(this);

            //se crea el thread
            thread = new DrawThread(getHolder(), this);

            //obtenemos referencias y tamaños de objetos a dibujar
            DisplayMetrics dm= new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm);
            width = dm.widthPixels;
            height = dm.heightPixels;
            icon = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
            cuadro = BitmapFactory.decodeResource(getResources(), R.drawable.cuadro);
            //fondo = BitmapFactory.decodeResource(getResources(), R.drawable.fo);
            cuadroHeight=cuadro.getHeight();
            cuadroWidth=cuadro.getWidth();

            pictureHeight=icon.getHeight();
            pictureWidth=icon.getWidth();
            vibratorService=(Vibrator)(getSystemService(Service.VIBRATOR_SERVICE));
        }

        //en el mismo momento que se crea el SurfaceView creamos e iniciamos el
        //thread que se va a encargar de mover la bola.
        @Override
        public void surfaceCreated (SurfaceHolder holder){
            thread.setRunning(true);
            thread.start();
        }

        @Override
        protected void onDraw (Canvas canvas){
            if (canvas != null){
                canvas.drawColor(0xFF000000);
                Paint pintureta = new Paint();
                Paint puntos = new Paint();
                pintureta.setColor(Color.alpha(0)); // aqui poner gris para ver
                canvas.drawRect(debajoBola, pintureta);
                canvas.drawRect(debajoTuenti, pintureta);
                canvas.drawBitmap(cuadro,cdx,cdy, null);
                canvas.drawBitmap(icon, cx, cy, null);
                puntos.setTextSize(40);//tamaño de la puntuacion
                puntos.setColor(Color.RED);//color de la puntuacion
                canvas.drawText("puntuacion: "+dentro,40,60,puntos);//pintar la puntuacion
            }
        }

        public Boolean CollisionTest (RectF one, RectF two) {

            return one.intersect(two);
        }
        //la funcion para acutalizar la posicion de la bola
        public void updateMe (float inx, float iny) {
            //actualizamos la aceleracion
            lastGX += inx;
            lastGY += iny;

            //actualizamos la posicion
            if (noBorderX){cx += lastGX;}
            if (noBorderY){cy += lastGY;}
            //Rectangle bounds = new Rectangle();
            //if (icon.intersects)

            debajoBola.left =(int) cx+pictureWidth;
            debajoBola.top =(int) cy+pictureHeight;
            debajoBola.right =(int) cx;
            debajoBola.bottom =(int) cy;
            debajoTuenti.left =(int) cdx+cuadroWidth-50;
            debajoTuenti.top =(int) cdy+cuadroHeight;
            debajoTuenti.right = (int) cdx;
            debajoTuenti.bottom = (int) cdy;

            //comprobamos si esta en el borde, no vamos a dejar que se salga,
            //si no que va a vibrar. Recordar ponerlo en el manifest
            if (cx > (cdx - pictureWidth)&&cx<(cdx+pictureWidth)){
                if (cy > (cdy - pictureHeight)&&cy<(cdy+pictureHeight)){

                        Random r = new Random(); int i1 = r.nextInt((width-pictureWidth) - 0);//1755 /*poner aleatoriamente la posicion x de la imagen*/
                        Random d = new Random(); int i2 = d.nextInt((height-pictureHeight) - 0);//930 /*poner aleatoriamente la posicion y de la imagen*/
                        cdx=(float)i1;
                        cdy=(float)i2;
                        dentro++;//variable puntuacion


                    //}


                }
            }

            if (cx > (width - pictureWidth)) {
                cx = width - pictureWidth;
                //lastGX = 0;
                if (noBorderX) {
                    enElBorde(cx,cy);
                    //vibratorService.vibrate(100);
                    noBorderX = false;
                }
            } else if (cx < (0)) {
                cx = 0;
                //lastGX = 0;
                if (noBorderX) {
                    enElBorde(cx,cy);
                    //vibratorService.vibrate(100);
                    noBorderX = false;
                }
            } else {
                noBorderX = true;
            }

            if (cy > (height - pictureHeight)) {
                cy = height - pictureHeight;
                //lastGY = 0;
                if (noBorderY) {
                    enElBorde(cx,cy);
                    //vibratorService.vibrate(100);
                    noBorderY = false;
                }
            } else if (cy < (0)) {
                cy = 0;
                //lastGY = 0;
                if (noBorderY) {
                    enElBorde(cx,cy);
                    //vibratorService.vibrate(100);
                    noBorderY = false;
                }
            } else {

                noBorderY = true;
            }

            //forzamos el redibujado (onDraw) invalidando la superficie
            invalidate();
        }

        //implements SurfaceHolder nos obliga a definir surfaceDestroyed
        //y surfaceChanged
        public void enElBorde(float cx,float cy){

            if(lastGX<-5 || lastGX>5){//Si hay movimiento fuerte en el x
                lastGX = (-lastGX)/(float) accDividir; //rebota
            }else{
                lastGX=0;
            }
            if(lastGY<-5 || lastGY>5){//si hay movimiento fuerte en el y
                lastGY = (-lastGY)/(float) accDividir;//rebota
            }else{
                lastGY=0;
            }
        }
        public void surfaceDestroyed(SurfaceHolder holder){}
        public void surfaceChanged(SurfaceHolder holder, int format, int heght, int wdth){}

    }


    //Creamos esta clase para gestionar la ejecucion del juego y que nada "se interponga"
    class DrawThread extends Thread{

        private SurfaceHolder surfaceHolder;
        private GroundView panel;
        private boolean run= false;

        public DrawThread(SurfaceHolder surfaceHolder, GroundView panel){
            this.surfaceHolder = surfaceHolder;
            this.panel = panel;
        }

        public void setRunning (boolean run){
            this.run = run;
        }

        @Override
        public void run(){
            Canvas c;
            while (run){
                c=null;
                try {
                    c = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        panel.onDraw(c);
                    }
                }finally{
                    //hacer esto en un bloque finally facilita que,
                    //si ocurre un error, no se deja el SurfaceView inconsistente,
                    //se deja de forma "elegante"
                    if ( c!= null){
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

    }

}