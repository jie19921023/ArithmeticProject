import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dajie on 18-5-25.
 */
public class ThreadTest {
    private static ReentrantLock lock=new ReentrantLock();
    private static Condition conditiona=lock.newCondition();
    private static Condition conditionb=lock.newCondition();
    private static Condition conditionc=lock.newCondition();
    private static int a=0;

    static class Test1 implements Runnable{

        @Override
        public void run() {
            lock.lock();
           try{
               while (true){
                   if(a!=0){
                       conditiona.await();
                   }
                   System.out.println("A--");
                   a=1;
                   conditionb.signal();
               }
           }catch (Exception e){
               e.printStackTrace();
           }finally {
               lock.unlock();
           }
        }
    }
    static class Test2 implements Runnable{

        @Override
        public void run() {
            lock.lock();
            try{
                while(true){
                    if(a!=1){
                        conditionb.await();
                    }
                    System.out.println("B--");
                    a=2;
                    conditionc.signal();

                }

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    static class Test3 implements Runnable{

        @Override
        public void run() {
            lock.lock();
            try{
                while(true){
                    if(a!=2){
                        conditionc.await();
                    }
                    System.out.println("C--");
                    a=0;
                    conditiona.signal();
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args){
        new Thread(new Test1()).start();
        new Thread(new Test2()).start();
        new Thread(new Test3()).start();
    }
}
