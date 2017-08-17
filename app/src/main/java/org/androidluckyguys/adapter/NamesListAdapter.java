package org.androidluckyguys.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidluckyguys.R;
import org.androidluckyguys.model.NameModel;

import java.util.ArrayList;

/**
 * Created by lucky on 16/08/2017.
 */

public class NamesListAdapter extends RecyclerView.Adapter<NamesListAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<NameModel> dataList;




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTv, emailTv,phoneTv,countTv;

        public MyViewHolder(View view) {
            super(view);
            countTv = (TextView) view.findViewById(R.id.countTv);
            nameTv = (TextView) view.findViewById(R.id.nameTv);
            emailTv = (TextView) view.findViewById(R.id.emailTv);
            phoneTv = (TextView) view.findViewById(R.id.phoneTv);

        }
    }


    public NamesListAdapter(Context mContext, ArrayList<NameModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public NamesListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_user_data_adapter, parent, false);

        return new NamesListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NamesListAdapter.MyViewHolder holder, int position) {
        NameModel model = dataList.get(position);
        
        holder.countTv.setText(position+"");
        holder.nameTv.setText(model.getName());
        holder.emailTv.setText(model.getEmail());
        holder.phoneTv.setText(model.getPhone());


    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
