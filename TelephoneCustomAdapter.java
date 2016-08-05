package me.kaelaela.sample.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

import me.kaelaela.sample.Model.TelephoneModel;
import me.kaelaela.sample.R;

/**
 * Created by genie7 on 6/6/16.
 */
public class TelephoneCustomAdapter extends BaseAdapter
{

    private Context context;
    private ArrayList<TelephoneModel> mlist;
    private static LayoutInflater inflater;
    private final ArrayList<TelephoneModel> SocialContactsList;
    public TelephoneCustomAdapter(Context context, ArrayList<TelephoneModel> rlist) {

        this.context = context;
        this.mlist = rlist;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.SocialContactsList = new ArrayList<TelephoneModel>();
        this.SocialContactsList.addAll(mlist);

    }


    @Override
    public int getCount()
    {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public class ViewHolder {

        public TextView naME;
        public TextView ctName;

        public ImageView imageProfile;


    }


    @Override
    public View getView( int  position, View view, ViewGroup parent)

    {



//LayoutInflater inflater = Context.getLayoutInflater();

        ViewHolder holder;

        View rowView = view;
        if (rowView == null)

        {

            rowView = inflater.inflate(R.layout.rowtelephonelayout, parent, false);

            holder = new ViewHolder();
            holder.imageProfile = (ImageView) rowView.findViewById(R.id.imageViewProfile);
            holder.naME = (TextView) rowView.findViewById(R.id.name);
            holder.ctName = (TextView) rowView.findViewById(R.id.cityname);


            rowView.setTag(holder);


        }
        else
        {


            holder = (ViewHolder) rowView.getTag();

        }
        try
        {
            holder.naME.setText(mlist.get(position).getName());
            holder.ctName.setText(mlist.get(position).getCity());
            Picasso.with(context).load(mlist.get(position).getImage()).placeholder(R.drawable.jainsamachar).into(holder.imageProfile);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        return rowView;
    }

    public void filter(String charText)
    {
        charText = charText.toLowerCase(Locale.getDefault());

        mlist.clear();
        if (charText.length() == 0) {
            mlist.addAll(SocialContactsList);
        } else {
            for (TelephoneModel wp : SocialContactsList) {
                if ((wp.getName().toString().toLowerCase(Locale.getDefault()).contains(charText))||(wp.getCity().toString().toLowerCase(Locale.getDefault()).contains(charText))) {
                    mlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}

