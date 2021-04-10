package com.leetcode.hot.four;

import java.util.ArrayList;
import java.util.List;

class Solution {



  public boolean isHappy(int n) {

    List list = new ArrayList();

    while(true){

      char[] array = (n+"").toCharArray();

      int tempNum = 0;
      System.out.println(n);

      if(list.contains(n))
      {
        return false;
      }else {
        list.add(n);
      }
      for(int i=0;i<array.length;i++)
      {
         int temp = array[i]-48;

         tempNum += (temp * temp);
      }

      if(tempNum ==1)
      {
        return true;
      }
      n = tempNum;

    }




  }

  public static void main(String[] args) {
    int num = 2;
    Solution solution = new Solution();
    boolean result = solution.isHappy(num);
    System.out.println(result);
  }
}