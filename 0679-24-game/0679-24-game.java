class Solution {
    // Entry method to judge whether it's possible to reach the target number 24.
    public boolean judgePoint24(int[] nums) {
        // Convert the input array of integers to a list of doubles.
        List<Double> numList = Arrays.stream(nums).boxed().map(Double::new).collect(Collectors.toList());
        // Initiate depth-first search to evaluate all possible results.
        return dfs(numList);
    }

    // Recursive method to perform depth-first search.
    private boolean dfs(List<Double> numList) {
        // If there are no numbers, we cannot perform any operations; return false.
        if (numList.size() == 0) {
            return false;
        }
        // If there is only one number left, check if it's approximately 24.
        if (numList.size() == 1) {
            return Math.abs(numList.get(0) - 24.0) < 1e-6;
        }
        // Try all pairs of numbers with all operations.
        for (int i = 0; i < numList.size(); i++) {
            for (int j = i + 1; j < numList.size(); j++) {
                // Check if the result of any operation on these two numbers
                // combined with the remaining numbers can result in 24.
                for (int operation = 0; operation < 6; operation++) {
                    List<Double> nextList = getNextList(numList, i, j, operation);
                    if (!nextList.isEmpty() && dfs(nextList)) {
                        return true;
                    }
                }
            }
        }
        // If no combination resulted in 24, return false.
        return false;
    }

    // Method to create a new list by applying an operation to a pair of numbers.
    private List<Double> getNextList(List<Double> numList, int firstIndex, int secondIndex, int operation) {
        List<Double> nextNumList = new ArrayList<>();
        // Add all numbers except the pair we're operating on.
        for (int k = 0; k < numList.size(); k++) {
            if (k != firstIndex && k != secondIndex) {
                nextNumList.add(numList.get(k));
            }
        }

        // Perform the operation based on the operation index.
        switch (operation) {
            case 0: // Addition
                nextNumList.add(numList.get(firstIndex) + numList.get(secondIndex));
                break;
            case 1: // Subtraction (first - second)
                nextNumList.add(numList.get(firstIndex) - numList.get(secondIndex));
                break;
            case 2: // Subtraction (second - first)
                nextNumList.add(numList.get(secondIndex) - numList.get(firstIndex));
                break;
            case 3: // Multiplication
                nextNumList.add(numList.get(firstIndex) * numList.get(secondIndex));
                break;
            case 4: // Division (first / second), check for division by zero.
                if (numList.get(secondIndex) == 0) {
                    return Collections.emptyList();
                }
                nextNumList.add(numList.get(firstIndex) / numList.get(secondIndex));
                break;
            case 5: // Division (second / first), check for division by zero.
                if (numList.get(firstIndex) == 0) {
                    return Collections.emptyList();
                }
                nextNumList.add(numList.get(secondIndex) / numList.get(firstIndex));
                break;
        }

        // Return the new list of numbers to continue the search.
        return nextNumList;
    }
}