package com.monitor;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018/4/26.
 */
public class FutrueTaskDemoTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
            //第一种方式
            ExecutorService executor = Executors.newCachedThreadPool();
        class Task implements Callable<Integer> {
            long start1 = System.currentTimeMillis();

            @Override
            public Integer call() throws Exception {
                System.out.println("第一个子线程在进行计算");

                Thread.sleep(3000);
                int sum = 0;
                for(int i=0;i<100;i++)
                    sum += i;
                System.out.println("第一个子线程在进行计算消耗时间="+String.valueOf(System.currentTimeMillis()-start1));
                return sum;
            }
        }
        class Task2 implements Callable<Integer> {
            long start2 = System.currentTimeMillis();
            @Override
            public Integer call() throws Exception {
                System.out.println("第二个子线程在进行计算");
                Thread.sleep(3000);
                int sum = 0;
                for(int i=0;i<100;i++)
                    sum += i;
                System.out.println("第二个子线程在进行计算消耗时间="+String.valueOf(System.currentTimeMillis()-start2));
                return sum;
            }
        }
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new Task());
            FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new Task2());

            executor.submit(futureTask);
            executor.execute(futureTask2);

            executor.shutdown();


        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
        /*Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
//
//            System.out.println("主线程在执行任务");
//
//            try {
//                System.out.println("task运行结果"+futureTask.get());
//                System.out.println("task2运行结果"+futureTask2.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
        try {
            System.out.println("task运行结果"+futureTask.get());
            System.out.println("task2运行结果"+futureTask2.get());
        }catch (Exception e){
            e.printStackTrace();
        }
            System.out.println("所有任务执行完毕");
        System.out.println("主线程消耗时间="+String.valueOf(System.currentTimeMillis()-start));
        }

    }
