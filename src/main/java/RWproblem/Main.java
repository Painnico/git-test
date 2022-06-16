package RWproblem;
/**
 * 主函数
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        int writerNumber=3;
        int readerNumber=7;
        int diskNumber=1;
        Disk[] disks=new Disk[diskNumber];
        Thread[] writer_threads=new Thread[writerNumber];
        Writer[] writers=new Writer[writerNumber];
        Thread[] reader_threads=new Thread[readerNumber];
        Reader[] readers=new Reader[readerNumber];
        Thread[] disk_threads=new Thread[diskNumber];

        /*模拟磁搬线程启动*/
        for (int i=0;i<diskNumber;i++)
        {
            disks[i]=new Disk();
            disk_threads[i]=new Thread(disks[i],Integer.toString(i));
            disk_threads[i].start();
        }


        /*启动部分写者*/
        for (int i=0;i<writerNumber/2;i++)
        {
            writers[i]=new Writer(disks[0]);
            writer_threads[i]=new Thread(writers[i],Integer.toString(i));
            writer_threads[i].start();
        }

        /*暂停等待写者启动*/
        Thread.sleep(100);
        /*启动读者*/
        for (int i=0;i<readerNumber;i++)
        {
            readers[i]=new Reader(disks[0]);
            reader_threads[i]=new Thread(readers[i],Integer.toString(i));
            reader_threads[i].start();
        }

        /*追加启动写者*/
        for (int i=writerNumber/2;i<writerNumber;i++)
        {
            writers[i]=new Writer(disks[0]);
            writer_threads[i]=new Thread(writers[i],Integer.toString(i));
            writer_threads[i].start();
        }

    }
}

