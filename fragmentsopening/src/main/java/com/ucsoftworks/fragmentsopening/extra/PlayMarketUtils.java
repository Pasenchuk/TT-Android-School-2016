package com.ucsoftworks.fragmentsopening.extra;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Pasenchuk Victor on 26.11.15
 */
public class PlayMarketUtils {

    public static void openPlayMarket(Activity activity, String appPackageName) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException ex) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void openPlayMarketDevPage(Activity activity, String devId) {

        final Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("market://dev?id=" + devId));

        if (intent.resolveActivity(activity.getPackageManager()) != null)
            activity.startActivity(intent);
        else
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/dev?id=" + devId)));
    }
}
