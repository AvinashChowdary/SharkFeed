package com.example.avinashravilla.sharkfeed.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.avinashravilla.sharkfeed.R;
import com.example.avinashravilla.sharkfeed.activities.LightBoxActivity;
import com.example.avinashravilla.sharkfeed.helpers.Constants;
import com.example.avinashravilla.sharkfeed.model.search.Photo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharkFeedAdapter extends RecyclerView.Adapter<SharkFeedAdapter.SharkViewHolder> {

    private final LayoutInflater mInflater;
    private final Context context;
    private List<Photo> sharks;

    public SharkFeedAdapter(Context context, List<Photo> sharks) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.sharks = new ArrayList<>(sharks);
    }

    @NonNull
    @Override
    public SharkViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = mInflater.inflate(R.layout.item_list, viewGroup, false);
        return new SharkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SharkViewHolder viewHolder, final int position) {
        // loading best available image quality
        Glide.with(context)
                .load(sharks.get(position).getUrl_o())
                .error(Glide.with(context)
                        .load(sharks.get(position).getUrl_l())
                        .error(Glide.with(context)
                                .load(sharks.get(position).getUrl_c())))
                .thumbnail(Glide.with(context)
                    .load(sharks.get(position).getUrl_t()))
                .into(viewHolder.imgShark);
        viewHolder.imgShark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LightBoxActivity.class);
                intent.putExtra(Constants.SHARK, sharks.get(position));
                View viewStart = viewHolder.imgShark;

                // animation for loading to and from image
                String transitionName = context.getString(R.string.transition_string);
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,
                                viewStart,
                                transitionName
                        );

                ActivityCompat.startActivity(context, intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return sharks.size();
    }

    /**
     * adding new items to existing list
     * @param newSharks
     */
    public void addAllItems(List<Photo> newSharks) {
        this.sharks.addAll(new ArrayList<>(newSharks));
        notifyDataSetChanged();
    }

    /**
     * adding items to display
     * @param newSharks
     */
    public void setItems(List<Photo> newSharks) {
        this.sharks = new ArrayList<>(newSharks);
        notifyDataSetChanged();
    }

    public static class SharkViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_shark)
        ImageView imgShark;

        public SharkViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
