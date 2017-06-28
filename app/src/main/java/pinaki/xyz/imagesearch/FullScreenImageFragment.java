package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by pinaki on 6/27/17.
 */

public class FullScreenImageFragment extends Fragment {
    private static final String TAG = FullScreenImageFragment.class.getSimpleName();
    String url;
    int imageWidthPx = 800;
    int imageHeightPx = 1200;
    public FullScreenImageFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.full_screen_image, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        View closeIcon = view.findViewById(R.id.full_screen_close_icon);
        // add an onclick handler
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send a callback to the activity that close icon has been tapped.
                Log.i(TAG, "onclick close button");
            }
        });
        ImageView fullScreenImage = (ImageView)view.findViewById(R.id.full_screen_image);
        Picasso.with(getContext()).load(url).resize(imageWidthPx, imageHeightPx).centerCrop()
                .placeholder(R.drawable.placeholder).into(fullScreenImage);
    }
}
