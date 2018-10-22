import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hy9902
 * @create 2018-09-20 14:25
 */
public class JavaTest {

    private int n;
    private int[] array;
    private Random random;
    private int count;

    @Before
    public void init(){
        n = 10;
        count = 0;
        HashSet<Integer> intSet = new LinkedHashSet<>();
        random = new Random();
        while (true){
            int t = random.nextInt(n)+1;
            intSet.add(t);
            if(intSet.size() == n){
                break;
            }
        }
        array = intSet.stream().mapToInt(i -> i.intValue()).toArray();

    }

    @Test
    public void testQuickSort(){
        array = new int[]{1,5,3,2,4};
        System.out.println("排序前");
        println(array);
        System.out.println("---------------");
        quicksort(0, n-1);
        System.out.println("最终排序后");
        println(array);
        System.out.println("---------------");
        System.out.println("循环次数:" + count);
    }

    private void println(int[] array) {
        StringBuilder stringBuilder = new StringBuilder("|");
        for(int i=0; i<array.length; i++){
            stringBuilder.append(array[i]).append("|");
        }
        System.out.println(stringBuilder.toString());
    }

    private void quicksort(int left, int right){

        int i, j, t, key;
        if(left >= right){
            return;
        }

        //选取最左边的为基准key
        key = array[left];
        i = left;
        j = right;

        while (i < j){

            //顺序很重要，要先从右边开始找小于key的数
            while (array[j] >= key && i < j){
                j--;
            }
            //同理，再从左边开始找大于key的数
            while (array[i] <= key && i < j){
                i++;
            }

            if(i < j){
                //找到后,交换2个数的位置，使小于基数key的数在左边，大于key的数在右边
                t = array[i];
                array[i] = array[j];
                array[j] = t;
                count++;
                System.out.println("左右交换 :" + count);
                System.out.println("-----------------");
                println(array);
                System.out.println("-----------------");
            } else if(i == j){
                if(i != left){
                    array[left] = array[i];
                    array[i] = key;
                    count++;
                    System.out.println("key 交换 :" + count);
                    System.out.println("-----------------");
                    println(array);
                    System.out.println("-----------------");
                }
            }

        }

       /* if(i != left){
            //最终将基准数归位
            array[left] = array[i];
            array[i] = key;
            System.out.println("key 交换 :" + count);
            System.out.println("-----------------");
            println(array);
            System.out.println("-----------------");
        }*/

        //继续处理左边的，这里是一个递归的过程
        quicksort(left,i-1);
        //继续处理右边的 ，这里是一个递归的过程
        quicksort(i+1,right);
    }

    /**
     * 每次排序，都把最大值排到最后
     */
    @Test
    public void testBubbleSort(){
        System.out.println("排序前");
        println(array);
        System.out.println("---------------");


        for(int i=0; i<array.length-1; i++){
            for (int j= 0; j<array.length-1-i; j++){
                count++;
                if(array[j] > array[j+1]){
                    int tmp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = tmp;
                }
                System.out.println("中间排序:" + count);
                println(array);
                System.out.println("---------------");
            }
        }

        System.out.println("最终排序后");
        println(array);
        System.out.println("---------------");
    }

    /**
     * 　选择排序也是一种简单直观的排序算法。它的工作原理很容易理解：初始时在序列中找到最小（大）元素，放到序列的起始位置作为已排序序列；
     * 然后，再从剩余未排序元素中继续寻找最小（大）元素，放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
     *  注意选择排序与冒泡排序的区别：冒泡排序通过依次交换相邻两个顺序不合法的元素位置，从而将当前最小（大）元素放到合适的位置；
     *  而选择排序每遍历一次都记住了当前最小（大）元素的位置，最后仅需一次交换操作即可将其放到合适的位置。
     */
    @Test
    public void testSelectSort(){
        System.out.println("排序前");
        println(array);
        System.out.println("---------------");

        for (int i=0; i<array.length-1; i++){
            //取最小值坐标
            int min = i;
            for(int j=i+1; j<array.length; j++){
                //最小值变化，记录坐标
                if(array[min] > array[j]){
                    min = j;
                }
            }

            //最小值变化，则交换
            if(min != i){
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }

        System.out.println("排序后");
        println(array);
        System.out.println("---------------");
    }

    /**
     * 对于未排序数据(右手抓到的牌)，在已排序序列(左手已经排好序的手牌)中从后向前扫描，找到相应位置并插入。
     * 　具体算法描述如下：
     *
     * 1. 从第一个元素开始，该元素可以认为已经被排序
     * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
     * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
     * 4. 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置
     * 5. 将新元素插入到该位置后
     * 6. 重复步骤2~5
     */
    @Test
    public void testInsertionSort(){
        System.out.println("排序前");
        println(array);
        System.out.println("---------------");

        for(int i=1; i<array.length; i++){
            //右手牌，待排序的
            int get = array[i];
            //左手已排好的牌的最大一张
            int j = i-1;
            //空出来的位置，待插入的
            while (j>=0 && array[j] > get){
                //排好序的牌比待排序的大，则移动大的那张牌
                array[j+1] = array[j];
                //移动比较下一个
                j--;
            }
            //此时比排好序的大，则插入到排好序的末尾
            array[j+1] = get;
        }


        System.out.println("排序后");
        println(array);
        System.out.println("---------------");
    }

    @Test
    public void testDiv() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(10);
        map.put("a", "a1");
        System.out.println(map.remove("a"));
    }
}
