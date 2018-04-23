//package com.monitor;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by Administrator on 2018/4/19.
// */
//public class ThreadPoolDemo {
//
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//
////        //创建一个单线程的线程池
////        ExecutorService es=Executors.newSingleThreadExecutor();
////
////        //创建一个固定大小的线程池
////        ExecutorService es1= Executors.newFixedThreadPool(2);
//
//        //创建一个可缓存的线程池
//        ExecutorService es2=Executors.newCachedThreadPool();
//
////        //创建一个大小无限的线程池
////        ExecutorService es3=Executors.newScheduledThreadPool(2);
//
//        MyThread my =new MyThread();
//        MyThread my2 =new MyThread();
//        es2.execute(my);//不用new Thread,一分钟还没有就释放
//        es2.execute(my2);//不用new Thread,一分钟还没有就
//        es2.execute(my2);//不用new Thread,一分钟还没有就
//        es2.execute(my2);//不用new Thread,一分钟还没有就
//        es2.execute(my2);//不用new Thread,一分钟还没有就
//
//        System.out.println("当前线程名"+Thread.currentThread().getName());
//        es2.shutdown();//关闭线程池，如果不关闭线程池将一直运行。
//    }
//
//
//
//}
//
//class MyThread implements Runnable{
//
//    @Override
//    public void run() {
//        // TODO Auto-generated method stub
//        for(int i=0;i<10;i++){
//            try {
//                Thread.sleep(1000);
//            } catch (Exception e) {
//                // TODO: handle exception
//                e.printStackTrace();
//            }
//            System.out.println("MyThread---"+i);
//        }
//    }
//
//}
