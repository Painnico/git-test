package toOffer.day2;

//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetZeroes {
    public static void main(String[] args) {
        SetZeroes setZeroes = new SetZeroes();
        int[][] matrix = new int[][]{{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        setZeroes.setZeroes(matrix);
        for(int i = 0;i < matrix.length;i++){
            for(int j = 0;j < matrix[0].length;j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }
    public void setZeroes(int[][] matrix) {
        List<Integer> col = new ArrayList<>();
        List<Integer> row = new ArrayList<>();
        for (int i = 0;i < matrix.length;i++){
            for (int j = 0;j < matrix[0].length;j++){
                if(matrix[i][j] == 0){
                    row.add(i);
                    col.add(j);
                }
            }

        }
        for(int i = 0;i < matrix.length;i++){
            for (int j = 0;j < col.size();j++){
                matrix[i][col.get(j)] = 0;
            }
        }
        for(int i = 0;i < matrix[0].length;i++){
            for (int j = 0;j < row.size();j++){
                matrix[row.get(j)][i] = 0;
            }
        }

    }
}
