package cn.myhydt.app.gateway;

import org.junit.Test;

/**
 * @author hy9902
 * @create 2018-08-23 9:37
 */
public class JavaTest {

    @Test
    public void testOffsetDay(){
       Long offsetDay =  (System.currentTimeMillis() - 1535018400000L)/(1000 * 60 * 60 *24);
        System.out.println(offsetDay);
    }
}
