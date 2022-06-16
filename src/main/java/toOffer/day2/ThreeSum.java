package toOffer.day2;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
//
//注意：答案中不可以包含重复的三元组。

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        List<List<Integer>> lists = threeSum.threeSum(new int[]{-1,0,1,2,-1,-4});
        for(int i = 0;i < lists.size();i++){
            System.out.println(lists.toString());
        }
    }
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> lists = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for(int i = 0;i < nums.length;i++){
            for(int j = i+1;j < nums.length;j++){
                for(int k = j+1;k < nums.length;k++){
                    if(!set.contains(Math.abs(nums[i])) && nums[k]+nums[j] == -nums[i]){
                        set.add(Math.abs(nums[i]));
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        lists.add(temp);
                    }
                }
            }
        }

        return lists;
    }
    public List<List<Integer>> twoSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(int i = 0;i < nums.length;i++){
            for(int j = i+1;j < nums.length;j++) {
                if(!set.contains(Math.abs(nums[i])) && nums[j]+nums[i]==0){
                    set.add(Math.abs(nums[i]));
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    lists.add(temp);
                }
            }
        }
        return lists;
    }
}
