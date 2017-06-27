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
                // TODO: query should be parsed from msg
                // Whethers its thumbnail or full res should be parsed from WHAT
                List<String> l = DataManager.getInstance().queryThumbNails("Cat");
                Log.i(TAG, l.get(0));
                // POST THE RESULTS BACK TO mResponseHandler
                if (mResponseHandler != null) {

                }
//                msg.recycle();
                return true;
            }
        });
    }

    /* package */ void queueQuery(String query) {
        apiQueryHandler.obtainMessage(1, query).sendToTarget();
    }
}
