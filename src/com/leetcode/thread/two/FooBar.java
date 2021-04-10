package com.leetcode.thread.two;

import java.util.concurrent.atomic.AtomicInteger;

public class FooBar {
  private int n;
  public volatile AtomicInteger sign = new AtomicInteger(1);
  private Object lock = new Object();

  public FooBar(int n) {
    this.n = n;
  }

  public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {


      // printFoo.run() outputs "foo". Do not change or remove this line.

      if(sign.get()%2==1)
      {
        //synchronized(lock)
        //{

        System.out.println(sign.get()+"step1");
        printFoo.run();
        sign.incrementAndGet();

        //lock.notifyAll();
        //}

      }else
      {
        System.out.println(sign.get()+"step2");
        Thread.sleep(100);

      }

    }
  }

  public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {

      // printBar.run() outputs "bar". Do not change or remove this line.
      //synchronized(lock)
      //{
      if(sign.get()%2==0){

        // lock.wait();
        System.out.println(sign.get()+"step1");
        printBar.run();
        sign.incrementAndGet();
        // lock.wait();
        //lock.notifyAll();


      }else
      {
        Thread.sleep(100);
        System.out.println(sign.get()+"step4");
      }
      //}

    }
  }

  public static void main(String[] args) {
    int n =100;
    AtomicInteger sign = new AtomicInteger(1);
    Object lock = new Object();

    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");
        for (int i = 0; i < n; i++) {
          while(true)
          {
            if(sign.get()%2==1) {
              System.out.println("foo");
              sign.incrementAndGet();
              break;
            }else {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
        }

      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run()  {
        Thread.currentThread().setName("seconde");

        for (int i = 0; i < n; i++) {
          while(true)
          {
            if(sign.get()%2==0) {
              System.out.println("bar");
              sign.incrementAndGet();
              break;
            }else {
              try {
                Thread.sleep(100);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
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
