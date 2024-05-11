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

/** Approach- Priority Queue (Max Heap) + Sorting
Alright, listen up, you merc-loving maniacs! Let's break down this code in the most Deadpool-esque way possible :

1. Searching for Offers

First and foremost, we need to arrange these employees in order of their wage-quality ratio. Why? Because we want to employ those who are cheapest but meet the desired quality. It’s almost like getting the least expensive hitmen that are still very good at their job.

2. Create a Priority Queue for Squad Upgrades

Following this, we establish a priority queue for keeping track of our highest quality workers up to that point. Imagine an elite group of soldiers of fortune for whom it is always important to find better warriors.

3. Constructing the Starting Lineup

To start off with, we will offer jobs to first k persons in term of wage-quality ratio. That gives us initial cost and initial sum of qualities. It looks like assembling our team from scratch just before going into battle.

4. Promotion by Demotion

And here comes the fun part! Go through remaining workers and see if there is any chance of replacing one lowest quality worker among those already hired with another one that is slightly better than him or her. This can be thought of as trading a rookie player for a veteran star athlete.

5. Swapping That Kills

So, we do away with the worst worker in our group and replace them with another worker who gives us a better value for money spent. The point here is that one can substitute a cheap mercenary with an expensive but very deadly one.

6. Money control

We should calculate the minimum cost after each replacement using the updated quality sum and the highest wage-to-quality ratio of all employees hired so far. It’s just like drawing up a budget for our new and improved league of killers.

7. Picking Up the Check

Eventually, at this stage, we return back to the smallest cost found before now. Paying our last bill for this ultimate team of hired guns will be it, and getting bang-for-your-buck!

There you go! A simple yet effective way of hiring superior workers while maintaining low costs.

(Suppose Deadpool was making gesture implying “mic drop” as he says something like “That’s how you hire the best team for the lowest price, folks!”)

Dry - Run :
Input: quality = [10, 20, 5], wage = [70, 50, 30], k = 2

Arrange the staff in order of wage-to-quality ratio: =
[(2.5, 1), (6.0, 2), (7.0, 0)]

Initialize maxHeap (priority queue), qualitySum=0, maxRate=0.0.

Hire the first k = 2 workers:
i = 0: qualitySum = 20, maxRate = 2.5, maxHeap = [20]
i = 1: qualitySum = 25, maxRate = 6.0, maxHeap = [20, 5]
res = maxRate * qualitySum = 6.0 * 25 = 150.0000

Iterate through remaining workers (i = 2 to n-1):
i = 2:
maxRate = max(6.0, 7.0) = 7.0
qualitySum = 25 - 20 + 10 = 15 (removed top of maxHeap and added quality[0])
maxHeap = [10, 5]
res = min(res, maxRate * qualitySum) = min(150.0, 7.0 * 15) = 105.0

No more workers left, so return res = 105.0

The key steps:

First hire k lowest wage/quality workers
Then see if remaining higher wage/quality workers can replace existing highest wage/quality worker while lowering cost
So the output 105.00000 is correct:

Hire worker 1 (quality 20) for wage 50
Hire worker 2 (quality 5) for wage 30
Complexity
Time complexity: O(nlogn)

Space complexity: O(n)

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
