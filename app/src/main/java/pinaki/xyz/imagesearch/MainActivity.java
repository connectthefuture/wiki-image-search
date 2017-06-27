package pinaki.xyz.imagesearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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
        handleSearchIntent(getIntent());
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
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleSearchIntent(intent);
    }

    private void handleSearchIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            queryThread.queueQuery(query);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void startWorkerThread() {

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

    private static List<WikiImage> getTempList() {
        List<WikiImage> imageList = new ArrayList<>();
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/30/Low_Temperature_Oxidation_Catalyst" +
                ".jpeg/96px-Low_Temperature_Oxidation_Catalyst.jpeg";
        String title = "Title 1";
        imageList.add(new WikiImage(url, title, 1, 1));
        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/07/Oscilloscopic_tube.jpg/96px-Oscilloscopic_tube.jpg";
        title = "Title 2";
        imageList.add(new WikiImage(url, title, 1, 1));
        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Flag_of_Catalonia.svg/96px-Flag_of_Catalonia.svg.png";
        title = "Title 3";
        imageList.add(new WikiImage(url, title, 1, 1));
        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d6/Catherine_aragon.jpg/73px-Catherine_aragon.jpg";
        title = "Title 4";
        imageList.add(new WikiImage(url, title, 1, 1));
        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Kette_Kettenkurve_Catenary_2008_PD.JPG/96px-Kette_Kettenkurve_Catenary_2008_PD.JPG";
        title = "Title 5";
        imageList.add(new WikiImage(url, title, 1, 1));
        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Catherine-de-medici.jpg/71px-Catherine-de-medici.jpg";
        title = "Title 6";
        imageList.add(new WikiImage(url, title, 1, 1));
        url = "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/Catania-Etna-Sicilia-Italy-Castielli_CC0_HQ1.JPG/96px-Catania-Etna-Sicilia-Italy-Castielli_CC0_HQ1.JPG";
        title = "Title 7";
        imageList.add(new WikiImage(url, title, 1, 1));
        return imageList;
    }

}
