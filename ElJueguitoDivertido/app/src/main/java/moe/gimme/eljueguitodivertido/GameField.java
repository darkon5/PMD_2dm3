package moe.gimme.eljueguitodivertido;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameField extends AppCompatActivity {
    int topTileX = 3;
    int topTileY = 3;
    int topElements = 2;
    boolean hasSound = false;
    boolean hasVibration = false;
    String modo;
    int contador = 0;
    boolean igual =false;



    //array con los identificadores de las celdas cuando se añaden
    //al layout, para poder recuperrarlos durante la partida
    private int ids[][] = null;

    //array para guardar los indices <background> de cada una de las celdas.
    //se utilizara para agilizar la comprobacion de final de partida
    private int values[][] = null;

    private Vibrator vibratorService = null;
    private MediaPlayer mp = null;

    private static final int[] colors = new int[]{
            R.drawable.naranja,
            R.drawable.amarillo,
            R.drawable.azul,
            R.drawable.gris,
            R.drawable.morado,
            R.drawable.raro,
            R.drawable.rojo,
            R.drawable.rosa,
            R.drawable.verde
    };


    private static final int[] numbers = new int[]{
            R.drawable.n1,
            R.drawable.n2,
            R.drawable.n3,
            R.drawable.n4,
            R.drawable.n5,
            R.drawable.n6,
            R.drawable.n7,
            R.drawable.n8,
            R.drawable.n9
    };

    private int[] pictures = null;

    private Chronometer chronometer = null;
    private TextView clickTxt = null;
    private LinearLayout l1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamefild);

        chronometer = (Chronometer) findViewById(R.id.ch);
        clickTxt = (TextView) findViewById(R.id.clicksTxt);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        l1 = (LinearLayout) findViewById(R.id.fieldLandscape);
        l1.removeAllViews();

        Bundle extras = getIntent().getExtras();

        topTileX = extras.getInt("sbx");
        topTileY = extras.getInt("sby");
        topElements = extras.getInt("sbTramas");
        hasSound = extras.getBoolean("ckSonido");
        hasVibration = extras.getBoolean("ckVibracion");

        modo = extras.getString("tipoJuego");
        if (modo.equals("colores")) {
            pictures = colors;
        } else {
            pictures = numbers;
        }
        vibratorService = (Vibrator) (getSystemService(Service.VIBRATOR_SERVICE));
        /*mp = MediaPlayer.create(this, R.raw.rana);*/

        DisplayMetrics dm = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels / topTileX;
        int height = (dm.heightPixels - 180) / topTileY;

        ids = new int[topTileX][topTileY];
        values = new int[topTileX][topTileY];

        Random r = new Random(System.currentTimeMillis());
        int tilePictureToShow = r.nextInt(topElements);
        int ident = 0;

        for (int i = 0; i < topTileY; i++) {
            LinearLayout l2 = new LinearLayout(this);
            l2.setOrientation(LinearLayout.HORIZONTAL);

            for (int j = 0; j < topTileX; j++) {
                tilePictureToShow = r.nextInt(topElements);

                values[j][i] = tilePictureToShow;
                TitleView tv = new TitleView(this, j, i, topElements,
                        tilePictureToShow, pictures[tilePictureToShow]);
                ident++;
                tv.setId(ident);
                ids[j][i] = ident;

                tv.setHeight(height);
                tv.setWidth(width);

                tv.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View vista) {
                        hasClick(((TitleView) vista).x, ((TitleView) vista).y);
                    }

                });

                l2.addView(tv);
            }
            l1.addView(l2);
        }


    }




    public void hasClick(int x, int y) {
        if (hasSound) mp.start();
        if (hasVibration) vibratorService.vibrate(100);
        changeView(x, y);
        if (x == 0 && y == 0) {//esquina superior izquierda
            changeView(x + 1, y);
            changeView(x + 1, y + 1);
            changeView(x, y + 1);
        } else if (x == topTileX - 1 && y == 0) {//esquina superior derecha
            changeView(x - 1, y);
            changeView(x - 1, y + 1);
            changeView(x, y + 1);
        } else if (x == topTileX - 1 && y == topTileY - 1) {//esquina inferior derecha
            changeView(x - 1, y);
            changeView(x - 1, y - 1);
            changeView(x, y - 1);
        } else if (x == 0 && y == topTileY - 1) {//esquina inferior izquierda
            changeView(x + 1, y);
            changeView(x + 1, y - 1);
            changeView(x, y - 1);
        } else if (x == 0) {//lateral izquierdo
            changeView(x + 1, y);
            changeView(x, y + 1);
            changeView(x, y - 1);
        } else if (x == topTileX - 1) {//lateral derecho
            changeView(x - 1, y);
            changeView(x, y - 1);
            changeView(x, y + 1);
        } else if (y == 0) {//borde superior
            changeView(x - 1, y);
            changeView(x + 1, y);
            changeView(x, y + 1);
        } else if (y == topTileY - 1) {//borde inferior
            changeView(x + 1, y);
            changeView(x - 1, y);
            changeView(x, y - 1);
        } else {
            changeView(x + 1, y);
            changeView(x - 1, y);
            changeView(x, y + 1);
            changeView(x, y - 1);
        }
        contador++;
        clickTxt.setText("Pulsaciones:" + contador);

        for (int i=0;i<topTileY;i++){
            for (int j=0;j<topTileX;j++) {
                if(values[i][j] == values[0][0]){
                    igual= true;
                }
                else{
                    igual = false;
                    break;
                }
            }
            if(igual==false){
                break;
            }
        }
        if(igual==true){
            Intent intent = new Intent(this, flipMain.class);
            Toast.makeText(getApplicationContext(),"Enhorabuena lo has logrado!", Toast.LENGTH_SHORT).show();
            startActivityForResult(intent,1);
        }
    }

    private void changeView(int x, int y) {
        TitleView tt = (TitleView) findViewById(ids[x][y]);
        int newIndex = tt.getNewIndex();
        values[x][y] = newIndex;
        tt.setBackgroundResource(pictures[newIndex]);
        tt.invalidate();
    }





}

