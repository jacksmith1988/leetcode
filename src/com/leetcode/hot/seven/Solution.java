package com.leetcode.hot.seven;

import java.util.HashMap;
import java.util.Map;

public class Solution {

  public String longestPalindrome(String s) {
    if(s.length()==0)
    {
      return "";
    }

    int max = 0;
    int startIndex = 0;
    String result = s.substring(0, 1);

    Map<String,Integer> locationMap = new HashMap();
    for(int i=0;i<s.length();i++)
    {
      String element = s.substring(i, i+1);
      if(!locationMap.containsKey(element)) {
        locationMap.put(element, i);
      }else {
        int lastIndex = locationMap.get(element);//上一次出现的地方
        if(i - lastIndex > max)
        {
          max = i - lastIndex;
          startIndex = lastIndex;
          String temp = s.substring(startIndex, startIndex + max + 1);
          boolean flag = isRevertEqual(temp);
          if(flag)
          {
            result = temp;
          }
        }
      }
    }


    return result;
  }


  public boolean isRevertEqual(String temp)
  {
      String left = temp.substring(0, temp.length()/2);
      String right = temp.substring(temp.length()/2, temp.length());

    if(temp.length()%2!=0)
    {
      right = temp.substring(temp.length()/2+1, temp.length());
    }

      if(new StringBuffer(left).reverse().toString().equals(right)){
        return true;
      }



    return false;
  }
  public static void main(String[] args) {

    Solution solution = new Solution();
    String result =  solution.longestPalindrome("abacab");
    System.out.printf(result);

//    String string = "12345";
//    System.out.printf(string.substring(2, string.length()));
  }
}
