package cn.myhydt.app.commonservice.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author hy9902
 * @create 2018-09-25 15:59
 */
@Slf4j
public class HyUtil {



    /**
     * 系统默认延迟5秒后退出
     */
    public static void shutdownDelay() {
        shutdownDelay(5);
    }

    /**
     * 系统延迟退出
     *
     * @param seconds
     *            延迟多少秒后退出
     */
    public static void shutdownDelay(int seconds) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            @Override
            public void run() {
                if (log != null) {
                    log.info("shutdown delaying {} s ...", seconds);
                }
                if (seconds > 0) {
                    ThreadUtil.sleepForAWhile(seconds * 1000);
                }
                if (log != null) {
                    log.info("shutdown delaying complete.");
                }
            }
        }));
    }
}
