package pinaki.xyz.imagesearch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pinaki on 6/27/17.
 */

public class ThumbnailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // TODO: add butterknife here ?
    public @BindView(R.id.thumbnail_title) TextView title;
    public @BindView(R.id.thumbnail_image) ImageView thumbnail;
    public WikiData wikiData;
    private ThumbnailClickListener thumbnailClickListener;

    public ThumbnailViewHolder(View itemView, ThumbnailClickListener thumbnailClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
        this.thumbnailClickListener = thumbnailClickListener;
    }

    @Override
    public void onClick(View v) {
        this.thumbnailClickListener.onThumbnailClick(wikiData);
    }
}
