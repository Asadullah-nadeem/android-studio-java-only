package com.livescorescrickets.livescores.utilities;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RequestHandler {
    private static Context mCtx;
    private static RequestHandler mInstance;
    private RequestQueue mRequestQueue = getRequestQueue();

    private RequestHandler(Context context) {
        mCtx = context;
    }

    public static synchronized RequestHandler getInstance(Context context) {
        RequestHandler requestHandler;
        synchronized (RequestHandler.class) {
            if (mInstance == null) {
                mCtx=context;
                mInstance = new RequestHandler(context);
            }
            requestHandler = mInstance;
        }
        return requestHandler;
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(mCtx);
        }
        return this.mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
