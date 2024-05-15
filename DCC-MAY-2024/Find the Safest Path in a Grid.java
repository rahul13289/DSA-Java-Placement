/* 
GOOGLE INTERVIEW PROBLEM

Problem no: 2812

Intuition
Breadth-First Search (BFS) Initialization:

Thieves are the starting points, and the goal is to calculate the minimum distance from each cell to the nearest thief.
By using BFS, we can propagate the distances from the thieves to all other cells efficiently.

Safeness Calculation:

The safeness factor for a path from the top-left corner to the bottom-right corner of the grid is the minimum distance to a thief encountered along the path.
By using a priority queue, we can explore paths in descending order of safeness, ensuring we find the path with the highest possible safeness factor first.

Approach
Setup and Initialization:

Define the possible directions for movement (right, left, down, up).
Initialize the dimensions of the grid (nRows and nCols).
In-Bounds Check:

Create a helper function inBound to check if a cell is within the grid boundaries.

Breadth-First Search (BFS):

Initialize a deque thievesGrids to store the coordinates of thief cells.
Mark thief cells with a distance of 0 (initial safeness value) and other cells with -1 (unvisited).
Use BFS to update the safeness values (distances to the nearest thief) for all cells in the grid.

Calculate Safeness:

Initialize a priority queue to explore paths based on safeness.
Start from the top-left corner with the safeness value of that cell.
Use a while loop to process the cells in the priority queue:

If the current cell is the bottom-right corner, return the safeness value (negative because of max-heap emulation).
For each neighboring cell, if it's within bounds and unvisited, push it onto the priority queue with the updated safeness value (minimum safeness value encountered so far).
If no valid path is found, return -1.
Complexity
Time complexity:
O(N^2*log(N))

Space complexity:
O(N^2)


https://www.youtube.com/watch?v=vsM-uGbLDyU

*/

class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int nRows = grid.size();
        int nCols = grid.get(0).size();

        Queue<int[]> thievesGrids = new LinkedList<>();
        for (int row = 0; row < nRows; row++) {
            for (int col = 0; col < nCols; col++) {
                if (grid.get(row).get(col) == 1) {
                    thievesGrids.offer(new int[]{row, col});
                    grid.get(row).set(col, 0);  // Mark thief cell with 0
                } else {
                    grid.get(row).set(col, -1); // Mark empty cell with -1
                }
            }
        }

        bfs(grid, thievesGrids, nRows, nCols);
        return calculateSafeness(grid, nRows, nCols);
    }

    private void bfs(List<List<Integer>> grid, Queue<int[]> thievesGrids, int nRows, int nCols) {
        while (!thievesGrids.isEmpty()) {
            int size = thievesGrids.size();
            for (int i = 0; i < size; i++) {
                int[] current = thievesGrids.poll();
                int row = current[0], col = current[1];
                int value = grid.get(row).get(col);

                for (int[] direction : DIRECTIONS) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    if (inBound(newRow, newCol, nRows, nCols) && grid.get(newRow).get(newCol) == -1) {
                        grid.get(newRow).set(newCol, value + 1);
                        thievesGrids.offer(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }

    private int calculateSafeness(List<List<Integer>> grid, int nRows, int nCols) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        priorityQueue.offer(new int[]{-grid.get(0).get(0), 0, 0});
        grid.get(0).set(0, -1); // Mark as visited

        while (!priorityQueue.isEmpty()) {
            int[] current = priorityQueue.poll();
            int safeness = -current[0];
            int row = current[1];
            int col = current[2];

            if (row == nRows - 1 && col == nCols - 1) {
                return safeness;
            }

            for (int[] direction : DIRECTIONS) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (inBound(newRow, newCol, nRows, nCols) && grid.get(newRow).get(newCol) != -1) {
                    priorityQueue.offer(new int[]{-Math.min(safeness, grid.get(newRow).get(newCol)), newRow, newCol});
                    grid.get(newRow).set(newCol, -1); // Mark as visited
                }
            }
        }

        return -1;
    }

    private boolean inBound(int row, int col, int nRows, int nCols) {
        return 0 <= row && row < nRows && 0 <= col && col < nCols;
    }
}
