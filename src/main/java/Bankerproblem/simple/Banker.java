package Bankerproblem.simple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Banker {
    int[] available ;//可用资源
    int[][] work;//可用资源（判断是否为死锁时使用）
    int[][] max;//进程最大需求量
    int[][] allocation;//进程已占有资源数
    int[][] need;//进程还需资源数
    List<Integer> sequence;//进程执行顺序
    int processNum;//进程数
    int sourceNum;//资源数
    Set<Integer> finshed = new HashSet<Integer>();//存储已经分配了的进程号

    //初始化资源
    public Banker(int[][] max,int[][] allocation,int processNum,int sourceNum){
        this.max = max;
        this.allocation = allocation;
        this.processNum = processNum;
        this.sourceNum = sourceNum;
        this.need = new int[processNum][sourceNum];
        this.work = new int[processNum][sourceNum];
        this.sequence = new ArrayList<Integer>();
        for(int i = 0;i < processNum;i++){
            for(int j = 0;j < sourceNum;j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    void notDeadLock(int[] available){
        this.available = available;
        this.work[0] = available.clone();
        int workNum = 0;//为方便后序输出work序列
        boolean canMeetNeed;//所有资源种类都够分配才能分配
        for(int i = 0;i < processNum;i++) {
            if(!finshed.contains(i)){
                canMeetNeed = true;
                for (int j = 0; j < sourceNum; j++) {
                    if(work[workNum][j] < need[i][j] || canMeetNeed == false){//提供的资源够分配
                        canMeetNeed = false;
                    }
                }
                if(canMeetNeed) {
                    workNum++;
                    if(workNum < processNum){
                        for (int j = 0; j < sourceNum; j++) {
                            work[workNum][j] = work[workNum-1][j] + allocation[i][j];//将该进程占有的资源添加到空闲资源
                        }
                    }
                    finshed.add(i);
                    sequence.add(i);
                    if(workNum == processNum) break;
                    //结束一个进程时会收回其资源，跳到第一个进程重新检查是否存在能够分配的进程
                    i = -1;
                }
            }
        }
        if(sequence.size() == processNum) printSequence();
        else {
            if(sequence != null){
                System.out.print("资源不够分配，目前顺序：");
                for(int i = 0;i < sequence.size();i++) System.out.print(sequence.get(i)+" , ");
            }
            else System.out.println("资源不够分配");
        }
    }
    void printSequence(){
        printSecurityTable();
        System.out.print("资源足够分配，其安全序列为：");
        for(int i = 0;i < sequence.size();i++) System.out.print(sequence.get(i)+"  ");
    }
    void printSecurityTable() {
        System.out.println("进程\t\tWork\tAllocation\t\tNeed\tWork+Allocation");
        for(int i = 0;i < processNum;i++) {
            System.out.print("p"+sequence.get(i)+"\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(work[i][j]+" ");
            }
            System.out.print("\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(allocation[sequence.get(i)][j]+" ");
            }
            System.out.print("\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(need[sequence.get(i)][j]+" ");
            }
            System.out.print("\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(work[i][j]+allocation[sequence.get(i)][j]+" ");
            }
            System.out.print("\n");
        }
    }
    void printTable(){
        System.out.println("进程\t\tMax\t\t Allocation\t\tNeed\t");
        for(int i = 0;i < processNum;i++) {
            System.out.print("p"+i+"\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(max[i][j]+" ");
            }
            System.out.print("\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(allocation[i][j]+" ");
            }
            System.out.print("\t|\t");
            for (int j = 0; j < sourceNum; j++) {
                System.out.print(need[i][j]+" ");
            }
            System.out.print("\n");
        }
    }
}
