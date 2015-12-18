package com.burnweb.rnwebview;


import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;

/* package */ class NavigationStateChangeEvent extends Event<NavigationStateChangeEvent> {

    public static final String EVENT_NAME = "navigationStateChange";

    private final boolean mIsLoading;
    private final String mUrl;
    private final boolean mCanGoBack;
    private final boolean mCanGoForward;
    private final String mTitle;

    protected NavigationStateChangeEvent(int viewTag, long timestampMs, boolean isLoading, String url, boolean canGoBack, boolean canGoForward, String title) {
        super(viewTag, timestampMs);

        mIsLoading = isLoading;
        mUrl = url;
        mCanGoBack = canGoBack;
        mCanGoForward = canGoForward;
        mTitle = title;
    }

    @Override
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override
    public void dispatch(RCTEventEmitter rctEventEmitter) {
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
    }

    private WritableMap serializeEventData() {
        WritableMap eventData = Arguments.createMap();
        eventData.putBoolean("loading", mIsLoading);
        eventData.putBoolean("canGoBack", mCanGoBack);
        eventData.putBoolean("canGoForward", mCanGoForward);
        eventData.putString("url", mUrl);
        eventData.putString("title", mTitle);
        return eventData;
    }

}
