package com.leetcode.hot.five;

import com.leetcode.hot.one.ListNode;

public class Solution2 {


  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

    ListNode result = new ListNode(0);

    ListNode temp1 = l1;
    ListNode temp2 = l2;

    ListNode curr = result;

    while (temp1!=null && temp2!=null)
    {

      if(temp1.val>temp2.val)
      {
        curr.next = temp2;
        temp2 = temp2.next;
      }else {
        curr.next = temp1;
        temp1 = temp1.next;
      }
      curr = curr.next;
    }
    if(temp1==null)
    {
      curr.next=temp2;
    }else {
      curr.next=temp1;
    }


    return result.next;
  }

  public static void main(String[] args) {

    ListNode listNodea = new ListNode(1);
    ListNode listNodea1 = new ListNode(2);
    ListNode listNodea2 = new ListNode(4);
    listNodea.next=listNodea1;
    listNodea1.next=listNodea2;

    ListNode listNodeb = new ListNode(1);
    ListNode listNodeb1 = new ListNode(3);
    ListNode listNodeb2 = new ListNode(4);
    listNodeb.next=listNodeb1;
    listNodeb1.next=listNodeb2;

    Solution2 solution = new Solution2();
    solution.mergeTwoLists(listNodea, listNodeb);
    //solution.addTwoNumbers(new ListNode(0), new ListNode(0));



  }
}
