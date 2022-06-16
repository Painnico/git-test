package RWproblem.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    //创建一个用于记录正在读的读进程数
    static int readCount=0;
    //创建一个读写线程共享的临界资源
    static volatile int data=0;

    static int MAX_CIRCULATE_TIMES = 10;
    static volatile int print_count = 0;

    //读者优先
    public static void main(String[] args){
        //创建一个线程池
        ExecutorService es=Executors.newFixedThreadPool(10);
        //创建一个读写互斥的信号量
        final Semaphore wmutex=new Semaphore(1,true);

        //创建一个用于读进程间的修改临界资源readCount的互斥信号量
        final Semaphore rmutex=new Semaphore(1,true);


        //创建读进程
        for(int i=0;i<4;i++){
            es.execute(new Runnable(){

                @Override
                public void run() {
                    while(!Thread.interrupted()){
                        try {
                            if(data >=MAX_CIRCULATE_TIMES){
//                                System.out.println(Thread.currentThread().getId()+"号线程结束");
                                break;
                            }
                            //获取可以修改readCount的互斥信号量
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线开始");
//                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程准备获取读锁");
                            rmutex.acquire();
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程获取读锁");
                            //当readCount==0时说明，不存在读线程获得读写的互斥信号量,该信号量只对写线程操作互斥，对读线程不互斥
                            if(readCount==0){//获取可以进行IO操作的的互斥信号
//                                System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程尝试获取写锁");
                                wmutex.acquire();
                                System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程获取写锁");
                            }

                            readCount++;
                            //释放信号量
//                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程准备释放读锁");
                            rmutex.release();
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程释放读锁");
                            //进行IO读操作
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程读");
//                            Thread.sleep(100);
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程读\t当前数据为:"+data);

                            //获取可以修改readCount的互斥信号量
                            rmutex.acquire();
                            readCount--;
                            //释放信号量
                            rmutex.release();
                            //当readCount==0时说明，所有读线程不再对共享资源进行操作了，则释放互斥信号量
                            if(readCount==0) {
//                                System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程准备释放写锁");
                                wmutex.release();
                                System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线程释放写锁");
                            }
                            //将cpu调度权让给其他读线程，但操作不一定成功
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号读线结束");
                            Thread.sleep(100);
                            Thread.yield();

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }


            });
        }
        //创建写线程
        for(int i=0;i<3;i++){
            es.execute(new Runnable(){

                @Override
                public void run() {
                    while(!Thread.interrupted()){
                        try {
                            if(data >=MAX_CIRCULATE_TIMES){
//                                System.out.println(Thread.currentThread().getId()+"号线程结束");
                                break;
                            }
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线开始");
                            //获取可以进行IO操作的互斥信号量，防止其他读写线程对临界资源进行操作，如果获取不到，则等待
//                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线程准备获取写锁");
                            wmutex.acquire();
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线程获取写锁");
                            //进行IO 写操作
                            //同样便于简单明了，这里只输出打印
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线程写");
                            //修改临界资源的值
                            data++;
                            //模拟IO操作所需时间
//                            Thread.sleep(500);
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线程写\t当前数据为:"+data);
                            //释放信号量，让其他的读或写线程可以竞争
//                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线程准备释放取写锁");
                            wmutex.release();
                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线程释放取写锁");

                            System.out.println(print_count+++":"+Thread.currentThread().getId()+"号写线结束");
                            //将cpu的调度权让给其他写线程,但操作不一定成功

                            Thread.sleep(500);
                            Thread.yield();

                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
