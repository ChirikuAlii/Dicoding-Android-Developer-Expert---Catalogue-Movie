package com.ysn.cataloguemovie.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by yudisetiawan on 10/29/17.
 */

public class StackWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(
                this.getApplicationContext(),
                intent
        );
    }
}
