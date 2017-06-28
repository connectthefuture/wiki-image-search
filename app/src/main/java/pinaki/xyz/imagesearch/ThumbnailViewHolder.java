package pinaki.xyz.imagesearch;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pinaki on 6/27/17.
 */

public class ThumbnailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    // TODO: add butterknife here ?
    public TextView title;
    public ImageView thumbnail;
    public WikiImage wikiImage;
    private ThumbnailClickListener thumbnailClickListener;

    public ThumbnailViewHolder(View itemView, ThumbnailClickListener thumbnailClickListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        title = (TextView) itemView.findViewById(R.id.thumbnail_title);
        thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail_image);
        this.thumbnailClickListener = thumbnailClickListener;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(v.getContext(), "Clicked Position = " + getPosition(), Toast.LENGTH_SHORT).show();
        this.thumbnailClickListener.onThumbnailClick(wikiImage);
    }
}
