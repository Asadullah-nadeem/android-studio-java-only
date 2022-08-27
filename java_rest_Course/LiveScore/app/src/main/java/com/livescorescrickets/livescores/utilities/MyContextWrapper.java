package com.livescorescrickets.livescores.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import java.util.Locale;

public class MyContextWrapper extends ContextWrapper {
    public MyContextWrapper(Context context) {
        super(context);
    }

    public static ContextWrapper wrap(Context context, String str) {
        Locale locale;
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= 24) {
            locale = getSystemLocale(configuration);
        } else {
            locale = getSystemLocaleLegacy(configuration);
        }
        if (!str.equals("") && !locale.getLanguage().equals(str)) {
            Locale locale2 = new Locale(str);
            Locale.setDefault(locale2);
            if (Build.VERSION.SDK_INT >= 24) {
                setSystemLocale(configuration, locale2);
            } else {
                setSystemLocaleLegacy(configuration, locale2);
            }
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }
        return new MyContextWrapper(context);
    }

    public static Locale getSystemLocaleLegacy(Configuration configuration) {
        return configuration.locale;
    }

    public static Locale getSystemLocale(Configuration configuration) {
        return configuration.getLocales().get(0);
    }

    public static void setSystemLocaleLegacy(Configuration configuration, Locale locale) {
        configuration.locale = locale;
    }

    public static void setSystemLocale(Configuration configuration, Locale locale) {
        configuration.setLocale(locale);
    }
}
