package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private ApiQueryThread  queryThread;
    Handler workerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
//        queryThread = new HandlerThread(TAG);
//        queryThread.start();
//        Handler workerHandler = new Handler(queryThread.getLooper());
//        workerHandler.post(new Runnable() {
//            @Override
//            public void run() {
//                List<String> l = DataManager.getInstance().queryThumbNails("Cat");
//                Log.i(TAG, l.get(0));
//            }
//        });
        queryThread = new ApiQueryThread(createUIHandler());
        queryThread.start();
        queryThread.prepareHandler();
        queryThread.queueQuery("Cat"); // example query

//        workerHandler.sendMessage();
//        Handler h = new Handler();
    }

    private void startWorkerThread() {

    }

    private Handler createUIHandler() {
        Handler uiHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                // GET THE URLs and do stuff here
//                msg.a
                switch (msg.what) {
                    // if SEARCH URL DOWNLOAD COMPLETED RENDER THEM
                    // if HIGH RES DNLD COMPLETED RENDER THEm
                }
                return false;
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
