package what.is.jsonpractice.sync;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class IntentReceiver extends ResultReceiver {
    private Receiver mReceiver;

    public IntentReceiver(Handler handler) {
        super(handler);
    }


    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }


    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}