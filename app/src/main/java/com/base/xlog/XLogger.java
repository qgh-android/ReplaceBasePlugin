package com.base.xlog;

public class XLogger {

    /**
     * 主工程需要初始化(库工程不需要),放在getExternalCacheDir目录下,不需要存储权限.
     *
     * @param context 上下文环境
     * @param debug   是否是debug模式
     */
//    public static void init(Context context, boolean debug) {
//
//    }


    //i级别日志，在正式包会写入到日志文件中
    public static void i(String tag, String msg) {
        System.out.println(msg);
    }


    //i级别日志，在正式包会写入到日志文件中
    public static int i(String msg) {
        System.out.println(msg);
        return 0;
    }


    //v级别日志，正式包不会写入到日志文件中
    public static int v(String tag, String msg) {

        System.out.println(msg);
        return 0;
    }


    //d级别日志，正式包不会写入到日志文件中
    public static int d(String tag, String msg) {
        System.out.println(msg);
        return 0;
    }


    //e级别日志，正式包会写入到日志文件中
    public static int e(String tag, String msg) {
        System.out.println(msg);
        return 0;
    }


    //w级别日志，正式包会写入到日志文件中
    public static int w(String tag, String msg) {

        System.out.println(msg);
        return 0;
    }


    //f级别日志,会强制把缓存中的日志写入到xlog文件中,线下使用,线上不要使用。
    public static int f(String tag, String msg) {
        System.out.println(msg);
        return 0;
    }


    private XLogger() {
    }
//
//    /**
//     * 日志存放位置
//     */
//    private static String getDefLogDir(Context context) {
//        if (context != null) {
//            File externalCacheDir = context.getExternalCacheDir();
//            if (externalCacheDir != null) {
//                return externalCacheDir.getPath() + "/xlogger/logDir";
//            }
//        }
//        return "";
//    }
//
//    /**
//     * mmap存放位置
//     */
//    private static String getDefCachePath(Context context) {
//        if (context != null) {
//            File externalCacheDir = context.getExternalCacheDir();
//            if (externalCacheDir != null) {
//                return externalCacheDir.getPath() + "/xlogger/cacheDir";
//            }
//        }
//        return "";
//    }
//
//    /**
//     * 获取当前进程名称,防止多进程操作同一个文件
//     */
//    private static String getProcessName(Context context) {
//        if (context != null) {
//            int pid = android.os.Process.myPid();
//            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//            for (ActivityManager.RunningAppProcessInfo processInfo : activityManager.getRunningAppProcesses()) {
//                if (processInfo.pid == pid) {
//                    return processInfo.processName;
//                }
//            }
//        }
//        return "";
//    }

}
