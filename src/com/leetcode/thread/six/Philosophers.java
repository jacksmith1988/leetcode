package com.leetcode.thread.six;

public class Philosophers {
  private String name;

  public void putLeft()
  {
    System.out.println(name+"放下左叉子");
  }
  public void putRight()
  {
    System.out.println(name+"放下右叉子");
  }

  public void getLeft()
  {
    System.out.println(name+"拿起左叉子");
  }
  public void getRight()
  {
    System.out.println(name+"拿起右叉子");
  }


}
