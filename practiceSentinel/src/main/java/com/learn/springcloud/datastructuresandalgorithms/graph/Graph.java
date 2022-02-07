package com.learn.springcloud.datastructuresandalgorithms.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 图
 * 深度优先 和 广度优先
 */
public class Graph {
    private int v;
    private LinkedList<Integer> adj[];


    boolean found = false;
    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v;i++ ){
            adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int s, int t) {
        // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先
     *
     * int prev[]记录路径
     * boolean visited[] 记录是否访问
     * Queue<Integer> 需要访问的节点
     */

    public void bfv(int s,int e){
        int prev [] = new int[v];
        Queue<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[v];
        visited[s] =true;
        queue.add(s);
        while (queue.size()>0){
            Integer i = queue.poll();
            for (Integer  post :adj[i]){
                if(!visited[post]){
                    visited[post] = true;
                    prev[post] = i;
                    if(post == e){
                        print(prev,s,e);
                    }

                    queue.add(post);
                }


            }



        }

    }

    /**
     * 深度优先
     * boolean[] visited
     * int [] prev 路径
     * Queue 需要访问的节点
     * boolean found终结条件
     */
    public void dfs(int s, int e){
        boolean[] visited = new boolean[v];
        int [] prev = new int[v];
        for (int i = 0;i<v;i++){
            prev[i] = -1;
        }
        boolean found = false;
        reduce(s,e,visited,prev);
        print(prev,s,e);


    }

    public void reduce(int s,int e,   boolean[] visited,int [] prev){

            visited[s] = true;
            if (s==e) {
                found =true;
            }
            if(found == true) return;

            for(int i : adj[s] ){
                if (!visited[i]){
                    prev[i] = s;
                    reduce(i,e,visited,prev);
                }
            }

    }
    private void print(int[] prev, int s, int e) {

        int curr = prev[e];
        if(curr == s){
            System.out.println(curr);
            System.out.println(e);
        }else{
            print(prev,s,curr);
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge(0,1);
        graph.addEdge(1,2);
        graph.addEdge(2,5);
        graph.addEdge(5,7);
//        graph.addEdge(6,7);
        graph.addEdge(4,6);
        graph.addEdge(4,5);
        graph.addEdge(3,4);
        graph.addEdge(0,3);
        graph.addEdge(1,4);
//        graph.bfv(0,7);
        System.out.println(

                "-------------"
        );
        graph.dfs(0,6);
    }
}
