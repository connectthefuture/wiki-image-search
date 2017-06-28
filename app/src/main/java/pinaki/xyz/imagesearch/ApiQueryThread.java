package pinaki.xyz.imagesearch;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.util.List;

import pinaki.xyz.imagesearch.data.DataManager;

/**
 * Created by pinaki on 6/27/17.
 */

/* package */ final class ApiQueryThread extends HandlerThread {
    private static final String TAG = ApiQueryThread.class.getSimpleName();
    private static final int PAUSE_BEFORE_QUERY_MS = 500;
    private static final int MIN_LENGTH_FOR_QUERY = 2;
    private Handler mResponseHandler; // typically the ui handler where the messages will be posted back.
    private Handler apiQueryHandler;
    /* package */ ApiQueryThread(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }
    /* package */ void prepareHandler() {
        apiQueryHandler = new Handler(getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                List<WikiData> wikiDataList = null;
                switch (msg.what) {
                    case MainActivity.THUMBNAIL_DOWNLOAD:
                        String query = (String) msg.obj;
                        if (query != null && query.length() >= MIN_LENGTH_FOR_QUERY) {
                            Log.i("MainActivity", "querying: " + query);
                            wikiDataList = DataManager.getInstance().queryThumbNails(query);
                        }
                        break;
                    default:
                        break;
                }
                // POST THE RESULTS BACK TO mResponseHandler
                if (mResponseHandler != null && wikiDataList != null) {
                    mResponseHandler.obtainMessage(msg.what, wikiDataList).sendToTarget();
                }
//                msg.recycle();
                return true;
            }
        });
    }

    /* package */ void queueQuery(String query) {
        Message msg = apiQueryHandler.obtainMessage(MainActivity.THUMBNAIL_DOWNLOAD, query);
        apiQueryHandler.removeCallbacksAndMessages(null);
        apiQueryHandler.sendMessageDelayed(msg, PAUSE_BEFORE_QUERY_MS);
    }
}
