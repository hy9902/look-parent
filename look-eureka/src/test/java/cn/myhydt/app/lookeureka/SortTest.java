package cn.myhydt.app.lookeureka;

import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 排序算法测试类
 */
public class SortTest {

    private int[] array;

    @Before
    public void init(){
        Random random = new Random();
        Set<Integer> set = new HashSet();
        while (true){
            int a = random.nextInt(1000);

            set.add(a);
            if(set.size()==20){
                break;
            }
        }
        array = set.stream().mapToInt(i->{
            System.out.print(i+", ");
            return Integer.parseInt(i.toString());}).toArray();
    }

    @Test
    public void bubbleSort(){
        int len = array.length;

        //循环的次数为数组长度减一，剩下的一个数不需要排序
        for(int i=0;i<len-1;++i){
            boolean noswap=true;
            //循环次数为待排序数第一位数冒泡至最高位的比较次数
            for(int j=0;j<len-i-1;++j){
                if(array[j]>array[j+1]){
                    //array[j]=array[j]+array[j+1];
                    //array[j+1]=array[j]-array[j+1];
                    //array[j]=array[j]-array[j+1];
                    //交换或者使用如下方式
                    array[j]=array[j]^array[j+1];
                    array[j+1]=array[j+1]^array[j];
                    array[j]=array[j]^array[j+1];
                    noswap=false;
                }
            }
            if(noswap) break;
        }
        System.out.println();
        System.out.println("------------------------------------");
        Ints.asList(array).stream().forEach(i->System.out.print(i+", "));
    }
}
