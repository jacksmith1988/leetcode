package com.leetcode.hot.two;

/*
*
* 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

* */
public class Solution {

  //dvdf
  public int lengthOfLongestSubstring(String s) {

    if(s==null || s.length()==0)
    {
      return 0;
    }
    char[] chars =  s.toCharArray();
    String temp = "";
    String maxTemp = "";
    for(int i=0;i<chars.length;i++)
    {
      char current = chars[i];
      if(temp.contains(current+""))
      {
        temp = temp.substring(temp.indexOf(current)+1, temp.length());
        temp += current;

      }else {
        temp += current;
      }


      if(temp.length()>maxTemp.length()) {
        maxTemp = temp;
      }
    }

    return maxTemp.length();
  }


  public int lengthOfLongestSubstring2(String s) {
    char[] freq = new char[256];
    // 设定s的[left,right]子串无重复字符
    int l = 0, r = -1;
    int max = 0;
    while (l < s.length()){
      if (r == s.length() - 1){
        break;
      }
      if (freq[s.charAt(r + 1)] == 0){
        freq[s.charAt(r + 1)]++;
        r++;
        max = Math.max(max, r - l + 1);
      } else {
        freq[s.charAt(l)]--;
        l++;
      }
    }
    return max;
  }


  //sabcabcb
  public static void main(String[] args) {
    String input = "ckilbkd";
    Solution solution = new Solution() ;
    int j = solution.lengthOfLongestSubstring(input);
    System.out.println("args = [" + j + "]");
  }
}
