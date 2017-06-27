package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    /* package */ static final int THUMBNAIL_DOWNLOAD = 1;
    private ApiQueryThread  queryThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

}
