package cn.myhydt.app.commonservice.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程工具
 *
 * @author hy9902
 * @create 2018-09-25 16:02
 */
@Slf4j
public class ThreadUtil {

    /**
     * 默认睡眠100ms
     */
    public static void sleepForAWhile() {
        sleepForAWhile(100);
    }

    /**
     *
     * @param mills
     */
    public static void sleepForAWhile(long mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            // do nothing
        }
    }
}
