package geogram.example.sportnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import geogram.example.sportnews.R;
import geogram.example.sportnews.rest.models.FootballRespose.NewsItem;
import geogram.example.sportnews.ui.activityes.SingleArticleActivity;

/**
 * Created by geogr on 14.04.2018.
 */

public class NewsAdapter extends RecyclerView.Adapter {
    private List<NewsItem> items = new ArrayList<>();
    private Context context;
    private List<NewsItem> horizontalList = new ArrayList<>();

    public NewsAdapter(Context context, onListClickedRowListner listner) {
        this.listner = listner;
        this.context = context;
    }

    onListClickedRowListner listner;

    public interface onListClickedRowListner {
        void onListSelected(NewsItem item);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;
        View itemLayoutView;

        switch (viewType) {
            case 0:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.first_type_layout, parent, false);
                vh = new ViewHolderFirst(itemLayoutView);

                break;
            case 1:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.second_type_layout, parent, false);
                vh = new ViewHolderSecond(itemLayoutView);
                break;
            case 2:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.third_type_layout, parent, false);
                vh = new ViewHolderThird(itemLayoutView);
                break;
            case 3:
                itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fourth_type_layout, parent, false);
                vh = new ViewHolderFourth(itemLayoutView);
                break;
        }

        return vh;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (this.getItemViewType(position)) {

            case 0:
                ViewHolderFirst first = (ViewHolderFirst) holder;
                Picasso.with(context)
                        .load(items.get(position).getPicture())
                        .into(first.imageView);
                first.title.setText(items.get(position).getTitle());
                first.textView.setText(items.get(position).getShortText());

                break;
            case 1:
                ViewHolderSecond second = (ViewHolderSecond) holder;
                Picasso.with(context)
                        .load(items.get(position).getPicture())
                        .into(second.imageView);
                second.title.setText(items.get(position).getTitle());
                break;
            case 2:
                ViewHolderThird third = (ViewHolderThird) holder;
                Picasso.with(context)
                        .load(items.get(position).getPicture())
                        .into(third.imageView);
                third.title.setText(items.get(position).getTitle());
                break;
            case 3:
                horizontalList.add(items.get(position));
                if (horizontalList.size() >= 2) {
                    final ViewHolderFourth fourth = (ViewHolderFourth) holder;
                    fourth.adapter.clear();
                    fourth.adapter.addAll(horizontalList);
                    horizontalList.clear();

                    final GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                        @Override
                        public void onLongPress(MotionEvent e) {

                        }

                        @Override
                        public boolean onSingleTapConfirmed(MotionEvent e) {
                            NewsItem item;
                             if(fourth.recyclerView.getChildLayoutPosition(fourth.recyclerView.findChildViewUnder(e.getX(), e.getY()))==1) {
                                 item =items.get(position);
                             }else {
                                 int pos = position-1;
                                 item =items.get(pos);
                             }

                            listner.onListSelected(item);
                            return super.onSingleTapConfirmed(e);
                        }
                    });

                    fourth.recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return gestureDetector.onTouchEvent(motionEvent);
                        }
                    });
                }


            break;

        }

    }


    @Override
    public int getItemViewType(int position) {
        int decision;
        if (position > 17 || position == 17) {
            int a = position / 17;
            int b = 17 * a;
            int c = position - b;
            decision = c / 3;
        } else {
            decision = position / 3;
        }
        switch (decision) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 0;
            case 3:
                return 2;
            case 4:
                return 0;
            case 5:
                return 3;
            default:
                return 1;
        }
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<NewsItem> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolderFirst extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewItemFirst)
        ImageView imageView;
        @BindView(R.id.titleItemFirst)
        TextView title;
        @BindView(R.id.textItemFirst)
        TextView textView;

        public ViewHolderFirst(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolderSecond extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewItemSecond)
        ImageView imageView;
        @BindView(R.id.titleItemSecond)
        TextView title;

        public ViewHolderSecond(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHolderThird extends RecyclerView.ViewHolder {
        @BindView(R.id.imageViewItemThird)
        ImageView imageView;
        @BindView(R.id.titleItemThird)
        TextView title;

        public ViewHolderThird(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

        public class ViewHolderFourth extends RecyclerView.ViewHolder {
        @BindView(R.id.horizontal_list)
        RecyclerView recyclerView;
        HorizontalAdapter adapter;

        public ViewHolderFourth(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            adapter = new HorizontalAdapter(context);
            recyclerView.setAdapter(adapter);


        }
    }
//    public class ViewHolderFive extends RecyclerView.ViewHolder {
//        @BindView(R.id.firstHorizontal)
//        LinearLayout linearLayout;
//        @BindView(R.id.firstHorizontalImage)
//        ImageView imageFirst;
//        @BindView(R.id.firstHorizontalText)
//        TextView textFirst;
//        @BindView(R.id.secondHorizontal)
//        LinearLayout linearLayoutSecond;
//        @BindView(R.id.secondHorizontalImage)
//        ImageView imageSecond;
//        @BindView(R.id.secondHorizontalText)
//        TextView textSecond;
//
//        public ViewHolderFive(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }

}
