package fetl.kachintorn.siripong.fetlqrcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Siripong.K on 18/05/2017.
 */

public class MyAdapter extends BaseAdapter {

    private Context context;
    private String[] iconSting, titleString, detailString;
    private ImageView imageView;
    private TextView titleTextView, detailTextView;
    private String detailShort;

    public MyAdapter(Context context,
                     String[] iconSting,
                     String[] titleString,
                     String[] detailString) {
        this.context = context;
        this.iconSting = iconSting;
        this.titleString = titleString;
        this.detailString = detailString;
    }

    @Override
    public int getCount() {
        return titleString.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.listview_layout, parent, false);

        //Initial View

        imageView = (ImageView) view.findViewById(R.id.imvIcon);
        detailTextView = (TextView) view.findViewById(R.id.txtDetail);
        titleTextView = (TextView) view.findViewById(R.id.txtTitle);

        //Show Text
        titleTextView.setText(titleString[position]);

        if (detailString[position].length() > 30) {
            detailShort = detailString[position].substring(0, 30) + "...";
        } else {
            detailShort = detailString[position];
        }
        detailTextView.setText(detailShort);

        // Show Image

        Picasso.with(context).load(iconSting[position]).into(imageView);

        return view;

    }
}// Main Class
