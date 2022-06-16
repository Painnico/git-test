package RWproblem;

/**
 * 模拟写者
 */
public class Writer implements Runnable{
    private Disk disk;

    public Writer(Disk disk) {
        this.disk = disk;
    }

    @Override
    public void run() {

        for (int i=10;i>=0;i--)
        {
            /*写操作*/
            try {
                write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*等待1s*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void write() throws InterruptedException {
        disk.start_write();
        /*写数据并打印*/
        String str=disk.write("data+"+Thread.currentThread().getId());
        System.out.println("写操作："+Thread.currentThread().getId()+" 现在的数据为："+str);
        disk.finish_write();

    }
}

