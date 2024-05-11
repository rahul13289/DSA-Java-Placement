/** let's break down the code line by line:

1. `public double mincostToHireWorkers(int[] quality, int[] wage, int k) {`
   - This line defines a method named `mincostToHireWorkers` that takes three parameters: `quality` (an array of integers representing the quality of workers), `wage` (an array of integers representing the wage of workers), and `k` (an integer representing the number of workers to hire).
   - The method returns a `double`, which represents the minimum cost to hire `k` workers.

2. `PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());`
   - This line creates a priority queue named `maxHeap`, which stores integers.
   - The `Collections.reverseOrder()` ensures that elements are stored in descending order.

3. `List<Pair<Double, Integer>> ratio = new ArrayList<>();`
   - This line creates a list named `ratio`, which stores pairs of doubles and integers.
   - Each pair represents the ratio of wage to quality for a worker.

4. `int n = quality.length, qualitySum = 0;`
   - This line declares two integers: `n` is assigned the length of the `quality` array, and `qualitySum` is initialized to 0.

5. `double res = Double.MAX_VALUE, maxRate = 0.0;`
   - This line declares two doubles: `res` is initialized to the maximum possible value for a `double`, and `maxRate` is initialized to 0.0.
   - These variables will be used to store the result and the maximum ratio of wage to quality, respectively.

6. `for (int i = 0; i < n; ++i) {`
   - This line starts a loop that iterates over each worker.

7. `ratio.add(new Pair<>((double) wage[i] / quality[i], i));`
   - Inside the loop, this line calculates the ratio of wage to quality for the current worker (`wage[i] / quality[i]`) and adds it to the `ratio` list along with the index of the worker.

8. `ratio.sort(Comparator.comparingDouble(p -> p.getKey()));`
   - After calculating the ratios for all workers, this line sorts the `ratio` list based on the ratio value in ascending order.

9. `for (int i = 0; i < k; ++i) {`
   - This line starts another loop that iterates `k` times, representing the number of workers to hire.

10. `qualitySum += quality[ratio.get(i).getValue()];`
    - Inside the loop, this line adds the quality of the worker at index `i` to `qualitySum`.

11. `maxRate = Math.max(maxRate, ratio.get(i).getKey());`
    - This line updates `maxRate` to the maximum of its current value and the ratio of the worker at index `i`.

12. `maxHeap.offer(quality[ratio.get(i).getValue()]);`
    - This line adds the quality of the worker at index `i` to the `maxHeap` priority queue.

13. `res = maxRate * qualitySum;`
    - This line calculates the cost of hiring `k` workers by multiplying the maximum ratio `maxRate` by the sum of their qualities `qualitySum`.

14. `for (int i = k; i < n; ++i) {`
    - This line starts another loop that iterates from `k` to `n-1`, representing the remaining workers.

15. `maxRate = Math.max(maxRate, ratio.get(i).getKey());`
    - Inside the loop, this line updates `maxRate` to the maximum of its current value and the ratio of the worker at index `i`.

16. `qualitySum -= maxHeap.poll();`
    - This line removes the highest quality worker from the `maxHeap` and subtracts its quality from `qualitySum`.

17. `qualitySum += quality[ratio.get(i).getValue()];`
    - This line adds the quality of the current worker to `qualitySum`.

18. `maxHeap.offer(quality[ratio.get(i).getValue()]);`
    - This line adds the quality of the current worker to the `maxHeap` priority queue.

19. `res = Math.min(res, maxRate * qualitySum);`
    - This line updates `res` to the minimum of its current value and the cost of hiring the current set of workers.

20. `return res;`
    - Finally, the method returns the minimum cost `res` to hire `k` workers. 
    
**/



class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        List<Pair<Double, Integer>> ratio = new ArrayList<>();
        int n = quality.length, qualitySum = 0;
        double res = Double.MAX_VALUE, maxRate = 0.0;

        for (int i = 0; i < n; ++i) {
            ratio.add(new Pair<>((double) wage[i] / quality[i], i));
        }

        ratio.sort(Comparator.comparingDouble(p -> p.getKey()));
        for (int i = 0; i < k; ++i) {
            qualitySum += quality[ratio.get(i).getValue()];
            maxRate = Math.max(maxRate, ratio.get(i).getKey());
            maxHeap.offer(quality[ratio.get(i).getValue()]);
        }

        res = maxRate * qualitySum;
        for (int i = k; i <n; ++i) {
            maxRate = Math.max(maxRate, ratio.get(i).getKey());
            qualitySum -= maxHeap.poll();
            qualitySum += quality[ratio.get(i).getValue()];
            maxHeap.offer(quality[ratio.get(i).getValue()]);
            res = Math.min(res, maxRate * qualitySum);
        }

        return res;
    }
}
