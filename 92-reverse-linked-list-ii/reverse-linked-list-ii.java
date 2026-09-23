/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        
        ListNode before=null;
        ListNode t=head;
        int pos=1;
        while(pos<left)
        {
          before=t;
          t=t.next;
          pos++;
          continue;
        }

        int times=right-left+1;
        ListNode prev=null;
        ListNode curr=t;

        while(times>0)
        {
            ListNode nex=curr.next;
            curr.next=prev;
            prev=curr;
            curr=nex;
            times--;
        }

        t.next=curr;
        if(before!=null)
        {
            before.next=prev;
            return head;

        }
        else
        {
            return prev;
        }
        
    }
}