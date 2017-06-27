package pinaki.xyz.imagesearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by pinaki on 6/27/17.
 */

public class ThumbnailRecyclerViewAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
    private List<WikiImage> imageList;
    private Context context;
    private int thumbnailSizePx;
    public ThumbnailRecyclerViewAdapter(Context context, List<WikiImage> itemList, int size) {
        this.context = context;
        this.imageList = itemList;
        this.thumbnailSizePx = size;
    }
    @Override
    public ThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, null);
        return new ThumbnailViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(ThumbnailViewHolder holder, int position) {
        holder.title.setText(imageList.get(position).title);
        Picasso.with(context).load(imageList.get(position).url).resize(thumbnailSizePx, thumbnailSizePx).centerCrop().into(holder
                .thumbnail);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
