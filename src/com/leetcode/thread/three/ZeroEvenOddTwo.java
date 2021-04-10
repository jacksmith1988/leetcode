package com.leetcode.thread.three;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

class ZeroEvenOddTwo {

  int flag = 0;
  private int n;

  public ZeroEvenOddTwo(int n) {
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

    ZeroEvenOddTwo obj = new ZeroEvenOddTwo(2);

    int n = 2;

    Semaphore s0 = new Semaphore(0);
    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(0);




    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("first");

        //System.out.println(Thread.currentThread().getName());
        for(int i =1;i<=n;i++)
        {
          try {
            s0.acquire();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(0);
          if(i%2==0)
          {
            s2.release();
          }else {
            s1.release();
          }
        }



      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("sencond");


        //System.out.println(Thread.currentThread().getName());
        for (int i = 1; i <= n; i=i+2) {
          try {
            s1.acquire();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          if (i % 2 == 1) {
            System.out.println(i);
            s0.release();
          }

        }

      }

    };
    Runnable n3 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("third");


        //System.out.println(Thread.currentThread().getName());

        for(int i =2;i<=n;i=i+2)
        {
          try {
            s2.acquire();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          if(i%2==0)
          {
            System.out.println(i);
            s0.release();
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
  }
}