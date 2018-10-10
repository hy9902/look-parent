package jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hy9902
 * @create 2018-10-08 10:03
 */
@Slf4j
public class JvmTest {

    /**
     * 获取当前jvm内存信息
     * @return
     */
    private String toMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        int freeMemory = (int) runtime.freeMemory()/1024/1024;
        int totalMemory = (int) runtime.totalMemory()/1024/1024;
        return freeMemory + "M/" + totalMemory + "M/(free/total)";
    }

    @Before
    public void init() {
        System.out.println(toMemoryInfo());
    }

    @After
    public void destory() {
        System.out.println(toMemoryInfo());
    }

    @Test
    public void testMemoryLeak() {
        Map map = new HashMap();
        Integer i = 0;
        while (true){
            Key key = new Key(i);
            if(!map.containsKey(key)){
                map.put(key, "Number: " + i);
            }
            i++;
        }
    }

   
}
