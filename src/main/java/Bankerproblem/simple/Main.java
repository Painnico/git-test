package Bankerproblem.simple;

public class Main {

    public static void main(String[] args) {
        int[] available = new int[]{3,3,2};//可用资源
        int[][] max = new int[][]{{7,5,3},{3,2,2},{9,0,2},{2,2,2},{4,3,3}};//进程最大需求量
        int[][] allocation = new int[][]{{0,1,0},{2,0,0},{3,0,2},{2,1,1},{0,0,2}};;//进程已占有资源数
        int processNum = 5;
        int sourceNum = 3;
//        int[] available = new int[]{3,3,2,1};//可用资源
//        int[][] max = new int[][]{{7,5,3,0},{3,2,2,1},{9,0,2,0},{2,2,2,2},{4,3,3,1}};//进程最大需求量
//        int[][] allocation = new int[][]{{0,1,0,0},{2,0,0,1},{3,0,2,0},{2,1,1,2},{0,0,2,0}};;//进程已占有资源数
//        int processNum = 5;
//        int sourceNum = 4;
        Banker banker = new Banker(max,allocation,processNum,sourceNum);
//        banker.printTable();
        banker.notDeadLock(available);

    }
}
