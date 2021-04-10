package com.leetcode.thread.one;


import java.security.Signature;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Foo {

  public volatile AtomicInteger sign = new AtomicInteger(1);
  public Foo() {

  }

  public void first(Runnable printFirst) throws InterruptedException {


    // printFirst.run() outputs "first". Do not change or remove this line.
    printFirst.run();


  }

  public void second(Runnable printSecond) throws InterruptedException {

    // printSecond.run() outputs "second". Do not change or remove this line.
    printSecond.run();

  }

  public void third(Runnable printThird) throws InterruptedException {

    // printThird.run() outputs "third". Do not change or remove this line.
    printThird.run();

  }


  public static void main(String[] args) throws  Exception{
//    boolean isRun = true;
    Lock lock = new ReentrantLock();
    Foo foo =new Foo();

    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");
        //System.out.println(Thread.currentThread().getName()+i);

        while(true)
        {
          if(foo.sign.get()==1) {
            System.out.println(Thread.currentThread().getName() + "-" + foo.sign.get());
            foo.sign.incrementAndGet();

            break;
          }
        }


      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("seconde");


        //System.out.println(Thread.currentThread().getName()+i);
        while (true)
        {
          //System.out.println(Thread.currentThread().getName()+foo.sign.get());
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
            break;
          }

          if(foo.sign.get()==2) {
            System.out.println(Thread.currentThread().getName() + "-" + foo.sign.get());
            foo.sign.incrementAndGet();
            break;
          }
        }

      }

    };
    Runnable n3 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("third");

        //System.out.println(Thread.currentThread().getName()+i);
        while (true)
        {
          //System.out.println(Thread.currentThread().getName()+foo.sign.get());
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
            break;
          }
          if(foo.sign.get()==3) {
            System.out.println(Thread.currentThread().getName() + "-" + foo.sign.get());
            break;
          }
        }

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