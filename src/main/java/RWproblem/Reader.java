package RWproblem;

import java.util.concurrent.Semaphore;

/**
 * 模拟读者
 */
public class Reader implements Runnable {
    private Disk disk;

    public Reader(Disk disk) {
        this.disk = disk;
    }

    @Override
    public void run() {
        for (int i=10;i>=0;i--)
        {
            /*读操作*/
            try {
                read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /*暂停1s*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void read() throws InterruptedException {
        disk.start_read();
        /*读数据并打印*/
        String str=disk.read();
        System.out.println("读操作："+Thread.currentThread().getId()+" 现在的数据为："+str);
        disk.finish_read();
    }

}

