package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    /* package */ static final int THUMBNAIL_DOWNLOAD = 1;
    private ApiQueryThread  queryThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        initLayout();
    }

    // create the staggeredview and the layout manager.
    private void initLayout() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        List<WikiImage> tempList = getTempList();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int thumbnailWidth = metrics.widthPixels / 4;

        ThumbnailRecyclerViewAdapter thumbnailRecyclerViewAdapter = new ThumbnailRecyclerViewAdapter(
                MainActivity.this, tempList, thumbnailWidth);
        recyclerView.setAdapter(thumbnailRecyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        queryThread = new ApiQueryThread(createUIHandler());
        queryThread.start();
        queryThread.prepareHandler();
        queryThread.queueQuery("India"); // example query TODO: get stuff from UI input.
    }

    private void startWorkerThread() {

    }

    private Handler createUIHandler() {
        Handler uiHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // GET THE URLs and do stuff here
                switch (msg.what) {
                    // if SEARCH URL DOWNLOAD COMPLETED RENDER THEM
                    case THUMBNAIL_DOWNLOAD:
                        String url = (String) msg.obj;
                        Log.i(TAG, url);
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
    public void onPause() {
        super.onPause();
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
