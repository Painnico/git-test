package toOffer.day1;

//https://leetcode.cn/problems/flatten-a-multilevel-doubly-linked-list/

import java.util.Stack;

class Node {
    public Node(int val){val=val;}
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
class Solution {
//    public Node flatten(Node head) {
//        Stack<Node> stack = new Stack<>();
//        if(head == null) return null;
//        stack.push(head);
//        Node currentNode;
//        while (!stack.empty()){
//            currentNode = stack.pop();
//            if(currentNode.next != null){
//                stack.push(currentNode.next);
//            }
//            if(currentNode.child != null){
//                stack.push(currentNode.child);
//                currentNode.child = null;
//            }
//            if(!stack.empty()){
//                Node topNode = stack.peek();
//                currentNode.next = topNode;
//                topNode.prev = currentNode;
//            }
//        }
//        return head;
//    }
    Node prevHead = new Node(0);    // 哨兵节点
    Node curr = prevHead;   // 尾部指针
    public Node flatten(Node head) {
        if(head == null) return null;
        dfs(head);
        prevHead.next.prev = null;   // 断开哨兵节点
        return prevHead.next;
    }
    public void dfs(Node node) {
        if(node == null) {
            return;
        }
        Node a = new Node(node.val);
        curr.next = a;
        a.prev = curr;
        curr = curr.next;
        if(node.child != null) {    // 先子节点
            dfs(node.child);
        }
        if(node.next != null) {     // 再next节点
            dfs(node.next);
        }
    }
}
