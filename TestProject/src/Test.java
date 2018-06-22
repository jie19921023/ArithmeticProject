import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by dajie on 17-8-16.
 */
public class Test {
    public static void main(String[] args){
//        String a=new MD5().toHexString("sdfdgdfhfghgj");
//        System.out.println(a);
//        BigInteger bigInteger=new BigInteger("324507245",8);
//        System.out.print(bigInteger.intValue());
//        Map<String,Map<String,String>> map=new LinkedHashMap<String,Map<String,String>>();
//        Map<String,String> a=map.get("key");
//        System.out.print(a.get("key"));
//ParentClass p=new SonClass();
//        p.eat();
//System.out.print(p.getA()+"---"+p.b);
        //new Test().test();
        int[][] a={{1,2,3},{4,5,6},{7,8,9}};
        int[] b={1,2,3,4,5},c={2,3,4,5,1};
        int[] sdsf=new int[3];
//int b=new Calcate().containsNum(a,20);
//System.out.println("-----b="+b);

       // int[] pre={1,2,4,7,3,5,6},mid={4,7,2,1,5,3,6};
        Calcate calcate= new Calcate();
        //c.printTree(c.createTree(pre,mid,0));
        //System.out.print("-----" + c.fbnq(5));
        //int[] a={2,1,4,8,7,3};
//        c.changeAb(a);
//        for(int i=0;i<a.length;i++){
//            System.out.print(a[i]+",");
//        }
        //boolean flag=calcate.stackPopOrder(b,c);
//        char[] chars={'a','b','c','d'};
//        calcate.permutation(chars,0,3);
//        //System.out.print(flag);
//        Map map=new HashMap();
//        ConcurrentHashMap map1=new ConcurrentHashMap();
//        map1.get("");
//        count count=new count();
//        Thread thread1=new Thread(new MyTest(count),"线程1");
//        Thread thread2=new Thread(new MyTesta(count),"线程2");
//        thread2.start();
//        thread1.start();
      // System.out.print(new people().a);

//        Class c2=people.class;
//        try {
//
//            Class c1=Class.forName("people");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        SonClass sonClass=new SonClass();
//        try {
//            SonClass sonClass1=(SonClass)sonClass.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        ExecutorService executorService= Executors.newFixedThreadPool(5);
//        CyclicBarrier barrier=new CyclicBarrier(5);
//        Semaphore semaphore=new Semaphore(3);
//        for(int i=0;i<5;i++){
//            executorService.execute(new MyTest(barrier,semaphore));
//        }
        int[] d={7,8,9,1,2,3,4,5};
       System.out.print(calcate.findMin(c));


    }
     class A{
         int a=100;
         int b=30;
        public A(){
            System.out.println(a);
            System.out.print(b);
            a=200;
        }

    }

   static class count{
       String lock="lock";
       void add(String name,int num){
           System.out.println("----" + Thread.currentThread().getName());
           synchronized (lock){
               System.out.println("----" + name + "---" + num);
               try{
                   Thread.sleep(10000);
               }catch (Exception e){}

           }

       }
    }
   static class MyTest implements Runnable{
        CyclicBarrier barrier;
        Semaphore semaphore;
        MyTest(CyclicBarrier barrier,Semaphore semaphore){
            this.barrier=barrier;
            this.semaphore=semaphore;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+"---准备好了");
                barrier.await();
                System.out.println("大家开始赛跑!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            for(int i=0;i<10;i++){
               System.out.println("正在跑" + Thread.currentThread().getName());
            }
            if(semaphore.availablePermits()>0){
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
   static class MyTesta implements Runnable{
        count count;
        MyTesta(count count){
            this.count=count;
        }
        @Override
        public void run() {
            for(int i=0;i<3;i++){
                count.add(Thread.currentThread().getName(),i);
            }
        }
    }



}
