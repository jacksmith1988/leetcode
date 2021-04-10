package com.leetcode.thread.three;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOdd {

  int flag = 0;
  private int n;

  public ZeroEvenOdd(int n) {
    this.n = n;
  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void zero(IntConsumer printNumber) throws InterruptedException {

    for(int i=1;i<=n;i++)
    {
      printNumber.accept(i);
    }

  }

  public void even(IntConsumer printNumber) throws InterruptedException {


  }

  public void odd(IntConsumer printNumber) throws InterruptedException {

  }

  public static void main(String[] args) {

    ZeroEvenOdd obj = new ZeroEvenOdd(2);

    int n = 9;

    Semaphore semaphore = new Semaphore(2);

    ReentrantLock lock = new ReentrantLock();
    Condition zeroLock = lock.newCondition();
    Condition evenLock = lock.newCondition();
    Condition oddLock = lock.newCondition();


    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");

        //System.out.println(Thread.currentThread().getName());
        lock.lock();

        try {

          for(int i=1;i<=n;i++)
          {
            if(obj.flag!=0) {
              try {
                zeroLock.await();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
            System.out.println(0);
            if(i%2==1)
            {
              obj.flag=1;
              evenLock.signal();
            }else {
              obj.flag=2;
              oddLock.signal();
            }
          }

        }finally {
          lock.unlock();
        }



      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run()  {
        Thread.currentThread().setName("sencond");



        //System.out.println(Thread.currentThread().getName());
        lock.lock();
        try {

          for(int i=1;i<=n;i++)
          {

            if(obj.flag!=1)
            {
              try {
                evenLock.await();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }

            if(i%2==1)
            {
              System.out.println(i);
              obj.flag=0;
              zeroLock.signal();
            }

          }

        }finally {
          lock.unlock();
        }

      }

    };
    Runnable n3 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("third");


        //System.out.println(Thread.currentThread().getName());

        lock.lock();
        try {

          for(int i=1;i<=n;i++)
          {
            if(obj.flag!=2) {
              try {
                oddLock.await();
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }


            if(i%2==0)
            {
              System.out.println(i);
              obj.flag=0;
              zeroLock.signal();
              //obj.flag=2;
              //evenLock.signal();
            }
//            else {
//              obj.flag=0;
//              zeroLock.signal();
//            }
          }

        }finally {
          lock.unlock();
        }

      }
    };
    Thread t1 = new Thread(n1);
    Thread t2 = new Thread(n2);
    Thread t3 = new Thread(n3);
    t3.start();
    t2.start();
    t1.start();
  }
}