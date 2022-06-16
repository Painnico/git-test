package toOffer.day1;

//https://leetcode.cn/problems/shortest-path-in-a-grid-with-obstacles-elimination/

//给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。
// 每一步，您都可以在空白单元格中上、下、左、右移动。
//如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，
// 并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1 。
//
public class ShortestPath {
    public int shortestPath(int[][] grid, int k) {
        int result = 0;
        return result;
    }

    public static void main(String[] args) {
        ShortestPath shortestPath = new ShortestPath();
        int[][] grid = new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        int k = 1;

        System.out.println(shortestPath.shortestPath(grid,k));
    }
}
