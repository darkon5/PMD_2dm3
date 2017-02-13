package moe.gimme.ejemploadapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ik_2dm3 on 13/02/2017.
 */

public class ListAdapter extends ArrayAdapter<String> {

    private Activity activity;
    ArrayList<String> mensajes;

    public ListAdapter(Activity activity, ArrayList<String> mensajes) {
        super(activity, R.layout.lista);
        this.activity = activity;
        this.mensajes = mensajes;
    }

    static class ViewHolder {
        protected TextView nameTextView;

    }

    public int getCount() {
        return mensajes.size();
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        // inflamos nuestra vista con el layout
        View view = null;
        LayoutInflater inflator = activity.getLayoutInflater();
        view = inflator.inflate(R.layout.lista, null);
        final ViewHolder viewHolder = new ViewHolder();

        // *** instanciamos a los recursos
        viewHolder.nameTextView = (TextView) view
                .findViewById(R.id.nameTextView);

        // importante!!! establecemos el mensaje
        viewHolder.nameTextView.setText(mensajes.get(position));

        return view;
    }

}