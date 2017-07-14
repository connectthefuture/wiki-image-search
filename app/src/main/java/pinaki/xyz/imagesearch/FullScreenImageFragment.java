package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by pinaki on 6/27/17.
 */

public class FullScreenImageFragment extends Fragment {
    static final String TAG = FullScreenImageFragment.class.getSimpleName();
    private String url;
    private FragmentCloseListener fragmentCloseListener;
    int imageWidthPx = 800;
    int imageHeightPx = 1200;
    @BindView(R.id.full_screen_close_icon)  View closeIcon;
    private Unbinder unbinder;
    public FullScreenImageFragment() {
        // empty constructor
    }

    void setProperties(String url, FragmentCloseListener fragmentCloseListener) {
        this.url = url;
        this.fragmentCloseListener = fragmentCloseListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.full_screen_image, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // add an onclick handler
        closeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // send a callback to the activity that close icon has been tapped.
                if (fragmentCloseListener!= null) {
                    fragmentCloseListener.onFragmentCloseClick(FullScreenImageFragment.this);
                }
            }
        });
        ImageView fullScreenImage = (ImageView)view.findViewById(R.id.full_screen_image);
        Picasso.with(getContext()).load(url).resize(imageWidthPx, imageHeightPx).centerCrop()
                .placeholder(R.drawable.placeholder).into(fullScreenImage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
