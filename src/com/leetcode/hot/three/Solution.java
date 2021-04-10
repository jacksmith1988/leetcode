package com.leetcode.hot.three;

class Solution {

  public int[] twoSum(int[] nums, int target) {



    for(int i=0;i<nums.length;i++)
    {
      for(int j =0;j<nums.length;j++)
      {
        if(i!=j && nums[i]+nums[j]==target)
        {
          return new int[]{i,j};
        }
      }
    }

    return null;
  }

  public static void main(String[] args) {

    int[] nums = {1,2,3,4,5};
    Solution solution = new Solution();

    int[] result = solution.twoSum(nums, 8);
    System.out.println(result[0]+"--"+result[1]);
  }
}
