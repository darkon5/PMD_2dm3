package moe.gimme.eljueguitodivertido;

import android.content.Context;
import android.widget.Button;



public class TitleView extends Button{

    public int x = 0;
    public int y = 0;

    private int index = 0;

    private int topElements;

    public TitleView (Context context, int x,int y, int topElements, int index, int background){
        super(context);
        this.x=x;
        this.y=y;
        this.topElements=topElements;
        this.index=index;
        this.setBackgroundResource(background);
    }
    public int getNewIndex(){
        index ++;
        if(index == topElements) index=0;
        return index;
    }


}
