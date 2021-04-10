package com.leetcode.thread.five;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

class FizzBuzz {
  private int n;

  public FizzBuzz(int n) {
    this.n = n;
  }

  // printFizz.run() outputs "fizz".
  public void fizz(Runnable printFizz) throws InterruptedException {

  }

  // printBuzz.run() outputs "buzz".
  public void buzz(Runnable printBuzz) throws InterruptedException {

  }

  // printFizzBuzz.run() outputs "fizzbuzz".
  public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {

  }

  // printNumber.accept(x) outputs "x", where x is an integer.
  public void number(IntConsumer printNumber) throws InterruptedException {

  }

  public static void main(String[] args) {

    int i =100;
    Semaphore semaphoreOne = new Semaphore(0);
    Semaphore semaphoreTwo = new Semaphore(0);
    Semaphore semaphoreThree = new Semaphore(0);
    Semaphore semaphoreFour = new Semaphore(0);



    Runnable n1 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("fizz");


        for(int j=1;j<=i;j++) {

          if(j%3==0) {
            try {
              semaphoreOne.acquire();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphoreFour.release();
          }
        }
      }

    };
    Runnable n2 = new Runnable() {
      @Override
      public void run()  {
        Thread.currentThread().setName("buzz");



        for(int j=1;j<=i;j++) {

          if(j%5==0) {
            try {
              semaphoreTwo.acquire();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphoreFour.release();
          }
        }


      }

    };
    Runnable n3 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("fizzbuzz");


        for(int j=1;j<=i;j++) {

          if(j%5==0 && j%3==0) {
            try {
              semaphoreThree.acquire();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            semaphoreFour.release();
          }
        }

      }
    };
    Runnable n4 = new Runnable() {
      @Override
      public void run() {
        Thread.currentThread().setName("fourth");
//        if(semaphoreOne.availablePermits()==0 && semaphoreTwo.availablePermits()==0 && semaphoreThree.availablePermits()==0)
//        {
//
//        }

        for(int j=1;j<=i;j++)
        {

          if(j%3==0 && j%5==0)
          {
            semaphoreThree.release();
            try {
              semaphoreFour.acquire();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

          }else if(j%5==0 && j%3!=0)
          {
            semaphoreTwo.release();
            try {
              semaphoreFour.acquire();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

          }else if(j%3==0 && j%5!=0)
          {
            semaphoreOne.release();
            try {
              semaphoreFour.acquire();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }

          }else {

            System.out.println(j);
          }

        }


      }
    };
    Thread t1 = new Thread(n1);
    Thread t2 = new Thread(n2);
    Thread t3 = new Thread(n3);
    Thread t4 = new Thread(n4);
    t4.start();
    t3.start();
    t2.start();
    t1.start();
  }
}