package pinaki.xyz.imagesearch;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import pinaki.xyz.imagesearch.data.DataManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private HandlerThread  queryThread;
    Handler workerHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onResume() {
        super.onResume();
        queryThread = new HandlerThread(TAG);
        queryThread.start();
        Handler workerHandler = new Handler(queryThread.getLooper());
        workerHandler.post(new Runnable() {
            @Override
            public void run() {
                List<String> l = DataManager.getInstance().queryThumbNails("Cat");
                Log.i(TAG, l.get(0));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        queryThread.quit();
    }

}
