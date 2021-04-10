package com.leetcode.thread.four;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class H2OSecond {

  public H2OSecond() {

  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

    // releaseHydrogen.run() outputs "H". Do not change or remove this line.
    releaseHydrogen.run();
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {

    // releaseOxygen.run() outputs "O". Do not change or remove this line.
    releaseOxygen.run();
  }



  public static void main(String[] args) {
    int i = 6;

    Object lock = new Object();



    Semaphore sh = new Semaphore(2);
    Semaphore so = new Semaphore(1);

    AtomicInteger ah = new AtomicInteger();
    AtomicInteger ao = new AtomicInteger();

    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");
        int i=1;
        while (i<=6) {
          try {
            so.acquire();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
//        int i = 1;
//        while (i<=sh.availablePermits()) {
//          System.out.println("h");
//          i++;
//        }
          System.out.print("h");
          sh.release();
          i++;
        }
      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("seconde");
        int i = 1;
        while (i <= 6) {
          try {
            sh.acquire(2);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
//        int i = 1;
          System.out.print("o");
          so.release(2);
          i++;
        }
      }

    };

    Thread t1 = new Thread(n1);
    Thread t2 = new Thread(n2);


    t1.start();
    t2.start();
  }
}