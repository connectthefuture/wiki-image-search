package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    /* package */ static final int THUMBNAIL_DOWNLOAD = 1;
    private ApiQueryThread  queryThread;
    private ThumbnailRecyclerViewAdapter thumbnailRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queryThread = new ApiQueryThread(createUIHandler());
        queryThread.start();
        queryThread.prepareHandler();
        setContentView(R.layout.search_results);
        initLayout();
    }

    // create the staggeredview and the layout manager.
    private void initLayout() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<WikiImage> tempList = new ArrayList<>();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int thumbnailWidth = metrics.widthPixels / 4;

        thumbnailRecyclerViewAdapter = new ThumbnailRecyclerViewAdapter(MainActivity.this, tempList, thumbnailWidth);
        recyclerView.setAdapter(thumbnailRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "submit: " + query);
                queryThread.queueQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "change: " + newText);
                queryThread.queueQuery(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private Handler createUIHandler() {
        Handler uiHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // GET THE URLs and do stuff here
                switch (msg.what) {
                    case THUMBNAIL_DOWNLOAD:
                        List<WikiImage> images = (List<WikiImage>) msg.obj;
                        Log.i(TAG, "Num Images: " + images.size());
                        thumbnailRecyclerViewAdapter.update(images);
                        thumbnailRecyclerViewAdapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                    // if HIGH RES DNLD COMPLETED RENDER THEM
                }
                return true;
            }
        });
        return uiHandler;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        queryThread.quit();
    }

}
