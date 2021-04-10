package com.leetcode.hot.one;//两数相加
//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807
/*  给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。*/

public class Solution {



  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode returnNode = new ListNode(0);
    ListNode temp1=l1;
    ListNode temp2=l2 ;
    ListNode cur = returnNode;

    int sum = 0;
    int carry = 0;
    //System.out.printf("tem1:"+temp1.val+"temp2:"+temp2.val);
    while(temp1!=null || temp2!=null)
    {
      sum = carry;

      if(temp1!=null)
      {
        sum += temp1.val;
        temp1 = temp1.next;
      }
      if(temp2!=null)
      {
        sum += temp2.val;
        temp2 = temp2.next;
      }

      carry = sum/10;
      cur.next = new ListNode(sum%10);
      cur = cur.next;


    }

    if (carry == 1) {
      cur.next = new ListNode(1);
    }



    return returnNode;
  }
  




  public static void main(String[] args) {

    ListNode listNodea = new ListNode(8);
    ListNode listNodea1 = new ListNode(2);
    ListNode listNodea2 = new ListNode(7);
    listNodea.next=listNodea1;
    listNodea1.next=listNodea2;

    ListNode listNodeb = new ListNode(6);
    ListNode listNodeb1 = new ListNode(5);
    ListNode listNodeb2 = new ListNode(4);
    listNodeb.next=listNodeb1;
    listNodeb1.next=listNodeb2;

    Solution solution = new Solution();
    solution.addTwoNumbers(listNodea, listNodeb);
    //solution.addTwoNumbers(new ListNode(0), new ListNode(0));



  }
}