package toOffer.day1;
//用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
// 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
//    示例 1：
//
//    输入：
//    ["CQueue","appendTail","deleteHead","deleteHead"]
//    [[],[3],[],[]]
//    输出：[null,null,3,-1]
//    示例 2：
//
//    输入：
//    ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
//    [[],[],[5],[2],[],[]]
//    输出：[null,-1,null,null,5,2]

import java.util.Stack;

public class CQueue {
    private Stack<Integer> s1;
    private Stack<Integer> s2;
    public CQueue() {
        s1 = new Stack<Integer>();
        s2 = new Stack<Integer>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if(s1 == null || s1.empty()) return -1;
//        return s1.pop();
        while(!s1.empty()){
            s2.push(s1.pop());
        }
        int result = s2.pop();
        while(!s2.empty()){
            s1.push(s2.pop());
        }
        return result;
    }
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(3);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());

    }
}

