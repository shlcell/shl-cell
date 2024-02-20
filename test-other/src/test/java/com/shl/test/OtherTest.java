package com.shl.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class OtherTest {


    public int[] twoSum(int[] nums, int target) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        return null;
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.putIfAbsent(nums[i], i);
        }
        return new int[0];
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }


    public class ListNode {
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

    @Test
    public void shl() {
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = this.twoSum(nums, 13);
    }

}
