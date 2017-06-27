package pinaki.xyz.imagesearch;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.List;

import pinaki.xyz.imagesearch.data.DataManager;

/**
 * Created by pinaki on 6/27/17.
 */

/* package */ final class ApiQueryThread extends HandlerThread {
    private static final String TAG = ApiQueryThread.class.getSimpleName();
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
                List<WikiImage> wikiImages = null;
                switch (msg.what) {
                    case MainActivity.THUMBNAIL_DOWNLOAD:
                        String query = (String) msg.obj;
                        wikiImages = DataManager.getInstance().queryThumbNails(query);
                        break;
                    default:
                        break;
                }
                // POST THE RESULTS BACK TO mResponseHandler
                if (mResponseHandler != null && wikiImages != null) {
                    mResponseHandler.obtainMessage(msg.what, wikiImages).sendToTarget();
                }
//                msg.recycle();
                return true;
            }
        });
    }

    /* package */ void queueQuery(String query) {
        apiQueryHandler.obtainMessage(MainActivity.THUMBNAIL_DOWNLOAD, query).sendToTarget();
    }
}
