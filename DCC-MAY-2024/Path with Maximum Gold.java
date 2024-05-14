/*

Intuition
we need to find the max possible gold we can take without going into '0' cells or revisiting already visited cells
its like a snake game you cannot bite any part of your body
body being the cells we travelled to reach here eating their gold along the way

Approach
we need a snake(traveller) that will travell for us
we check boundary conditions so that it doesnt go out of bounds or into the zero cells
from there it is simple we can just start new travellers in all 4 directions and they will find the maximum
when ever a traveller finds a dead end either out of bounds or a zero cell we see if the gold we have is maximum or not and we update it accordingly

Complexity
Time complexity:O(N^m)
Space complexity:O(1)

*/

public class Solution {
    public int getMaximumGold(int[][] grid) {
        int len = grid.length;
        int br = grid[0].length;
        int[] max = new int[1];  // Using an array to simulate pass-by-reference for max value

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < br; j++) {
                traveller(grid, i, j, 0, max);
            }
        }
        return max[0];
    }

    private void traveller(int[][] grid, int i, int j, int money, int[] max) {
        int len = grid.length;
        int br = grid[0].length;

        if (i >= len || i < 0 || j >= br || j < 0 || grid[i][j] == 0) {
            if (money > max[0]) max[0] = money;
            return;
        }

        int temp = grid[i][j];
        grid[i][j] = 0;

        traveller(grid, i + 1, j, money + temp, max);
        traveller(grid, i - 1, j, money + temp, max);
        traveller(grid, i, j + 1, money + temp, max);
        traveller(grid, i, j - 1, money + temp, max);

        grid[i][j] = temp;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = { {0, 6, 0}, {5, 8, 7}, {0, 9, 0} };
        System.out.println(sol.getMaximumGold(grid));  // Example usage
    }
}
