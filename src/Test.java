
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Test {
    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        int[] a = {1, 2, 3, 3, 2, 1};
        ListNode next = node;
        for (int i = a.length - 1; i >= 0; i--) {
            next.next = new ListNode(a[i]);
            next = next.next;
        }

        Test t = new Test();
        System.out.println(t.isPalindrome(node));
    }

    boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.val != prev.val) {
                return false;
            }
            slow = slow.next;
            prev = prev.next;
        }

        return true;
    }
}