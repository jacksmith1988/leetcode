package com.leetcode.thread.one;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class FooThird {
 public Semaphore semaphoreOne = new Semaphore(0);
  public Semaphore semaphoreTwo = new Semaphore(0);

  public FooThird() {

  }

  public void first(Runnable printFirst) throws InterruptedException {


    Thread.currentThread().setName("first");




      printFirst.run();
      semaphoreOne.release();

  }

  public void second(Runnable printSecond) throws InterruptedException {

    // printSecond.run() outputs "second". Do not change or remove this line.
    Thread.currentThread().setName("seconde");
    semaphoreOne.acquire();
      printSecond.run();
    semaphoreTwo.release();


  }

  public void third(Runnable printThird) throws InterruptedException {

    // printThird.run() outputs "third". Do not change or remove this line.



    Thread.currentThread().setName("third");
    semaphoreTwo.acquire();
    printThird.run();

  }


  public static void main(String[] args) throws  Exception{
//    boolean isRun = true;

    FooThird foo = new FooThird();

     Semaphore semaphoreOne = new Semaphore(0);
     Semaphore semaphoreTwo = new Semaphore(0);

    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");

        System.out.println(Thread.currentThread().getName());
        semaphoreOne.release();
      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run()  {
        Thread.currentThread().setName("seconde");


        try {
          semaphoreOne.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
          semaphoreTwo.release();

        }

    };
    Runnable n3 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("third");

        try {
          semaphoreTwo.acquire();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());

      }
    };
    Thread t1 = new Thread(n1);
    Thread t2 = new Thread(n2);
    Thread t3 = new Thread(n3);
    t3.start();
    t2.start();
    t1.start();
    //Thread.sleep(5000);
    //foo.isRun=false;
  }

}
