package com.change.demox.themecolor.screen;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.change.demox.R;

/**
 * @decs: Colorful
 * @author: 郑少鹏
 * @date: 2019/7/17 12:01
 */
public class Colorful {
    private static final String PREFERENCE_KEY = "COLORFUL_PREFERENCE_KEY";
    private static ThemeDelegate delegate;
    private static ThemeColor primaryColor = Defaults.primaryColor;
    private static ThemeColor accentColor = Defaults.accentColor;
    private static boolean isTranslucent = Defaults.trans;
    private static boolean isDark = Defaults.darkTheme;
    private static String themeString;

    private Colorful() {
        // prevent initialization
    }

    public static void init(Context context) {
        themeString = PreferenceManager.getDefaultSharedPreferences(context).getString(PREFERENCE_KEY, null);
        if (themeString == null) {
            primaryColor = Defaults.primaryColor;
            accentColor = Defaults.accentColor;
            isTranslucent = Defaults.trans;
            isDark = Defaults.darkTheme;
            themeString = generateThemeString();
        } else {
            initValues();
        }
        delegate = new ThemeDelegate(context, primaryColor, accentColor, isTranslucent, isDark);
    }

    public static void applyTheme(@NonNull Activity activity) {
        applyTheme(activity, true);
    }

    public static void applyTheme(@NonNull Activity activity, boolean overrideBase) {
        if (overrideBase) {
            activity.setTheme(R.style.AppTheme);
        }
        activity.getTheme().applyStyle(getThemeDelegate().getStyleResPrimary(), true);
        activity.getTheme().applyStyle(getThemeDelegate().getStyleResAccent(), true);
    }

    private static void writeValues(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(PREFERENCE_KEY, generateThemeString()).apply();
    }

    private static void initValues() {
        String[] colors = themeString.split(":");
        isDark = Boolean.parseBoolean(colors[0]);
        isTranslucent = Boolean.parseBoolean(colors[1]);
        primaryColor = Colorful.ThemeColor.values()[Integer.parseInt(colors[2])];
        accentColor = Colorful.ThemeColor.values()[Integer.parseInt(colors[3])];
    }

    private static String generateThemeString() {
        return isDark + ":" + isTranslucent + ":" + primaryColor.ordinal() + ":" + accentColor.ordinal();
    }

    static ThemeDelegate getThemeDelegate() {
        if (delegate == null) {
        }
        return delegate;
    }

    static String getThemeString() {
        return themeString;
    }

    public static Config config(Context context) {
        return new Config(context);
    }

    public static Defaults defaults() {
        return new Defaults();
    }

    public enum ThemeColor {
        /**
         * BLUE
         */
        BLUE(R.color.md_blue_500, R.color.md_blue_700),
        /**
         * GREEN
         */
        GREEN(R.color.md_green_500, R.color.md_green_700);
        @ColorRes
        private int colorRes;
        @ColorRes
        private int darkColorRes;

        ThemeColor(@ColorRes int colorRes, @ColorRes int darkColorRes) {
            this.colorRes = colorRes;
            this.darkColorRes = darkColorRes;
        }

        public @ColorRes
        int getColorRes() {
            return colorRes;
        }

        public @ColorRes
        int getDarkColorRes() {
            return darkColorRes;
        }
    }

    public static class Defaults {
        private static ThemeColor primaryColor = ThemeColor.BLUE;
        private static ThemeColor accentColor = ThemeColor.BLUE;
        private static boolean trans = false;
        private static boolean darkTheme = false;

        public static ThemeColor getPrimaryColor() {
            return primaryColor;
        }

        public static ThemeColor getAccentColor() {
            return accentColor;
        }

        public static boolean isDarkTheme() {
            return isDark;
        }

        public Defaults primaryColor(ThemeColor primary) {
            primaryColor = primary;
            return this;
        }

        public Defaults accentColor(ThemeColor accent) {
            accentColor = accent;
            return this;
        }

        public Defaults translucent(boolean translucent) {
            trans = translucent;
            return this;
        }

        public Defaults dark(boolean dark) {
            darkTheme = dark;
            return this;
        }
    }

    public static class Config {
        private Context context;

        private Config(Context context) {
            this.context = context;
        }

        public Config primaryColor(ThemeColor primary) {
            primaryColor = primary;
            return this;
        }

        public Config accentColor(ThemeColor accent) {
            accentColor = accent;
            return this;
        }

        public Config translucent(boolean translucent) {
            isTranslucent = translucent;
            return this;
        }

        public Config dark(boolean dark) {
            isDark = dark;
            return this;
        }

        public void apply() {
            writeValues(context);
            themeString = generateThemeString();
            delegate = new ThemeDelegate(context, primaryColor, accentColor, isTranslucent, isDark);
        }
    }
}
