package RWproblem;
import java.util.concurrent.Semaphore;

/**
 * 模拟被读和被写的对象
 */
public class Disk implements Runnable{
    private String data_str;    /*读写数据*/
    private int reader_count;   /*读者计数*/
    private Semaphore write_mutex;  /*写者信号量*/
    private Semaphore read_mutex;   /*读者信号量*/
    private Semaphore read_count_mutex; /*修改读者计数的信号量*/

    Disk()
    {
        /*new和设置最大信号量数*/
        write_mutex=new Semaphore(1);
        read_mutex=new Semaphore(1000);
        read_count_mutex=new Semaphore(1);
    }

    public void start_read() throws InterruptedException {
        /*有写者时忙等*/
        while (write_mutex.availablePermits()==0){ }

        /*修改读者计数*/
        read_count_mutex.acquire();
        reader_count++;
        read_count_mutex.release();

        /*获取信号量*/
        read_mutex.acquire();

    }

    public void finish_read() throws InterruptedException {

        /*修改读者计数*/
        read_count_mutex.acquire();
        reader_count--;
        read_count_mutex.release();

        /*获取读者信号量*/
        read_mutex.release();

    }

    public void start_write() throws InterruptedException {
        /*获取写者信号量*/
        write_mutex.acquire();
        /*当有读者时忙等*/
        while (getReader_count()!=0){}
    }

    public void finish_write()
    {
        /*释放写者信号量*/
        write_mutex.release();
    }

    /**
     * 读操作
     * @return 读取的数据
     * @throws InterruptedException
     */
    public String read() throws InterruptedException {
        return data_str;
    }

    /**
     * 写操作
     * @param new_data 写入的数据
     * @return 写入后的数据
     * @throws InterruptedException
     */
    public String write(String new_data) throws InterruptedException {
        this.data_str=new_data;
        return data_str;
    }

    public int getReader_count() {
        return reader_count;
    }

    public void setReader_count(int reader_count) {
        this.reader_count = reader_count;
    }

    @Override
    public void run() {

    }
}