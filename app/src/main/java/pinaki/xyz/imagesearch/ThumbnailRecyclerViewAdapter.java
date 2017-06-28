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
    private List<WikiData> imageList;
    private Context context;
    private int thumbnailSizePx;
    private ThumbnailClickListener thumbnailClickListener;
    public ThumbnailRecyclerViewAdapter(Context context, List<WikiData> itemList, int size, ThumbnailClickListener thumbnailClickListener) {
        this.context = context;
        this.imageList = itemList;
        this.thumbnailSizePx = size;
        this.thumbnailClickListener = thumbnailClickListener;
    }

    public void update(List<WikiData> itemList) {
        this.imageList = itemList;

    }
    @Override
    public ThumbnailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, null);
        return new ThumbnailViewHolder(layoutView, thumbnailClickListener);
    }

    @Override
    public void onBindViewHolder(ThumbnailViewHolder holder, int position) {
        holder.title.setText(imageList.get(position).getTitle());
        Picasso.with(context).load(imageList.get(position).getThumbnail().url).resize(thumbnailSizePx, thumbnailSizePx)
                .centerCrop()
                .placeholder(R.drawable.placeholder).into(holder.thumbnail);
        holder.wikiData = imageList.get(position);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
