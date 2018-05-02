package geogram.example.sportnews.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.sportnews.R;
import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;

/**
 * Created by geogr on 14.04.2018.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsItem> list = new ArrayList<>();
    private int mRowIndex = -1;
    private Context context;

    public HorizontalAdapter(Context context) {
        this.context = context;
    }

    public void addItem(NewsItem item) {
        if(item!=null)
        list.add(item);
        notifyDataSetChanged();
    }

    public void addAll(List<NewsItem> data) {
        if(data!=null)
        list.addAll(data);
        notifyDataSetChanged();
    }


    public void setRowIndex(int index) {
        mRowIndex = index;
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.horizontalImage)
        ImageView imageView;
        @BindView(R.id.horizontalText)
        TextView text;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.horizontal_recycler_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder rawHolder, int position) {
        ItemViewHolder holder = (ItemViewHolder) rawHolder;
        holder.text.setText(list.get(position).getTitle());
        Picasso.with(context)
                .load(list.get(position).getPicture())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}

