package aqs.lock;

import org.junit.Test;

//重入锁测试
public class ReEnterLockDemo {


    static Object lockA = new Object();

    public static void m1() {
        new Thread(()->{
            synchronized(lockA) {
                System.out.println(Thread.currentThread().getName()+"进入外层。。。");
                synchronized(lockA) {
                    System.out.println(Thread.currentThread().getName()+"进入中层。。。");
                    synchronized(lockA) {
                        System.out.println(Thread.currentThread().getName()+"进入内层。。。");
                    }
                }
            }
        },"t1").start();
    }

    @Test
    public void testDemo(){
        m1();
    }

}
