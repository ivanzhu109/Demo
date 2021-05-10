package cn.ivanzhu.test.leetCode.test2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ivanzhu
 * @time 2021/4/8 21:33
 * @Version 1.0
 */
@Data
@NoArgsConstructor
public class LeetCodeTest2 {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l1.add(new ListNode(9));
        l1.add(new ListNode(9));
        l1.add(new ListNode(9));
        l1.add(new ListNode(9));
        l1.add(new ListNode(9));
        l1.add(new ListNode(9));
        l2.add(new ListNode(9));
        l2.add(new ListNode(9));
        l2.add(new ListNode(9));
        System.out.println(l1);
        System.out.println(l2);
        ListNode listNode = addTwoNumbers(l1, l2);
        System.out.println(listNode);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        int i = 0;
        while (l1 != null || l2 != null) {
            int num = i;
            if (l1 != null) {
                num += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                num += l2.val;
                l2 = l2.next;
            }
            i = Math.floorDiv(num, 10);
            ListNode next = new ListNode(Math.floorMod(num, 10));
            if (tail != null) {
                tail.next = next;
            }else {
                head = next;
            }
            tail = next;
        }
        if (i > 0) {
            tail.next = new ListNode(i);
        }
        return head;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListNode {
        int val;
        ListNode next;
        ListNode tail;

        ListNode(int val) {
            this.val = val;
        }

        public void add(ListNode node) {
            if (node == null) {
                return;
            }
            if (next == null) {
                next = node;
            }else {
                tail.setNext(node);
            }
            tail = node;
        }

        @Override
        public String toString() {
            StringBuilder builder = new StringBuilder("[");
            builder.append(val);
            ListNode next = this;
            while ((next = next.next) != null) {
                builder.append(",");
                builder.append(next.val);
            }
            return builder.append("]").toString();
        }
    }
}
