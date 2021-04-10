package com.leetcode.thread.four;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class H2O {

  public H2O() {

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

        synchronized (lock) {
          int j= 1;
          while (j<=i*2) {


            System.out.println("h");
            ah.incrementAndGet();

            if (ah.get()==2 && ao.get()==1) {
              System.out.println("/");
              ah.set(0);
              ao.set(0);
              j++;
//              lock.notifyAll();
              continue;
            } else if(ao.get()<1 && ah.get()==2)
            {
              lock.notifyAll();
              try {
                lock.wait();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }else {
              continue;
            }


          }
        }
      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run()  {
        Thread.currentThread().setName("seconde");
        synchronized (lock) {
          int j = 1;
          while (j <= i) {


            System.out.println("o");
            ao.incrementAndGet();

            if (ah.get() == 2 && ao.get() == 1) {
              System.out.println("/");
              ah.set(0);
              ao.set(0);
              j++;
//            lock.notifyAll();
              continue;
            } else if (ah.get() < 2 && ao.get() == 1) {
              lock.notifyAll();
              try {
                lock.wait();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            } else {
              continue;
            }


          }
        }
      }

    };

    Thread t1 = new Thread(n1);
    Thread t2 = new Thread(n2);


    t2.start();
    t1.start();
  }
}