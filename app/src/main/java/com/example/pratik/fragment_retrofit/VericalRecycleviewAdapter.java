package com.example.pratik.fragment_retrofit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pratik.fragment_retrofit.Model.ListOfShow;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 24-12-2016.
 */

public class VericalRecycleviewAdapter extends RecyclerView.Adapter<VericalRecycleviewAdapter.ViewHolder> {

    private Context mContext;
    private List<ListOfShow> listOfShows;

    private RecycleViewItemClickListener recycleViewItemClickListener = null;


    public VericalRecycleviewAdapter(Context mContext, List<ListOfShow> noteList) {
        this.mContext = mContext;
        this.listOfShows = noteList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tv_writer.setText(listOfShows.get(position).getShowTime());
        holder.tv_showtime.setText(listOfShows.get(position).getShowDetails().getWriter());
//        holder.tv_channel.setText(worldpopulationList.get(position).getPopulation());
//        Picasso.with(mContext)
//                .load(worldpopulationList.get(position).getFlag())
//                .resize(50, 50)
//                .centerCrop()
//                .into(holder.img_flag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recycleViewItemClickListener != null)
                    recycleViewItemClickListener.onClickItem(position, listOfShows.get(position));
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s1 = worldpopulationList.get(position).getCountry();
//                Toast.makeText(mContext, ""+s1, Toast.LENGTH_SHORT).show();
//
//
//                Bundle bundle = new Bundle();
//
//                            bundle.putString("s1",s1);
//
//
//                            FragmentManager fragmentManager = getFragmentManager();
//                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                            BlankFragment blankFragment = new BlankFragment();
//                            blankFragment.setArguments(bundle);
//                            getSupportFragmentManager().beginTransaction()
//                                    .add(R.id.frg_1, blankFragment)
//                                    .commit();
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();
//
//            }
//        });

//        holder.btnClickHere.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(mContext,"Position ---> "+position, Toast.LENGTH_SHORT).show();
//
//                onRecycleViewItemClick.onClickButton(position, 1);
//            }
//        });
//
//        holder.tv_rank.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onRecycleViewItemClick.onClickTextView(position);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return listOfShows.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_writer,tv_showtime,tv_channel;
        ImageView img_flag;

        ViewHolder(View view) {
            super(view);

            tv_writer = (TextView) view.findViewById(R.id.tv_writer);
            tv_showtime = (TextView) view.findViewById(R.id.tv_showtime);
            tv_channel = (TextView) view.findViewById(R.id.tv_channel);
//            img_flag = (ImageView) view.findViewById(R.id.img_flag);
        }
    }

    public RecycleViewItemClickListener getRecycleViewItemClickListener() {
        return recycleViewItemClickListener;
    }

    public void setRecycleViewItemClickListener(RecycleViewItemClickListener recycleViewItemClickListener) {
        this.recycleViewItemClickListener = recycleViewItemClickListener;
    }


}