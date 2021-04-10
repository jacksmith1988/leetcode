package com.leetcode.thread.four;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

class H2OThird {

  private CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
    @Override
    public void run() {
      semaphoreH.release(2);
      semaphoreO.release();
    }
  });
  private Semaphore semaphoreH;
  private Semaphore semaphoreO;

  public H2OThird() {
    semaphoreH = new Semaphore(2);
    semaphoreO = new Semaphore(1);
  }

  public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
    semaphoreH.acquire();
    releaseHydrogen.run();
    try {
      barrier.await();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }

  public void oxygen(Runnable releaseOxygen) throws InterruptedException {
    semaphoreO.acquire();
    releaseOxygen.run();
    try {
      barrier.await();
    } catch (BrokenBarrierException e) {
      e.printStackTrace();
    }
  }


}