package moe.gimme.eljueguitodivertido;

import android.content.Context;
import android.widget.Button;

/**
 * Created by ik_2dm3 on 29/09/2016.
 */

public class TileView extends Button {
    public int x=0,y=0;//coordenadas
    private int index=0;//tramas
    private int topElements=0;//maxtramas

    public TileView (Context context, int x, int y, int topElements, int index, int background){
        super(context);
        this.x=x;
        this.y=y;
        this.topElements=topElements;
        this.index=index;
        this.setBackgroundResource(background);
    }

}
