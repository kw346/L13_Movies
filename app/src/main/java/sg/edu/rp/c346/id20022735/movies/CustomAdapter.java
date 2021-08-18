package sg.edu.rp.c346.id20022735.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movies> verlist;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> objects){
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        verlist = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvtitle = rowView.findViewById(R.id.movietitle);
        TextView tvrate = rowView.findViewById(R.id.movierate);
        TextView tvdesc = rowView.findViewById(R.id.moviedesc);
        ImageView iv = rowView.findViewById(R.id.sticker);

        // Obtain the Android Version information based on the position
        Movies currentVersion = verlist.get(position);

        // Set values to the TextView to display the corresponding information
        tvtitle.setText(currentVersion.getName());
        tvrate.setText(currentVersion.toString());
        tvdesc.setText(currentVersion.getDescription());

        if (currentVersion.getStars() == 5){
            iv.setImageResource(R.drawable.watch);
        }
        else{
            iv.setVisibility(View.INVISIBLE);
        }
        return rowView;
    }
}
