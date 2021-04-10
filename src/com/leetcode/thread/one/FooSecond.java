package com.leetcode.thread.one;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class FooSecond {

  private Object lock = new Object();
  boolean isFirstDone = false;
  boolean isSecondDone = false;

  public volatile AtomicInteger sign = new AtomicInteger(1);
  public FooSecond() {

  }

  public void first(Runnable printFirst) throws InterruptedException {


    Thread.currentThread().setName("first");

    synchronized (lock) {
      printFirst.run();
      this.isFirstDone = true;
      lock.notifyAll();
    }


  }

  public void second(Runnable printSecond) throws InterruptedException {

    // printSecond.run() outputs "second". Do not change or remove this line.
    Thread.currentThread().setName("seconde");
    synchronized (lock) {
      while(!this.isFirstDone) {

        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

      }
      printSecond.run();
      this.isSecondDone=true;
      lock.notifyAll();
    }


  }

  public void third(Runnable printThird) throws InterruptedException {

    // printThird.run() outputs "third". Do not change or remove this line.



    Thread.currentThread().setName("third");
    synchronized (lock) {
      while(!this.isSecondDone) {

        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        printThird.run();
      }
    }

  }


  public static void main(String[] args) throws  Exception{
//    boolean isRun = true;

    FooSecond foo =new FooSecond();

    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");
        synchronized (foo.lock) {
          System.out.println(Thread.currentThread().getName());
          foo.isFirstDone = true;
          foo.lock.notifyAll();
        }
      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("seconde");

        synchronized (foo.lock) {
          while(!foo.isFirstDone) {
            try {
              foo.lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

          }
          System.out.println(Thread.currentThread().getName());
          foo.isSecondDone=true;
          foo.lock.notifyAll();
        }
      }

    };
    Runnable n3 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("third");

        synchronized (foo.lock) {
          while(!foo.isSecondDone) {
            try {
              foo.lock.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          System.out.println(Thread.currentThread().getName());
        }

      }
    };
    Thread t1 = new Thread(n1);
    Thread t2 = new Thread(n2);
    Thread t3 = new Thread(n3);
    t1.start();
    t2.start();
    t3.start();
    //Thread.sleep(5000);
    //foo.isRun=false;
  }


}