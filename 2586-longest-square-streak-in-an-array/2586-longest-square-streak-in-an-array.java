class Solution {
    public int longestSquareStreak(int[] nums) {
        Map<Integer, Integer> nextSquareToLength = new HashMap<>();
        // First, sort nums so we don't have issues linking the squares together
        Arrays.sort(nums);
        int maxLength = 0;
        for (int num : nums) {
            if (nextSquareToLength.containsKey(num)) {
                int val = nextSquareToLength.get(num) + 1;
                nextSquareToLength.put(num*num, val);
                if (val > maxLength) {
                    maxLength = val;
                }
            } else {
                nextSquareToLength.put(num*num, 1);
            }
        }
        return (maxLength > 1) ? maxLength : -1;
    }
}