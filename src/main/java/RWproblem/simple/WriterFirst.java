package RWproblem.simple;

import java.util.concurrent.Semaphore;

public class WriterFirst {
    //创建一个用于记录正在读的读进程数
    static int readCount = 0;
    static int writerCount = 0;
    //创建一个读写线程共享的临界资源
    static volatile int data = 0;

    static volatile int print_count = 0;


    //读者优先
    public static void main(String[] args){
       //创建一个读写互斥的信号量
        final Semaphore wmutex=new Semaphore(1,true);

        //创建一个用于读进程间的修改临界资源readCount的互斥信号量
        final Semaphore rmutex=new Semaphore(1,true);

        //创建一个用于读进程间的修改临界资源readCount的互斥信号量
        final Semaphore mutex1=new Semaphore(1,true);
        final Semaphore mutex2=new Semaphore(1,true);

        for(int i = 0;i < 6;i++){
            int num = i;
            new Thread(()->{
            try {
                Thread.sleep(num*10);

                System.out.println(print_count+++":="+Thread.currentThread().getId()+"号读线开始=");
                Thread.sleep(100);

                mutex2.acquire();
                if(readCount == 0) rmutex.acquire();
                readCount++;
                mutex2.release();


//                System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程读");
                Thread.sleep(10);
                System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程读\t当前数据为:"+data);

                mutex2.acquire();
                readCount--;
                if(readCount == 0) rmutex.release();
                mutex2.release();

                Thread.sleep(2000);
                System.out.println(print_count+++":="+Thread.currentThread().getId()+"号读线结束=");

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        },"读线程"+i).start();
    }

    for(int j = 0;j < 3;j++) {
        int num = j;
        new Thread(() -> {
            try {
                Thread.sleep(5+num*10);

                System.out.println(print_count++ + ":=" + Thread.currentThread().getId() + "号写线开始=");
                Thread.sleep(100);

                mutex1.acquire();
                if(writerCount == 0) rmutex.acquire();
                writerCount++;
                mutex1.release();

                wmutex.acquire();

                data++;
                System.out.println(print_count++ + ":" + Thread.currentThread().getId() + "号写线程写\t当前数据为:" + data);
                Thread.sleep(10);

                wmutex.release();
//                System.out.println(print_count++ + ":" + Thread.currentThread().getId() + "号写线程释放取写锁");

                mutex1.acquire();
                writerCount--;
                if(writerCount == 0) rmutex.release();
                mutex1.release();


                Thread.sleep(2000);
                System.out.println(print_count++ + ":=" + Thread.currentThread().getId() + "号写线结束=");


            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "写线程" + j).start();
    }
}
}
