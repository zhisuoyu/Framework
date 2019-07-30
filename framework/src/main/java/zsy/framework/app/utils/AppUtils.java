package zsy.framework.app.utils;//package mzs.lib.app.utils;
//
//import android.app.ActivityManager;
//import android.app.Application;
//import android.content.Context;
//
//import java.util.List;
//
///**
// * Created by 24275 on 2016/9/19.
// */
//public class AppUtils {
//
//    public static boolean isAppOnForeground(Context context) {
//        ActivityManager activityManager = (ActivityManager) context.getSystemService(
//                Context.ACTIVITY_SERVICE);
//        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
//        if (appProcesses == null) {
//            return false;
//        }
//        String packageName = context.getPackageName();
//        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
//            if (appProcess.processName.equals(packageName)
//                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}
